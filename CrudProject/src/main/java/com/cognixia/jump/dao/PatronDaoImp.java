package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
//import java.sql.Date;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Patron;
import com.cognixia.jump.model.BookDate;



public class PatronDaoImp implements PatronDao{

	public static final Connection conn = ConnectionManager.getConnection();
	
	
	private static String GET_ALL_PATRONS = "SELECT * FROM patron";
	private static String GET_PATRON_ID = "SELECT patron_id FROM patron WHERE username = ?";
	private static String SELECT_ALL_PATRON_BOOKS = "SELECT  book.isbn, book.title, book.descr, book.rented, book.added_to_library, book_checkout.checkedout, book_checkout.due_date, book_checkout.returned from book "
													+ "INNER JOIN book_checkout ON book.isbn=book_checkout.isbn WHERE book_checkout.patron_id = ?";
	private static String ADD_PATRON = "INSERT INTO patron (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
	private static String GET_PATRON = "SELECT * FROM patron WHERE username = ? AND password = ?";
	private static String GET_PATRON_BY_ID = "SELECT * FROM patron WHERE patron_id = ?";
	private static String CHECKOUT = "INSERT INTO book_checkout (patron_id, isbn, checkedout, due_date) VALUES (?, ?, ?, ?)";
	private static String RENT = "UPDATE book SET rented = 1 WHERE isbn = ?";
	private static String RETURN = "UPDATE book_checkout SET returned = ? WHERE patron_id = ? AND isbn = ?";
	private static String UNRENT = "UPDATE book SET rented = 0 WHERE isbn = ?";
	private static String DUE_DATE = "SELECT due_date FROM book_checkout WHERE patron_id = ? AND isbn = ?";
	private static String UPDATE = "UPDATE patron SET first_name = ?, last_name = ?, username = ?, password = ? WHERE patron_id = ?";
	
	@Override
	public List<Patron> getAllPatrons(){
		
		List<Patron> allPatrons = new ArrayList<Patron>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(GET_ALL_PATRONS);
				ResultSet rs = pstmt.executeQuery() ) {
			
			while(rs.next()) {
				int patron_id = rs.getInt("patron_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean account_frozen = rs.getBoolean("account_frozen");
				
				allPatrons.add(new Patron(patron_id, first_name, last_name, username, password, account_frozen));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allPatrons;
		
	}
	
	@Override
	public int getPatronId(String username) {
		
		int id = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(GET_PATRON_ID) ) {
			
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("patron_id");
				return id;
			}
	
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return id;
		
	}
	
	
	@Override
	public List<BookDate> getPatronBooks(int id) {
		
		List<BookDate> allPatronBooks = new ArrayList<BookDate>();
		
		try( PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_PATRON_BOOKS)){
			
			pstmt.setInt(1, id);
			
			
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String description = rs.getString("descr");
				boolean rented = rs.getBoolean("rented");
				Date added_to_library = rs.getDate("added_to_library");
				String checkedout = rs.getDate("checkedout").toString();
				String due_date = rs.getDate("due_date").toString();
				String returnDateString = "";
				java.sql.Date returned = rs.getDate("returned");
				if(returned != null) {
					returnDateString = returned.toString();
				}
				Book book = new Book(isbn, title, description, rented, added_to_library);
				allPatronBooks.add(new BookDate(book, checkedout, due_date, returnDateString));
			}
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allPatronBooks;
		
	}
	
	@Override
	public boolean addPatron(Patron patron) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(ADD_PATRON)) {
			
			pstmt.setString(1, patron.getFirst_name());
			pstmt.setString(2, patron.getLast_name());
			pstmt.setString(3, patron.getUsername());
			pstmt.setString(4, patron.getPassword());
			
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
			
		}catch(SQLException e) {
			
		}
		
		return false;
	}
	
	@Override
	public Patron getPatron(String userName, String passWord) {
		
		Patron patron = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(GET_PATRON)) {
			
			pstmt.setString(1, userName);
			pstmt.setString(2, passWord);
			
			ResultSet rs = pstmt.executeQuery();
			if(!rs.isBeforeFirst()) {
				return patron;
			}
			if(rs.next()) {
				int patron_id = rs.getInt("patron_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean account_frozen = rs.getBoolean("account_frozen");
				
				patron = new Patron(patron_id, first_name, last_name, username, password, account_frozen);
				return patron;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patron;
		
	}
	
	@Override
	public Patron getPatronById(int id) {
		
		Patron patron = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(GET_PATRON)) {
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if(!rs.isBeforeFirst()) {
				return patron;
			}
			if(rs.next()) {
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean account_frozen = rs.getBoolean("account_frozen");
				
				patron = new Patron(id, first_name, last_name, username, password, account_frozen);
				return patron;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patron;
		
	}
	
	@Override
	public boolean checkoutBook(int id, String isbn) {
		
		long WEEK = 7 * 24 * 60 * 60 * 1000;
		
		try(PreparedStatement pstmt = conn.prepareStatement(CHECKOUT);
				PreparedStatement rentPstmt = conn.prepareStatement(RENT)){
			
			
			pstmt.setInt(1, id);
			pstmt.setString(2, isbn);
			pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis() + WEEK));
			
			rentPstmt.setString(1, isbn);
			rentPstmt.executeUpdate();
			
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	@Override
	public boolean returnBook(int id, String isbn) {
		
		 
		
		try(PreparedStatement pstmt = conn.prepareStatement(RETURN);
				PreparedStatement duePstmt = conn.prepareStatement(DUE_DATE);
				PreparedStatement unrentPstmt = conn.prepareStatement(UNRENT)) {
			
			// Calculates the return date
			java.sql.Date returnDate = new java.sql.Date(System.currentTimeMillis());
			
			pstmt.setDate(1, returnDate);
			pstmt.setInt(2, id);
			pstmt.setString(3, isbn);
			
			duePstmt.setInt(1, id);
			duePstmt.setString(2, isbn);
			
			unrentPstmt.setString(1, isbn);
			unrentPstmt.executeUpdate();
			
			// retrieves the due date
			ResultSet rs = duePstmt.executeQuery();
			rs.next();
			java.sql.Date dueDate = rs.getDate("due_date");
			
			// returns true if pstmt executed and returnDate was before dueDate
			if(pstmt.executeUpdate() > 0 && ( dueDate.compareTo(returnDate) < 0)) {
				return true;
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	@Override
	public boolean updatePatron(Patron patron) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {
			
			pstmt.setString(1, patron.getFirst_name());
			pstmt.setString(2, patron.getLast_name());
			pstmt.setString(3, patron.getUsername());
			pstmt.setString(4, patron.getPassword());
			
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
}
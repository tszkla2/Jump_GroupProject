package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Librarian;

public class LibrarianDaoImp implements LibrarianDao, Book {
	
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static String SELECT_ALL_LIBRARIANS = "select * from product";
	private static String INSERT_BOOK = "insert into product(item, qty, description) values(?, ?, ?)";
	private static String DELETE_BOOK = "delete from product where id = ?";
	private static String UPDATE_BOOK = "update product set item = ?, qty = ?, description = ? where id = ?";

	@Override
	public List<Librarian> getAllLibrarians() {
		
		List<Librarian> allLibrarians = new ArrayList<Librarian>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_LIBRARIANS);
				ResultSet rs = pstmt.executeQuery() ) {
			
			while(rs.next()) {
				
				int librarian_id = rs.getInt("librarian_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				allLibrarians.add(new Librarian(librarian_id, username, password));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allLibrarians;
	}


	@Override
	public boolean addBook(Book book) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_BOOK)) {
			
			pstmt.setString(1, book.getIsbn());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getDescription());
			pstmt.setBoolean(4, book.getRented());
			pstmt.setDate(5, book.getAddDate());
			
			// at least one row added
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteBook(int isbn) {

		try (PreparedStatement pstmt = conn.prepareStatement(DELETE_BOOK)) {

			pstmt.setInt(1, isbn);

			// at least one row deleted
			if (pstmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		
		try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_BOOK)) {

			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getDescription());

			// at least one row updated
			if (pstmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}

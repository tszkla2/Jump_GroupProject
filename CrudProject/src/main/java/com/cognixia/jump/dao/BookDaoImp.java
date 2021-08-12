package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Book;

public class BookDaoImp implements BookDao {
    
    public static final Connection conn = ConnectionManager.getConnection();

    private static String SELECT_ALL_BOOKS = "select * from book";
    private static String SELECT_BOOK_BY_ISBN = "select * from book where isbn = ?";
    private static String INSERT_BOOK = "insert into book(isbn,title, descr, rented, added_to_library ) values(?, ?, ?, ?, ?)";
    private static String DELETE_BOOK = "delete from book where isbn = ?";
    private static String UPDATE_BOOK = "update book set title = ?, rented = ?, descr = ? where isbn = ?";
    private static String UPDATE_BOOK_AVAILABILITY = "update book set rented = ? where isbn = ?";

    @Override
    public List<Book> getAllBooks() {

        List<Book> allBooks = new ArrayList<Book>();

        try (PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_BOOKS); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                boolean rented = rs.getBoolean("rented");
                Date added_to_library = rs.getDate("added_to_library");
                String description = rs.getString("descr");

                allBooks.add(new Book(isbn, title, description, rented, added_to_library));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allBooks;
    }
    @Override
    public Book getBookByIsbn(String isbn) {

        Book book = null;

        try (PreparedStatement pstmt = conn.prepareStatement(SELECT_BOOK_BY_ISBN)) {
            System.out.println("in get by isbn");
            pstmt.setString(1, isbn);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                boolean rented = rs.getBoolean("rented");
                Date added_to_library = rs.getDate("added_to_library");
                String description = rs.getString("descr");

                book = new Book(isbn, title, description, rented, added_to_library);
            } else {
                System.out.println("rs next failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("in get by isbn end " + book.toString());
        return book;
    }
    
    @Override
    public boolean addBook(Book book) {

        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_BOOK)) {

            pstmt.setString(1, book.getIsbn());
            pstmt.setString(2, book.getTitle());
            pstmt.setBoolean(4, book.isRented());
            pstmt.setString(3, book.getDescription());
            java.sql.Date sqlPackageDate = new java.sql.Date(book.getAdded_to_library().getTime());
            System.out.println(sqlPackageDate);
            pstmt.setDate(5, sqlPackageDate);
            System.out.println(book);


            // at least one row added
            if (pstmt.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    @Override
    public boolean deleteBook(String isbn) {

        try (PreparedStatement pstmt = conn.prepareStatement(DELETE_BOOK)) {

            pstmt.setString(1, isbn);

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

            pstmt.setString(1, book.getTitle());
            pstmt.setBoolean(2, book.isRented());
            pstmt.setString(3, book.getDescription());
            pstmt.setString(4, book.getIsbn());

            System.out.println("book");

            // at least one row updated
            if (pstmt.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    @Override
	public boolean updateBookAvailability(Book book) {
		
        try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_BOOK_AVAILABILITY)) {

            pstmt.setBoolean(1, book.isRented());
			pstmt.setString(2, book.getIsbn());

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

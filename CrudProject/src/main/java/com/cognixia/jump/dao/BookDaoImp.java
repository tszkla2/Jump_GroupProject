package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookDao;
import com.cognixia.jump.model.Book;

public class BookDaoImp implements BookDao {
    
    public static final Connection conn = ConnectionManager.getConnection();

    private static String SELECT_ALL_BOOKS = "select * from book";
    private static String SELECT_BOOK_BY_ISBN = "select * from book where isbn = ?";
    private static String INSERT_BOOK = "insert into book(title, rented, add_to_library, description ) values(?, ?, ?, ?)";
    private static String DELETE_BOOK = "delete from book where isbn = ?";
    private static String UPDATE_BOOK = "update book set title = ?, rented = ?, description = ? where isbn = ?";
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
                String description = rs.getString("description");

                allBooks.add(new Book(isbn, title, description, rented, added_to_library));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allBooks;
    }
    
    public Book getBookByIsbn(String isbn) {

        Book book = null;

        try (PreparedStatement pstmt = conn.prepareStatement(SELECT_BOOK_BY_ISBN)) {

            pstmt.setInt(1, isbn);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                boolean rented = rs.getBoolean("rented");
                Date added_to_library = rs.getDate("added_to_library");
                String description = rs.getString("description");

                book = new Book(isbn, title, description, rented, added_to_library);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }
    
    @Override
    public boolean addBook(Book book) {

        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_BOOK)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setInt(2, book.isRented());
            pstmt.setDate(3, book.getAdded_to_library());
            pstmt.setString(4, book.getDescription());

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

            pstmt.setString(1, book.getTitle());
            pstmt.setInt(2, book.isRented());
            pstmt.setString(3, book.getDescription());
            pstmt.setInt(4, book.getId());

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
			pstmt.setInt(2, book.getId());

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

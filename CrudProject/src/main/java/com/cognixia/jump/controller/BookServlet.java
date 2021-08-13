package com.cognixia.jump.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.BookDate;
import com.cognixia.jump.utility.Utility;
import com.cognixia.jump.model.Patron;
import com.cognixia.jump.dao.PatronDaoImp;
import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.dao.LibrarianDao;
import com.cognixia.jump.dao.LibrarianDaoImp;


@WebServlet("/")
public class BookServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;

    private BookDaoImp bookDao;
    private PatronDaoImp patronDao;
    private Patron loggedInPatron;
	private Librarian loggedInLibrarian;
	private LibrarianDao librarianDao;
	
    public void init() {
        bookDao = new BookDaoImp();
        patronDao = new PatronDaoImp();
		librarianDao = new LibrarianDaoImp();
    }

    public void destroy() {

        try {
            ConnectionManager.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            // case "/list":
            //     listBooks(request, response);
            //     break;
            case "/delete":
                deleteBook(request, response);
                break;
            case "/edit":
                goToEditBookForm(request, response);
                break;
            case "/update":
                updateBook(request, response);
                break;
            case "/new":
                goToNewBookForm(request, response);
                break;
            case "/add":
                addNewBook(request, response);
                break;
            
            case "/login":
                goToLoginPage(request, response);
                break;

            case "/about":
                goToAboutPage(request, response);
                break;

    		case "/patron":
    			goToPatronView(request, response);
    			break;	

    		case "/librarian":
    			goToLibrarianView(request, response);	
                break;

            case "/trylogin":
                handleLogin(request, response);
                break;

            case "/listLibrarian":
                listBooks(request, response,"librarian");
                break;
            
            case "/listPatron":
                listBooks(request, response, "patron");
                break;
            case "/rent":
                rentBook(request, response);
                break;
            case "/returnbook":
            	listRentedBooks(request, response);
            	break;
            case "/return":
            	returnBook(request, response);
            	break;
            case "/logout":
            	response.sendRedirect("/CrudProject");
            	break;
            default:

                response.sendRedirect("/");
                break;
        }

    }
    
    private void listBooks(HttpServletRequest request, HttpServletResponse response, String user) 
            throws ServletException, IOException {

        List<Book> allBooks = bookDao.getAllBooks();

        request.setAttribute("allBooks", allBooks);
        request.setAttribute("user", user);
        request.setAttribute("patron", loggedInPatron);
        request.setAttribute("librarian", loggedInLibrarian);
        RequestDispatcher dispatcher = null;
        if(user == "librarian") {dispatcher = request.getRequestDispatcher("book-list-librarian.jsp");}
        else { dispatcher = request.getRequestDispatcher("book-list-patron.jsp"); }

        dispatcher.forward(request, response);
    }
    
    private void deleteBook(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // get the id for the product to delete
        String isbn = request.getParameter("isbn");

        // use our dao to do our delete
        bookDao.deleteBook(isbn);

        // redirect back to our product list page (should show table without product we just
        // deleted)
        response.sendRedirect("listLibrarian");
    }
    
    private void goToEditBookForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // get id of product we need to edit/update
        String isbn = request.getParameter("isbn");
        
        System.out.println(isbn);
		
		// get full row info for the product
        Book book = bookDao.getBookByIsbn(isbn);
        
        System.out.println(book.toString());
		
		// send product info to the form page so it can be updated
		request.setAttribute("book", book);

        // forward info and redirect to that page

        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");

        dispatcher.forward(request, response);
    }
    
    private void updateBook(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // grab info to do update for product submitted by form
        String isbn = request.getParameter("isbn").trim();
        String title = request.getParameter("title").trim();
        boolean rented = Boolean.parseBoolean(request.getParameter("rented").trim());
        String description = request.getParameter("description").trim();

        // create the product object
        Book book = new Book(isbn, title, description, rented, new Date());

        // pass object to update from the dao
        bookDao.updateBook(book);

        // redirect to our list products page once we finish updating info on product
        response.sendRedirect("listLibrarian");
    }

    private void goToNewBookForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");

        dispatcher.forward(request, response);
    }
    
    private void addNewBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// grab values to create product from our form
		String title = request.getParameter("title").trim();
        boolean rented = Boolean.parseBoolean(request.getParameter("rented").trim());
        String description = request.getParameter("description").trim();
		
        // create object for product
		Book book = new Book(Utility.randomIsbn(), title, description, rented, new Date());
		
		// call dao to add product to our database
		bookDao.addBook(book);
		
		// once added, redirect to the product list page
		response.sendRedirect("listLibrarian");
		
	}

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String temp = request.getParameter("choice");
		String userType = new String();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(temp.equals("0")) {
			userType = "patron";
			Patron patron = patronDao.getPatron(username, password);
			// if patron is found in db
			if(patron != null) {
				loggedInPatron = patron; 
				//HttpSession session = request.getSession();
				request.setAttribute("patron", patron);
				
			}
			else { // invalid username/password, routed back to login
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
		else {
			userType = "librarian";
			Librarian librarian = librarianDao.getLibrarian(username, password);
			// if patron is found in db
			if(librarian != null) {
				loggedInLibrarian = librarian; 
				//HttpSession session = request.getSession();
				request.setAttribute("librarian", librarian);
				
			}
			else { // invalid username/password, routed back to login
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}

		}
		
		String buildString = userType.concat(".jsp");

        System.out.println(buildString);
		RequestDispatcher dispatcher = request.getRequestDispatcher(buildString);
		
		dispatcher.forward(request, response);
	}
	
	private void goToLibrarianView(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("librarian.jsp");
		
		dispatcher.forward(request, response);
	}
	
	
	private void goToPatronView(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("patron.jsp");
		
		dispatcher.forward(request, response);
	}
	
	
	private void goToLoginPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		
		dispatcher.forward(request, response);
	}
	
	

	private void goToAboutPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("about.jsp");
		
		dispatcher.forward(request, response);
	}

    private void rentBook(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    	// get the book isbn
    			String isbn = request.getParameter("isbn").trim();
    			
    			// create new book checkout record for patron and book
    			patronDao.checkoutBook(loggedInPatron.getId(), isbn);
    			
    			response.sendRedirect("/CrudProject/listPatron");
    	    	
//    	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/rent");
    	//
//    	        dispatcher.forward(request, response);
    }
    
    private void listRentedBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<BookDate> bookDate = patronDao.getPatronBooks(loggedInPatron.getId());
		
		request.setAttribute("bookDate", bookDate);
		request.setAttribute("patron", loggedInPatron);
        RequestDispatcher dispatcher = request.getRequestDispatcher("returnbook.jsp");

        dispatcher.forward(request, response);
		
	}
    
    private void returnBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// get the book isbn
		String isbn = request.getParameter("isbn").trim();
		// get the patron id
		
		request.setAttribute("patron", loggedInPatron);
		
		// return book/update book_checkout table with return date
		patronDao.returnBook(loggedInPatron.getId(), isbn);
		
		// redirect to returns page
		response.sendRedirect("/CrudProject/returnbook");
		
	}

}

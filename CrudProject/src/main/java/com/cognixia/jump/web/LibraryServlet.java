//
//package com.cognixia.jump.web;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.List;
//
//import java.io.PrintWriter;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.cognixia.jump.connection.ConnectionManager;
//import com.cognixia.jump.dao.PatronDao;
//import com.cognixia.jump.dao.PatronDaoImp;
//import com.cognixia.jump.model.Patron;
////import com.cognixia.jump.model.Product;
//import com.cognixia.jump.model.Book;
//import com.cognixia.jump.model.Librarian;
//import com.cognixia.jump.dao.BookDaoImp;
//import com.cognixia.jump.dao.LibrarianDao;
//import com.cognixia.jump.dao.LibrarianDaoImp;
//
//@WebServlet("/")
//public class LibraryServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	private BookDaoImp bookDao;
//	private PatronDao patronDao;
//	private Patron loggedInPatron;
//	private Librarian loggedInLibrarian;
//	private LibrarianDao librarianDao;
//
//	public void init(ServletConfig config) throws ServletException {
//		
//		bookDao = new BookDaoImp();
//		patronDao = new PatronDaoImp();
//		librarianDao = new LibrarianDaoImp();
//		
//	}
//
//
//	public void destroy() {
//		
//		try {
//			
//			ConnectionManager.getConnection().close();
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		doGet(request, response);
//	}
//
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	
//		// based on the ending url -> perform some request/response through servlet
//		// localhost:8080/CrudProject/ProductServlet -> domain url
//		// localhost:8080/CrudProject/ProductServlet/abc -> "abc" is the ending url
//		String action = request.getServletPath();
//		
//		switch (action) {
//		case "/register": // go to product list page
//			//listProducts(request, response);
//			break;
//			
//		case "/login":
//			goToLogin(request, response);
//			break;
//		
//		case "/patron": 
//			loginPatron(request, response);
//			break;
//			
//		case "/librarian": 
//			loginLibrarian(request, response);
//			break;
//		
//		case "/update_patron_info": // perform update on Patron
//			goToUpdatePatronInfo(request, response);
//			break;
//			
//		case "/listPatron":
//            listBooks(request, response, "patron");
//            break;
//            
//		case "/listLibrarian":
//            listBooks(request, response, "librarian");
//            break;
//			
//		case "/checkoutbook": // redirect to page where we can add a new product
//			checkoutBook(request, response);
//			break;
//			
//		case "/returnbook": // add product to db
//			listRentedBooks(request, response);
//			break;
//		
//		case "/return":
//			returnBook(request, response);
//			break;
//		case "/logout": // patron logout
//			response.sendRedirect("/CrudProject");
//			break;
//			
//		case "/addbook": // add book to db
//			addBooks(request, response);
//			break;
//			
//		case "/updatebook": // update book to db
//			updateBooks(request, response);
//			break;
//			
//		default:
//			// redirect the the url: localhost:8080/CrudProject
//			// display index.js page
//			response.sendRedirect("/");
//			break;
//		}
//		
//	}
//	
//	private void goToLogin(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		response.sendRedirect("login.jsp");
//		
//	}
//	
//	private void loginPatron(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		PrintWriter out = response.getWriter();
//		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		Patron patron = patronDao.getPatron(username, password);
//		
//		if(patron != null) {
//			loggedInPatron = patron; 
//			HttpSession session = request.getSession();
//			request.setAttribute("patron", patron);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("patron.jsp");
//			dispatcher.forward(request, response);
//		}
//		else {
//			out.print("Sorry, username or password error!");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//			dispatcher.forward(request, response);
//		}
//		
//	}
//	
//	private void loginLibrarian(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		PrintWriter out = response.getWriter();
//		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		Librarian librarian = librarianDao.getLibrarian(username, password);
//		
//		if(librarian != null) {
//			loggedInLibrarian = librarian; 
//			HttpSession session = request.getSession();
//			request.setAttribute("librarian", librarian);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("librarian.jsp");
//			dispatcher.forward(request, response);
//		}
//		else {
//			out.print("Sorry, username or password error!");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//			dispatcher.forward(request, response);
//		}
//		
//	}
//	
//	
//	private void goToUpdatePatronInfo(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		
//		// get id of patron we need to edit/update
//		int id = Integer.parseInt(request.getParameter("id"));
//		
//		// get full row info for the patron
//		Patron patron = patronDao.getPatronById(id);
//		
//		// send product info to the form page so it can be updated
//		request.setAttribute("patron", patron);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("patron-update-form.jsp");
//		dispatcher.forward(request, response);
//	}
//	
//	private void updatePatronInfo(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// grab info to do update for product submitted by form
//		int patron_id = Integer.parseInt(request.getParameter("id").trim());
//		String first_name = request.getParameter("first_name").trim();
//		String last_name = request.getParameter("last_name").trim();
//		String username = request.getParameter("username").trim();
//		String password = request.getParameter("password").trim();
//		
//		// create the patron object
//		Patron patron = new Patron(patron_id, first_name, last_name, username, password);
//		
//		// pass object to update from dao
//		patronDao.updatePatron(patron);
//		
//		// redirect to patron page
//		response.sendRedirect("");
//	}
//	
//	private void listBooks(HttpServletRequest request, HttpServletResponse response, String user)
//			throws ServletException, IOException {
//		
//		 List<Book> allBooks = bookDao.getAllBooks();
//
//        request.setAttribute("allBooks", allBooks);
//        request.setAttribute("user", user);
//        request.setAttribute("patron", loggedInPatron);
//        request.setAttribute("librarian", loggedInLibrarian);
//        RequestDispatcher dispatcher = null;
//        if(user == "librarian") {
//        	dispatcher = request.getRequestDispatcher("book-list-librarian.jsp");
//        }
//        else { 
//        	
//        	dispatcher = request.getRequestDispatcher("book-list-patron.jsp"); 
//        }
//
//        dispatcher.forward(request, response);
//		
//	}
//	
//	private void checkoutBook(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		// get the book isbn
//		String isbn = request.getParameter("isbn").trim();
//		// get the patron id
//		//int id = Integer.parseInt(request.getParameter("id"));
//		
//		// create new book checkout record for patron and book
//		patronDao.checkoutBook(loggedInPatron.getId(), isbn);
//		
//		response.sendRedirect("/CrudProject/listPatron");
//		
//		
//	}
//	
//	private void listRentedBooks(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		List<Book> patronBooks = patronDao.getPatronBooks(loggedInPatron.getId());
//		//List<>
//		
//		for(Book book : patronBooks) {
//			List<String> book_dates = patronDao.getBookDates(loggedInPatron.getId(), book.getIsbn());
//			
//		}
//		
//		request.setAttribute("patronBooks", patronBooks);
//		request.setAttribute("patron", loggedInPatron);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("returnbook.jsp");
//
//        dispatcher.forward(request, response);
//		
//	}
//	
//	private void returnBook(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		// get the book isbn
//		String isbn = request.getParameter("isbn").trim();
//		// get the patron id
//		
//		request.setAttribute("patron", loggedInPatron);
//		
//		// return book/update book_checkout table with return date
//		patronDao.returnBook(loggedInPatron.getId(), isbn);
//		
//		// redirect to returns page
//		response.sendRedirect("/CrudProject/returnbook");
//		
//	}
//	
//	private void addBooks(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		
//		String isbn, String title, String description, boolean rented, Date added_to_library
//		
//		
//				String isbn = request.getParameter("isbn");
//				String title = request.getParameter("title");
//				String description = request.getParameter("description");
//				boolean rented = request.getParameter("rented");
//				Date added_to_library = 
//
//				// create object for product
//				Product product = new Product(0, item, qty, description);
//
//				// call dao to add product to our database
//				productDao.addProduct(product);
//
//				// once added, redirect to the product list page
//				response.sendRedirect("list");
//		
//		
//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
//		dispatcher.forward(request, response);
//	}
//	
//	
//	private void updateBooks(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// grab info to do update for product submitted by form
//		int patron_id = Integer.parseInt(request.getParameter("id").trim());
//		String first_name = request.getParameter("first_name").trim();
//		String last_name = request.getParameter("last_name").trim();
//		String username = request.getParameter("username").trim();
//		String password = request.getParameter("password").trim();
//		
//		// create the patron object
//		Patron patron = new Patron(patron_id, first_name, last_name, username, password);
//		
//		// pass object to update from dao
//		patronDao.updatePatron(patron);
//		
//		// redirect to patron page
//		response.sendRedirect("");
//	}
//
//}
//

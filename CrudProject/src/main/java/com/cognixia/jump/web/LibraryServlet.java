package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.PatronDao;
import com.cognixia.jump.dao.PatronDaoImp;
import com.cognixia.jump.model.Patron;
import com.cognixia.jump.model.Book;


@WebServlet("/")
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PatronDao patronDao;

	public void init(ServletConfig config) throws ServletException {
		
		patronDao = new PatronDaoImp();
		
	}


	public void destroy() {
		
		try {
			
			ConnectionManager.getConnection().close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// based on the ending url -> perform some request/response through servlet
		// localhost:8080/CrudProject/ProductServlet -> domain url
		// localhost:8080/CrudProject/ProductServlet/abc -> "abc" is the ending url
		String action = request.getServletPath();
		
		switch (action) {
		case "/register": // go to product list page
			//listProducts(request, response);
			break;
		
		case "/login": // delete a single product
			loginPatron(request, response);
			break;
		
			
		case "/update_patron_info": // perform update on Patron
			goToUpdatePatronInfo(request, response);
			break;
			
		case "/checkoutbook": // redirect to page where we can add a new product
			//goToNewProductForm(request, response);
			break;
			
		case "/returnbook": // add product to db
			//addNewProduct(request, response);
			break;
		
		case "/logout": // patron logout
			break;
			
		default:
			// redirect the the url: localhost:8080/CrudProject
			// display index.js page
			response.sendRedirect("/");
			break;
		}
		
	}
	
	private void loginPatron(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// grab values to find patron from library db
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Patron patron = patronDao.getPatron(username, password);
		
		request.setAttribute("patron", patron);
		RequestDispatcher dispatcher = request.getRequestDispatcher("patron.jsp");
		dispatcher.forward(request, response);
	}
	
	private void goToUpdatePatronInfo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// get id of patron we need to edit/update
		int id = Integer.parseInt(request.getParameter("id"));
		
		// get full row info for the patron
		Patron patron = patronDao.getPatronById(id);
		
		// send product info to the form page so it can be updated
		request.setAttribute("patron", patron);
		RequestDispatcher dispatcher = request.getRequestDispatcher("patron-update-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void updatePatronInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// grab info to do update for product submitted by form
		int patron_id = Integer.parseInt(request.getParameter("id").trim());
		String first_name = request.getParameter("first_name").trim();
		String last_name = request.getParameter("last_name").trim();
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		
		// create the patron object
		Patron patron = new Patron(patron_id, first_name, last_name, username, password);
		
		// pass object to update from dao
		patronDao.updatePatron(patron);
		
		// redirect to patron page
		response.sendRedirect("");
	}
	
	private void checkoutBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// get the book isbn
		String isbn = request.getParameter("isbn").trim();
		// get the patron id
		int id = Integer.parseInt(request.getParameter("id"));
		
		// create new book checkout record for patron and book
		patronDao.checkoutBook(id, isbn);
		
		// redirect to patron page
		response.sendRedirect("");
		
	}
	
	private void returnBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// get the book isbn
		String isbn = request.getParameter("isbn").trim();
		// get the patron id
		int id = Integer.parseInt(request.getParameter("id"));
		
		// return book/update book_checkout table with return date
		patronDao.returnBook(id, isbn);
		
		// redirect to patron page
		response.sendRedirect("");
		
	}

}

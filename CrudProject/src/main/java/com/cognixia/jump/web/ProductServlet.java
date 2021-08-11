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
import com.cognixia.jump.dao.ProductDao;
import com.cognixia.jump.dao.ProductDaoImp;
import com.cognixia.jump.model.Product;

@WebServlet("/")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;
	
	public void init(ServletConfig config) throws ServletException {
		
		productDao = new ProductDaoImp();
		
	}

	public void destroy() {
		
		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {
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
		case "/list": // go to product list page
			listProducts(request, response);
			break;
		
		case "/delete": // delete a single product
			deleteProduct(request, response);
			break;
		
		case "/edit": // redirect to page where we can edit a single product's info
			goToEditProductForm(request, response);
			break;
			
		case "/update": // perform update on product
			updateProduct(request, response);
			break;
			
		case "/new": // redirect to page where we can add a new product
			goToNewProductForm(request, response);
			break;
			
		case "/add": // add product to db
			addNewProduct(request, response);
			break;
			
		case "/login":
			goToLoginPage(request, response);

			
			break;
			
			
		case "/about":
			goToAboutPage(request, response);

			
			break;
//		case "/patron":
//			goToPatronView(request, response);
//
//			
//			break;	
//		case "/librarian":
//			goToLibrarianView(request, response);

			
//			break;	
		case "/trylogin":
			handleLogin(request, response);

			
			break;
			
		default:
			// redirect the the url: localhost:8080/CrudProject
			// display index.js page
			response.sendRedirect("/");
			break;
		}
	}
	
	private void handleLogin(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		String temp = request.getParameter("choice");
		String userType = new String();
		if(temp.equals("0")) {
			userType = "patron";
		}
		else {
			userType = "librarian";

		}
		
		String buildString = userType.concat(".jsp");
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	********************************************************************************************************************************
//	********************************	REFERENCE MATERIAL ********************************************************
//	****************************************************************************************************************
	private void addNewProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// grab values to create product from our form
		String item = request.getParameter("item");
		int qty = Integer.parseInt(request.getParameter("qty"));
		String description = request.getParameter("description");

		// create object for product
		Product product = new Product(0, item, qty, description);

		// call dao to add product to our database
		productDao.addProduct(product);

		// once added, redirect to the product list page
		response.sendRedirect("list");
	}
	
	private void goToEditProductForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// get id of product we need to edit/update
		int id = Integer.parseInt(request.getParameter("id"));
		
		// get full row info for the product
		Product product = productDao.getProductById(id);
		
		// send product info to the form page so it can be updated
		request.setAttribute("product", product);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// grab info to do update for product submitted by form
		int id = Integer.parseInt(request.getParameter("id").trim());
		String item = request.getParameter("item").trim();
		int qty = Integer.parseInt(request.getParameter("qty").trim());
		String description = request.getParameter("description").trim();

		// create the product object
		Product product = new Product(id, item, qty, description);

		// pass object to update from the dao
		productDao.updateProduct(product);

		// redirect to our list products page once we finish updating info on product
		response.sendRedirect("list");
		
	}

	
	private void listProducts(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Product> allProducts = productDao.getAllProducts();
		
		// add in data you're going to send, through the request object
		request.setAttribute("allProducts", allProducts);
		
		// redirect to jsp page and send data we just pulled
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// get the id for the product to delete
		int id = Integer.parseInt(request.getParameter("id"));
		
		// use dao to perform delete
		productDao.deleteProduct(id);
		
		// redirect back to products page (refresh)
		// table will reload and not contain product we just deleted
		response.sendRedirect("list"); // send another request to servlet to load /list
	}

	private void goToNewProductForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		
		dispatcher.forward(request, response);
	}
	

}

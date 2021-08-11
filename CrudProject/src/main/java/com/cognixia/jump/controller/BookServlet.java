package main.java.com.cognixia.jump.controller;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServlet;

import com.cognixia.jump.connection.ConnectionManager;

import main.java.com.cognixia.jump.dao.BookDaoImp;

@WebServlet("/")
public class BookServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;

    private BookDaoImp bookDao;

    public void init() {
        bookDao = new BookDaoImp();
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
            case "/list":
                listBooks(request, response);
                break;
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

            default:

                response.sendRedirect("/");
                break;
        }

    }
    
    private void listBooks(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        List<Book> allBooks = bookDao.getAllBooks();

        request.setAttribute("allBooks", allBooks);

        RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");

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
        response.sendRedirect("list");
    }
    
    private void goToEditBookForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // get the id of the product we need edit/update
        String isbn = request.getParameter("isbn");

        // get the full row info for the product
        Book book = bookDao.getBookById(isbn);

        // send product to edit to new page
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
        Book book = new Book(isbn, title, rented, description);

        // pass object to update from the dao
        bookDao.updateProduct(book);

        // redirect to our list products page once we finish updating info on product
        response.sendRedirect("list");
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
		Book book = new Book(0, title, rented, description, new Date());
		
		// call dao to add product to our database
		bookDao.addBook(book);
		
		// once added, redirect to the product list page
		response.sendRedirect("list");
		
	}
    
}

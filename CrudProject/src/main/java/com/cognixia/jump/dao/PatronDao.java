package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Patron;

public interface PatronDao {
	
	public List<Patron> getAllPatrons();
	
	public List<Book> getPatronBooks(int id);
	
	public List<String> getBookDates(int id, String isbn);

	public boolean addPatron(Patron patron);
	
	public Patron getPatron(String username, String password);
	
	public Patron getPatronById(int id);
	
	public int getPatronId(String username);
	
	public boolean checkoutBook(int patron_id, String isbn);
	
	public boolean returnBook(int id, String isbn);
	
	public boolean updatePatron(Patron patron);

	
	
	
}

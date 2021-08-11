package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Patron;

public interface PatronDao {
	
	public List<Patron> getAllPatrons();
	
	public List<Book> getPatronBooks(int id);

	public boolean addPatron(Patron patron);
	
	public Patron getPatron(String username, String password);
	
	public int getPatronId(String username);
	
	public boolean checkoutBook(Patron patron, Book book);
	
	public boolean returnBook(Patron patron, Book book);
	
	public boolean updatePatron(Patron patron);

	
	
	
}

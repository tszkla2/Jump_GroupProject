package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Patron;
import com.cognixia.jump.model.BookDate;

public interface PatronDao {
	
	public List<Patron> getAllPatrons();
	
	public List<BookDate> getPatronBooks(int id);

	public boolean addPatron(Patron patron);
	
	public Patron getPatron(String username, String password);
	
	public Patron getPatronById(int id);
	
	public int getPatronId(String username);
	
	public boolean checkoutBook(int patron_id, String isbn);
	
	public boolean returnBook(int id, String isbn);
	
	public boolean updatePatron(Patron patron);

	
	
	
}
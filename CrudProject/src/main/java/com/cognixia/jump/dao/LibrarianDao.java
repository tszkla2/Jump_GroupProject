package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Librarian;

public interface LibrarianDao {
	
	public List<Librarian> getAllLibrarians();
	
	public boolean addBook(Book book);
	
	public boolean deleteBook(int isbn);
	
	public boolean updateBook(Book book);
	
	public boolean updateUsername(Librarian Librarian);
	
	public boolean updatePassword(Librarian Librarian);
	
	

}

package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Librarian;

public interface LibrarianDao { 
	
	public List<Librarian> getAllLibrarians();
	
	public boolean updateUsername(Librarian Librarian);
	
	public boolean updatePassword(Librarian Librarian);
	
	public Librarian getLibrarian(String username, String password);

}

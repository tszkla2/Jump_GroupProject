package com.cognixia.jump.model;

import java.util.Date;

public class Book {

	private String isbn;
	private String title;
	private String description;
	private boolean rented;
	private Date added_to_library;

	public Book(String isbn, String title, String description, boolean rented, Date added_to_library) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.rented = rented;
		this.added_to_library = added_to_library;
	}

	public Date getAdded_to_library() {
		return added_to_library;
	}

	public void setAdded_to_library(Date added_to_library) {
		this.added_to_library = added_to_library;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title  + ", description=" + description + ", available=" + rented + "]";
	}

}

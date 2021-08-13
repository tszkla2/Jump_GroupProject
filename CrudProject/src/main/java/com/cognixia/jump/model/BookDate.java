package com.cognixia.jump.model;

public class BookDate {
	
	private Book book;
	private String checkedout;
	private String due_date;
	private String returned;
	
	public BookDate(Book book, String checkedout, String due_date, String returned){
		this.book = book;
		this.checkedout = checkedout;
		this.due_date = due_date;
		this.returned = returned;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getCheckedout() {
		return checkedout;
	}

	public void setCheckedout(String checkedout) {
		this.checkedout = checkedout;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getReturned() {
		return returned;
	}

	public void setReturned(String returned) {
		this.returned = returned;
	}
	
	

}
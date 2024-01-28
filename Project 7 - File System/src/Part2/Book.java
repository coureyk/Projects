package Part2;

//-----------------------------------------------------
//Assignment 3
//Part: 2
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import java.io.Serializable;

public class Book implements Serializable {
	
	/**
	 * A unique identifier for the MissingFieldException class.
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String authors;
	private double price;
	private String isbn;
	private String genre;
	private int year;
	
	/**
	 * This method constructs a Book object with a default title, authors, price, isbn, genre and year.
	 */
	public Book() {
		this.title = null;
		this.authors = null;
		this.price = 0;
		this.isbn = null;
		this.genre = null;
		this.year = 0;
	}
	
	/**
	 * This method constructs a Book object with a specified title, authors, price, isbn, genre and year.
	 * @param title Title of the book
	 * @param authors Author(s) of the book
	 * @param price Price of the book
	 * @param isbn ISBN of the book
	 * @param genre Genre of the book
	 * @param year Year of the book
	 */
	public Book(String title, String authors, double price, String isbn, String genre, int year) {
		this.title = title;
		this.authors = authors;
		this.price = price;
		this.isbn = isbn;
		this.genre = genre;
		this.year = year;
	}
	
	/**
	 * This method constructs a copy of a pre-existing Book.
	 * @param otherBook Another object of type Book.
	 */
	public Book(Book otherBook) {
		this.title = otherBook.title;
		this.authors = otherBook.authors;
		this.price = otherBook.price;
		this.isbn = otherBook.isbn;
		this.genre = otherBook.genre;
		this.year = otherBook.year;
	}
	
	/**
	 * This method returns a description of the Book, with regard to its
	 * title, authors, price, isbn, genre and year.
	 * @return Book description with regard to title, authors, price, isbn, genre and year.
	 */
	@Override
	public String toString() {
		return (this.title + ", " + this.authors + ", " + this.price + ", " + this.isbn + ", " + this.genre + ", " + year);
	}
	
	/**
	 * This method compares two objects and determines whether or not they are of type Book and 
	 * share the same contents.
	 * @param obj Any object.
	 * @return True if the objects are of type Book and share the same contents and False if
	 * at least one of these conditions is not met.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && this.getClass().equals(obj.getClass())) {
			Book otherBook = (Book) obj;
			return (this.title.equals(otherBook.title) && this.authors.equals(otherBook.authors) && this.price == otherBook.price && this.isbn.equals(otherBook.isbn) && this.genre.equals(otherBook.genre) && this.year == otherBook.year);
		}
		return false;
	}
}

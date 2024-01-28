//-----------------------------------------------------
//Assignment 4
//Part 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

/**
* The Book class is designed to contain the most basic attributes/methods which all book records must possess.
* @author Kcour
*
*/
public class Book {
	private String title;
	private String author;
	private double price;
	private long ISBN;
	private String genre;
	private int year;
	
	/**
	 * This method constructs a Book object with a default title, author, price, isbn, genre and year.
	 */
	public Book() {
		this.title = null;
		this.author = null;
		this.price = 0.0;
		this.ISBN = 0L;
		this.genre = null;
		this.year = 0;
	}
	
	/**
	 * This method constructs a Book object with a specified title, author, price, isbn, genre and year.
	 * @param title Title of the book
	 * @param authors Author(s) of the book
	 * @param price Price of the book
	 * @param isbn ISBN of the book
	 * @param genre Genre of the book
	 * @param year Year of the book
	 */
	public Book(String title, String author, double price, long ISBN, String genre, int year) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.ISBN = ISBN;
		this.genre = genre;
		this.year = year;
	}
	
	/**
	 * This method constructs a copy of a pre-existing Book.
	 * @param otherBook Another object of type Book.
	 */
	public Book(Book otherBook) {
		this.title = otherBook.title;
		this.author = otherBook.author;
		this.price = otherBook.price;
		this.ISBN = otherBook.ISBN;
		this.genre = otherBook.genre;
		this.year = otherBook.year;
	}
	
	/**
	 * This method will return the title of the Book that invokes this method.
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * This method will return the author of the Book that invokes this method.
	 * @return author
	 */
	public String getAuthor() {
		return this.author;
	}
	
	/**
	 * This method will return the price of the Book that invokes this method.
	 * @return price
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * This method will return the ISBN of the Book that invokes this method.
	 * @return isbn
	 */
	public long getISBN() {
		return this.ISBN;
	}
	
	/**
	 * This method will return the genre of the Book that invokes this method.
	 * @return genre
	 */
	public String getGenre() {
		return this.genre;
	}
	
	/**
	 * This method will return the year of the Book that invokes this method.
	 * @return year
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * This method will set the title of the Book that invokes this method to a specified value.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * This method will set the author of the Book that invokes this method to a specified value.
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * This method will set the price of the Book that invokes this method to a specified value.
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * This method will set the ISBN of the Book that invokes this method to a specified value.
	 * @param ISBN
	 */
	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}
	
	/**
	 * This method will set the genre of the Book that invokes this method to a specified value.
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/**
	 * This method will set the year of the Book that invokes this method to a specified value.
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * This method returns a description of the Book, with regard to its
	 * title, authors, price, isbn, genre and year.
	 * @return Book description with regard to title, authors, price, isbn, genre and year.
	 */
	@Override
	public String toString() {
		return (this.title + ", " + this.author + ", " + this.price + ", " + this.ISBN + ", " + this.genre + ", " + this.year);
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
			return (this.title.equals(otherBook.title) && this.author.equals(otherBook.author) && this.price == otherBook.price && this.ISBN == otherBook.ISBN && this.genre.equals(otherBook.genre) && this.year == otherBook.year);
		}
		return false;
	}
}
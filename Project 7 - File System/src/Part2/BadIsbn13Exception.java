package Part2;

//-----------------------------------------------------
//Assignment 3
//Part: 2
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

/**
* The BadIsbn13Exception is a custom exception class designed to be invoked when a book record contains an invalid 13-digit ISBN number.
* @author Kcour
*/
public class BadIsbn13Exception extends Exception {
	
	/**
	 * A unique identifier for the MissingFieldException class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method constructs a BadIsbn13Exception object with a default error message.
	 */
	public BadIsbn13Exception() {
		super("Error: Invalid 13-digit ISBN number.");
	}
	
	/**
	 * This method constructs a BadIsbn13Exception object with a specified error message.
	 * @param record A book record
	 */
	public BadIsbn13Exception(String record) {
		super("Error: Invalid 13-digit ISBN number.\nRecord: " + record + "\n");
	}
	
	/**
	 * This method returns the error message of the BadIsbn13Exception object that invoked it.
	 * @return Error message.
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
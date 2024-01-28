package Part2;

//-----------------------------------------------------
//Assignment 3
//Part: 2
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

/**
* The BadIsbnException is a custom exception class designed to be invoked when a book record contains a generally invalid ISBN number.
* @author Kcour
*/
public class BadIsbnException extends Exception {
	
	/**
	 * A unique identifier for the MissingFieldException class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method constructs a BadIsbnException object with a default error message.
	 */
	public BadIsbnException() {
		super("Error: Invalid ISBN number.");
	}
	
	/**
	 * This method constructs a BadIsbnException object with a specified error message.
	 * @param record A book record
	 */
	public BadIsbnException(String record) {
		super("Error: Invalid ISBN number.\nRecord: " + record + "\n");
	}
	
	/**
	 * This method returns the error message of the BadIsbnException object that invoked it.
	 * @return Error message.
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
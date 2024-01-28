package Part1;

//-----------------------------------------------------
//Assignment 3
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

/**
* The TooManyFieldsException is a custom exception class designed to be invoked when a book record contains more than 6 fields of information.
* @author Kcour
*/
public class TooManyFieldsException extends Exception {
	/**
	 * A unique identifier for the TooManyFieldsException class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method constructs a TooManyFieldsException object with a default error message.
	 */
	public TooManyFieldsException() {
		super("Error. Too many fields have been entered in this book record.");
	}
	
	/**
	 * This method constructs a TooManyFieldsException object with a specified error message.
	 * @param record A book record
	 */
	public TooManyFieldsException (String record) {
		super("Error: too many fields.\nRecord: " + record + "\n");
	}
	
	/**
	 * This method returns the error message of the TooManyFieldsException object that invoked it.
	 * @return Error message.
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
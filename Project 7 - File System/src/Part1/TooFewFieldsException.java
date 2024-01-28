package Part1;

//-----------------------------------------------------
//Assignment 3
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

/**
* The TooFewFieldsException is a custom exception class designed to be invoked when a book record contains fewer than 6 fields of information.
* @author Kcour
*/
public class TooFewFieldsException extends Exception {
	
	/**
	 * A unique identifier for the TooFewFieldsException class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method constructs a TooFewFieldsException object with a default error message.
	 */
	public TooFewFieldsException() {
		super("Error. Too few fields have been entered in this book record.");
	}
	
	/**
	 * This method constructs a TooFewFieldsException object with a specified error message.
	 * @param record A book record
	 */
	public TooFewFieldsException (String record) {
		super("Error: too few fields.\nRecord: " + record + "\n");
	}
	
	/**
	 * This method returns the error message of the TooFewFieldsException object that invoked it.
	 * @return Error message.
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
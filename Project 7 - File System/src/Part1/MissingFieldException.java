package Part1;

//-----------------------------------------------------
//Assignment 3
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

/**
* The MissingFieldException is a custom exception class designed to be invoked when a book record contains an empty field of information.
* @author Kcour
*/
public class MissingFieldException extends Exception {
	/**
	 * A unique identifier for the MissingFieldException class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method constructs a MissingFieldException object with a default error message.
	 */
	public MissingFieldException() {
		super("Error. There is a missing field in this book record.");
	}
	
	/**
	 * This method constructs a MissingFieldException object with a specified error message.
	 * @param missingField An empty field of information.
	 * @param record A book record
	 */
	public MissingFieldException (String missingField, String record) {
		super("Error: missing " + missingField + "\nRecord: " + record + "\n");
	}
	
	/**
	 * This method returns the error message of the MissingFieldException object that invoked it.
	 * @return Error message.
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
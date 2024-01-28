package Part2;

//-----------------------------------------------------
//Assignment 3
//Part: 2
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

/**
* The BadYearException is a custom exception class designed to be invoked when a book record contains an invalid year.
* @author Kcour
*/
public class BadYearException extends Exception {
	
	/**
	 * A unique identifier for the MissingFieldException class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method constructs a BadYearException object with a default error message.
	 */
	public BadYearException() {
		super("Error: Invalid year.");
	}
	
	/**
	 * This method constructs a BadYearException object with a specified error message.
	 * @param record A book record
	 */
	public BadYearException(String record) {
		super("Error: Invalid year.\nRecord: " + record + "\n");
	}
	
	/**
	 * This method returns the error message of the BadYearException object that invoked it.
	 * @return Error message.
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}

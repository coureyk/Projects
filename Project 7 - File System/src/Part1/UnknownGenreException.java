package Part1;

//-----------------------------------------------------
//Assignment 3
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

/**
* The UnknownGenreException is a custom exception class designed to be invoked when a book record contains an unknown genre in its record.
* @author Kcour
*/
public class UnknownGenreException extends Exception {
	/**
	 * A unique identifier for the UnknownGenreException class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method constructs a UnknownGenreException object with a default error message.
	 */
	public UnknownGenreException() {
		super("Error. Genre has not been specified in this book record.");
	}
	
	/**
	 * This method constructs a UnknownGenreException object with a specified error message.
	 * @param record A book record
	 */
	public UnknownGenreException (String record) {
		super("Error: unknown genre.\nRecord: " + record + "\n");
	}
	
	/**
	 * This method returns the error message of the UnknownGenreException object that invoked it.
	 * @return Error message.
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}

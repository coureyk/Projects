package Part2;

//-----------------------------------------------------
//Assignment 3
//Part: 2
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

/**
* The BadPriceException is a custom exception class designed to be invoked when a book record contains negative value in the price field.
* @author Kcour
*/
public class BadPriceException extends Exception {
	
	/**
	 * A unique identifier for the MissingFieldException class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method constructs a BadPriceException object with a default error message.
	 */
	public BadPriceException() {
		super("Error: Invalid price.");
	}
	
	/**
	 * This method constructs a BadPriceException object with a specified error message.
	 * @param record A book record
	 */
	public BadPriceException(String record) {
		super("Error: Invalid price.\nRecord: " + record + "\n");
	}
	
	/**
	 * This method returns the error message of the BadPriceException object that invoked it.
	 * @return Error message.
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}

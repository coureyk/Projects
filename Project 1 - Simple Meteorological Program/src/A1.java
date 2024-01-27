//-------------------------------------------------------
//Assignment 1
//Written by: Kevin Courey 40245966
//For COMP 248 Section S – Fall 2022
//--------------------------------------------------------
import java.util.Scanner; //This allows me to use the Scanner class.

/*
* This program is designed to take 2 temperature values from the user
* (one being in Fahrenheit and the other in Celcius) and convert them
* to the opposite temperature scale, as well as calculate the modulus
* and resultant of these new temperature values. 
*/
public class A1 {
	
	public static void main(String[] args) {		
		System.out.println("Welcome to the Simple Meteorological Program:");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print("Please enter the values for the 'Fahrenheit' and 'Celcius' scale, simultaneously: ");
		Scanner userInput = new Scanner(System.in); //Creates a scanner object designed to read user input.
		/*
		 * This method finds and returns the next token from the user input.  
		 * If the token can be translated into a valid double value, 
		 * then user input is stored into variable 'f' (for 'Fahrenheit') and
		 * the scanner advances to the next token.
		 */
		double f = userInput.nextDouble(); 
		/*
		 * This method finds and returns the next token from the user input.  
		 * If the token can be translated into a valid double value, 
		 * then user input is stored into variable 'c' (for 'Celcius') and
		 * the scanner advances to the next token.
		 */
		double c = userInput.nextDouble(); 
		userInput.close(); //Closes the scanner method, thus preventing the possibility of semantic errors in the future.
		/*
		 * Assigning the empirical formula for deriving the Celcius value from the Fahrenheit value.
		 * (10.0 / 18.0) was written instead of (10 / 18) so as to yield the expected result. 
		 * The reason why (10 / 18) does not yield the expected result, even though it is stored
		 * in a variable of data type "double", is because in Java, when you divide an integer
		 * by an integer the result will always be an integer.
		 */
		double fToC = (10.0 / 18.0) * (f - 32); 
		/*
		 * Assigning the empirical formula for deriving the Fahrenheit value from the Celcius value.
	     * (18.0 / 10.0) was written instead of (18 / 10) so as to yield the expected result. 
		 */
		double cToF = (18.0 / 10.0) * c + 32; 
		/*
		 * "\n" inserts a new line before the text so that the
		 * output matches the examples from the assignment
		 * instructions. Based on the examples from
		 * the assignment instructions, fToC should always take the form of
		 * data type "double", therefore, it should remain unaltered.
		 */
		System.out.println("\n" + "The corresponding temperature in Celcius unit is: " + fToC); 
		/*
		 * Based on the examples from the assignment instructions, 
		 * cToF should always take the form of a real number rounded
		 * to one decimal point. The formula Math.round(cToF * 10) / 10.0)
		 * ensures this.
		 */
		System.out.println("The corresponding temperature in Fahrenheit unit is: " + Math.round(cToF * 10) / 10.0); 
		/* 
		 * Assigning the formula for deriving the modulus of cToF and fToC to the variable 'x'. 
		 * In this case, the double value fToC is converted to an integer value 
	     * so that the modulus will match the examples provided in the assignment instructions,
	     * particularly Figure 6. Sample3.
		 */
		double x = cToF % (int)fToC; 
		double y = Math.pow(cToF, fToC); //Assigning the formula for deriving the resultant of cToF and fToC to the double value 'y'.
		/*
		 * The function ((double)Math.round(x)) rounds x to the nearest integer and then adds a '.0'
		 * after it. This was done so that the output would match the examples given in the assignment instructions.
		 */
		System.out.println("The corresponding value for x is: " + (double)Math.round(x));
		System.out.println("The corresponding value for y is: " + y);
		System.out.print("\n" + "Thank you for using my bespoke Meterological program!");
	}

}

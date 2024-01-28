import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Multiple_Recursion_Model extends TetranacciCalculator{
	
	//Invokes the TetranacciCalculator parameterized constructor.
	public Multiple_Recursion_Model(Scanner sc, PrintStream outputFile) {
		super(sc, outputFile);
	} 
	
	/*
	 * Foreword: The nth number in the Tetranacci sequence is equal to the sum of the previous four Tetrancacci
	 * numbers. Similary, each of THESE previous four Tetranacci numbers are also equal to the sum of their
	 * four preceding Tetranacci numbers. And so on and so forth until we reach the base cases.
	 * 
	 * This method breaks the nth Tetracanacci number into its four preceding Tetranacci numbers
	 * (and each of these Tetranacci numbers into their four preceding Tetranacci numbers and so on...)
	 * until a base case has been reached, at which point, its value is returned and used as a building block
	 * to calculate the following number in the Tetranacci sequence, until the end of the stack trace is reached.
	 */
	public BigInteger calculate(int n) {
		if (n <= 2) {
			return new BigInteger("0");
		}
		else if (n == 3) {
			return new BigInteger("1");
		}
		else {
			return (calculate(n - 4).add(calculate(n - 3)).add(calculate(n - 2)).add(calculate(n - 1)));
		}
	}
}

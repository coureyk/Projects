import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Linear_Recursion_Model extends TetranacciCalculator{

	//Invokes the TetranacciCalculator parameterized constructor.
	public Linear_Recursion_Model(Scanner sc, PrintStream outputFile) {
		super(sc, outputFile);
	}
	
	/*
	 * Recursive Method that backtracks (invokes itself) until a basecase is reached, at which point
	 * the four initial Tetranacci numbers are returned, and their sum makes up the next
	 * Tetracanni number in the sequence. We then continue this sequence of 
	 * returning the four most recent Tetranacci numbers that have been acquired, 
	 * then calculating their sum, until we have reached the end of the stack trace.
	 * 
	 * At this point, an array consisting of four BigIntegers (with the nth number in the Tetranacci sequence
	 * being located at index 0) will be returned to the "calculate" method, and the value located at index 0
	 * will be returned to where the "calculate" method was initially called.
	 */
	public BigInteger[] calculateMain(int n) {
		if (n <= 2) {
			BigInteger[] answer = {new BigInteger("0"), new BigInteger("0"), new BigInteger("0"), new BigInteger("0")};
			return answer;
		}
		else if (n == 3) {
			BigInteger[] answer = {new BigInteger("1"), new BigInteger("0"), new BigInteger("0"), new BigInteger("0")};
			return answer;
		}
		else {
			BigInteger[] temp = calculateMain(n - 1);
			BigInteger[] answer = {temp[0].add(temp[1]).add(temp[2]).add(temp[3]), temp[0], temp[1], temp[2]}; // we want {Fn, Fn-1}
			return answer;
		}
	}
	
	public BigInteger calculate(int n) {
		return calculateMain(n)[0]; //This additional method executes nearly instantaniously.
	}
}
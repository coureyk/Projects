import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public abstract class TetranacciCalculator {
	
	//Parameterized constructor is invoked by one of the child objects.
	public TetranacciCalculator(Scanner sc, PrintStream outputFile) {
		/*
		 * Prompt the user to enter an integer.
		 * Display the corresponding Tetranacci number to the user.
		 * Write the amount of time it took to compute the Tetranacci number to TetraOut.txt.
		 * Loop until the user enters a non-integer value.
		 */
		while (true) {
			try {
				System.out.print("Please enter the Tetranacci number you would like me to compute or enter a non-integer value to return to the options menu: ");
				String input = sc.nextLine();
				int num = Integer.parseInt(input);
				
				long startTimer = System.currentTimeMillis();
				BigInteger answer = this.calculate(num);				
				System.out.printf("Tetranacci(%d) = %d%n%n", num, answer);
				long endTimer = System.currentTimeMillis();				
				
				if (this.getClass().getName() == "Linear_Recursion_Model") {
					outputFile.printf("Tetranacci(%d) Linear Result: %d (%d milliseconds)%n", num, answer, (endTimer - startTimer));
				} else {
					outputFile.printf("Tetranacci(%d) Multiple Result: %d (%d milliseconds)%n", num, answer, (endTimer - startTimer));
				}
			} catch (NumberFormatException e) {
				System.out.printf("Non-integer value entered. Returning to options menu.%n%n");
				break;
			}
		}
	}
	
	public abstract BigInteger calculate(int num);
}
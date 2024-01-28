
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		//Creating new output stream that will be used to store the duration of Tetracanni calculations.
		PrintStream outputFile = null;
		try {			
			outputFile = new PrintStream(new FileOutputStream("TetraOut.txt"));
		} catch (FileNotFoundException E) {
			System.err.println("Output file not found");
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to my Tetranacci Calculator Program!");
		System.out.printf("+++++++++++++++++++++++++++++++++++++++++++++%n%n");
		
		String menu = "Options Menu:\n"
					+ "1. Linear Recursion Model\n"
					+ "2. Multiple Recursion Model\n"
					+ "3. Display Options Menu\n"
					+ "4. Exit\n\n";
		
		System.out.print(menu);
		
		/*
		 * Loop until user select ones of the four options. If one of the Tetranacci models are selected,
		 * construct that model and pass "sc" and "outputFile" as parameters so they may be used by the TetranacciCalculator object.
		 * If "3" is entered, re-display the options menu. If "4" is entered, exit the loop, thus terminating the program.
		 */
		while (true) {
			System.out.print("Please enter an option number between 1 and 4: ");
			try {
				String input = sc.nextLine();
				int num = Integer.parseInt(input); //This and the previous line ensure that num will either store an integer or throw a NumberFormatException.
				if (num == 1) {
					new Linear_Recursion_Model(sc, outputFile); //Invokes the Linear_Recusion_Model paramaterized constructor.
				} else if (num == 2) {
					new Multiple_Recursion_Model(sc, outputFile); //Invokes the Invokes Multiple_Recusion_Model paramaterized constructor.
				} else if (num == 3) {
					System.out.printf(menu);
				} else if (num == 4) {
					break;
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				System.out.print("Invalid entry. ");
			}
		}
		System.out.printf("%nThank you for using my Tetranacci Calculator Program!");
		sc.close();
		outputFile.close();
	}
}

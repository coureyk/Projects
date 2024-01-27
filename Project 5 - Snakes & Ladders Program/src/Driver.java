import java.util.Scanner;

/**
 * The Driver class is in charge of running the other classes and thus, running the LadderAndSnake game.
 * @author Kcour
 *
 */
public class Driver {
	/**
	 * Creates an object of type scanner, which will allow the user to enter values of his choosing when prompted to do so.
	 */
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * This method displays a welcome message to the user and prompts him to enter the number of players they would like in their game.
	 * Once this information has been gathered, a LadderAndSnake object (or rather, game) is created and the gameplay begins shortly thereafter.
	 * 
	 * @param args The LadderAndSnake game
	 */
	public static void main(String[] args) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Welcome to the World's Greatest Board Game: Ladders and Snakes(TM)");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println();
		int numOfPLayers = 0;
		boolean continueLooping = true;
		while (continueLooping) {
			System.out.print("Please enter the number of players: ");
			if (sc.hasNextInt()) {
				numOfPLayers = sc.nextInt();
				sc.nextLine();
				continueLooping = false;
			} else {
				System.out.println("Error 404. Invalid Entry.");
				sc.nextLine();
			}
		}
		LadderAndSnake game = new LadderAndSnake(numOfPLayers);
		game.play();
		sc.close();
	}

}
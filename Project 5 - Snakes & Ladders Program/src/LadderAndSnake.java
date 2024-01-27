//-----------------------------------------------------
//Assignment 1
//Part 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------
/**
* The LadderAndSnake class implements methods that allow the game to function like a real game of Snakes and Ladders.
* @author Kcour
*
*/
public class LadderAndSnake {

	/**
	 * Stores the number of players in the current game. Will be used to determine the length of a 1D player array. 
	 */
	private static int numOfPlayers;

	/**
	 * This method constructs a game where the number of players is set to the default value of zero.
	 */
	public LadderAndSnake() {
		numOfPlayers = 0;
	}
	
	/**
	 * This method constructs a game with a specified number of players.
	 * @param numOfPlayers The number of players in the current game.
	 */
	public LadderAndSnake(int numOfPlayers) {
		//if (numOfPlayers > 2) {
		//	System.out.printf("Initialization was attempted for %d member of players; however, this is only expected for an extended version of the game. Value will be set to 2.%n%n", numOfPlayers);
		//	LadderAndSnake.numOfPlayers = 2;
		if (numOfPlayers < 2) {
			System.out.println("Error: Cannot execute the game with less than 2 players! Will exit.");
			System.exit(0);
		} else {
			LadderAndSnake.numOfPlayers = numOfPlayers;
		}
	}
			
	/**
	 * This method returns the number of players in the current game.
	 * @return The number of players in the current game.
	 */
	public static int getNumOfPlayers() {
		return LadderAndSnake.numOfPlayers;
	}
		
	/**
	 * This method sets the number of players to an integer value of the user's choosing.
	 * @param numOfPLayers The number of players in the current game.
	 */
	public static void setNumOfPlayers(int numOfPlayers) {
		LadderAndSnake.numOfPlayers = numOfPlayers;
	}
	
	/**
	 * This method acts as a dice roll and will be called at the start of each player's turn.
	 * @return player A random integer value between 1 and 6 inclusively.
	 */
	public static int flipDice(Player player) {
		System.out.println();
		System.out.printf("Turn goes to %s. (Press the enter key to roll the dice)", player.getName());
		Driver.sc.nextLine();
		int diceCount = (int) (Math.floor(Math.random() * 6) + 1);
		return diceCount;
	}
	
	/**
	 * This method commences the actual game. There are 2 distinct parts to the game.
	 * 1) The initialization of the board(s) and players (Lines 77-160).
	 * 2) The actual gameplay (Lines 162-202)
	 */
	public void play() {
		Board numBoard = new Board(10, 10);
		Board gameBoard = new Board(10, 10);
		numBoard.fillBoard();

		Player player[] = new Player[getNumOfPlayers()];
		boolean gameOver = false;
		
		//Assigning Names to Each Player
		for (int i = 0; i < getNumOfPlayers(); i++) {
			player[i] = new Player();
			boolean validName = false;
			while (!validName) {
				System.out.printf("Please enter a name for Player %d: ", i + 1);
				String name = Driver.sc.nextLine();
				if (name.trim().equals("") || name.trim().length() > 8) {
					System.out.println("Error. Name must be between 1 to 8 characters long.");
				} else {
					player[i].setName(name.trim().toUpperCase());
					validName = true;
				}
			}
		}
		
		//Assigning Dice Roll Values to Each Player
		for (int i = 0; i < getNumOfPlayers(); i++) {
			player[i].setDiceRoll(flipDice(player[i]));
			System.out.printf("%s got a dice value of %d%n", player[i].getName(), player[i].getDiceRoll());
		}
		
		//Bubble Sort
		for (int i = 0; i < getNumOfPlayers() - 1; i++) {
			for (int j = 1; j < getNumOfPlayers() - i; j++) {
				if (player[j - 1].getDiceRoll() < player[j].getDiceRoll()) {
					Player temp = player[j-1];
					player[j-1] = player[j];
					player[j] = temp;
				}
			}
		}
		
		//Assigning Temporary Ranking Positions
		int position = 1;
		player[0].setRank(1);
		for (int i = 1; i < getNumOfPlayers(); i++) {
			if (player[i-1].getDiceRoll() > player[i].getDiceRoll()) {
				position = i + 1;
			}
			player[i].setRank(position);
		}
		
		//Tie Breaking
		int attemptNum = 1;
		for (int i = 1; i < getNumOfPlayers(); i++) {
			for (int j = 0; j < i; j++) {
				if (player[i].getRank() == player[j].getRank()) {
					do {
						System.out.printf("%nA tie was achieved between %s and %s. Attempting to break the tie.%n", player[j].getName(), player[i].getName());
						attemptNum++;
						player[j].setDiceRoll(flipDice(player[j]));
						System.out.printf("%s got a dice value of %d%n", player[j].getName(), player[j].getDiceRoll());
						player[i].setDiceRoll(flipDice(player[i]));
						System.out.printf("%s got a dice value of %d%n", player[i].getName(), player[i].getDiceRoll());
					} while (player[i].getDiceRoll() == player[j].getDiceRoll());
						if (player[i].getDiceRoll() > player[j].getDiceRoll()) {
							player[j].setRank(player[j].getRank() + 1);
							Player temp = player[i];
							player[i] = player[j];
							player[j] = temp;
						} else {
							player[i].setRank(player[i].getRank() + 1);
						}
						if (attemptNum > 1) { 
							System.out.printf("%nIt took %d attempts before a decision could be made%n", attemptNum);
							attemptNum = 1; //resets count
						}
				}
			}
		}
		
		//Player Order
		System.out.printf("%nReached final decision on order of playing:%n");
		for (int i = 0; i < getNumOfPlayers(); i++) {
			System.out.printf("%d. %s%n", i + 1, player[i].getName());
		}
		
		//Gameplay
		while (!gameOver) {
			for (int i = 0; i < getNumOfPlayers(); i++) {
				if (player[i].getSquareNum() != 0) {
					gameBoard.removePlayer(player[i]);
				}
				player[i].setDiceRoll(flipDice(player[i]));
				player[i].setSquareNum(player[i].getSquareNum() + player[i].getDiceRoll());
				
				System.out.printf("%s rolled a %d and landed on square %d%n", player[i].getName(), player[i].getDiceRoll(), player[i].getSquareNum());		
				
				if (player[i].getSquareNum() > 100) {
					int overrollAmount = player[i].getSquareNum() - 100;
					player[i].setSquareNum(100 - overrollAmount);
					System.out.printf("Unfortunately, you overrolled by %d and move back to square %d%n", overrollAmount, player[i].getSquareNum());
				}
				
				if (player[i].landedOnLadder()) {
					System.out.printf("Congrats! You landed on a ladder and moved up to square %d%n",  player[i].getSquareNum());
				} else if (player[i].landedOnSnake()) {
					System.out.printf("Bummer! You landed on a snake and moved all the way down to square %d%n", player[i].getSquareNum());
				} 
				
				player[i].checkIfLandedOnPlayer(player);
						
				gameBoard.movePlayer(player[i]);
				
				if (player[i].getSquareNum() == 100) { //change x to x X y value variable
					gameOver = true;
					System.out.printf("Well played %s! You landed directly on square %d and won the game!", player[i].getName(), player[i].getSquareNum());
					System.out.printf(" You have earned my respect.%n");

					break;
				}
			}
			Board.displayBoard(numBoard, gameBoard);
			if (!gameOver) {
				System.out.printf("Game not over; flipping again.%n%n");
			} else {
				System.out.println("Thanks for playing!");
			}
		}
	}
}
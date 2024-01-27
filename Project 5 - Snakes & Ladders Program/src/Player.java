/**
 * The Player class allows the user to create as many distinct players as they want and implements methods to calculate a player's position on the gameboard.
 * @author Kevin Courey
 *
 */
public class Player {
	
	/**
	 * Stores a name entered by the user. Will be displayed at the start of each player's turn and serve as each player's "game piece".
	 */
	private String name;
	
	/**
	 * Stores the player's current dice roll. Will be used to determine player order as well as how many squares a player must advance from his current location.
	 */
	private int diceRoll;
	
	/**
	 * Stores a player's rank (his position on the player order list). Will be used to sort players into an ordered array from highest rank (1) to lowest (# of total players).
	 */
	private int rank;
	
	/**
	 * Stores a player's current square/tile on the gameboard. Will be used for the purposes of determining a player's current location on the gameboard.
	 */
	private int squareNum;
	
	/**
	 * This method constructs a player with a default name, dice roll, rank, and square number.
	 */
	public Player() {
		this.name = "";
		this.diceRoll = 0;
		this.rank = 0;
		this.squareNum = 0;
	}
	
	/**
	 * This method constructs a player with a specified name, dice roll, rank, and square number.
	 * @param name The name of the player.
	 * @param diceRoll The number rolled by the player.
	 * @param rank The rank of the player.
	 * @param squareNum The current gameboard square occupied by the player.
	 */
	public Player(String name, int diceRoll, int rank, int squareNum) {
		this.name = name;
		this.diceRoll = diceRoll;
		this.rank = rank;
		this.squareNum = squareNum;
	}
	
	/**
	 * This method constructs a copy of a pre-existing player with a specified name, dice roll, rank, and square number.
	 * @param anotherPlayer Contains the name, dice roll, rank and square number the user wishes to copy unto another player.
	 */
	public Player(Player anotherPlayer) {
		this.name = anotherPlayer.name;
		this.diceRoll = anotherPlayer.diceRoll;
		this.rank = anotherPlayer.rank;
		this.squareNum = anotherPlayer.squareNum;
	}
	
	/**
	 * This method retrieves the name of the player who invokes this method.
	 * @return The name of the player.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This method retrieves the dice roll of the player who invokes this method.
	 * @return The number rolled by player.
	 */
	public int getDiceRoll() {
		return this.diceRoll;
	}
	
	/**
	 * This method retrieves the rank of the player who invokes this method.
	 * @return The rank of the player.
	 */
	public int getRank() {
		return this.rank;
	}

	/**
	 * This method retrieves the square number of the player who invokes this method.
	 * @return The player's current square number.
	 */
	public int getSquareNum() {
		return this.squareNum;
	}
	
	/**
	 * This method calculates the x-coordinate of the player who invokes this method.
	 * @return The player's x-coordinate location on the gameboard.
	 */
	public int getXCoordinate() {
		return ((this.squareNum - 1) / 10);
	}
	
	/**
	 * This method calculates the y-coordinate of the player who invokes this method.
	 * @return The player's y-coordinate location on the gameboard.
	 */
	public int getYCoordinate() {
		if (this.getXCoordinate() % 2 == 0) {
			return ((this.squareNum - 1) % 10);
		} else {
			return (9 - ((this.squareNum - 1) % 10));
		}
	}
	
	/**
	 * This method assigns a specified name to the player who invokes this method.
	 * @param name The name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method assigns a specified dice roll value to the player who invokes this method.
	 * @param diceRoll The number rolled by player.
	 */
	public void setDiceRoll(int diceRoll) {
		this.diceRoll = diceRoll;
	}
	
	/**
	 * This method assigns a specified rank to the player who invokes this method.
	 * @param rank The rank of the player.
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/**
	 * This method assigns a specified rank to the player who invokes this method.
	 * @param rank The player's current square number.
	 */
	public void setSquareNum(int squareNum) {
		this.squareNum = squareNum;
	}
	
	/**
	 * This method checks if the player who called the method landed on a ladder and assigns 
	 * them a new square/tile number if they did.
	 * @return A true or false value. If True, the player will be moved to a new square number on the gameboard and the LadderAndSnake class
	 * (the class in which this method is called) will display a congratulations message. If False, no actions will be taken.
	 */
	public boolean landedOnLadder() {
		switch (this.squareNum) {
		case 1:
			this.setSquareNum(38);
			return true;
		case 4:
			this.setSquareNum(14);
			return true;
		case 9:
			this.setSquareNum(31);
			return true;
		case 21:
			this.setSquareNum(42);
			return true;
		case 28:
			this.setSquareNum(84);
			return true;
		case 36:
			this.setSquareNum(44);
			return true;
		case 51:
			this.setSquareNum(67);
			return true;
		case 71:
			this.setSquareNum(91);
			return true;
		case 80:
			this.setSquareNum(100);
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * This method checks if the player who called the method landed on a snake and assigns 
	 * them a new square/tile number if they did.
	 * @return A true or false value. If True, the player will be moved to a new square number on the gameboard and the LadderAndSnake class
	 * (the class in which this method is called) will display an apologetic message. If False, no actions will be taken.
	 */
	public boolean landedOnSnake() {
		switch (this.squareNum) {
			case 16:
				this.setSquareNum(6);
				return true;
			case 48:
				this.setSquareNum(30);
				return true;
			case 64:
				this.setSquareNum(60);
				return true;
			case 79:
				this.setSquareNum(19);
				return true;
			case 93:
				this.setSquareNum(68);
				return true;
			case 95:
				this.setSquareNum(24);
				return true;
			case 97:
				this.setSquareNum(76);
				return true;
			case 98:
				this.setSquareNum(78);
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * This method checks if the player who called the method landed on another player and assigns 
	 * the square/tile number of this other player to 0, thus kicking him off the board.
	 */
	public void checkIfLandedOnPlayer(Player otherPlayer[]) {
		for (int i = 0; i < otherPlayer.length; i++) {
			if (this.squareNum == otherPlayer[i].squareNum && this.rank != otherPlayer[i].rank){
				otherPlayer[i].setSquareNum(0);
				System.out.printf("Sorry %s, %s landed on your square and knocked you clean off the board%n", otherPlayer[i].name, this.name);
			}
		}
	}
}
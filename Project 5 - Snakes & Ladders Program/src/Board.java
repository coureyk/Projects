/**
 * The Board class implements methods to move players across the gameboard and display results to the user.
 * @author Kevin Courey
 *
 */

public class Board {
	
	/**
	 * Stores the length of the gameboard.
	 */
	private static int length;
	
	/**
	 * Stores the width of the gameboard.
	 */
	private static int width;
	
	/**
	 * Stores the contents of the gameboard into a two dimensional array.
	 */
	private String contents[][];
	
	/**
	 * This method constructs a board with a default length, width and content storage size.
	 */
	public Board() {
		Board.length = 0;
		Board.width = 0;
		this.contents = null;
	}
	
	/**
	 * This method constructs a board with a specified length, width and content storage size.
	 * @param length The length of the gameboard.
	 * @param width The width of the gameboard.
	 */
	public Board(int length, int width) {
		Board.length = length;
		Board.width = width;
		this.contents = new String[length][width];
	}
	
	/**
	 * This method retrieves the length of the gameboard.
	 * @return The length of the gameboard.
	 */
	public static int getLength() {
		return Board.length;
	}
	
	/**
	 * This method retrieves the width of the gameboard.
	 * @return The width of the gameboard.
	 */
	public static int getWidth() {
		return Board.width;
	}
	
	/**
	 * This method retrieves the contents of the square at coordinates (x, y) on the gameboard that invokes this method.
	 * @param x The x-coordinate on the gameboard.
	 * @param y The y-coordinate on the gameboard.
	 * @return The content located at position (x, y) on the gameboard that invokes this method.
	 */
	public String getContents(int x, int y) {
		return this.contents[x][y];
	}
	
	/**
	 * This method sets the length of the gameboard.
	 * @param length The length of the gameboard.
	 */
	public static void setLength(int length) {
		Board.length = length;
	}
	
	/**
	 * This method sets the width of the gameboard.
	 * @param width The width of the gameboard.
	 */
	public static void setWidth(int width) {
		Board.width = width;
	}
	
	/**
	 * This method sets the contents of the square at coordinates (x, y) on the gameboard that invokes this method.
	 * @param x The x-coordinate on the gameboard.
	 * @param y The y-coordinate on the gameboard.
	 * @param contents The content to be placed at location (x, y) on the gameboard that invokes this method. 
	 */
	public void setContents(int x, int y, String contents) {
		this.contents[x][y] = contents;
	}
	
	/**
	 * This method sets snake heads and tails on the gameboard.
	 */
	public void placeSnakes() {
		this.setContents(1, 4, "SA-16");
		this.setContents(0, 5, "SA-6");
		
		this.setContents(4, 7, "SB-48");
		this.setContents(2, 9, "SB-30");
		
		this.setContents(6, 3, "SD-64");
		this.setContents(5, 0, "SD-60");
		
		this.setContents(7, 1, "SC-79");
		this.setContents(1, 1, "SC-19");
		
		this.setContents(9, 7, "SE-93");
		this.setContents(6, 7, "SE-68");
		
		this.setContents(9, 5, "SF-95");
		this.setContents(2, 3, "SF-24");
		
		this.setContents(9, 3, "SG-97");
		this.setContents(7, 4, "SG-76");
		
		this.setContents(9, 2, "SH-98");
		this.setContents(7, 2, "SH-78");
	}
	
	/**
	 * This method sets ladder heads and bases on the gameboard.
	 */
	public void placeLadders() {
		this.setContents(0, 0, "LA-1");
		this.setContents(3, 2, "LA-38");
		
		this.setContents(0, 3, "LB-4");
		this.setContents(1, 6, "LB-14");
		
		this.setContents(0, 8, "LC-9");
		this.setContents(3, 9, "LC-31");
		
		this.setContents(2, 0, "LD-21");
		this.setContents(4, 1, "LD-42");
		
		this.setContents(2, 7, "LE-28");
		this.setContents(8, 3, "LE-84");
		
		this.setContents(3, 4, "LF-36");
		this.setContents(4, 3, "LF-44");
		
		this.setContents(5, 9, "LG-51");
		this.setContents(6, 6, "LG-67");
		
		this.setContents(7, 9, "LI-71");
		this.setContents(9, 9, "LI-91");
		
		this.setContents(7, 0, "LH-80");
		this.setContents(9, 0, "LH-100");
	}
	
	/**
	 * This method fills the gameboard (from top to bottom) with each square/tile's respective number.
	 * Once completed, replace the contents that occupy the snakes and ladders squares and with their approriate snake and ladder identifiers.
	 */
	public void fillBoard() {
		for (int i = getLength() - 1; i >= 0; i--) {
			for (int j = 0; j < getWidth(); j++) {
				if (i % 2 == 0) {
					this.setContents(i, j, Integer.toString((j + 1) + (i * 10)));
				} else {
					this.setContents(i, 9-j, Integer.toString((j + 1) + (i * 10)));
				}
			}
		}
		this.placeSnakes();
		this.placeLadders();
	}
	
	/**
	 * This method displays the contents of two boards to the user. One board contains the numbers, as well as the snake and ladder identifiers.
	 * The second board contains the player psoitions.
	 * @param numBoard The board which contains the square numbers, as well as the snake and ladder identifiers.
	 * @param gameBoard The board which contains the player locations.
	 */
	public static void displayBoard(Board numBoard, Board gameBoard) {
		for (int i = getLength() - 1; i >= 0; i--) {
			System.out.println("+---------+---------+---------+---------+---------+---------+---------+---------+---------+---------+");
			for (int j = 0; j < getWidth(); j++) {
				System.out.print("|");
				System.out.printf("%8s ", numBoard.getContents(i, j));
			}
			System.out.println("|");
			System.out.println("|         |         |         |         |         |         |         |         |         |         |");
			for (int j = 0; j < getWidth(); j++) {
				System.out.print("|");
				if (gameBoard.getContents(i, j) == null) {
					System.out.print("         ");
				} else {
					System.out.printf(" %-8s", gameBoard.getContents(i, j));
				}
			}
			System.out.println("|");
		}
		System.out.println("+---------+---------+---------+---------+---------+---------+---------+---------+---------+---------+");
	} 
	
	/**
	 * This method sets a player to their appropriate location on the gameboard by using their x and y coordinates, calculated from within the player class.
	 * @param player The player being moved across the gameboard.
	 */
	public void movePlayer(Player player) {
		this.setContents(player.getXCoordinate(), player.getYCoordinate(), player.getName());
	}
	
	/**
	 * This method replaces the player's previous position with null.
	 * @param player The player being moved across the gameboard.
	 */
	public void removePlayer(Player player) {
		this.setContents(player.getXCoordinate(), player.getYCoordinate(), null);
	}
}
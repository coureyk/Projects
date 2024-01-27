//-------------------------------------------------------
//Assignment 3
//Written by: Kevin Courey 40245966
//For COMP 248 Section S – Fall 2022
//--------------------------------------------------------
import java.util.Scanner;

/*
* This program initially takes an input from the user in the form {id_1},{name1,};{id_2},{name_2};... and then
* offers the user 5 options to choose from (display candidates, vote for candidates, add candidates, display election results, end program) and
* executes the selected option. It continues to offer these options, while saving all inputs given by the user, until the user decides to end
* the program.  
*/
public class A3 {

	public static void main(String[] args) {
		System.out.println("Welcome to the Simple Electronic Voting System (SEVS):");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		/*
		 * This line prompts the user to enter all candidate's particulars
		 * in the form {id_1},{name1,};{id_2},{name_2};....
		 */
		System.out.printf("%nPlease enter a String collection of electoral candidates below:%n");
		
		//Creates a scanner object designed to read user input.
		Scanner in = new Scanner(System.in);		
		
		/*
		 * This method finds and returns the next token from the user input.  
		 * If the token can be translated into a valid string value, 
		 * then it is stored into variable "input" and the scanner advances
		 * to the next token.
		 */
		String input = in.nextLine();
		
		/*
		 * The set array is designed to store all SETS of candidate particulars from the string variable "input" and
		 * its purpose is to determine how many candidates there are. 
		 * For example, if set[] = {"id0, name0","id1, name1", ...} then set[X] == "idX, nameX". Find the value of X and you find the number of candidates there are.
		 * 
		 * The element array is designed to store THE FIRST TWO ELEMENTS (id and name) of candidate particulars from the string variable "input" and
		 * its purpose is to separate these particulars so that each one can be modified (trimmed and converted to upper case) individually, in the future.
		 */
		String set[] = input.split(";");
		String element[] = input.split("[,;]");
		
		/*
		 * The tally array is initialized to the number of candidates so that each candidate can receive a vote.
		 * Also, each slot within the array is initialized to zero so that each candidate starts off with zero votes and so that when the user 
		 * casts his vote, it will be added to the desired candidate's tally as expected.
		 */
		int tally[] = new int [set.length];
		for (int i = 0; i < set.length; i++) {
			tally[i] = 0;
		}
		
		/*
		 * The 2D array "candidate" is set to data type string since it will store values of type string and its number of sets (or rows) is initialized to
		 * to the number of candidates entered by the user, and the number of elements contained within those sets (or columns) is initialized to the 
		 * number of particulars within each set (4), since each set of candidates should contain an id, name, tally, and position.
		 * Since determining the position is   
		 */
		String candidate[][] = new String[set.length][4];
		
		/*
		 * The variable "count" will be used to keep track of which element (from the variable "input") we are currently on in the following for loop.
		 * 
		 * The for loop loops through every element from the variable "input" and stores its modified version into its respective slot WITHIN its respective set or candidate.
		 * Once these elements (id and name) have been added, the program breaks from the inner for loop and stores the candidate's tally in index 2 of that candidate's set, respectively.
		 * 
		 * The process repeats until there are no candidates left to loop through.
		 */
		int count = 0;
		for (int a = 0; a < candidate.length; a++) {
			for (int b = 0; b < 2; b++) {
				candidate[a][b] = element[count].trim().toUpperCase();
				count++;
			}
			candidate[a][2] = Integer.toString(tally[a]);
		}
		
		System.out.println("********************************");
		System.out.println("| Code >> Description          |");
		System.out.println("********************************");
		System.out.println("|  1   >> Display candidates   |");
		System.out.println("|  2   >> Vote a candidate     |");
		System.out.println("|  3   >> Add new candidate(s) |");
		System.out.println("|  4   >> Display results      |");
		System.out.println("|  0   >> End SEVS             |");
		System.out.println("********************************");
		
		//Prompts user to enter code from 0-4.
		System.out.printf("%nEnter a code, from the aforementioned, that corresponds to your task: ");
		
		//The variable "code" is initiated to type String,so that the user may enter a non-integer value without terminating the program.
		String code;
		
		//Important variables for later.
		boolean end = false;
		boolean skipAutoPrompt = true;
		
		//Loop this this part of the program until variable end == true (this will occur when the user enters "0" as the code).
		while (!end) {
			
			//If this is the first go-around in the while loop, skip the auto-prompt, otherwise, display it everytime the loop resets.
			if (skipAutoPrompt) {
				skipAutoPrompt = false;
			} else {
				System.out.printf("%nKindly continue by entering a valid code, from the aforementioned, which corresponds to your task: ");
			}
			
			/*
			 * This method finds and returns the next token from the user input.  
			 * If the token can be translated into a valid string value, 
			 * then it is stored into variable "code" and the scanner advances
			 * to the next token.
			 */
			code = in.nextLine();
			
			/*
			 * If the value of code is equal to "1", loop through each set of candidates within the candidate array and display the respective id and
			 * name values using the following format.
			 */
			if (code.equals("1")) {
				System.out.println("****************************************");
				System.out.println("| ID >> Candidate's Name               |");
				System.out.println("****************************************");
				for (int i = 0; i < candidate.length; i++) {
					System.out.print("| ");
					System.out.printf("%-3s", candidate[i][0]);
					System.out.print(">> ");
					System.out.printf("%-31s", candidate[i][1]);
					System.out.println("|");
				}
				System.out.println("****************************************");
			} 
			
			/*
			 * If the value of code is equal to "2", prompt the user to enter an ID that corresponds to one of the candidates, then force him into a while loop
			 * that will only break when his input matches one of the candidate IDs.
			 * 
			 * Variable "ID" is of type String, so that if the user decides to enter a non-integer value, he will be met with the same custom error message
			 * he would receive, had he entered an invalid ID. (I know this was not specified in the assignment instructions, but I thought it would make a nice
			 * addition to the SEVS.
			 * 
			 * When ID matches one of the candidate IDs, increase the tally (belonging to the same set as the candidate) by 1, then convert that value into a String and
			 * store it in the candidate array at the appropriate slot and finally, break from the while loop.
			 */
			else if (code.equals("2")) {
				boolean match = false;
				System.out.print("\n" + "Please enter the ID of the candidate you wish to vote for: ");
				while (!match) {
					String ID = in.nextLine();
					for (int i = 0; i < set.length; i++) {
						if (ID.equals(candidate[i][0])) {
							tally[i]++;
							candidate[i][2] = Integer.toString(tally[i]);
							System.out.println("Your ballot has been successfully casted for: "+ (candidate[i][1]) + " bearing candidate ID: " + (candidate[i][0]));
							match = true;
							break;
						}
					}
					if (!match) {
						System.out.print("Kindly continue by entering a valid ID: ");
					} 
				}
				
			} 
			
			/*
			 * If the value of code is equal to "3" prompt the user to enter a new string of candidate particulars, then take these particulars and store them
			 * into the variable "newInput", along with the previous candidate particulars entered by the user (separate these inputs with a semi-colon
			 * so that the number of new candidate sets and elements will be accurate.
			 * 
			 * Create an array called newTally, which will store the previous tallies in their appropriate slots and initialize the new slots corresponding with
			 * the new candidates to zero (since they have not received any votes at the moment).
			 * 
			 * Create an array called newCandidate and store values (both new and old) into it using the same methods as we did in the beginning of this program.
			 *
			 * Reinitialize all old values to the current values so that they can be accessed by other parts of the program..
			 */
			else if (code.equals("3")) {
				System.out.println("\n" + "Please enter a String collection of the NEW electoral candidates below: ");
				String newInput = input + ";" + in.nextLine();
				String newSet[] = newInput.split(";");
				String newElement[] = newInput.split("[,;]");
				
				int newTally[] = new int[newSet.length];
				for (int i = 0; i < newSet.length; i++) {
					if (i >= set.length) {
						newTally[i] = 0;
					} else {
						newTally[i] = tally[i];
					}
				}				

				String newCandidate[][] = new String[(newSet.length)][4];
				int newCount = 0;
				for (int a = 0; a < newSet.length; a++) {
					for (int b = 0; b < 2; b++) {
						newCandidate[a][b] = newElement[newCount].trim().toUpperCase();
						newCount++;
					}
					newCandidate[a][2] = Integer.toString(newTally[a]);
				}
				candidate = newCandidate;
				input = newInput;
				set = newSet;
				element = newElement;
				tally = newTally;

				System.out.print("Successfully added a NEW set of electoral candidates to the Simple Electronic Voting System (SEVS)." + "\n");
			}

			/*
			 * The array of candidates is cloned, so that the program can rank the candidates later (using the "bubble method") without affecting the original version.
			 * 
			 * The rank array is initialized to the number of candidates so that each candidate can receive a ranking.
			 * 
			 * The third index (the rank or position) belonging to the first candidate in the ordered array is initialized to 1, since the first candidate's tally in this
			 * array will be the highest compared to the rest of the candidates (this we can know for certain, because the bubble method sorts the tallies from highest to loweest values).
			 * Since the following candidate tallies can only be equal to or less than the previous one, I created an if statement that checks for these conditions, and changes the candidate's
			 * rank accordingly.
			 * 
			 * Once the ranks have been determined, we can display all candidates and their particulars to the console. The display is formatted in such a way
			 * as to respect the sample outputs given in the assignment instructions.
			 */
			else if (code.equals("4")) {
				
				String correctOrder[][] = candidate.clone();
				
				for (int i = 0; i < set.length; i++) {
					for (int j = 1; j < (set.length - i); j++) {
						if (Integer.parseInt(correctOrder[j-1][2]) < Integer.parseInt(correctOrder[j][2])) {
							String temp[] = (correctOrder[j-1]);
							correctOrder[j-1] = correctOrder[j];
							correctOrder[j] = temp;
						}
					}
				}
				
				int rank[] = new int [set.length];
				
				int position = 1;
				for (int i = 1; i < set.length; i++) {
					if (i - 1 == 0) {
						rank[i - 1] = 1;
						correctOrder[i - 1][3] = Integer.toString(rank[i - 1]);
					}
					if (Integer.parseInt(correctOrder[i-1][2]) > Integer.parseInt(correctOrder[i][2])) {
						position++;
					}
					rank[i] = position;
					correctOrder[i][3] = Integer.toString(rank[i]);
				}
				
				System.out.println("******************************************************************");
				System.out.println("| Position | Votes/Ballots | ID | Candidate's Name               |");
				System.out.println("******************************************************************");
				for (int i = 0; i < correctOrder.length; i++) {
					System.out.print("|");
					System.out.printf("%9s", correctOrder[i][3]);
					System.out.print(" |");
					System.out.printf("%14s", correctOrder[i][2]);
					System.out.print(" |");
					System.out.printf("%3s", correctOrder[i][0]);
					System.out.print(" | ");
					System.out.printf("%-31s", correctOrder[i][1]);
					System.out.println("|");
				}
				System.out.println("******************************************************************");
			
			} 
			//Once the user inputs 0 as his code, end becomes true and and thus causes this while loop to break upon relooping.
			else if (code.equals("0")) {
				end = true;
			} 
			//If the user does not enter a valid input (0-4), reloop.
			else {
				continue;
			}
		}
		System.out.print("\n" + "Thank you for using our Simple Electronic Voting System (SEVS).");
		in.close();
	}

}

//import java.lang.System.*;
import java.util.Scanner;

/* 
 * This program finds the best path for a frog to take along a path of rocks.
 * The frog can move ahead either 1 or 2 rocks at a time.
 */
public class frogb {
// read two integers separated by space and output their product
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the number of rocks followed by their respective heights simulatenously: ");
		do {
			// Reading the input
			int N = scan.nextInt();
			int[] H = new int[N];
			int[] S = new int [N];
			
			for(int i=0;i<N;++i) {
				H[i] = scan.nextInt();
			}
			
			int currentRock = N-1;
			int energyOutput = 0;
			boolean init = true;
			
			// Calling the function that solves the problem
			//long start = System.nanoTime();
			int e = findPath(S, H, currentRock, energyOutput, init);
			//long end = System.nanoTime();
			//System.out.printf("Time Spent: %d%n", (end - start));
			
			// writing the output
			System.out.println("Energy Spent:\t" + e);
			System.out.print("Path Taken:\t");
			for(int i=0;i<N;++i) {
				if(i>0)
					System.out.print(" ");
				System.out.print(S[i]);
				}
			System.out.println();
			} while(!scan.hasNext());
		scan.close();
		}

/*
Need to implement your solution in the findPath function as specified in the problem specifications.

In this file you are only allowed to change the body of the findPath function (i.e. the implementation) and
the signature of the function only by adding new input arguments as long as the space of the input
variables that you add are O(1).

For example you can add an int and a String input variable, but not a array of length n of int

If you change the signature of the findPath function you are allowed to minimally change also the line that calls it
in the main function in order for the program to compile and run

*/

	public static int findPath(int [] S, int []H, int currentRock, int energyOutput, boolean init) {
		if (init) {			
			if (H.length == 0) { //Check if array contains any elements. If not, return 0 and terminate. Else, continue.
				return 0;
			} else if (H.length == 1) {
				S[0] = 1;
				return 0;
			} else if (H.length == 2) {
				S[0] = 1;
				S[H.length - 1] = 1;
				return Math.abs(H[0] - H[1]);
			} else {
				S[H.length - 1] = 1;
				init = false;
			}
		}

		if (currentRock == H.length - 1) { //If frog is on last rock
			return findPath(S, H, currentRock - 1, energyOutput, init);
		} else if (currentRock == H.length - 2) { //If frog is 1 position away from last rock.
			S[currentRock] = 1;
			return findPath(S, H, currentRock - 1, energyOutput, init);
		} else { //If frog is 2 or more positions away from last rock.		
			int i = currentRock;
			
			S[currentRock] = 1;
			int choice1 = 0;
			while (i < H.length - 1) { //work your way from currentRock to finalRock (taking a leap of 1, followed by the most efficient path), and keep track of energySpent.
				choice1 += Math.abs(H[i] - Math.abs(H[i + S[i]]));
				i += S[i];
			}
			
			i = currentRock; //reset i
			S[i] = 2;
			int choice2 = 0;
			while (i < H.length - 1) { //work your way from currentRock to finalRock (taking a leap of 2, followed by the most efficient path), and keep track of energySpent.
				choice2 += Math.abs(H[i] - Math.abs(H[i + S[i]]));
				i += S[i];
			}
			
			if (currentRock != 0) { //Compare choice1 and choice2 on currentRock, replace S[currentRock] with the better of the two, then repeat the process until we reach startingRock.
				if (choice2 <= choice1) {
					S[currentRock] = 2;
				} else {
					S[currentRock] = 1;
				}
				return findPath(S, H, currentRock - 1, energyOutput, init);
			} else {
				if (choice2 <= choice1) { //Modify array S, so that it begins with [1,0,...], then fill in the rest of the array with the appropriate sequence of 1's and 0's.
					S[0] = 1;
					S[1] = 0;
					for (i = 2; i < H.length; i++) { //if the rock we landed on contains a 1, make no changes and continue to next rock. 				
						if (S[i] == 2) { //if the rock we landed on contains a 2, change S[i] to 1, S[i+1] to 0, and increase i by 2.
							S[i] = 1;
							S[i+1] = 0;
							i++;
						}
					}
					return choice2;
				} else { //Modify array S, so that it begins with [1,...], then fill in the rest of the array with the appropriate sequence of 1's and 0's.
					S[0] = 1;
					for (i = 1; i < H.length; i++) { //if the rock we landed on contains a 2, change S[i] to 1, S[i+1] to 0, and increase i by 2.				
						if (S[i] == 2) {
							S[i] = 1;
							S[i+1] = 0;
							i++;
						}
					}
					return choice1;
				}
			}
		} 
	}
}

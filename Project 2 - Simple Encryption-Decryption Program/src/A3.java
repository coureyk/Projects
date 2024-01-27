//-------------------------------------------------------
//Assignment 3
//Written by: Kevin Courey 40245966
//For COMP 248 Section S – Fall 2022
//--------------------------------------------------------
import java.util.Scanner; //This allows me to use the Scanner class.

/*
* This program is designed to take a line of text from a user (known as the ciphertext)
* and encrypts it into a new line that
* a) Removes leading and trailing spaces
* and b) Encrypts the line into a new message (one that swaps two subsequent characters until it reaches the end of the line).
*/

public class A3 {

	public static void main(String[] args) {
		
		System.out.println("Welcome to the 3D-Space Encryption-Decryption Program:");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.printf("%nPlease enter your plain text below:%n");
		
		//Creates a scanner object designed to read user input.
		Scanner in = new Scanner(System.in);
		
		/*
		 * This method finds and returns the next token from the user input.  
		 * If the token can be translated into a valid string value, 
		 * then it is stored into variable "input" and the scanner advances
		 * to the next token.
		 */
		String input = in.nextLine();
		
		//Closes the scanner method, thus preventing the possibility of semantic errors in the future.
		in.close();
		
		/*
		 * Creates an array which will store the characters from the string variable "input" minus any leading/trailing spaces.
		 * The array is initialized to the length of "input" so that the maximum number of characters it can store will always equal
		 * the total number of characters entered by the user.
		 */
		char[] ciphertext = new char[input.length()];
		
		//These positions will be used to mark the beginning and end of the valid user input, respectively.
		int position1 = 0;
		int position2 = input.length() - 1;
		
		/*
		 * Checks user input up to and including the last character.
		 * Position1 increments until it locates the INDEX of the first valid character.
		 * If no valid characters are found, position1 will exceed the value of position2 by 1 (this is important for later) and end the while loop.
		 * If a valid character is found, its location will be represented by position1 and subsequently, position2 will decrement by 1 until
		 * it locates the INDEX of the last valid character from the user input.
		 * Note: If the user enters nothing or only enters spaces, position1 will exceed the value of position2 by 1.
		 */
		while (position1 <= position2) {
			if (input.charAt(position1) == ' ') {
				position1++;
			} else if (input.charAt(position2) == ' ') {
				position2--;
			} else {
				break;
			}
		}
				
		/*
		 * Stores every character belonging to the valid user input (from position1 up to and including position2)
		 * into its respective slot in the ciphertext array.
		 * Instead of storing the characters from position1 to position2, I chose to store them from pos1 (a copy of position1) to position2 so that the
		 * value of the first valid character from the user input would be preserved.
		 */
		int pos1 = position1;
		for (int i = 0; pos1 <= position2; i++) {
			ciphertext[i] = input.charAt(pos1);
			pos1++;
		}
		
		System.out.printf("%nKindly find below your encrypted text output:%n");
		
		/*
		 * If Statement: As mentioned before, the only time an error will occur is when the value of position1 exceeds the value of position2.
		 * 
		 * Else if Statement: Now that we have checked for errors, we can safely check the number of characters within the valid user input.
		 * When there are an even number of characters (i.e. when there are an odd number of indices),
		 * this statement will swap the first two characters, print the result on the same line of the console and then repeat this process until position1 reaches
		 * position2 (the end of valid user input).
		 * 
		 * Else Statement: If the code reaches this point, then we have determined that the user input contains an odd number of valid characters.
		 * This statement starts off by printing the first valid character, then swaps the two characters that precede it,
		 * prints them on the same line as the first valid character and repeats until position1 reaches position2.
		 */
		if (position1 > position2) {
			System.out.println("Kindly continue by entering a valid code, from the aforementioned, which corresponds to your task:");
		} else if ((position2 - position1) % 2 != 0) {
			int a = 0;
			int b = 1;
			while (position1 < position2) {
				char tempA = ciphertext[a];
				ciphertext[a] = ciphertext[b];
				ciphertext[b] = tempA;
				System.out.printf("%s%s", ciphertext[a], ciphertext[b]);
				a += 2;
				b += 2;
				position1 += 2;
			}
		} else {
			int a = 1;
			int b = 2;
			System.out.print(ciphertext[0]);
			while ((position1 + 1) < position2) {
				char tempA = ciphertext[a];
				ciphertext[a] = ciphertext[b];
				ciphertext[b] = tempA;
				System.out.printf("%s%s", ciphertext[a], ciphertext[b]);
				a += 2;
				b += 2;
				position1 += 2;
			}
		}
		System.out.printf("%n%nThank you for your contribution to this Space Project.");
	}
}

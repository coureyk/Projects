//-----------------------------------------------------
//Assignment 4
//Part 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.FileOutputStream;

/**
* The Driver class is designed to invoke the methods from the BookList/ArrayList classes in order to display and compare them. 
* @author Kevin Courey
*
*/
public class Driver {
	
	/**
	 * The validateRecord method takes in a String, determines whether or not it is a valid book record, then returns a new Book object that contains the contents of this String if it is in fact a valid book record. 
	 * @param record A String
	 * @return A new Book that contains the contents of record
	 */
	public static Book validateRecord(String record) {
		Pattern pattern = Pattern.compile("^(\".*\"),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*)$");
		Matcher matcher = pattern.matcher(record);
		if (!matcher.find()) {
			throw new NumberFormatException();
		}
		
		double newPrice = 0.0;
		long newISBN = 0L;
		int newYear = 0;
		
		//Validating Price, ISBN and Year
		newPrice = Double.parseDouble(matcher.group(3).trim());
		newISBN = Long.parseLong(matcher.group(4).trim());
		newYear = Integer.parseInt(matcher.group(6).trim());

		if (newPrice < 0) {
			throw new NumberFormatException();
		}
		if (newISBN < 0) {
			throw new NumberFormatException();
		}
		if (newYear > 2023) {
			throw new NumberFormatException();
		}
		
		//If no exception is thrown/caught, return the a deep copy of the valid book.
		return new Book(matcher.group(1), matcher.group(2), newPrice, newISBN, matcher.group(5), newYear);
	}

	/**
	 * The main method displays a welcome message to the user and prompts him to enter a command number that will modify a BookList.
	 * 
	 * @param args The Circular BookList vs ArrayList program
	 */
	public static void main(String[] args) {
		ArrayList<Book> arrLst = new ArrayList<Book>();
		BookList BkList = new BookList();

		String inputFile = ".\\src\\Database\\Books.txt";
		String outputFile = ".\\src\\Database\\Update_Books.txt";
		String errFile = ".\\src\\Database\\YearErr.txt";
		
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Read recods from inputFile and seperate valid from invalid records. !MADE SOME ADJUSTMENTS SINCE SUBMISSION!
		while (sc.hasNextLine()) {
			Pattern pattern = Pattern.compile("^(\".*\"),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*)$");
			Matcher matcher = pattern.matcher(sc.nextLine());
			if (matcher.find()) {
				Book record = new Book(matcher.group(1), matcher.group(2), Double.parseDouble(matcher.group(3)), Long.parseLong(matcher.group(4)), matcher.group(5), Integer.parseInt(matcher.group(6)));
				if (Integer.parseInt(matcher.group(6)) > 2023) { //checking year
					arrLst.add(record);
				} else {
					BkList.addToEnd(record);
				}
			}
		}

		/*
		 * Read all the objects from the ArrayList and store them in a file
		 * called YearErr.txt.
		 */
		PrintStream errStream = null;
		if (!arrLst.isEmpty()) {
			System.out.printf("%s has been created.", errFile.substring(19));
			try {
				errStream = new PrintStream(new FileOutputStream(errFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			for (Book record : arrLst) {
				errStream.println(record);
			}
			errStream.close();
		}
		
		System.out.printf("%nHere are the contents of your Circular Book List:");
		System.out.printf("%n+++++++++++++++++++++++++++++++++++++++++++++++++%n");
		BkList.displayContent();

		int commandNum = 0;
		boolean validEntry = false;
		sc = new Scanner(System.in);
		
		while (commandNum != 8) {
			
			//Display a menu to the user
			System.out.print("Tell me what you want to do. Let's Chat since this is trending now! Here are the options:");
			System.out.printf("%n\t1) Give me a year # and I would extract all records of that year and store them in a file for that year.");
			System.out.printf("%n\t2) Ask me to delete all consecutive repeated records.");
			System.out.printf("%n\t3) Give me an author name and I will create a new list with the records of this author and display them.");
			System.out.printf("%n\t4) Give me an ISBN number and a Book object, and I will insert a Node with the book before the record with this ISBN.");
			System.out.printf("%n\t5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!");
			System.out.printf("%n\t6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!");
			System.out.printf("%n\t7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called %s", outputFile);
			System.out.printf("%n\t8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");
			System.out.printf("%nEnter your selection: ");
			
			commandNum = 0;
			while (!validEntry) {
				try {
					commandNum = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {

				} finally {
					if (commandNum >= 1 && commandNum <= 8) {
						System.out.println();
						break;
					}
					System.out.print("Invalid entry. Please enter a number between 1 and 8: ");
				}
			}
			
			//Sort records based on year
			if (commandNum == 1) {
				int year = 2024;
				System.out.print("Enter year: ");
				while (!validEntry) {
					try {
						year = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {

					} finally {
						if (year <= 2023) {
							BkList.storeRecordsByYear(year);
							break;
						}
						System.out.print("Invalid year. Please try again: ");
					}
				}
			}
			
			//Delete Consecutive Records
			if (commandNum == 2) {
				boolean a = BkList.delConsecutiveRepeatedRecords();
				if (a == true) {
					System.out.println("Here are the contents of your new list: ");
					BkList.displayContent();
				}
			}
			
			//Extract authors
			if (commandNum == 3) {
				System.out.print("Enter author name(s): ");
				String input = sc.nextLine();
				while (input.trim().length() == 0) {
					System.out.print("Invalid entry. Please try again: ");
					input = sc.nextLine();
				}
				BookList authorList = BkList.extractAuthList(input);
				if (authorList != null) {
					authorList.displayContent();
				}
			}
			
			//Insert Before
			if (commandNum == 4) {
				long isbn = 0L;
				Book newRecord = null;
				
				System.out.print("Enter ISBN: ");
				while (!validEntry) {
					try {
						isbn = Long.parseLong(sc.nextLine());
					} catch (NumberFormatException e) {

					} finally {
						if (isbn != 0) {
							break;
						}
						System.out.print("Invalid ISBN. Please try again: ");
					}
				}
				
				System.out.println("Book record format: \"Title\", Author Name, Price, ISBN, Genre, Year");				
				System.out.printf("Please enter a Book record: ");

				while (!validEntry) {
					try {
						newRecord = validateRecord(sc.nextLine());
						break;
					} catch (InputMismatchException e) {
						System.out.print("Invalid record. Please try again: ");
						continue;
					} catch (NumberFormatException e) {
						System.out.print("Invalid record. Please try again: ");
						continue;
					}
				}
				BkList.insertBefore(isbn, newRecord);
			}
			
			//Insert Between
			if (commandNum == 5) {
				long isbnArr[] = new long[2];
				Book newRecord = null;
				
				for (int i = 0; i < 2; i++) {
					System.out.printf("Enter ISBN #%d: ", i+1);
					while (!validEntry) {
						try {
							isbnArr[i] = Long.parseLong(sc.nextLine());
							break;
						} catch (NumberFormatException e) {
							System.out.print("Invalid ISBN. Please try again: ");
						}
					}
				}
				
				System.out.println("Book record format: \"Title\", Author Name, Price, ISBN, Genre, Year");				
				System.out.printf("Please enter a Book record: ");

				while (!validEntry) {
					try {
						newRecord = validateRecord(sc.nextLine());
						break;
					} catch (InputMismatchException e) {
						System.out.print("Invalid record. Please try again: ");
						continue;
					} catch (NumberFormatException e) {
						System.out.print("Invalid record. Please try again: ");
						continue;
					}
				}
				BkList.insertBetween(isbnArr[0], isbnArr[1], newRecord);
			}
			
			//Swap
			if (commandNum == 6) {
				long isbnArr[] = new long[2];
				
				for (int i = 0; i < 2; i++) {
					System.out.printf("Enter ISBN #%d: ", i+1);
					while (!validEntry) {
						try {
							isbnArr[i] = Long.parseLong(sc.nextLine());
							break;
						} catch (NumberFormatException e) {
							System.out.print("Invalid ISBN. Please try again: ");
						}
					}
				}
				BkList.swap(isbnArr[0], isbnArr[1]);
			}
			
			if (commandNum == 7) {
				BkList.commit();
			}
		}
		System.out.print("Thank you for using my Circular List program. Have a wonderful day!");
	}
}
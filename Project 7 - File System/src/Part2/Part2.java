package Part2;

//-----------------------------------------------------
//Assignment 3
//Part: 2
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import Part1.*;
import java.util.regex.*;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Part2 {
	
	/**
	 * The purpose of this method is to seperate semantically valid book records from the semantically invalid book records.
	 * @param sc A scanner object capable of reading from a specified text file.
	 * @return A deep copy of a valid book.
	 * @throws BadIsbn10Exception This exception is thrown when a 10-digit ISBN number is invalid.
	 * @throws BadIsbn13Exception This exception is thrown when a 13-digit ISBN number is invalid.
	 * @throws BadIsbnException This exception is thrown when an ISBN number is generally invalid.
	 * @throws BadPriceException This exception is thrown when the price is invalid.
	 * @throws BadYearException This exception is thrown when the year is invalid.
	 */
	public static Book checkForSemanticErrors(Scanner sc) throws BadIsbn10Exception, BadIsbn13Exception, BadIsbnException, BadPriceException, BadYearException {
		String record = sc.nextLine();
		
		//Using regex to validate book records.
		Pattern pattern = Pattern.compile("^(\".*\"|[^,]*),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*)$");
		Matcher matcher = pattern.matcher(record);
		matcher.find();
		
		String title = matcher.group(1); //.trim();
		String authors = matcher.group(2);
		double price = 0;
		String isbn = (matcher.group(4));
		String genre = matcher.group(5);
		int year = 0;

		//Validating Price
		try {
			price = Double.parseDouble(matcher.group(3));
			
			if (price < 0) {
				throw new BadPriceException(record);
			}
		} catch (InputMismatchException e) {
			throw new BadPriceException(record); 
		}
		
		//Validating ISBN
		if (Pattern.matches("^([0-9]){9}([0-9]|X)$", isbn)) {
			int sum = 0;
			
			for (int j = 0; j < 10; j++) {
				int value = 0;
				if (isbn.charAt(j) == 'X') {
					value = 10;
				} else {
					value = (10 - j) * (Integer.parseInt(isbn.charAt(j) + ""));
				}
				sum += value;
			}
			
			if (sum % 11 != 0) {
				throw new BadIsbn10Exception(record);
			}
		} else if (Pattern.matches("^([0-9]){13}$", isbn)) {
			int sum = 0;
			
			for (int j = 0; j < 13; j++) {
				if (j % 2 == 0) {
					sum += Integer.parseInt(isbn.charAt(j) + "");
				} else {
					sum += 3 * (Integer.parseInt(isbn.charAt(j) + ""));		
				}
			}
			
			if (sum % 10 != 0) {
				throw new BadIsbn13Exception(record);
			}
		} else {
			throw new BadIsbnException(record);
		}
				
		//Validating Year
		try {
			year = Integer.parseInt(matcher.group(6));
			
			if (year < 1995 || year > 2010) {
				throw new BadYearException(record);
			}
		} catch (InputMismatchException e) {
			throw new BadYearException(record);
		}
				
		//If no exception is thrown, return the a deep copy of the valid book.
		Book validBook = new Book(title, authors, price, isbn, genre, year);
		return (new Book(validBook));
	}
	
	/**
	 * This method creates all necessary input/output streams in order to read and write book records across multiple text/binary files.
	 */
	public static void do_part_2() {
		PrintStream errStream = null;
		try {
			errStream = new PrintStream(new FileOutputStream(".\\src\\Database\\semantic_error_file.txt"));
			System.setErr(errStream);
		} catch (FileNotFoundException e) {
			System.err.println("Output file not found");
		}
		
		//This counter will be used to keep track of which index inside tempArray is next to be occupied.
		int validCount = 0;
		
		//The integer values contained within this array will serve as maximum storage capacities for tempArray.
		int numOfSyntacticallyValidBooksByGenre[] = Part1.getNumOfSyntacticallyValidBooksByGenre();
		
		/*
		 * When total value of errorCount is reached, it will be subtracted from tempArray's maximum index in order
		 * to determine the total number of indeces that are to be occupied by valid Books.
		 */
		int errorCount = 0;
		
		//This for loop allows us to read all 8 .csv.txt files.
		for (int i = 0; i < 8; i++) {
			int numOfBooksInFile = numOfSyntacticallyValidBooksByGenre[i];
			Book tempArray[] = new Book[numOfBooksInFile];
			
			String inputFileName = null;
			String outputFileName = null;
			switch (i) {
			case 0:
				inputFileName = ".\\src\\Database\\Cartoon_Comics.csv.txt";
				outputFileName = ".\\src\\Database\\Cartoon_Comics.csv.ser";
				break;
			case 1:
				inputFileName = ".\\src\\Database\\Hobbies_Collectibles.csv.txt";
				outputFileName = ".\\src\\Database\\Hobbies_Collectibles.csv.ser";
				break;				
			case 2:
				inputFileName = ".\\src\\Database\\Movies_TV_Books.csv.txt";
				outputFileName = ".\\src\\Database\\Movies_TV_Books.csv.ser";
				break;
			case 3:
				inputFileName = ".\\src\\Database\\Music_Radio_Books.csv.txt";
				outputFileName = ".\\src\\Database\\Music_Radio_Books.csv.ser";
				break;
			case 4:
				inputFileName = ".\\src\\Database\\Nostalgia_Eclectic_Books.csv.txt";
				outputFileName = ".\\src\\Database\\Nostalgia_Eclectic_Books.csv.ser";
				break;
			case 5:
				inputFileName = ".\\src\\Database\\Old_Time_Radio_Books.csv.txt";
				outputFileName = ".\\src\\Database\\Old_Time_Radio_Books.csv.ser";
				break;
			case 6:
				inputFileName = ".\\src\\Database\\Sports_Sports_Memorabilia.csv.txt";
				outputFileName = ".\\src\\Database\\Sports_Sports_Memorabilia.csv.ser";
				break;
			case 7:
				inputFileName = ".\\src\\Database\\Trains_Planes_Automobiles.csv.txt";
				outputFileName = ".\\src\\Database\\Trains_Planes_Automobiles.csv.ser";
				break;	
			}
			
			Scanner sc = null;
			ObjectOutputStream oos = null;
			try {
				sc = new Scanner(new FileInputStream(inputFileName));
				oos = new ObjectOutputStream(new FileOutputStream(outputFileName));
			} catch (FileNotFoundException e){
				System.err.println("UH OH");
			} catch (IOException e) {
				System.err.println("OH BOY");
			}
			
			Book record = null;
			
			//This while loop allows to loop through the entire contents of the .csv.txt file we are currently in.
			while (sc.hasNextLine()) {
				try {
					record = checkForSemanticErrors(sc);
				} catch (BadIsbn10Exception e) {
					System.err.println(e.getMessage());
					errorCount++;
				} catch (BadIsbn13Exception e) {
					System.err.println(e.getMessage());
					errorCount++;
				} catch (BadIsbnException e) {
					System.err.println(e.getMessage());
					errorCount++;
				} catch (BadPriceException e) {
					System.err.println(e.getMessage());
					errorCount++;
				} catch (BadYearException e) {
					System.err.println(e.getMessage());
					errorCount++;
				} finally {
					if (record != null) {
						tempArray[validCount] = record; 
						validCount++;
						record = null;
					}
				}
			}
			int numOfValidBooks = numOfBooksInFile - errorCount;
			Book validArray[] = new Book[numOfValidBooks];
			for (int j = 0; j < numOfValidBooks; j++) {
				validArray[j] = tempArray[j];
			}

			try {
				oos.writeObject(validArray);
				oos.close();
			} catch (IOException e) {
				System.err.println("COME ON");
			}
			sc.close();

			validCount = 0;
			errorCount = 0;
		}
		errStream.close();
	}
}

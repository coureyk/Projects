package Part1;

//-----------------------------------------------------
//Assignment 3
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import java.util.Scanner;
import java.io.PrintStream;
import java.util.regex.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Part1 {
	
	/**
	 * This values stored in this array will prove to be useful in Part 2.
	 */
	private static int numOfSyntacticallyValidBooksByGenre[] = new int[8];
	
	/**
	 * This method will allow the values stored in the private array to be accessed in the Part2 class
	 * @return An array containing the number of syntactically valid books by genre.
	 */
	public static int[] getNumOfSyntacticallyValidBooksByGenre() {
		return Part1.numOfSyntacticallyValidBooksByGenre;
	}
	
	/**
	 * The purpose of this method is to seperate syntactically valid books records from syntactically invalid book records.
	 * @param bookReader A scanner object capable of reading from a specified text file.
	 * @return A code letter (which will correspond with an output or csv.txt file) and a valid book record.
	 * @throws TooManyFieldsException This exception is thrown when a book record contains more than 6 fields of information.
	 * @throws TooFewFieldsException This exception is thrown when a book record contains fewer than 6 fields of information.
	 * @throws MissingFieldException This exception is thrown when a book record contains an empty field of information.
	 * @throws UnknownGenreException This exception is thrown when a book record contains an unknown genre type.
	 */
	public static String[] readBookRecords(Scanner bookReader) throws TooManyFieldsException, TooFewFieldsException, MissingFieldException, UnknownGenreException {
		String record = bookReader.nextLine();
		
		//Using regex to check for valid/invalid book records.
		if (Pattern.matches("^(\".*\"|[^,]*)(,[^,]*){0,4}$", record)) {
			throw new TooFewFieldsException(record);
		} else if (Pattern.matches("^(\".*\"|[^,]*),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*)$", record)) {
			if (Pattern.matches("^(\".*\"|[^,]*){0},([^,]*),([^,]*),([^,]*),([^,]*),([^,]*)$", record)) {
				throw new MissingFieldException("title", record);
			} else if (Pattern.matches("^(\".*\"|[^,]*),([^,]*){0},([^,]*),([^,]*),([^,]*),([^,]*)$", record)) {
				throw new MissingFieldException("authors", record);
			} else if (Pattern.matches("^(\".*\"|[^,]*),([^,]*),([^,]*){0},([^,]*),([^,]*),([^,]*)$", record)) {
				throw new MissingFieldException("price", record);
			} else if (Pattern.matches("^(\".*\"|[^,]*),([^,]*),([^,]*),([^,]*){0},([^,]*),([^,]*)$", record)) {
				throw new MissingFieldException("ISBN", record);
			} else if (Pattern.matches("^(\".*\"|[^,]*),([^,]*),([^,]*),([^,]*),([^,]*){0},([^,]*)$", record)) {
				throw new MissingFieldException("genre", record);
			} else if (Pattern.matches("^(\".*\"|[^,]*),([^,]*),([^,]*),([^,]*),([^,]*),(([^,]*){0})$", record)) {
				throw new MissingFieldException("year", record);
			}

			//Using regex to check if the genre field of a book record is valid or not.
			Pattern pattern = Pattern.compile(",\s*([A-Z]{3})\s*,");
			Matcher matcher = pattern.matcher(record);
			if (matcher.find()) {
				String array[] = new String[2];
				String genre = matcher.group(1);
				switch (genre) {
				case "CCB":
					array[0] = "a";
					array[1] = record;
					return array;
				case "HCB":
					array[0] = "b";
					array[1] = record;
					return array;
				case "MTV":
					array[0] = "c";
					array[1] = record;
					return array;
				case "MRB":
					array[0] = "d";
					array[1] = record;
					return array;
				case "NEB":
					array[0] = "e";
					array[1] = record;
					return array;
				case "OTR":
					array[0] = "f";
					array[1] = record;
					return array;
				case "SSM":
					array[0] = "g";
					array[1] = record;
					return array;
				case "TPA":
					array[0] = "h";
					array[1] = record;
					return array;
				default:
					throw new UnknownGenreException(record);
				}
			} else {
				throw new UnknownGenreException(record);
			}
		} else { //if (Pattern.matches("^(\".*\"|[^,]*)(,[^,]*){6,}$, record)) is implied
			throw new TooManyFieldsException(record);	
		}
	}

	/**
	 * This method creates all necessary input/output streams in order to read and write book records across multiple text files.
	 */
	public static void do_part_1() {
		Scanner fileReader = null;
		Scanner bookReader = null;
		String error = "Program has terminated";
		
		//Create file input stream to "part1_input_file_names.txt" so that we can read text from it later.
		try {
			fileReader = new Scanner(new FileInputStream(".\\src\\Database\\part1_input_file_names.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("The file you are attempting to read from does not exist.");
			System.out.println(error);
			System.exit(0);
		}
		
		/* 
		 * Read first line (an integer) from "part1_input_file_names.txt" and use that value as
		 * the maximum number of times we will be reading from "part1_input_file_names.txt".
		 */
		int numOfInputFiles = 0;
		if (fileReader.hasNextInt()) {
			numOfInputFiles = fileReader.nextInt();
			fileReader.nextLine(); //garbage collector
		} else {
			System.out.println("The number of book records has not been specified.");
			System.out.println(error);
			System.exit(0);
		}		
		
		//Create file output streams to specified .txt and .csv.txt files so that we can write text to them later on.
		PrintStream a = null;
		PrintStream b = null;
		PrintStream c = null;
		PrintStream d = null;
		PrintStream e = null;
		PrintStream f = null;
		PrintStream g = null;
		PrintStream h = null;
		
		PrintStream errStream = null;
		try {			
			a = new PrintStream(new FileOutputStream(".\\src\\Database\\Cartoon_Comics.csv.txt"));
			b = new PrintStream(new FileOutputStream(".\\src\\Database\\Hobbies_Collectibles.csv.txt"));
			c = new PrintStream(new FileOutputStream(".\\src\\Database\\Movies_TV_Books.csv.txt"));
			d = new PrintStream(new FileOutputStream(".\\src\\Database\\Music_Radio_Books.csv.txt"));
			e = new PrintStream(new FileOutputStream(".\\src\\Database\\Nostalgia_Eclectic_Books.csv.txt"));
			f = new PrintStream(new FileOutputStream(".\\src\\Database\\Old_Time_Radio_Books.csv.txt"));
			g = new PrintStream(new FileOutputStream(".\\src\\Database\\Sports_Sports_Memorabilia.csv.txt"));
			h = new PrintStream(new FileOutputStream(".\\src\\Database\\Trains_Planes_Automobiles.csv.txt"));

			errStream = new PrintStream(new FileOutputStream(".\\src\\Database\\syntax_error_file.txt"));
			System.setErr(errStream);
		} catch (FileNotFoundException E) {
			System.err.println("Output file not found");
		}
		
		//This for loop allows us to read all input files listed in "part1_input_file_names.txt".
		for (int i = 0; i < numOfInputFiles; i++) {
			String inputFileName = ".\\src\\Database\\" + fileReader.nextLine();
			try {
				bookReader = new Scanner(new FileInputStream(inputFileName));
			} catch (FileNotFoundException E) {
				System.out.println("The file you are attempting to read from does not exist.");
				System.out.println(error);
				System.exit(0);
			}
			
			boolean fileAlreadyDeclared = false;
			
			/* 
			 * This while loop allows us to pass all book records within the input file we are currently on to the 
			 * readBookRecords method.
			 */
			while (bookReader.hasNextLine()) {
				String outputFile[] = null;
				try {
					outputFile = readBookRecords(bookReader);
				} catch (TooManyFieldsException E) {
					if (!fileAlreadyDeclared) {
						fileAlreadyDeclared = true;
						System.err.println("\nsyntax error in file: " + inputFileName);
						System.err.println("=======================================");
					}
					System.err.println(E.getMessage());
				} catch (TooFewFieldsException E) {
					if (!fileAlreadyDeclared) {
						fileAlreadyDeclared = true;
						System.err.println("\nsyntax error in file: " + inputFileName);
						System.err.println("=======================================");
					}
					System.err.println(E.getMessage());
				} catch (MissingFieldException E) {
					if (!fileAlreadyDeclared) {
						fileAlreadyDeclared = true;
						System.err.println("\nsyntax error in file: " + inputFileName);
						System.err.println("=======================================");
					}
					System.err.println(E.getMessage());
				} catch (UnknownGenreException E) {
					if (!fileAlreadyDeclared) {
						fileAlreadyDeclared = true;
						System.err.println("\nsyntax error in file: " + inputFileName);
						System.err.println("=======================================");
					}
					System.err.println(E.getMessage());
				} finally {
					if (outputFile != null) { 
						switch (outputFile[0]) {
						case "a":
							a.println(outputFile[1]);
							numOfSyntacticallyValidBooksByGenre[0]++;
							break;
						case "b":
							b.println(outputFile[1]);
							numOfSyntacticallyValidBooksByGenre[1]++;
							break;
						case "c":
							c.println(outputFile[1]);
							numOfSyntacticallyValidBooksByGenre[2]++;
							break;
						case "d":
							d.println(outputFile[1]);
							numOfSyntacticallyValidBooksByGenre[3]++;
							break;
						case "e":
							e.println(outputFile[1]);
							numOfSyntacticallyValidBooksByGenre[4]++;
							break;
						case "f":
							f.println(outputFile[1]);
							numOfSyntacticallyValidBooksByGenre[5]++;
							break;
						case "g":
							g.println(outputFile[1]);
							numOfSyntacticallyValidBooksByGenre[6]++;
							break;
						case "h":
							h.println(outputFile[1]);
							numOfSyntacticallyValidBooksByGenre[7]++;
							break;
						}
					}
				}
			}
		}
		
		//Must close streams in order for flush() method to be invoked and text to be stored in respective output files.
		a.close();
		b.close();
		c.close();
		d.close();
		e.close();
		f.close();
		g.close();
		h.close();
		errStream.close();
		bookReader.close();
	}
}
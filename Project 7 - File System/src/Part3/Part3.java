package Part3;

//-----------------------------------------------------
//Assignment 3
//Part: 3
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import Part2.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.ObjectInputStream;
import java.util.Scanner;

/*
* This method is designed to deserialize the arrays stored in each .ser file, then store them in a single array, allowing
* them to be easily accessible and manipulatable.
*/
public class Part3 {
	public static void do_part_3() {
		Book deserializedBooks[][] = new Book[8][];
		String fileName[] = new String[8];
		
		//This for loop allows us to enter all 8 .ser files.
		for (int i = 0; i < 8; i++) {
			String inputFileName = null;
			switch(i) {
			case 0:
				inputFileName = "Cartoon_Comics.csv.ser";
				break;
			case 1:
				inputFileName = "Hobbies_Collectibles.csv.ser";
				break;				
			case 2:
				inputFileName = "Movies_TV_Books.csv.ser";
				break;
			case 3:
				inputFileName = "Music_Radio_Books.csv.ser";
				break;
			case 4:
				inputFileName = "Nostalgia_Eclectic_Books.csv.ser";
				break;
			case 5:
				inputFileName = "Old_Time_Radio_Books.csv.ser";
				break;
			case 6:
				inputFileName = "Sports_Sports_Memorabilia.csv.ser";
				break;
			case 7:
				inputFileName = "Trains_Planes_Automobiles.csv.ser";
				break;	
			}
		
			fileName[i] = inputFileName;
			String relativeFilePath = ".\\src\\Database\\" + inputFileName;
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(new FileInputStream(relativeFilePath));
				deserializedBooks[i] = (Book[]) ois.readObject();
				ois.close();
			} catch (IOException e) {
				System.out.println("YIKES");
			} catch (ClassNotFoundException e) {
				System.out.println("WWWWOOOOOWWWW");
			}
			//System.out.println(fileName[i]);
			//System.out.println(deserializedBooks[i]);
		}
		
		int caseNum = 0;
		int fileIndex = 0;
		boolean validChoice = false;
		boolean exit = false;
		Scanner sc = new Scanner(System.in);
	
		while (!exit) {
			
			//Main Menu Display
			if (caseNum == 0) {
				System.out.println("---------------------------------------------------------------------------");
				System.out.println("				Main Menu		");
				System.out.println("---------------------------------------------------------------------------");
				System.out.printf("v View the selected file: %s (%d records)\n", fileName[fileIndex], deserializedBooks[fileIndex].length);
				System.out.println("s Select a file to view");
				System.out.println("x Exit");
				System.out.println("---------------------------------------------------------------------------");
				System.out.print("Enter your choice: ");
				
				while (!validChoice) {
					String code = sc.next().toLowerCase();
					switch(code) {
					case "v":
						caseNum = 1;
						validChoice = true;
						break;
					case "s":
						caseNum = 2;
						validChoice = true;
						break;
					case "x":
						exit = true;
						validChoice = true;
					break;	
					default:
						System.out.print("This is not a valid code. Please try again: ");
						sc.nextLine(); //garbage collector	
					}
				}
				sc.nextLine(); //garbage collector
				System.out.printf("%n%n%n");
			}

			//Viewing Records Display
			if (caseNum == 1) {
				System.out.printf("Viewing: %s (%d records)\n", fileName[fileIndex], deserializedBooks[fileIndex].length);
				
				int currentIndex = 0;
				int code = 1;
				boolean BOF = false;
				boolean EOF = false;
				
				validChoice = false;
				while (code != 0) {
					System.out.print("Enter viewing command: ");
					while (!validChoice) {
						try {
							code = sc.nextInt();
							validChoice = true;
						} catch (InputMismatchException e ) {
							System.out.print("Invalid command. Please try again: ");
							sc.nextLine(); //garbage collector
						}
					}
					
					validChoice = false;	
					if (code == -1 || code == 1) {
						System.out.println(deserializedBooks[fileIndex][currentIndex]);
					}
					
					if (code > 1) {
						int oldIndex = currentIndex;
						int newIndex = currentIndex + code;
						
						if (newIndex > deserializedBooks[fileIndex].length) {
							newIndex = deserializedBooks[fileIndex].length;
							EOF = true;
						}
						for (int i = oldIndex; i < newIndex; i++) {
							System.out.println(deserializedBooks[fileIndex][i]);
						}
						if (EOF) {
							System.out.println("EOF has been reached!");
						}
						
						EOF = false;
						currentIndex = newIndex - 1;
						System.out.printf("%n%n");
					}
					
					if (code < -1) {
						int oldIndex = currentIndex + code + 1;
						int newIndex = currentIndex + 1;
						
						if (oldIndex < 0) {
							BOF = true;
							oldIndex = 0;
						}
						if (BOF) { 
							System.out.println("BOF has been reached!");
						}
						for (int i = oldIndex; i < newIndex; i++) {
							System.out.println(deserializedBooks[fileIndex][i]);
						}
						BOF = false;
						currentIndex = oldIndex;
						System.out.printf("%n%n");
					}
				}
				exit = false;
				caseNum = 0;
			}
			
			//Viewing File Sub-Menu Display
			if (caseNum == 2) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("			File Sub-Menu		");
				System.out.println("----------------------------------------------------------------");
				System.out.printf("1. Cartoons_Comics.csv.ser			(%d records)\n", deserializedBooks[0].length);
				System.out.printf("2. Hobbies_Collectibles.csv.ser			(%d records)\n", deserializedBooks[1].length);
				System.out.printf("3. Movies_TV_Books.csv.ser			(%d records)\n", deserializedBooks[2].length);
				System.out.printf("4. Music_Radio_Books.csv.ser			(%d records)\n", deserializedBooks[3].length);
				System.out.printf("5. Nostalgia_Eclectic_Books.csv.ser		(%d records)\n", deserializedBooks[4].length);
				System.out.printf("6. Old_Time_Radio_Books.csv.ser			(%d records)\n", deserializedBooks[5].length);
				System.out.printf("7. Sports_Sports_Memorabilia.csv.ser		(%d records)\n", deserializedBooks[6].length);
				System.out.printf("8. Trains_Planes_Automobiles.csv.ser 		(%d records)\n", deserializedBooks[7].length);
				System.out.println("9. Exit");
				System.out.println("----------------------------------------------------------------");
				System.out.print("Enter your choice: ");
				
				validChoice = false;
				while (!validChoice) {
					if (sc.hasNextInt()) {
						int choice = sc.nextInt();
						sc.nextLine(); //garbage collector
						if (choice > 0 && choice < 9) {
							fileIndex = choice - 1;
							break;
						}
						if (choice == 9) {
							break;
						}
					}
					System.out.print("This is not a valid choice. Please try again: ");
				}
				caseNum = 0;
				System.out.printf("%n%n%n");
			}
		}
		sc.close();
		System.out.println("Thank you for using my program.");
	}	
}

//-----------------------------------------------------
//Assignment 4
//Part 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

/**
* The BookList class is designed to contain the most basic attributes/methods which all BookLists must possess. Its structure is based off the Circular Linked List model.
* @author Kcour
*
*/
public class BookList {
	/**
	 * The inner Node class is designed to contain the most basic attributes/methods which all Nodes must possess.
	 * @author Kcour
	 *
	 */
	private class Node {
		private Book b;
		private Node next;
		
		/**
		 * This method constructs a Node with a default b and next value.
		 */
		public Node() {
			b = null;
			next = null;
		}
		
		/**
		 * This method constructs a Node with a specified b and next value. 
		 * @param b Book record
		 * @param next Next node in the BookList
		 */
		public Node(Book b, Node next) {
			this.b = b;
			this.next = next;
		}
		
		/**
		 * This method constructs a deep-copy of a pre-existing Node.
		 * @param otherNode An object of type "Node"
		 */
		public Node (Node otherNode) {
			this.b = otherNode.b;
			this.next = otherNode.next;
		}
	} //End of Node class
	
	private Node head;
	
	/**
	 * This method constructs a BookList with a default head value.
	 */
	public BookList() {
		head = null;
	}
	
	/**
	 * The addToStart method is designed to add a Node to the start of the BookList that invokes this method.
	 * @param b A book record
	 */
	public void addToStart(Book b) {		
		Node newHead = new Node(b, head);
		
		/*
		 * If this BookList is empty, create a head Node that points to itself and contains a specified b value.
		 * 
		 * If BookList is not empty, traverse this BookList one Node at a time until we reach the last Node.
		 * Once the last Node has been reached, have its "next" pointer point to a new Node that contains the book record that was just passed, and have this new Node become the new head of the BookList.
		 */
		if (this.head == null) {
			head = newHead;
			head.next = head;
		} else {
			Node currentNode = head;
			while (currentNode.next != head) {
				currentNode = currentNode.next;
			}
			currentNode.next = newHead;
			head = newHead;
		}
	}
	
	/**
	 * The addToEnd method is designed to add a Node to the end of the BookList that invokes this method.
	 * @param b A book record
	 */
	public void addToEnd(Book b) {
		/*
		 * If this BookList is empty, create a head Node that points to itself and contains a specified b value.
		 * 
		 * If this BookList is not empty, traverse this BookList one Node at a time until we reach the alst Node.
		 * Once the last Node has been reached, have it's "next" pointer point to a new Node with the book record that was just psased. 
		 */
		if (this.head == null) {
			head = new Node(b, head);
			head.next = head;
		} else {
			Node currentNode = head;
			while (currentNode.next != head) {
				currentNode = currentNode.next;
			}
			currentNode.next = new Node(b,head);
		}
	}
	
	/**
	 * The storeRecordsByYear method is designed to store all Book records containing the value of a specified year inside a new file.
	 * @param yr Publication date
	 */
	public void storeRecordsByYear(int yr) {
		/*
		 * If the BookList that invokes this method is empty, inform the user that the process cannot continue and then exit the method.
		 * 
		 * If the BookList that invokes this ethod is not empty, traverse this BookList one Node at a time starting from the head node until we reach the end of the list,
		 * check whether or not the Node we are currently on contains a Book record with a matching publication date within it, and finally,
		 * store this record into a file if the publication dates match.
		 */
		if (head == null) {
			if (yr < 0) {
				System.out.printf("Could not find any books published in %s; no records have been stored.%n%n", (0 - yr) + " B.C.");
			} else {
				System.out.printf("Could not find any books published in %d; no records have been stored.%n%n", yr);
			}
		} else {
			Node currentNode = head;
			String outputFile = ".\\src\\Database\\" + yr + ".txt";
			int numOfExtractedBooks = 0;
			
			PrintStream ps = null;
			
			do {
				if (currentNode.b.getYear() == yr) {
					if (numOfExtractedBooks == 0) { //Create an output file once AND only if there is at least one record within this BookList that has a matching publication date.
						try {
							ps = new PrintStream(new FileOutputStream(outputFile));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
					ps.println(currentNode.b); //toString() is automatically invoked
					numOfExtractedBooks++;
				}
				currentNode = currentNode.next;
			} while (currentNode != head);
			
			
			/*
			 * Display whether a file was created or not and if so, how many records were stored in this file.		
			 */
			if (numOfExtractedBooks == 0) {
				if (yr < 0) {
					System.out.printf("Could not find any books published in %s; no records have been stored.%n%n", (0 - yr) + " B.C.");
				} else {
					System.out.printf("Could not find any books published in %d; no records have been stored.%n%n", yr);
				}
			} else {
				if (numOfExtractedBooks == 1) {
					System.out.printf("Successfully stored 1 record in %s.%n%n", outputFile.substring(15));
				} else {
					System.out.printf("Successfully stored %d records in %s.%n%n", numOfExtractedBooks, outputFile.substring(15));
				}
				ps.close(); //If numOfExtarctedBooks is greater than zero, then this is an indicator that the PrintStream object was opened and therefore, must be closed. 
			}
		}
	}
	
	/**
	 * The insertBefore method is designed to place a Node containing a specified book record before an exisiting Node containing a specified ISBN number inside the BookList that invoked this method.
	 * @param isbn An ISBN number
	 * @param b A book record
	 * @return True in the event of a valid insert and false in the event of no insert.
	 */
	public boolean insertBefore (long isbn, Book b) {
		
		/*
		 * If this BookList is empty, inform the user that no insertion has occured and return false.
		 * 
		 * If this BookList is not empty, check if the head Node contains a matching ISBN number to the one that was passed.
		 * If this is the case, place a new Node with the specified book value at the end of the BookList. Otherwise, continue
		 * traversing the BookList until we reach the last Node inside this BookList OR a Node WHOSE NEXT NODE contains a matching ISBN number (whichever comes first).
		 * 
		 * If a Node's next Node does contain a matchcing ISBN number, place a new Node containing the specified book value in between the current Node we are on and its "old" next Node.
		 * Afterwards, notify the user that the insertion was a success and return true.
		 * 
		 * Otherwise, inform the user that no insertion occured and return false.
		 */
		if (head == null) {
			System.out.printf("List is empty. ISBN number %d cannot be found in the list; no insertion is possible.%n%n", isbn);
			return false;
		}

		Node currentNode = head;
		
		if (head.b.getISBN() == isbn) {
			while (currentNode.next != head) {
				currentNode = currentNode.next;
			}
			currentNode.next = new Node(b, head);
			return true;
		}
		
		do {
			if (currentNode.next.b.getISBN() == isbn) {
				currentNode.next = new Node(b, currentNode.next);
				System.out.printf("Successful insertion.%n%n", isbn);
				return true;
			}
			currentNode = currentNode.next;
		} while (currentNode.next != head);
		
		System.out.printf("ISBN number %d was not found in the list. No insertion is possible.%n%n", isbn);
		return false;
	}
	
	/**
	 * The insertBetween method is designed to insert a specified book record in between two existing ISBN numbers from the BookList which invokes this method.
	 * @param isbn1 An ISBN number
	 * @param isbn2 An ISBN number
	 * @param b A book record
	 * @return True in the event that the insertion was a success, and false in the event that no insertion occurred.
	 */
	public boolean insertBetween(long isbn1, long isbn2, Book b) {
		
		/*
		 * If this BookList is empty, inform the user that no insertion is possible and return false.
		 * 
		 * If this BookList is not empty, traverse this BokkList one Node at a time (starting from the head) until
		 * the end of the BookList has been reached or we have reached two neighboring Nodes that contain one matching and UNIQUE ISBN number each.
		 * (PRIOR TO ENTERING THIS METHOD, THE ISBN NUMBERS WERE CHECKED TO SEE IF THEY WERE LONG NUMBERS AND CONTAIN UNIQUe VALUES).
		 * 
		 * If this is the case, insert a new Node that contains the specified book record, inform the user and return true.
		 * Otherwise, inform the user no insertion has occurred and return false.
		 */
		if (head == null) {
			System.out.printf("List is empty. ISBN numbers %d and %d were not found in the list (sequetially); no insertion is possible.%n%n", isbn1, isbn2);
			return false;
		}
		
		Node currentNode = head;
		do {
			if (currentNode.b.getISBN() == isbn1 && currentNode.next.b.getISBN() == isbn2) {
				currentNode.next = new Node(b, currentNode.next);
				return true;
			}
			currentNode = currentNode.next;
		} while (currentNode != head);
		
		System.out.printf("ISBN numbers %d and %d were not found in the list (sequentially); no insertion is possible.%n%n", isbn1, isbn2);
		return false;
	}
	
	/**
	 * The displayContent method is designed to display all book records contained within the BookList that invokes this method.
	 */
	public void displayContent() {
		
		/**
		 * If this BookList is empty, inform the user that nothing can be displayed.
		 * 
		 * If this BookList is not empty, traverse this BookList one Node at a time (starting from the head) and print out
		 * the book record contained within each node until we re-reach the head of this BookList.
		 */
		if (head == null) {
			System.out.printf("List is empty; cannot be displayed.%n%n");
		} else {
			Node currentNode = head;		
			do {
				System.out.printf("%n%s ==>",currentNode.b);
				currentNode = currentNode.next;
			} while (currentNode != head);
			
			System.out.printf("%n==> head%n%n");
		}
	}
	
	/**
	 * The delConsecutiveRepeatedRecords method is designed to delete consecutive repeating records within the BookList that invokes this method.
	 * @return True if records were successfully deleted from this BookList and false if no records were deleted.
	 */
	public boolean delConsecutiveRepeatedRecords() {
		
		/**
		 * If this BookList contains at most one Node, then there is no chance of there being consecutive repeating records inside this BookList. Therefore return false.
		 * 
		 * If this BookList contains more than one Node, traverse this BookList one Node at a time (starting from the head Node) and check if two neighboring Nodes contain the same
		 * book records. If they do, have the reassign the next value of the current Node point to the neighbor of its neighboring Node.
		 */
		if (head == null || head.next == head) {
			System.out.printf("List is either empty or contains only one record; no deletion is possible.%n%n");
			return false;
		}
		
		Node currentNode = head;
		int numOfCRR = 0;
		do {
			if (currentNode.b.equals(currentNode.next.b)) { 
				if (currentNode.next == head) { //check if node being skipped is head. If so, reassign value of head.
					head = currentNode.next.next;
				}
				currentNode.next = currentNode.next.next; //reassign value of "next" to the node that is two nodes ahead from currentNode.
				numOfCRR++;
				continue;
			}
			currentNode = currentNode.next;
		} while (currentNode != head);
			
		if (numOfCRR == 0) {
			System.out.printf("List does not contain any consecutive repeated records; no deletion is possible.%n%n");
			return false;
		} else if (numOfCRR == 1) {
			System.out.printf("List contained 1 consecutive repeated record which has successfully been deleted.%n");
			return true;
		} else {
			System.out.printf("List contained %d consecutive repeated records which have successfully been deleted.%n", numOfCRR);
			return true;
		}
	}

	/**
	 * The extractAuthList method is designed to extract all book records containing a specified author name from the BookList that invokes this method and store them inside a new BookList.
	 * @param aut The author's name.
	 * @return A new BookList contauining a list of book records with the same author.
	 */
	public BookList extractAuthList(String aut) {
		
		/**
		 * If this BookList is empty, there are no records that can be extracted. Therefore notify the user about this and then return the value of null.
		 * 
		 * If this BookList is not empty, then traverse this BookList one Node at a time (starting from the head Node), and check every Node's author field to see if it matches the
		 * author name that was passed to this method. If there is a match, add the current Book record to the end of a new BookList, otherwise, continue traversing the BookList
		 * until we rereach the head of the BookList.
		 */
		if (head == null) {
			System.out.printf("List is empty; no extraction is possible.%n%n");
			return null;
		}
		
		int numOfEA = 0;
		BookList newList = new BookList();
		Node currentNode = head;	
		do {
			if (currentNode.b.getAuthor().trim().toLowerCase().equals(aut.trim().toLowerCase())) {
				numOfEA++;
				newList.addToEnd(new Book(currentNode.b));
			}
			currentNode = currentNode.next;
		} while (currentNode != head);

		if (newList.head == null) { //could also check if numOfEA == 0
			System.out.printf("List does not contain any records containing the author \"%s\"; no extraction is possible.%n%n", aut);
			return null;
		}
		
		System.out.printf("Successfully extracted %d records%n", numOfEA);
		System.out.printf("Here is the resulting list:%n");
		return newList;
	}
	
	/**
	 * The swap method is designed to swap two book records (based on their ISBN numbers) from the BookList that invoked this method.
	 * @param isbn1 An ISBN number
	 * @param isbn2 An ISBN number
	 * @return True in the event of a successful swap and False in the event that a swap does not occur. 
	 */
	public boolean swap(long isbn1, long isbn2) {
		
		/*
		 * If this BookList contains at most 1 Node, then there are not enough records within this BookList to be swapped. Therefore return false.
		 * 
		 * If this BookList contains more than 1 Node, check if the ISBN numbers that were entered are the same. If they are, swapping is redundant. Return false.
		 * 
		 * If this BookList contains more than 1 Node and the ISBN numbers entered by the user are unique, traverse this BookList one Node at a time (starting from the head Node)
		 * and check if the ISBN of the current book record we are on matches one of the two entered by the user.
		 * 
		 * If there are two Nodes within this BookList that each contain one of the two unique ISBN numbers entered by the user, swap their book record contents and return true.
		 * Otherwise, inform the user that at least one of the ISBN numbers entered was not found within this BookList and return false.
		 */
		if (head == null || head.next == head) {
			System.out.printf("List is either empty or contains only one record; no swapping is possible.%n%n");
			return false;		
		}
		if (isbn1 == isbn2) {
			System.out.printf("ISBN numbers are the same; no swapping is possible.%n%n");
			return false;
		}
		
		Node currentNode = head;
		Node isbn1Node = null;
		Node isbn2Node = null;
		do {
			if (currentNode.b.getISBN() == isbn1) {
				isbn1Node = currentNode;
			}
			if (currentNode.b.getISBN() == isbn2) {
				isbn2Node = currentNode;
			}
			if (isbn1Node != null && isbn2Node != null) {
				Book temp = isbn1Node.b;
				isbn1Node.b = isbn2Node.b;
				isbn2Node.b = temp;
				System.out.printf("Successful swap.%n%n");
				return true;
			}
			currentNode = currentNode.next;
		} while (currentNode != head);
		
		
		
		System.out.printf("At least one of these ISBN numbers is not present in the list; no swapping is possible.%n%n");
		return false;
	}	

	/**
	 * This method is designed to write the book records stored within the BookList that invokes this method to a file called "Update_Books.txt".
	 */
	public void commit() {
		
		/**
		 * Create/Open a file called "Update_Books.txt", then traverse this BookList one Node at a time (starting from the head Node) and write the book records stored within
		 * each node to "Update_Books.txt".
		 */
		PrintStream ps = null;
		try {
			ps = new PrintStream(new FileOutputStream("Update_Books.txt"));
			
			if (head == null) {
				ps.print("");
			} else {
				Node currentNode = head;
				do {
					ps.println(currentNode.b);
					currentNode = currentNode.next;
				} while (currentNode != head);
				
				System.out.printf("Successful commit!%n%n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ps.close();
	}
}


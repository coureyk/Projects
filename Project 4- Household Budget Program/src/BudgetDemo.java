//--------------------------------------------------------------------
//Assignment 4
//Written by: Kevin Courey 40245966
//For COMP 248 Section S – Fall 2022
//--------------------------------------------------------------------

/*
* This class allows the user to modify the HouseholdBudgets to their liking.
*/

import java.util.Scanner;
public class BudgetDemo {
	private static Scanner sc = new Scanner(System.in);
	
	private static Fund fund0 = new Fund(0,1,0,2,1);
	private static Expense exp00 = new Expense("Purchase", 85.75, "IGA", 25, 11);
	private static Expense exp01 = new Expense("Bill", 122.75, "Videotron", 02, 12);
	private static Expense[] exp0 = {exp00, exp01};
	
	private static Fund fund1 = new Fund(0,1,0,2,1);
	private static Expense exp10 = new Expense("Purchase", 85.5, "IGA", 25, 11);
	private static Expense exp11 = new Expense("Bill", 16.2, "Costco", 23, 11);
	private static Expense[] exp1 = {exp10, exp11};
	
	private static Fund fund2 = new Fund(2,0,2,1,1);
	private static Expense exp20 = new Expense("Purchase", 50.75, "Pharmaprix", 1, 12);
	private static Expense exp21 = new Expense("Purchase", 16.2, "TheBay", 16, 12);
	private static Expense exp22 = new Expense("Purchase", 65.85, "FIDO", 5, 1);
	private static Expense[] exp2 = {exp20, exp21, exp22};
	
	private static Fund fund3 = new Fund(2,1,2,1,5);
	private static Expense[] exp3 = {};
	
	private static Fund fund4 = new Fund(2,1,2,1,5);
	private static Expense[] exp4 = {};

	//Two householdBudgets have exactly the same fund distribution and the same number of expenses.
	private static HouseholdBudget budget0 = new HouseholdBudget(fund0, exp0);
	private static HouseholdBudget budget1 = new HouseholdBudget(fund1, exp1);
	
	/*
	 * One householdBudget with the same total value of fund of another householdBudget but
	 * with a different configuration of fund type and this householdBudget should have at least
	 * three expenses.
	 */
	private static HouseholdBudget budget2 = new HouseholdBudget(fund2, exp2);
	
	/*
	 * Two householdBudgets with the same breakdown of fund (but different from the
	 * other three) and both have no expenses.
	 */
	private static HouseholdBudget budget3 = new HouseholdBudget(fund3, exp3);
	private static HouseholdBudget budget4 = new HouseholdBudget(fund4, exp4);
	
	//Creates an array of HouseholdBudgets so that they can be called much more easily in the future.
	private static HouseholdBudget[] budget = {budget0, budget1, budget2, budget3, budget4};	
	

	//Method that displays the menu
	public static void displayMenu() {
		System.out.println("What would you like to do?");
		System.out.println("   1. See the possessions of all householdBudgets");
		System.out.println("   2. See the possessions of one householdBudget");
		System.out.println("   3. List householdBudgets with the same total amount of fund.");
		System.out.println("   4. List householdBudgets with same fund denomination(s)");
		System.out.println("   5. List householdBudgets with same total amount of fund and same number of expenses");
		System.out.println("   6. Add an expense to an existing householdBudget");
		System.out.println("   7. Remove an existing expense from a householdBudget");
		System.out.println("   8. Update the payment due date of an existing householBudget");
		System.out.println("   9. Add fund to a householdBudget");
		System.out.println("   0. To quit");
		System.out.println();
	}
	
	// Task 1
	// Method that displays the fund and expenses of each householdBudget.
	public static void displayEach() {
		System.out.println("Content of each householdBudget:");
		System.out.println("---------------------");
		for (int i = 0; i < budget.length; i++) {
			System.out.println("HouseholdBudget #" + i + ":");
			System.out.println(budget[i].toString());
		}
		System.out.printf("%n%n");
	}
	

	/* 
	 * Task 2
	 * Method that asks the user which householdBudget they wish to see the content
	 * of and display the fund and expenses(s) info for that householdBudget. 
	 */
	public static void displayOne() {
		boolean validChoice = false;
		
		System.out.print("Which HouseHoldBudget do you want to see possessions of? ");
		while (!validChoice) {
			System.out.printf("(Enter number 0 to %d): ", (budget.length - 1));
			if (sc.hasNextInt()) {
				int budgetChoice = sc.nextInt();
				sc.nextLine(); //garbage collector
				if (budgetChoice >= 0 && budgetChoice <= (budget.length - 1)) {
					System.out.printf("%s%n%n%n", budget[budgetChoice].toString());
					validChoice = true;
				} else {
					System.out.printf("Sorry but there is no HouseholdBudget number %d%n", budgetChoice);
					System.out.printf(" --> Try again (Enter number 0 to %d): ", (budget.length - 1));
				}
			} else {
				sc.nextLine(); //garbage collector
				System.out.println("Invalid entry");
				System.out.printf(" --> Try again. (Enter number 0 to %d): ", (budget.length - 1));
				continue;
			}
		}
	}
	
	/* 
	 * Task 3
	 * Method that compares all housholdBudgets and displays those pairs that have the same total fund value
	 * along with the $ amount. If you have displayed the pair 1 and 3, do not display the pair 3
	 * and 1 as that is repetitive.
	 */
	public static void sameTotal() {
		System.out.printf("List of HouseholdBudgets with same total fund:%n");
		for (int i = 0; i < budget.length; i++) {
			for (int j = i + 1; j < budget.length; j++) {
				if (budget[i].checkTotal(budget[j]) == true) {
					System.out.printf("%n\tHouseholdBudgets %d and %d both have %.1f", i, j, budget[i].hbFundTotal());
				}
			}
		}
		System.out.printf("%n%n%n%n");
	}
	
	/* 
	 * Task 4
	 * Method that compares all householdBudgets and displays the pairs that have the same fund distribution.
	 * Similarly, as in option 3, if you have displayed the pair 1 and 3, do not display the pair 3
	 * and 1 as that is repetitive. 
	 */
	public static void sameDistrib() {
		System.out.printf("List of HouseholdBudgets with same Fund:%n");
		for (int i = 0; i < budget.length; i++) {
			for (int j = i + 1; j < budget.length; j++) {
				if (budget[i].fundBreakdown().equals(budget[j].fundBreakdown())) {
					System.out.printf("%n\tHouseholdBudgets %d and %d both have %s", i, j, budget[i].fundBreakdown());
				}
			}
		}
		System.out.printf("%n%n%n%n");
	}
	
	/*
	 * Task 5
	 * Method age that lists all householdBudgets pairs that are equal based on the definition of equal in the class
	 * HouseholdBudget. Avoid repetitive displays. 
	 */
	public static void sameBudget() {
		System.out.printf("List of HouseholdBudgets with same amount of money and same number of expenses:%n");
		for (int i = 0; i < budget.length; i++) {
			for (int j = i + 1; j < budget.length; j++) {
				if (budget[i].equals((budget[j]))) {
					System.out.printf("%n\tHouseholdBudgets %d and %d", i, j);
				}
			}
		}
		System.out.printf("%n%n%n%n");
	}
	
	/*
	 * Task 6
	 * Method that asks the user which householdBudget they want to add an expense to, as well as the
	 * expense information, creates the new expense and adds it to the householdBudget in
	 * question.
	 */
	public static void addExpense() {
		int budgetChoice = 0;
		String expenseType;
		double amount;
		String businessName;
		int dueDay;
		int dueMonth;

		System.out.printf("Which HouseholdBudget do you want to add an Expense to? (Enter number 0 to %d): ", (budget.length - 1));
		boolean validInput = false;
		while (!validInput) {
			String input = sc.nextLine();
			if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < budget.length) {
				budgetChoice = Integer.parseInt(input);
				validInput = true;
			} else {
				System.out.printf("Sorry but there is no HouseholdBudget number %s%n", input);
				System.out.printf(" --> Try again (Enter number 0 to %d): ", (budget.length - 1));
			}
		}
		System.out.println("Please enter the following information so that we may complete the expense");
		System.out.print(" --> Type of expense (Bill, Purchase, etc...): ");
		expenseType = sc.nextLine();
		System.out.printf(" --> Amount of the expense: ");
		amount = sc.nextDouble();
		sc.nextLine(); //garbage collector
		System.out.printf(" --> Name of the business: ");
		businessName = sc.nextLine();
		System.out.printf(" --> Payment due day and month number (separate by a space): ");
		dueDay = sc.nextInt();
		dueMonth = sc.nextInt();
		sc.nextLine(); //garbage collector
		
		Expense newExpense = new Expense(expenseType, amount, businessName, dueDay, dueMonth);
		budget[budgetChoice].addExpense(newExpense);
		
		if (budget[budgetChoice].numOfExpenses() != 1) {
			System.out.printf("You now have %d expenses%n%n%n%n", budget[budgetChoice].numOfExpenses());
		} else {
			System.out.printf("You now have 1 expense%n%n%n%n");
		}
	}
	
	/*
	 * Task 7
	 * Method that asks the user which householdBudget they want to remove an expense from, as well as
	 * the expense index they want removed, and removes it from the householdBudget. 
	 */
	public static void removeExpense() {
		int budgetChoice = 0;
		int expenseChoice = 0;
		
		System.out.printf("Which HouseholdBudget do you want to remove an expense from? (Enter number 0 to %d): ", (budget.length - 1));
		boolean validInput = false;
		while (!validInput) {
			String input1 = sc.nextLine();
			if (Integer.parseInt(input1) >= 0 && Integer.parseInt(input1) < budget.length) {
				budgetChoice = Integer.parseInt(input1);
				validInput = true;
			} else {
				System.out.printf("Sorry, but there is no expense number %s%n", input1);
				System.out.printf(" --> Try again (Enter number 0 to %d): ", (budget.length - 1));
			}
		}
		
		if (budget[budgetChoice].numOfExpenses() != 0) {
			System.out.printf("Enter number 0 to %d: ", (budget[budgetChoice].numOfExpenses() - 1));
			boolean validExpense = false;
			while (!validExpense) {
				String input2 = sc.nextLine();
				if (Integer.parseInt(input2) >= 0 && Integer.parseInt(input2) < budget[budgetChoice].numOfExpenses()) {
					validExpense = true;
					expenseChoice = Integer.parseInt(input2);
					budget[budgetChoice].removeExpense(expenseChoice);
					System.out.printf("Expense was removed successfully%n%n%n%n");
				} else {
					System.out.printf("Sorry, but there is no expense number %s%n", input2);
					System.out.printf(" --> Try again (Enter number 0 to %d): ", (budget[budgetChoice].numOfExpenses() - 1));
				}
			}
		} else {
			System.out.printf("Sorry that HouseholdBudget has no expenses.%n%n%n%n");
		}
	}

	/*
	 * Task 8
	 * Method which asks the user which expense from which householdBudget they want to update. Also asks the
	 * user for the new due date and updates the expense. 
	 */
	public static void updateExpenseDate() {
		int budgetChoice = 0;
		int expenseChoice = 0;
		int newDay = 0;
		int newMonth = 0;
		
		System.out.printf("Which HouseholdBudget do you want to update an expense from? (Enter number 0 to %d): ", (budget.length - 1));
		boolean validInput = false;
		while (!validInput) {
			String input = sc.nextLine();
			if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < budget.length) {
				budgetChoice = Integer.parseInt(input);
				validInput = true;
			} else {
				System.out.println("Sorry there is no HouseholdBudget number " + input);
				System.out.printf(" --> Try again (Enter number 0 to %d): ", (budget.length - 1));
			}
		}
		
		if (budget[budgetChoice].numOfExpenses() != 0) {
			System.out.printf("Which expense do you want to update? (Enter number 0 to %d): ", (budget[budgetChoice].numOfExpenses() - 1));
			boolean validExpense = false;
			while (!validExpense) {
				String input = sc.nextLine();
				if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < budget[budgetChoice].numOfExpenses()) {
					expenseChoice = Integer.parseInt(input);
					validExpense = true;
					
					System.out.print("Enter new payment due day and month number (seperate by a space): ");
					newDay = sc.nextInt();
					newMonth = sc.nextInt();
					sc.nextLine(); //garbage collector
					
					budget[budgetChoice].updateDueDate(expenseChoice, newDay, newMonth);
				} else {
					System.out.printf("Sorry, but there is no expense number %s%n", input);
					System.out.printf(" --> Try again (Enter number 0 to %d): ", (budget[budgetChoice].numOfExpenses() - 1));
				}
			}
		} else {
			System.out.printf("There are no expenses within this HouseholdBudget.%n%n%n%n");
		}
	}
		
	/*
	 * Task 9
	 * Method that asks the user which housholdBudget’s fund they want to add to and the number of each
	 * cash type they want to add. Then add these fund types to the fund in the household.
	 */
	public static void addFund() {
		int budgetChoice = 0;
		int loonies = 0;
		int toonies = 0;
		int fins = 0;
		int sawbucks = 0;
		int vimmies = 0;
		
		System.out.printf("Which HouseholdBudget do you want to add Fund to? (Enter number 0 to %d): ", (budget.length - 1));
		boolean validInput = false;
		while (!validInput) {
			String input = sc.nextLine();
			if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < budget.length) {
				budgetChoice = Integer.parseInt(input);
				validInput = true;
			} else {
				System.out.println("Sorry there is no HouseholdBudget number " + input);
				System.out.printf(" --> Try again (Enter number 0 to %d): ", (budget.length - 1));
			}
		}
		
		System.out.print("How many loonies, toonies, $5, $10 and $20 bills do you want to add? (Enter 5 number, seperated by a space): ");
		loonies = sc.nextInt();
		toonies = sc.nextInt();
		fins = sc.nextInt();
		sawbucks = sc.nextInt();
		vimmies = sc.nextInt();
		sc.nextLine(); //garbage collector
		
		System.out.printf("You now have $%.1f%n%n%n%n", budget[budgetChoice].addFundToHB(loonies, toonies, fins, sawbucks, vimmies));
	}
	
	public static void main(String[] args) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		System.out.println("Welcome to COMP248 Geek's HouseholdBudget_2022 Application\n");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		boolean breakFromLoop = false;
		while (!breakFromLoop) {
			displayMenu();
			System.out.print("Please enter your choice and press <Enter>: ");
			String input = sc.nextLine();
			if (input.equals("1")) {
				displayEach();
			} else if (input.equals("2")) {
				displayOne();
			} else if (input.equals("3")) {
				sameTotal();
			} else if (input.equals("4")) {
				sameDistrib();
			} else if (input.equals("5")) {
				sameBudget();	
			} else if (input.equals("6")) {
				addExpense();
			} else if (input.equals("7")) {
				removeExpense();
			} else if (input.equals("8")) {
				updateExpenseDate();
			} else if (input.equals("9")) {
				addFund();
			} else if (input.equals("0")) {
				System.out.print("Thank you for using COMP248 Geek's HouseholdBudget application!");
				breakFromLoop = true;
			} else {
				System.out.printf("Sorry, that is not a valid choice. Try again.%n%n%n%n");
			}
	
		}
	}
}
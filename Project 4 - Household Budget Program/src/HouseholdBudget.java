//--------------------------------------------------------------------
//Assignment 4
//Written by: Kevin Courey 40245966
//For COMP 248 Section S – Fall 2022
//--------------------------------------------------------------------

/*
* This class uses the classes "Fund" and "Expense" to create
* HouseholdBudgets.
*/
public class HouseholdBudget {

	private Fund fund;
	private Expense[] expenses;
	
	//Constructors
	
	//Default constructor
	public HouseholdBudget() {
		this.fund = new Fund();
		this.expenses = null;
	}
	
	/*
	 * Constructor with 2 parameters to set the initial value of each attribute.
	 * One attribute is of type Fund and the other is an array of type Expense.
	 */
	public HouseholdBudget(Fund funds, Expense[] expenses) {
		this.fund = funds;
		this.expenses = expenses;
		if (this.expenses.length == 0) {
			this.expenses = null;
		}
	}
	
	/* 
	 * A method which will return true if THE TOTAL VALUE of the fund
	 * of two HouseholdBudget objects are equal, and false otherwise.
	 */
	public boolean checkTotal(HouseholdBudget anotherHouseholdBudget) {
		if (!(anotherHouseholdBudget instanceof HouseholdBudget)) {
			return false;
		}
		if (!(this.fund.fundTotal() == anotherHouseholdBudget.fund.fundTotal())) {
			return false;
		}
		return true;
	}
	
	/*
	 * A method which will return true if THE NUMBER OF EACH fund type
	 * of two HouseholdBudget objects are equal, and false otherwise. 
	 */
	public boolean checkEach(HouseholdBudget anotherHouseholdBudget) {
		if (!(anotherHouseholdBudget instanceof HouseholdBudget)) {
			return false;
		}
		if (!(this.fund.equals(anotherHouseholdBudget.fund))) {
			return false;
		}
		return true;
	}
	
	/*
	 * A method which will return the total value of fund of a HouseholdBudget.
	 * 
	 * Return value is of type double so that it will end with a decimal point
	 */
	public double hbFundTotal() {
		int total = this.fund.fundTotal();
		return total;
	}
	
	/*
	 * A method which will return the number of expenses of the HouseholdBudget.
	 */
	public int numOfExpenses() {
		int numOfExpenses = 0;
		if (this.expenses != null) {
			numOfExpenses = this.expenses.length;
		}
		return numOfExpenses;
	}
	
	/*
	 * A method which will add a new expense to the householdbudget. You will need to account
	 * for a householdbudget not having any expenses before the addition. (Reminder from
	 * COMP248 – how do you increase the number of elements in an array?). This method returns
	 * the number of expenses of a householdBudget after the addition.
	 */
	public int addExpense(Expense newExpense) {
		int newNumOfExpenses;
		if (this.expenses == null) {
			newNumOfExpenses = 1;
		} else {
			newNumOfExpenses = this.expenses.length + 1;
		}
		Expense[] newestExpenses = new Expense[newNumOfExpenses];
		for (int i = 0; i < newestExpenses.length; i++) {
			if (i >= (newestExpenses.length - 1)) {
				newestExpenses[i] = newExpense;
			} else {
				newestExpenses[i] = this.expenses[i];
			}
		}
		this.expenses = newestExpenses;
		return (this.expenses.length);
	}
	
	/*
	 * A method which will remove an expense from the householdBudget. You will need to account
	 * for a householdBudget that has no expenses. (Reminder from COMP248 – how do you reduce
	 * the number of elements in an array?). This method returns true if the removal of the expense
	 * was successful and false if it was not.
	 */
	public boolean removeExpense(int expensePosition) {
		if (expensePosition >= 0 && expensePosition < this.numOfExpenses()) {
			int newNumOfExpenses = this.numOfExpenses() - 1;
			Expense[] newestExpenses = new Expense[newNumOfExpenses];
			for (int i = 0; i < newNumOfExpenses; i++) {
				if (i >= expensePosition) {
					newestExpenses[i] = this.expenses[i + 1];
				} else {
					newestExpenses[i] = this.expenses[i];
				}
			}
			
			if (newestExpenses.length == 0) {
				newestExpenses = null;
			} 
			this.expenses = newestExpenses;
		
			return true;
		}
		return false;
	}
	
	// A method which will update the due date day and month of an expense
	public void updateDueDate(int position, int newDueDay, int newDueMonth) {
		this.expenses[position].setPaymentDueDay(newDueDay);
		this.expenses[position].setPaymentDueMonth(newDueMonth);
		System.out.printf("Due Date updated.%n%n%n%n");
	}
	
	/*
	 * A method which will add fund to the householdBudget. This method should have 5
	 * parameters, one for each fund type and returns the new total value of the fund of the
	 * householdBudget. 
	 * 
	 * Returns value of type double because hbFundTotal returns a value of type double
	 */
	public double addFundToHB(int newLoony, int newToony, int newFin, int newSawbuck, int newVimy) {
		this.fund.addFund(newLoony, newToony, newFin, newSawbuck, newVimy);
		return (this.hbFundTotal());
	}
	
	/*
	 * Method which will return true if the total value of fund and the number of
	 * expenses of two householdBudget objects are equal, and false otherwise. 
	 */
	public boolean equals(HouseholdBudget anotherHouseholdBudget) {
		return ((this.hbFundTotal() == anotherHouseholdBudget.hbFundTotal()) && (this.numOfExpenses() == anotherHouseholdBudget.numOfExpenses()));
	}
	
	/*
	 * Method which will return a string clearly indicating the number of each money type as
	 * well as the details of each expense of the householdBudget. It is possible for a
	 * householdBudget to have no expenses, in which case “No expense” should be indicated.
	 */
	public String toString() {
		String fundStr = this.fund.toString();
		String expensesStr = "";
		
		if (this.expenses == null) {
			expensesStr = "No Expenses" + "\n";
		} else {
			for (int i = 0; i < expenses.length; i++) {
				expensesStr = expensesStr + this.expenses[i].toString() + "\n";
			}
		}
		return (fundStr + "\n" + expensesStr);
	}
	
	// A method which will return a string with just the breakdown of the fund. 
	public String fundBreakdown() {
		return (this.fund.toString());
	}		
}
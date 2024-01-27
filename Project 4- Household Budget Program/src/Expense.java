//--------------------------------------------------------------------
//Assignment 4
//Written by: Kevin Courey 40245966
//For COMP 248 Section S – Fall 2022
//--------------------------------------------------------------------

/*
* This class allows the user to create Expenses, which will later be used
* when creating HouseholdBudgets.
*/
public class Expense {

	private String expenseType;
	private double amount;
	private String business;
	private int paymentDueDay;
	private int paymentDueMonth;
		
	//Constructors

	//Default constructor
	public Expense() {
		this.expenseType = "";
		this.amount = 0.0;
		this.business = "";
		this.paymentDueDay = 0;
		this.paymentDueMonth = 0;
	}
	
	//Constructor with 5 parameters to set the initial value of each attribute.
	public Expense(String expense, double num, String name, int day, int month) {
		this.expenseType = expense;
		this.amount = num;
		this.business = name;
		if (day >= 1 && day <= 31 ) {
			this.paymentDueDay = day;
		} else {
			this.paymentDueDay = 0;
		}
		if (month >= 1 && month <= 12 ) {
			this.paymentDueMonth = month;
		} else {
			this.paymentDueMonth = 0;
		}
	}
	
	//Copy constructor with one parameter of type Expense.
	public Expense(Expense objOfClass) {
		this.expenseType = objOfClass.expenseType;
		this.amount = objOfClass.amount;
		this.business = objOfClass.business;
		this.paymentDueDay = objOfClass.paymentDueDay;
		this.paymentDueMonth = objOfClass.paymentDueMonth;
	}

	//Accessor/Getter Methods
	public String getExpenseType() {
		return this.expenseType;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public String getBusiness() {
		return this.business;
	}
	
	public int getPaymentDueDay() {
		return this.paymentDueDay;
	}
	
	public int getPaymentDueMonth() {
		return this.paymentDueMonth;
	}
	
	//Mutator/Setter Methods
	
	public void setPaymentDueDay(int count) {
		if (count >= 1 && count <= 31 ) {
			this.paymentDueDay = count;
		} else {
			this.paymentDueDay = 0;
		}
	}
	
	public void setPaymentDueMonth(int count) {
		if (count >= 1 && count <= 12 ) {
			this.paymentDueMonth = count;
		} else {
			this.paymentDueMonth = 0;
		}
	}
	
	//Method which will return a string indicating the type of the expense, the name of the business as well as the due date formatted as dd/mm.
	public String toString() {
		String payDay = "";
		String payMonth = "";
		
		if (this.paymentDueDay < 10) {
			payDay = "0" + Integer.toString(this.paymentDueDay);
		} else {
			payDay = Integer.toString(this.paymentDueDay);
		}
		if (this.paymentDueMonth < 10) {
			payMonth = "0" + Integer.toString(this.paymentDueMonth);
		} else {
			payMonth = Integer.toString(this.paymentDueMonth);
		}
		return (this.expenseType + " - $" + this.amount + " - " + this.business + " - " + payDay + "/" + payMonth + ".");
	}
	
	//Method which will return true if the two objects of type Expense are identical (have exactly the same information) and false otherwise. 
	public boolean equals(Expense objOfClass) {
		if (!(objOfClass instanceof Expense)) {
			return false;
		}
		if (!(this.expenseType.equalsIgnoreCase(objOfClass.expenseType))) {
			return false;
		}
		if (!(this.amount == objOfClass.amount)) {
			return false;
		}
		if (!(this.business.equalsIgnoreCase(objOfClass.business))) {
			return false;
		}
		if (!(this.paymentDueDay == objOfClass.paymentDueDay)) {
			return false;
		}
		if (!(this.paymentDueMonth == objOfClass.paymentDueMonth)) {
			return false;
		}
		return true;
	}
}

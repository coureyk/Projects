//--------------------------------------------------------------------
//Assignment 4
//Written by: Kevin Courey 40245966
//For COMP 248 Section S – Fall 2022
//--------------------------------------------------------------------

/*
* This class allows the user to create Funds, which will later be used
* when creating HouseholdBudgets.
*/
public class Fund {

	//Attributes
	
	//Instance variables
	private int loony;
	private int toony;
	private int fin;
	private int sawbuck;
	private int vimy;
	
	//Constants
	private static final int constLoony = 1;
	private static final int constToony = 2;
	private static final int constFin = 5;
	private static final int constSawbuck = 10;
	private static final int constVimy = 20;
	
	//Constructors
	
	//Default constructor
	public Fund() {
		this.loony = 0;
		this.toony = 0;
		this.fin = 0;
		this.sawbuck = 0;
		this.vimy = 0;
	}
	
	//Constructor with 5 integer parameters to set the number of each fund type the household owns
	public Fund(int loonyCount, int toonyCount, int finCount, int sawbuckCount, int vimyCount) {
		this.loony = loonyCount;
		this.toony = toonyCount;
		this.fin = finCount;
		this.sawbuck = sawbuckCount;
		this.vimy = vimyCount;
	}
	
	//Copy constructor with one parameter of type Fund.
	public Fund(Fund objOfClass) {
		this.loony = objOfClass.loony;
		this.toony = objOfClass.toony;
		this.fin = objOfClass.fin;
		this.sawbuck = objOfClass.sawbuck;
		this.vimy = objOfClass.vimy;
	}
	
	//Accessor/Getter Methods
	public int getLoony() {
		return this.loony;
	}
	
	public int getToony() {
		return this.toony;
	}
	
	public int getFin() {
		return this.fin;
	}
	
	public int getSawbuck() {
		return this.sawbuck;
	}
	
	public int getVimy() {
		return this.vimy;
	}
	
	//Mutator/Setter Methods
	public void setLoony(int count) {
		this.loony = count;
	}
	
	public void setToony(int count) {
		this.toony = count;
	}
	
	public void setFin(int count) {
		this.fin = count;
	}
	
	public void setSawbuck(int count) {
		this.sawbuck = count;
	}
	
	public void setVimy(int count) {
		this.vimy = count;
	}
	
	//Method with 5 integer parameters which allows you to increase the number of each fund type by the indicated number. 
	public void addFund(int addLoony, int addToony, int addFin, int addSawbuck, int addVimy) { 
		this.loony += addLoony;
		this.toony += addToony;
		this.fin += addFin;
		this.sawbuck += addSawbuck;
		this.vimy += addVimy;
	}

	//Method which returns an integer indicating the total value of the fund possessed by the household.
	public int fundTotal() {
		int total = (this.loony * constLoony) + (this.toony * constToony) + (this.fin * constFin) + (this.sawbuck * constSawbuck) + (this.vimy * constVimy);
		return total;
	}
	
	//Method which will return a string clearly indicating the count of each fund type the household owns. 
	public String toString() {
		String str = "(" + this.loony + " x $1) + (" + this.toony + " x $2) + (" + this.fin + " x $5) + (" + this.sawbuck + " x $10) + (" + this.vimy + " x $20)";
		return (str);
	}
	
	public boolean equals(Fund objOfClass) {
		if (!(objOfClass instanceof Fund)) {
			return false;
		}
		if (!(this.loony == objOfClass.loony)) {
			return false;
		}
		if (!(this.toony == objOfClass.toony)) {
			return false;
		}
		if (!(this.fin == objOfClass.fin)) {
			return false;
		}
		if (!(this.sawbuck == objOfClass.sawbuck)) {
			return false;
		}
		if (!(this.vimy == objOfClass.vimy)) {
			return false;
		}
		return true;
	}
}
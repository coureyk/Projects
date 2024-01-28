package FlightMachine;

//-----------------------------------------------------
//Assignment 2
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import Superclass.Vehicle;

/**
* The Aircraft class is designed to contain the most basic attributes/methods which any aircraft should possess.
* @author Kevin Courey
*
*/
public class Aircraft extends Vehicle {
	
	/**
	 * Stores the price of the aircraft.
	 */
	private double price;
	
	/**
	 * Stores the maximum elevation that can be reached by the aircraft.
	 */
	private double maxElevation;
	
	/**
	 * This method constructs an aircraft with a default price and maximum elevation.
	 */
	public Aircraft() {
		price = 0.0;
		maxElevation = 0.0;
	}
	
	/**
	 * This method constructs an aircraft with a specified price and maximum elevation.
	 * @param price The price of the aircraft.
	 * @param maxElevation The maximum elevation.
	 */
	public Aircraft(double price, double maxElevation) {
		this.price = price;
		this.maxElevation = maxElevation;
	}
	
	/**
	 * This method constructs a copy of a pre-existing aircraft.
	 * @param otherAircraft An instanceof Aircraft.
	 */
	public Aircraft(Aircraft otherAircraft) {
		if (otherAircraft != null) {
			this.price = otherAircraft.price;
			this.maxElevation = otherAircraft.maxElevation;
		}
	}
	
	/**
	 * This method retrieves the price of the aircraft.
	 * @return The price of the aircraft.
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * This method retrieves the maximum elevation of the aircraft.
	 * @return the maximum elevation.
	 */
	public double getMaxElevation() {
		return this.maxElevation;
	}
	
	/**
	 * This method sets the price of the aircraft.
	 * @param price The price.
	 */
	public void setPrice(double price) {
		this.price = price; 
	}
	
	/**
	 * This method sets the maximum elevation that can be reached by the aircraft.
	 * @param maxElevation The maximum elevation.
	 */
	public void setMaxElevation(double maxElevation) {
		this.maxElevation = maxElevation;
	}
	
	/**
	 * This method returns a description of the vehicle, with regard to its
	 * price and maximum elevation.
	 * @return Vehicle description with regard to price and maximum elevation.
	 */
	@Override
	public String toString() {
		return ("This " + this.getClass().getSimpleName() + " is $" + String.format("%.2f", this.price) + " and it has a maximum elevation of " + Math.round(this.maxElevation) + " feet.");
	}
	
	/**
	 * This method compares two objects and determines whether or not they are of type Aircraft and 
	 * share the same contents.
	 * @param otherObject Any object.
	 * @return True if the objects are of type Aircraft and share the same contents and False if
	 * at least one of these conditions is not met.
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		}
		if (this.getClass() != otherObject.getClass()) {
			return false;
		}
		Aircraft otherAircraft = (Aircraft) otherObject;
		return (this.price == otherAircraft.price && this.maxElevation == otherAircraft.maxElevation);
	}
	
	/**
	 * This method overrides the copy method in the base class (Vehicle) and returns a copy of the Vehicle that invokes this method.
	 * @return An object of type Aircraft.
	 */
	public Aircraft copy() {
		return new Aircraft(this);
	}
}

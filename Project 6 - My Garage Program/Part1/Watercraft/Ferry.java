package Watercraft;

//-----------------------------------------------------
//Assignment 2
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import Superclass.Vehicle;

/**
* The Ferry class is designed to contain the most basic attributes/methods which any ferry should possess.
* @author Kevin Courey
*
*/
public class Ferry extends Vehicle {
	
	/**
	 * Stores the maximum speed the ferry can reach.
	 */
	private double maxSpeed;
	
	/**
	 * Stores the maximum load the ferry can carry.
	 */
	private double maxLoad;
	
	/**
	 * This method constructs a ferry with a default maximum speed and maximum load.
	 */
	public Ferry() {
		this.maxSpeed = 0.0;
		this.maxLoad = 0.0;
	}
	
	/**
	 * This method constructs a ferry with a speicified maximum speed and maximum load.
	 * @param maxSpeed The maximum speed.
	 * @param maxLoad The maximum load.
	 */
	public Ferry(double maxSpeed, double maxLoad) {
		this.maxSpeed = maxSpeed;
		this.maxLoad = maxLoad;
	}
	
	/**
	 * This method constructs a copy of a pre-exisiting ferry.
	 * @param otherFerry An instanceof Ferry.
	 */
	public Ferry(Ferry otherFerry) {
		if (otherFerry != null) {
			this.maxSpeed = otherFerry.maxSpeed;
			this.maxLoad = otherFerry.maxLoad;
		}
	}
	
	/**
	 * This method retrieves the maximum speed of the ferry. 
	 * @return The maximum speed.
	 */
	public double getMaxSpeed() {
		return this.maxSpeed;
	}
	
	/**
	 * This method retrieves the maximum load that the ferry can carry.
	 * @return The maximum load.
	 */
	public double getMaxLoad() {
		return this.maxLoad;
	}
	
	/**
	 * This method sets the maximum speed of the ferry to a specified amount.
	 * @param maxSpeed The maximum speed.
	 */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed; 
	}
	
	/**
	 * This method sets the maximum load the ferry can carry to a specified amount.
	 * @param maxLoad The maximum load.
	 */
	public void setMaxLoad(double maxLoad) {
		this.maxLoad = maxLoad;
	}
	
	/**
	 * This method returns a description of the vehicle, with regard to its maximum speed and maximum load.
	 * @return Vehicle description with regard to maximum speed and maximum load.
	 */
	@Override
	public String toString() {
		return ("This " + this.getClass().getSimpleName() + " has a maximum speed of " + Math.round(this.maxSpeed) + "km/hr and a maximum load of " + Math.round(this.maxLoad) + "kg.");
	}
	
	/**
	 * This method compares two objects and determines whether or not they are of type Ferry and 
	 * share the same contents.
	 * @param otherObject Any object.
	 * @return True if the objects are of type Ferry and share the same contents and False if
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
		Ferry otherFerry = (Ferry) otherObject;
		return (this.maxSpeed == otherFerry.maxSpeed && this.maxLoad == otherFerry.maxLoad);
	}
	
	/**
	 * This method overrides the copy method in the base class (Vehicle) and returns a copy of the Vehicle that invokes this method.
	 * @return An object of type Ferry.
	 */
	public Ferry copy() {
		return new Ferry(this);
	}
}
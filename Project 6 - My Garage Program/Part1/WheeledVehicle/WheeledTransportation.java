package WheeledVehicle;

//-----------------------------------------------------
//Assignment 2
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import Superclass.Vehicle;

/**
* The WheeledTransportation class is designed to contain the most basic attributes/methods which any wheeled vehicle should possess.
* @author Kcour
*
*/
public class WheeledTransportation extends Vehicle{
	/**
	 * Stores the number of wheels the vehicle has.
	 */
	private int numOfWheels;
	
	/**
	 * Stores the maximum speed the vehicle can reach.
	 */
	private double maxSpeed;
	
	/**
	 * This method constructs a WheeledTransportation object with a default number of wheels and maximum speed.
	 */
	public WheeledTransportation() {
		this.numOfWheels = 0;
		this.maxSpeed = 0.0;
	}
	
	/**
	 * This method constructs a WheeledTransportation object with a specified number of wheels and maximum speed.
	 * @param numOfWheels The number of wheels.
	 * @param maxSpeed The maximum speed.
	 */
	public WheeledTransportation(int numOfWheels, double maxSpeed) {
		this.numOfWheels = numOfWheels;
		this.maxSpeed = maxSpeed;
	}
	
	/**
	 * This method constructs a copy of a pre-existing WheeledTransportation object.
	 * @param otherWheeledTransportation An instance of WheeledTransportation.
	 */
	public WheeledTransportation(WheeledTransportation otherWheeledTransportation) {
		if (otherWheeledTransportation != null) {
			this.numOfWheels = otherWheeledTransportation.numOfWheels;
			this.maxSpeed = otherWheeledTransportation.maxSpeed;
		}
	}
	
	/**
	 * This method retrieves the number of wheels the vehicle has.
	 * @return The number of wheels.
	 */
	public int getNumOfWheels() {
		return this.numOfWheels;
	}
	
	/**
	 * This method retrieves the maximum speed the vehicle can reach.
	 * @return The maximum speed.
	 */
	public double getMaxSpeed() {
		return this.maxSpeed;
	}
	
	/**
	 * This method sets the number of wheels the vehicle has.
	 * @param numOfWheels The number of wheels.
	 */
	public void setNumOfWheels(int numOfWheels) {
		this.numOfWheels = numOfWheels;
	}
	
	/**
	 * This method sets the maximum speed of the vehicle.
	 * @param maxSpeed The maximum speed.
	 */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	/**
	 * This method returns a description of the vehicle, with regard to the number of wheels it has and its maximum speed.
	 * @return Vehicle description regarding the number of wheels and maximum speed.
	 */
	@Override
	public String toString() {
		if (this.getClass().getSimpleName().equals("Monowheel") && this.numOfWheels == 1) { //why does this.getClass.equals("class Package4.Monowheel") not work?
			return ("This Monowheel vehicle has 1 wheel and a maximum speed of " + Math.round(this.maxSpeed) + " km/hr.");
		}
		if (this.getClass().getSimpleName().equals("WheeledTransportation")) {
			if (this.numOfWheels == 1) {
				return ("This WheeledTransportation vehicle has 1 wheel and a maximum speed of " + Math.round(this.maxSpeed) + " km/hr.");
			}
			return ("This WheeledTransportation vehicle has " + this.numOfWheels + " wheels and a maximum speed of " + Math.round(this.maxSpeed) + " km/hr.");
		}
		return ("This " + this.getClass().getSimpleName() + " has " + this.numOfWheels + " wheels and a maximum speed of " + Math.round(this.maxSpeed) + " km/hr.");
	}
	
	/**
	 * This method compares two objects and determines whether or not they are of type WheeledTransportation and 
	 * share the same contents.
	 * @param otherObject Any object.
	 * @return True if the objects are of type WheeledTransportation and share the same contents and False if at least one of
	 * these conditions is not met.
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		}
		if (this.getClass() != otherObject.getClass()) {
			return false;
		}
		WheeledTransportation otherWheeledTransportation = (WheeledTransportation) otherObject;
		return (this.numOfWheels == otherWheeledTransportation.numOfWheels && this.maxSpeed == otherWheeledTransportation.maxSpeed); 
	}
	/**
	 * This method overrides the copy method in the base class (Vehicle) and returns a copy of the Vehicle that invokes this method.
	 * @return An object of type WheeledTransportation.
	 */
	public WheeledTransportation copy() {
		return new WheeledTransportation(this);
	}
}
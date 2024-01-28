package SingleWheeledVehicle;

//-----------------------------------------------------
//Assignment 2
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import WheeledVehicle.WheeledTransportation;

/**
* The Monowheel class is designed to contain the most basic attributes/methods which any monowheel vehicle should possess.
* @author Kevin Courey
*
*/
public class Monowheel extends WheeledTransportation{
	
	/**
	 * Stores the maximum weight of the monowheel vehicle.
	 */
	private double maxWeight;
	
	/**
	 * This method constructs a monowheel vehicle with a defult number of wheels, maximum speed and maximum weight.
	 */
	public Monowheel() {
		super();
		this.maxWeight = 0.0;
	}
	
	/**
	 * This method constructs a monowheel vehicle with a specified number of wheels, maximum speed and maximum weight.
	 * @param numOfWheels The number of wheels.
	 * @param maxSpeed The maximum speed.
	 * @param maxWeight The maximum weight.
	 */
	public Monowheel(int numOfWheels, double maxSpeed, double maxWeight) {
		super(numOfWheels, maxSpeed);
		this.maxWeight = maxWeight;
	}
	
	/**
	 * This method constructs a copy of a pre-existing monowheel vehicle.
	 * @param otherMonowheel An instanceof Monowheel.
	 */
	public Monowheel(Monowheel otherMonowheel) {
		super(otherMonowheel);
		if (otherMonowheel != null) {
			this.maxWeight = otherMonowheel.maxWeight;
		}
	}
	
	/**
	 * This method retrieves the maximum weight of the monowheel vehicle.
	 * @return The maximum weight.
	 */
	public double getMaxWeight() {
		return this.maxWeight;
	}
	
	/**
	 * This method sets the maximum weight of the monowheel vehicle.
	 * @param maxWeight The maximum weight.
	 */
	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}
	
	/**
	 * This method returns a description of the vehicle, with regard to the number of wheels
	 * it has, its maximum speed and maximum weight.
	 * @return Vehicle description regarding the number of
	 * wheels it has, its maximum speed and maximum weight.
	 */
	@Override
	public String toString() {
		if (this.getNumOfWheels() != 1) {
			return ("Error. This is not a Monowheel vehicle.");
		}
		return (super.toString() + " The maximum weight it can support is " + Math.round(this.maxWeight) + "kg.");
	}
	
	/**
	 * This method compares two objects and determines whether or not they are of type Monowheel and 
	 * share the same contents.
	 * @param otherObject Any object.
	 * @return True if the objects are of type Monowheel and share the same contents and False if
	 * at least one of these conditions is not met.
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) {
			return false;
		}
		Monowheel otherMonowheel = (Monowheel) otherObject;
		return (this.maxWeight == otherMonowheel.maxWeight);
	}
	
	/**
	 * This method overrides the copy method in the base class (Vehicle) and returns a copy of the Vehicle that invokes this method.
	 * @return An object of type Monowheel.
	 */
	public Monowheel copy() {
		return new Monowheel(this);
	}
}
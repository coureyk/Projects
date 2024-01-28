package Underground;

//-----------------------------------------------------
//Assignment 2
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import Locomotive.Train;

/**
* The Metro class is designed to contain the most basic attributes/methods which any metro vehicle should possess.
* @author Kevin Courey
*
*/
public class Metro extends Train{
	/**
	 * Stores the number of stops between the metro's starting and destinations stations.
	 */
	private int numOfStops;
	
	/**
	 * This method constructs a metro vehicle with a default number of wheels, maximum speed, number of vehicles, starting and destination stations
	 * and number of stops.
	 */
	public Metro() {
		super();
		this.numOfStops = 0;
	}
	
	/**
	 * This method constructs a metro vehicle with a specified number of wheels, maximum speed, number of vehicles, starting and destination stations
	 * and number of stops.
	 * @param numOfWheels The number of wheels.
	 * @param maxSpeed The maximum speed.
	 * @param numOfVehicles The number of vehicles.
	 * @param startingStation The starting station.
	 * @param destinationStation The destination station.
	 * @param numOfStops The number of stops.
	 */
	public Metro(int numOfWheels, double maxSpeed, int numOfVehicles, String startingStation, String destinationStation, int numOfStops) {
		super(numOfWheels, maxSpeed, numOfVehicles, startingStation, destinationStation);
		this.numOfStops = numOfStops;
	}
	
	/**
	 * This method constructs a copy of a pre-existing metro vehicle.
	 * @param otherMetro An instanceof Metro.
	 */
	public Metro(Metro otherMetro) {
		super(otherMetro);
		if (otherMetro != null) {
			this.numOfStops = otherMetro.numOfStops;
		}
	}
	
	/**
	 * This method retrieves the number of stops the metro has to make.
	 * @return The number of stops.
	 */
	public int getNumOfStops() {
		return this.numOfStops;
	}
	
	/**
	 * This method sets the number of stops the metro has to make.
	 * @param numOfStops The number of stops.
	 */
	public void setNumOfStops(int numOfStops) {
		this.numOfStops = numOfStops;
	}
	
	/**
	 * This method returns a description of the vehicle, with regard to the number of
	 * wheels it has, its maximum speed, the number of vehicles which it is
	 * composed of and its starting and destination stations.
	 * @return Vehicle description regarding the number of
	 * wheels it has, its maximum speed, the number of vehicles which it is
	 * composed of, its starting and destination stations and the number of stops
	 * it has to make.
	 */
	@Override
	public String toString() {
		return (super.toString() + " There are " + this.numOfStops + " stations it must stop at throughout the duration of its trip.");
	}
	
	/**
	 * This method compares two objects and determines whether or not they are of type Metro and 
	 * share the same contents.
	 * @param otherObject Any object.
	 * @return True if the objects are of type Metro and share the same contents and False if
	 * at least one of these conditions is not met.
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) {
			return false;
		}
		Metro otherMetro = (Metro) otherObject;
		return (this.numOfStops == otherMetro.numOfStops);
	}
	
	/**
	 * This method overrides the copy method in the base class (Vehicle) and returns a copy of the Vehicle that invokes this method.
	 * @return An object of type Metro.
	 */
	public Metro copy() {
		return new Metro(this);
	}
}
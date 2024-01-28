package Locomotive;

//-----------------------------------------------------
//Assignment 2
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import WheeledVehicle.WheeledTransportation;

/**
* The Train class is designed to contain the most basic attributes/methods which any train should possess.
* @author Kevin Courey
*
*/
public class Train extends WheeledTransportation {
	/**
	 * Stores the number of vehicles which make up the train.
	 */
	private int numOfVehicles;
	
	/**
	 * Stores the train's starting station.
	 */
	private String startingStation;
	
	/**
	 * Stores the train's destination station.
	 */
	private String destinationStation;
	
	/**
	 * This method constructs a train with a default number of number of wheels, maximum speed, number of vehicles, and a starting and destination station.
	 */
	public Train() {
		super();
		this.numOfVehicles = 0;
		this.startingStation = null;
		this.startingStation = null;
	}
	
	/**
	 * This method constructs a train with a specified number of number of wheels, maximum speed, number of vehicles, and a starting and destination station.
	 * @param numOfWheels The number of wheels.
	 * @param maxSpeed The maximum speed.
	 * @param numOfVehicles The number of vehicles.
	 * @param startingStation The starting station.
	 * @param destinationStation The destination station.
	 */
	public Train(int numOfWheels, double maxSpeed, int numOfVehicles, String startingStation, String destinationStation) {
		super(numOfWheels, maxSpeed);
		this.numOfVehicles = numOfVehicles;
		this.startingStation = startingStation;
		this.destinationStation = destinationStation;
	}
	
	/**
	 * This method constructs a copy of a pre-exisitng train.
	 * @param otherTrain An instanceof Train.
	 */
	public Train(Train otherTrain) {
		super(otherTrain);
		if (otherTrain != null) {
			this.numOfVehicles = otherTrain.numOfVehicles;
			this.startingStation = otherTrain.startingStation;
			this.destinationStation = otherTrain.destinationStation;
		}
	}
	
	/**
	 * This method retrieves the number of vehicles which make up the train.
	 * @return The number of vehicles.
	 */
	public int getNumOfVehicles() {
		return this.numOfVehicles;
	}
	
	/**
	 * This method retrieves the train's starting station.
	 * @return The starting station.
	 */
	public String getStartingStation() {
		return this.startingStation;
	}
	
	/**
	 * This method retrieves the train's destination station.
	 * @return The destination station.
	 */
	public String getDestinationStation() {
		return this.destinationStation;
	}
	
	/**
	 * This method sets the number of vehicles which make up the train.
	 * @param numOfVehicles The number of vehicles.
	 */
	public void setNumOfVehicles(int numOfVehicles) {
		this.numOfVehicles = numOfVehicles;
	}
	
	/**
	 * This method sets the train's starting station.
	 * @param startingStation The starting station.
	 */
	public void setStartingStation(String startingStation) {
		this.startingStation = startingStation;
	}
	
	/**
	 * This method sets the train's destination station.
	 * @param destinationStation The destination station.
	 */
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}
	
	/**
	 * This method returns a description of the vehicle, with regard to the number of
	 * wheels it has, its maximum speed, the number of vehicles which it is
	 * composed of and its starting and destination stations.
	 * @return Vehicle description regarding the number of
	 * wheels it has, its maximum speed, the number of vehicles which it is
	 * composed of and its starting and destination stations
	 */
	@Override
	public String toString() {
		return (super.toString() + " It has " + this.numOfVehicles + " vehicles and its starting and destination stations are " + this.startingStation + " and " + this.destinationStation + ".");
	}
	
	/**
	 * This method compares two objects and determines whether or not they are of type Train and 
	 * share the same contents.
	 * @param otherObject Any object.
	 * @return True if the objects are of type Train and share the same contents and False if
	 * at least one of these conditions is not met.
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) {
			return false;
		}
		Train otherTrain = (Train) otherObject;
		return (this.numOfVehicles == otherTrain.numOfVehicles && this.startingStation == otherTrain.startingStation && this.destinationStation == otherTrain.destinationStation);
	}
	
	/**
	 * This method overrides the copy method in the base class (Vehicle) and returns a copy of the Vehicle that invokes this method.
	 * @return An object of type Train.
	 */
	public Train copy() {
		return new Train(this);
	}
}

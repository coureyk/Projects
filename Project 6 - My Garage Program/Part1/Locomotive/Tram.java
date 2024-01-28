package Locomotive;

//-----------------------------------------------------
//Assignment 2
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import Underground.Metro;

/**
* The Tram class is designed to contain the most basic attributes/methods which any tram vehicle should possess.
* @author Kevin Courey
*
*/
public class Tram extends Metro {
	/**
	 * Stores the year the car was manufactured.
	 */
	private int makeYear;
	
	/**
	 * This method constructs a tram with a default number of number of wheels, maximum speed, number of vehicles,
	 * starting and destination station, number of stops and make year.
	 */
	public Tram() {
		super();
		this.makeYear = 0;
	}

	/**
	 * This method constructs a tram with a specified number of number of wheels, maximum speed, number of vehicles,
	 * starting and destination station, number of stops and make year.
	 * @param numOfWheels The number of wheels.
	 * @param maxSpeed The maximum speed.
	 * @param numOfVehicles The number of vehicles.
	 * @param startingStation The starting station.
	 * @param destinationStation The destination station.
	 * @param numOfStops The number of stops.
	 * @param makeYear The make year.
	 */
	public Tram(int numOfWheels, double maxSpeed, int numOfVehicles, String startingStation, String destinationStation, int numOfStops, int makeYear) {
		super(numOfWheels, maxSpeed, numOfVehicles, startingStation, destinationStation, numOfStops);
		this.makeYear = makeYear;
	}
	
	/**
	 * This method constructs a copy of a pre-existing tram.
	 * @param otherTram An instanceof Tram.
	 */
	public Tram(Tram otherTram) {
		super(otherTram);
		if (otherTram != null) {
			this.makeYear = otherTram.makeYear;
		}
	}
	
	/**
	 * This method retrieves the make year of the tram.
	 * @return makeYear The make year.
	 */
	public int getMakeYear() {
		return this.makeYear;
	}
	
	/**
	 * This method sets the make year of the tram.
	 * @param makeYear The make year.
	 */
	public void setMakeYear(int makeYear) {
		this.makeYear = makeYear;
	}
	
	/**
	 * This method returns a description of the vehicle, with regard to its number of wheels, maximum speed, number of vehicles,
	 * starting and destination stations, number of stops and make year.
	 * @return Vehicle description regarding the number of wheels it has, its maximum speed, number of vehicles, starting and destination
	 * stations, number of stops and make year.
	 */
	@Override
	public String toString() {
		return (super.toString() + " It was created in " + this.makeYear + ".");
	}
	
	/**
	 * This method compares two objects and determines whether or not they are of type Tram and 
	 * share the same contents.
	 * @param otherObject Any object.
	 * @return True if the objects are of type Tram and share the same contents and False if
	 * at least one of these conditions is not met.
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) {
			return false;
		}
		Tram otherTram = (Tram) otherObject;
		return (this.makeYear == otherTram.makeYear);
	}
	
	/**
	 * This method overrides the copy method in the base class (Vehicle) and returns a copy of the Vehicle that invokes this method.
	 * @return An object of type Tram.
	 */
	public Tram copy() {
		return new Tram(this);
	}
}
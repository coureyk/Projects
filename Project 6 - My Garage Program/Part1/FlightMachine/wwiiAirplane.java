package FlightMachine;

//-----------------------------------------------------
//Assignment 2
//Part: 1
//Written by: Kevin Courey 40245966
//-----------------------------------------------------


/**
* The wwiiAirplane class is designed to contain the most basic attributes/methods which any WWII Airplane should possess.
* @author Kevin Courey
*
*/
public class wwiiAirplane extends Aircraft {

	/**
	 * Stores a boolean value with regard to the presence of a twin engine within the WWII Airplane.
	 */
	private boolean twinEngine;
	
	/**
	 * This method constructs a WWII Airplane with a default price, maximum elevation and twin engine status.
	 */
	public wwiiAirplane() {
		super();
		this.twinEngine = false;
	}
	
	/**
	 * This method constructs a WWII Airplane with a specified price, maximum elevation and twin engine status.
	 * @param price The price.
	 * @param maxElevation The maximum elevation.
	 * @param twinEngine The twin engine.
	 */
	public wwiiAirplane(double price, double maxElevation, boolean twinEngine) {
		super(price, maxElevation);
		this.twinEngine = twinEngine;
	}
	
	/**
	 * This method constructs a copy of a pre-existing WWII Airplane.
	 * @param otherWWIIAirplane An instance of wwiiAirplane.
	 */
	public wwiiAirplane(wwiiAirplane otherWWIIAirplane) {
		super(otherWWIIAirplane);
		if (otherWWIIAirplane != null) {
			this.twinEngine = otherWWIIAirplane.twinEngine;
		}
	}
	
	/**
	 * This method retrieves the status regarding the presence of a twin engine within the WWII Airplane.
	 * @return The twin engine status.
	 */
	public boolean getTwinEngine() {
		return twinEngine;
	}
	
	/**
	 * This method sets the status regarding the presence of a twin engine within the WWII Airplane.
	 * @param twinEngine The twin engine status.
	 */
	public void setTwinEngine(boolean twinEngine) {
		this.twinEngine = twinEngine;
	}
	
	/**
	 * This method returns a description of the vehicle, with regard to its price, maximum elevation
	 * and twin engine status.
	 * @return Vehicle description with regrd to price, maximum elevation and twin engine status.
	 */
	@Override
	public String toString() {
		if (this.twinEngine) {
			return (super.toString() + " It also has a twin engine.");
		} else {
			return (super.toString() + " However, it does not have a twin engine.");
		}
	}
	
	/**
	 * This method compares two objects and determines whether or not they are of type wwiiAirplane and 
	 * share the same contents.
	 * @param otherObject Any object.
	 * @return True if the objects are of type wwiiAirplane and share the same contents and False if
	 * at least one of these conditions is not met.
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) {
			return false;
		}
		wwiiAirplane otherWWIIAirplane = (wwiiAirplane) otherObject;
		return (this.getPrice() == otherWWIIAirplane.getPrice() && this.getMaxElevation() == otherWWIIAirplane.getMaxElevation() && this.twinEngine == otherWWIIAirplane.twinEngine);
	}
	
	/**
	 * This method overrides the copy method in the base class (Vehicle) and returns a copy of the Vehicle that invokes this method.
	 * @return An object of type wwiiAirplane.
	 */
	public wwiiAirplane copy() {
		return new wwiiAirplane(this);
	}
}
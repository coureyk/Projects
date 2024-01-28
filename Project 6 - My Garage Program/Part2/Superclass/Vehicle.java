package Superclass;

import Superclass.Vehicle;

/**
 * The Vehicle class is designed to contain a copy method which must be overridden in all of its child classes.
 * @author Kevin Courey
 *
 */
public abstract class Vehicle {
	
	/**
	 * This method invokes the default constructor of the Object class.
	 */
	protected Vehicle() {
		super();
	}
	
	/**
	 * This method must be overriden by all extensions of the Vehicle class.
	 * @return An object of type Vehicle.
	 */
	public abstract Vehicle copy();
}


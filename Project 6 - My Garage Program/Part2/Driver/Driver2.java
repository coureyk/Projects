package Driver;

//-----------------------------------------------------
//Assignment 2
//Part: 2
//Written by: Kevin Courey 40245966
//-----------------------------------------------------

import WheeledVehicle.WheeledTransportation;
import Locomotive.Train;
import Locomotive.Tram;
import Underground.Metro;
import SingleWheeledVehicle.Monowheel;
import FlightMachine.Aircraft;
import FlightMachine.wwiiAirplane;
import Watercraft.Ferry;
import Superclass.Vehicle;

/**
* The Driver class is designed to invoke the methods from the vehicle classes in order to display and compare them. 
* @author Kevin Courey
*
*/
public class Driver2 {

	/**
	 * This method takes in an array of objects of type Vehicle and returns a copy of it.
	 * @param arrayOfVehicles An array of vehicle objects.
	 * @return A copy of the the array of vehicle objects.
	 */
	public static Vehicle[] copyOfVehicles(Vehicle arrayOfVehicles[]) {
		Vehicle copyOfVehicles[] = new Vehicle[arrayOfVehicles.length];
		
		for (int i = 0; i < arrayOfVehicles.length; i++) {
			if (arrayOfVehicles[i] != null) {
				copyOfVehicles[i] = arrayOfVehicles[i].copy();
			}
		}
		return copyOfVehicles;
	}
	
	/**
	 * This method utilizes all other methods that were defined in the Part1 folder and invokes them as required
	 * to display all necessary information to the user.
	 * 
	 * @param args An array of strings which stores arguments passed by command line while starting a program.
	 */
	public static void main(String[] args) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("                  Welcome to my Garage Program");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println();
		WheeledTransportation vehicle1 = new WheeledTransportation(4, 180);
		WheeledTransportation vehicle2 = new Train(12, 59, 15, "Gare Centrale", "Ottawa");
		WheeledTransportation vehicle3 = new Metro(24, 70, 13, "Angrignon", "Honore-Beaugrand", 27);
		WheeledTransportation vehicle4 = new Tram(4, 75, 11, "Northgate Station", "Angel Lake Station", 19, 1956);
		WheeledTransportation vehicle5 = new Monowheel(1, 20, 60);
		Ferry vehicle6 = new Ferry(45, 42700);
		Aircraft vehicle7 = new Aircraft(200000, 40000);
		Aircraft vehicle8 = new wwiiAirplane(600000, 45000, true);
		Train vehicle9 = new Train((Train) vehicle2);
		Metro vehicle10 = new Metro(24, 70, 13, "Angrignon", "Honore-Beaugrand", 27);
		Tram vehicle11 = new Tram(3, 80, 11, "Northgate Station", "Timbuktu", 10, 1956);
		Monowheel vehicle12 = new Monowheel();
		wwiiAirplane vehicle13 = new wwiiAirplane(300000, 42000, false);
		Train vehicle14 = new Train(12, 59, 15, "Saint-Lambert", "Ottawa");
		Metro vehicle15 = new Tram((Tram)vehicle11);
		Train vehicle16 = null;
		Train vehicle17 = new Train(vehicle15);
		Aircraft vehicle18 = new Aircraft(500000, 47000);
		WheeledTransportation vehicle19 = new Train();
		WheeledTransportation vehicle20 = new Metro();
		WheeledTransportation vehicle21 = new Tram();
		WheeledTransportation vehicle22 = new Monowheel();
		Ferry vehicle23 = new Ferry();
		Metro vehicle24 = new Metro();
		Train vehicle25 = new Train();
		Tram vehicle26 = new Tram();
		
		Vehicle vehicle27 = new WheeledTransportation(7, 200);
		
		
		Vehicle vehicle[] = {vehicle1, vehicle2, vehicle3, vehicle4, vehicle5, vehicle6, vehicle7, vehicle8, vehicle9, vehicle10, vehicle11, vehicle12, vehicle13, vehicle14, vehicle15, vehicle16, vehicle17, vehicle18, vehicle19, vehicle20, vehicle21, vehicle22, vehicle23, vehicle24, vehicle25, vehicle26, vehicle27};
		Vehicle vehicleCopy[] = null;
		
		if (vehicle == null) {
			String empty = "\tThis list of vehicles is empty\n";
			System.out.printf("Vehicle Descriptions%n%n%s%n%nCopied Vehicle Descriptions%n%n%s",empty,empty);
		} else {
			System.out.printf("Original Vehicle Desciptions%n%n");
			for (int i = 0; i < vehicle.length; i++) {
				System.out.printf("\tVehicle %d: ", (i + 1));
				if (vehicle[i] != null) {
					System.out.println(vehicle[i].toString()); //Dynamic Binding
				} else {
					System.out.println("This vehicle does not exist.");
				}
			}
			System.out.println();
			
			vehicleCopy = Driver2.copyOfVehicles(vehicle);
			
			System.out.printf("Copied Vehicle Descriptions%n%n");
			for (int i = 0; i < vehicleCopy.length; i++) {
				System.out.printf("\tVehicle %d: ", (i + 1));
				if (vehicleCopy[i] != null) {
					System.out.println(vehicleCopy[i].toString()); //Dynamic Binding
				} else {
					System.out.println("This vehicle does not exist.");
				}
			}
		}
		
		//Testing whether or not the method copyOfVehicles returns a deep copy.
		((Train)vehicle[1]).setDestinationStation("something");
		((Train)vehicle[1]).setStartingStation("something");
		((Train)vehicle[1]).setMaxSpeed(100000);
		((Train)vehicle[1]).setNumOfVehicles(200000);
		((Train)vehicle[1]).setNumOfWheels(300000);
		
		System.out.println();
		System.out.println(vehicle[1]);
		System.out.println(vehicleCopy[1]);
	}	
}
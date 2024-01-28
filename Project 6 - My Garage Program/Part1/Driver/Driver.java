package Driver;

//-----------------------------------------------------
//Assignment 2
//Part: 1
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
public class Driver {
	
	/**
	 * This method takes in an array of Vehicles and displays to the user which (if any) of these vehicles are
	 * the most and least expensive Aircrafts. 
	 * 
	 * @param vehicle An array of Vehicles.
	 */
	public static void findLeastAndMostExpensiveAircraft(Vehicle[] vehicle) {
		int aircraftCount = 0;
		System.out.printf("%n%nAircraft Price Ranking%n%n");

		//Ensures an error will not be thrown if the array passed is null.
		if (vehicle == null) {
			System.out.println("\tThere are no aircrafts to choose from.");
		} else {
			//Counts the number of aircrafts within the array of objects that was passed.
			for (int i = 0; i < vehicle.length; i++) {
				if (vehicle[i] instanceof Aircraft) {
					aircraftCount++;
				}
			}

			if (aircraftCount > 0) {
				/*
				 * Initializes two arrays (one for storing aircrafts and the other for storing their
				 * respective Vehicle #).
				 */
				Aircraft aircraft[] = new Aircraft[aircraftCount];
				int vehicleNum[] = new int[aircraftCount];
				
				/*
				 * Loops through the array of objects that was passed and stores all aircrafts inside the
				 * the aircraft array, and their respective vehicle numbers inside the vehicleNum array.
				 */
				int indexCount = 0;
				for (int i = 0; i < vehicle.length; i++) {
					if (vehicle[i] instanceof Aircraft) {
						aircraft[indexCount] = ((Aircraft)vehicle[i]).copy();
						vehicleNum[indexCount] = i + 1;
						indexCount++;
					}
				}
				
				//Bubble Sorting Method
				for (int i = 0; i < aircraft.length; i++) {
					for (int j = 1; j < aircraft.length - i; j++) {
						if (aircraft[j].getPrice() < aircraft[j-1].getPrice()) {
							Aircraft tempAircraft = aircraft[j-1];
							aircraft[j-1] = aircraft[j];
							aircraft[j] = tempAircraft;
							
							int tempNum = vehicleNum[j-1];
							vehicleNum[j-1] = vehicleNum[j];
							vehicleNum[j] = tempNum;
						}
					}
				}	
				System.out.printf("\tLeast Expensive Aircraft: Vehicle %02d ($%.2f).\n\tMost Expensive Aircraft:  Vehicle %02d ($%.2f).%n", vehicleNum[0], aircraft[0].getPrice(), vehicleNum[aircraftCount - 1], aircraft[aircraftCount - 1].getPrice());
			} else {
				System.out.println("\tThere are no aircrafts to choose from.");
			}
		}
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
		
		Vehicle vehicle[] = {vehicle1, vehicle2, vehicle3, vehicle4, vehicle5, vehicle6, vehicle7, vehicle8, vehicle9, vehicle10, vehicle11, vehicle12, vehicle13, vehicle14, vehicle15, vehicle16, vehicle17, vehicle18, vehicle19, vehicle20, vehicle21, vehicle22, vehicle23, vehicle24, vehicle25, vehicle26};
		Vehicle noAircrafts[] = {vehicle1, vehicle2, vehicle3, vehicle4, vehicle5, vehicle6, vehicle9, vehicle10, vehicle11, vehicle12, vehicle14, vehicle15, vehicle16, vehicle17, vehicle19, vehicle20, vehicle21, vehicle22, vehicle23, vehicle24, vehicle25, vehicle26};
				
		if (vehicle == null) {
			String empty = "\tThis list of vehicles is empty.\n";
			System.out.printf("Vehicle Descriptions%n%n%s%n%nList of Vehicles With Same Description%n%n%s",empty,empty);
		} else {
			System.out.printf("Vehicle Descriptions%n%n");
			for (int i = 0; i < vehicle.length; i++) {
				System.out.printf("\tVehicle %02d: ", (i + 1));	
				
				if (vehicle[i] != null) {
					System.out.println(vehicle[i].toString()); //Dynamic Binding
				} else {
					System.out.println("This vehicle does not exist.");
				}
			}
			
			System.out.printf("%n%nList of Vehicles With Same Description%n%n");
			boolean atLeastOneMatch = false;
			for (int i = 0; i < vehicle.length; i++) {
				for (int j = i + 1; j < vehicle.length; j++) {
					if (vehicle[i] != null && vehicle[j] != null && vehicle[i].toString().equals(vehicle[j].toString())) { //Dynamic Binding
						System.out.printf("\tVehicles %02d and %02d are the same %s.%n", i + 1, j + 1, vehicle[j].getClass().getSimpleName()); //Dynamic Binding
						atLeastOneMatch = true;
					}
				}
				if (i == vehicle.length - 1 && !atLeastOneMatch) {
					System.out.println("\tThere are no vehicles with the same description.");
				}
			}
		}
	
		Driver.findLeastAndMostExpensiveAircraft(vehicle);
		Driver.findLeastAndMostExpensiveAircraft(noAircrafts);
		
		System.out.printf("%n%n%nThank you for using my Garage program!%n");
	}
}

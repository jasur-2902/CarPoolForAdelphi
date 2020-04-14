package CsProject;

import java.util.ArrayList;

/**
 * @authors Jasur Shukurov && Matt Vang
 * Ride object - this object contains information about a particular ride
 * 
 * @version 1.0 04/09/2020
 */

public class Ride {

	
	/** Ride Information */
	
	// Ride ID# 
	private int rideId; 
	
	// PickUp Location & Destination 
	private String pickUpSpot;
	private String pickUpDestination;
	
	// PickUp Time and Date information 
	private int pickUpDay;
	private int pickUpMonth;
	private int pickUpYear;
	private int pickUpHour; 
	private int pickUpMinute; 
	
	// Driver information
	private Driver driverInformation; 

	// Passengers 
	private ArrayList<Passenger> passengers;
	
	//Seats 
	private int numberOfSeats; 

	

	/**
	 * Constructor to create a Ride object 
	 */
	public Ride(int rideId, String pickUpSpot, int pickUpDay, 
				int pickUpMonth, int pickUpYear, int pickUpHour, 
			 	int pickUpMinute, Driver driverInformation, 
			 	int numberOfSeats, String pickUpDestination) {
		
		this.rideId = rideId;
		this.pickUpSpot = pickUpSpot;
		this.pickUpDestination = pickUpDestination;
		this.pickUpDay = pickUpDay;
		this.pickUpMonth = pickUpMonth;
		this.pickUpYear = pickUpYear;
		this.pickUpHour = pickUpHour;
		this.pickUpMinute = pickUpMinute;
		this.driverInformation = driverInformation;
		this.numberOfSeats = numberOfSeats;
		
		// Creates array of Passengers 
		this.passengers = new ArrayList<Passenger>();

	}
	
	
	/**
	 * join - this functions add new passenger to the ride.
	 * 
	 * @param takes as a parametr passenger object. 
	 * @return returns confirmation or error message. 
	 */
	public String join(Passenger passenger) {
		
		// Returns Message if seats are full 
		if(this.passengers.size() >= this.numberOfSeats)
			return "Sorry, we can't add you to this Ride, seats are full";
		
		// Adds passenger to the ride 
		this.passengers.add(passenger); 
		
		// Returns confirmation 
		return "You have been added to this ride! Your ride is on " + this.pickUpDay + "/" + this.pickUpMonth;
	}

	

	
	// toString method which returns ride information as a String
	public String toString() {
		return "Ride [rideId=" + rideId + ", pickUpSpot=" + pickUpSpot + ", pickUpDestination=" + pickUpDestination
				+ ", pickUpDay=" + pickUpDay + ", pickUpMonth=" + pickUpMonth + ", pickUpYear=" + pickUpYear
				+ ", pickUpHour=" + pickUpHour + ", pickUpMinute=" + pickUpMinute + "]";
	}


	
	// Getters and Setters for Ride Object 
	public int getRideId() {
		return rideId;
	}


	public void setRideId(int rideId) {
		this.rideId = rideId;
	}


	public String getPickUpSpot() {
		return pickUpSpot;
	}


	public void setPickUpSpot(String pickUpSpot) {
		this.pickUpSpot = pickUpSpot;
	}


	public String getPickUpDestination() {
		return pickUpDestination;
	}


	public void setPickUpDestination(String pickUpDestination) {
		this.pickUpDestination = pickUpDestination;
	}


	public int getPickUpDay() {
		return pickUpDay;
	}


	public void setPickUpDay(int pickUpDay) {
		this.pickUpDay = pickUpDay;
	}


	public int getPickUpMonth() {
		return pickUpMonth;
	}


	public void setPickUpMonth(int pickUpMonth) {
		this.pickUpMonth = pickUpMonth;
	}


	public int getPickUpYear() {
		return pickUpYear;
	}


	public void setPickUpYear(int pickUpYear) {
		this.pickUpYear = pickUpYear;
	}


	public int getPickUpHour() {
		return pickUpHour;
	}


	public void setPickUpHour(int pickUpHour) {
		this.pickUpHour = pickUpHour;
	}


	public int getPickUpMinute() {
		return pickUpMinute;
	}


	public void setPickUpMinute(int pickUpMinute) {
		this.pickUpMinute = pickUpMinute;
	}


	public Driver getDriverInformation() {
		return driverInformation;
	}


	public void setDriverInformation(Driver driverInformation) {
		this.driverInformation = driverInformation;
	}


	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}


	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}


	public int getNumberOfSeats() {
		return numberOfSeats;
	}


	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	

}

package CsProject;

import java.io.*;
import java.util.ArrayList;

/**
 * @authors Jasur Shukurov && Matt Vang
 * Driver - Driver object 
 * 
 * @version 1.0 04/09/2020
 */


// NOTE: I am using Profile as a parent class
public class Driver extends Profile implements Serializable {

	// Serializable ID 
	private static final long serialVersionUID = 1L;
   
	// Car object 
    private Car car;
    
    // ArrayList of rides which belongs to this driver
    private ArrayList<Ride> driverRides; 
    
    // Default Constructor with some default values 
    public Driver(){
    	super(); 
    	this.car = new Car();
    	this.driverRides = new ArrayList<Ride>(); 
    }
    
    // Driver object Constructor
    public Driver (String name, String ID, String email, Car car) {
    	super(name,ID,email);
    	this.car = car; 
    	this.driverRides = new ArrayList<Ride>(); 
    }
    
    
    // This method Returns all the rides as a String 
    public String displayAll() {
		
		String result = "" ; 
				
		// If there are no rides, it returns error message.
		if(driverRides.size() == 0) {
			return "Sorry there are no rides"; 
		}
		
		
		// Adds all rides to single String
		for(int i = 0; i < driverRides.size(); i++) {
			result += driverRides.get(i).toString() + "\n"; 
		}
		
		return result; 
	}
    
 	// This method add new ride to the arrayList 	
	public void addRide(Ride ride)                 
    {
    	this.driverRides.add(ride);  
    }
    
    // Getters and Setters
    public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
    
}

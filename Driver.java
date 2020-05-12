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
    private RideList driverRides; 
    
    // Default Constructor with some default values 
    public Driver(){
    	super(); 
    	this.car = new Car();
    	this.driverRides = new RideList(); 
    }
    
    // Driver object Constructor
    public Driver (String name, String ID, String email, Car car) {
    	super(name,ID,email);
    	this.car = car; 
    	this.driverRides = new RideList(); 
    }
    
    
    // This method Returns all the rides as a String 
    public String displayAll() {
		
		String result = driverRides.displayAll();
		
		return result; 
	}
    
 	// This method add new ride to the arrayList 	
	public void addRide(Ride ride)                 
    {
    	this.driverRides.getList().add(ride);  
    }
    
    // Getters and Setters
    public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	// Gets number of rides driver has
	public int numOfRides(){
		return driverRides.getSize(); 
	}
    
}


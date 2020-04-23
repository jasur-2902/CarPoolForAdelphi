 

import java.io.*;
import java.util.ArrayList;

/**
 * @authors Jasur Shukurov && Matt Vang
 * Driver - Driver object 
 * 
 * @version 1.0 04/09/2020
 */


public class Passenger extends Profile implements Serializable {

    // Serializable ID 
    private static final long serialVersionUID = 1L;

    // ArrayList of rides which belongs to this driver
    private RideList passengerRides; 
    
    // Default Constructor with some default values 
    public Passenger(){
        super();      
        this.passengerRides = new RideList(); 
    }
    
    // Driver object Constructor
    public Passenger (String name, String ID, String email) {
        super(name,ID,email);
        this.passengerRides = new RideList(); 
    }
    
    
    // This method Returns all the rides as a String 
    public String displayAll() {
        
        String result = passengerRides.displayAll(); 
        return result; 
    }
    
    // This method add new ride to the arrayList    
    public void addRide(Ride ride)                 
    {
        this.passengerRides.getList().add(ride);  
    }
    
    
}

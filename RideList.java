package CsProject;

import java.util.ArrayList;


/**
 * @authors Jasur Shukurov && Matt Vang
 * Rides List - this object contains all the rides 
 * 
 * @version 1.0 04/09/2020
 */

public class RideList {

	// Array List of Ride objects 
	private ArrayList<Ride> rides; 
	
	
	// Constructor to creat Rides object 
	public RideList() {
		rides = new ArrayList<Ride>();
	}
	
	
	/**
	 * displayAll - this function displays adds all rides to the String and separates them with a new line
	 * 
	 * @return it returns string which contains all rides.
	 * 
	 * */
	public String displayAll() {
		
		String result = "" ; 
				
		// If there are no rides, it returns error message.
		if(rides.size() == 0) {
			return "Sorry there are no rides"; 
		}
		
		
		// Adds all rides to single String
		for(int i = 0; i < rides.size(); i++) {
			result = rides.get(i).toString() + "\n"; 
		}
		
		return result; 
	}

}

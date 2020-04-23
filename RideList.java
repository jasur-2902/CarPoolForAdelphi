 

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @authors Jasur Shukurov && Matt Vang
 * Rides List - this object contains all the rides 
 * 
 * @version 1.0 04/09/2020
 */

public class RideList implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
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
            result += (i+1) + ") " + rides.get(i).toString() + 
            "\n----------------------------------------------------------"; 
        }
        
        return result; 
    }
    
    // This function adds ride to the list
    public void addRide(Ride ride) {
        this.rides.add(ride); 
    }
    
    //get size of the list
    public ArrayList<Ride> getList(){
        return rides;
    }
    
    //Get the ride using ID given
    public Ride getRide(String ID)
    {
        
        Ride copy;
        for (int i = 0; i<rides.size(); i++){
            if (ID.equals(rides.get(i).getRideId())){
                return rides.get(i);
            }
        }
        return null;
    }
    
}

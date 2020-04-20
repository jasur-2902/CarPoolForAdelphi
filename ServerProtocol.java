package CsProject;

/**
 * @authors Jasur Shukurov && Matt Vang
 * ServerProtocol
 * 
 * @version 1.0 04/09/2020
 */

public class ServerProtocol {
    
	private static final int WAITING = 0;
    private static final int IN_CONVERSATION = 10;
    
    private static final int MENU_MESSAGE_DRIVER = 20;
    private static final int ADD_NEW_RIDE = 21;
    private static final int SEE_MY_RIDES = 22;
    private static final int SEE_ALL_RIDES = 23;
    
    private int state = WAITING;
    
    public Message processInput(Message theInput) {
        
    	Message theOutput = null;
               
        // Initial State, sending message that connections is established 
        if(state == WAITING) {
        	// Sending message to user, NOTE: I'm sending IN_CONVERSATION state, so user will be on the same state 
        	theOutput = new Message ("Server", "Connection Established!",IN_CONVERSATION);
        	state = IN_CONVERSATION;
        }
        else if (state == IN_CONVERSATION) {
        	   	
        		if (theInput.getName().equals("driver")) {
        			// Changing state to MENU_MESSAGE_DRIVER 
        			state = MENU_MESSAGE_DRIVER;
        			//Sending menu options to the driver menu 
        			theOutput = new Message ("Server", "Plesae enter corresponding number:"
        					+ "\n1 - Add new Ride"
        					+ "\n2 - See my Rides"
        					+ "\n3 - See all Rides"
        					+ "\n4 - Change Information"
        					+ "\n5 - My Car", MENU_MESSAGE_DRIVER);
        			}
        		
        		else if (theInput.getName().equals("passenger")) {
        			//Do some magic MATT :) 
        		}
        }
        else if (state == MENU_MESSAGE_DRIVER) {
        	// In this part I'm getting user input, and depanding on what option User chose, i do different tasks
        	// So if user selected 1, than I change state to ADD_NEW_RIDE, and await for user to send ride 
        	if(theInput.getMessage().equals("1")) {
        		state = ADD_NEW_RIDE;
    			theOutput = new Message ("Server", "Add new Ride", ADD_NEW_RIDE);
        	}
        	else if (theInput.getMessage().equals("2")) {
        		// If user selected 2 then we just sending simple message. Server is not doing anything specific,
        		// because displaying drivers rides is happening on the driver side, not on the server
        		state = IN_CONVERSATION; // I'm changing state to IN_CONVERSATION to get back to the menu
    			theOutput = new Message ("Server", "Displaying your Rides: " ,SEE_MY_RIDES);
        	}
        	else if (theInput.getMessage().equals("3")) {
        		// If User selected 3 then we are getting RideList from server (MultiServer.java) 
        		// and using displayAll method, we return String with all the rides 
        		state = IN_CONVERSATION;  
        		theOutput = new Message ("Server", "Displaying all Drivers Rides: \n" + MultiServer.getRideList().displayAll(), SEE_ALL_RIDES);
        		}
        	else {
        		state = IN_CONVERSATION;  // if user enters invalid input we just return to the menu
    			theOutput = new Message ("Server", "Invalid Input" , IN_CONVERSATION);
        	}
        		
        }
        // in this state we are awaiting driver to send ride
        else if (state == ADD_NEW_RIDE) {
        
        	if(theInput.getRide() != null) {
        		
        		state = IN_CONVERSATION;
        		
        		MultiServer.getRideList().addRide(theInput.getRide());; 
        		
        		theOutput = new Message ("Server", "The ride was successfully posted!",IN_CONVERSATION);
        		
        	}
        	
        }

        
        return theOutput;
        
        
    }
    
}
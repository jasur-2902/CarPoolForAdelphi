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
   private static final int CAR_INFO = 24;
   
   private static final int MENU_MESSAGE_PASSENGER = 30;
   private static final int GET_ON_RIDE = 31;
   private static final int FETCH_RIDE = 311;
   private static final int NEXT_RIDES = 32;
   
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
                   theOutput = new Message ("Server", "Please enter corresponding number:"
                           + "\n1 - Add new Ride"
                           + "\n2 - See my Rides"
                           + "\n3 - See all Rides"
                           
                           + "\n4 - My Car"
                           + "\n5 - End", MENU_MESSAGE_DRIVER);
                   }
               
               else if (theInput.getName().equals("passenger")) {
                  
                   //Change state to Menu Message for Passenger
                   state = MENU_MESSAGE_PASSENGER;
                   theOutput = new Message ("Server", "Please enter corresponding number:"
                           + "\n1 - See Available Rides"
                           + "\n2 - Add Ride Using ID"
                           + "\n3 - See your Rides"
                           + "\n4 - End"
                           , MENU_MESSAGE_PASSENGER);
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
               
           else if (theInput.getMessage().equals("4")){
               //If user enter 4, show his Car information
               state = IN_CONVERSATION;
               theOutput = new Message("Server","Your car information: \n",CAR_INFO);
           }
           
           else if (theInput.getMessage().equals("5"))
             {
                 state = IN_CONVERSATION;
                 theOutput = new Message ("Server","end",IN_CONVERSATION);
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
               
               //MultiServer.getRideList().addRide(theInput.getRide());
               
               theOutput = new Message ("Server", "The ride was successfully posted!",IN_CONVERSATION);
               
           }
       }
       // Menu processing for Passenger
       else if (state == MENU_MESSAGE_PASSENGER)
       {
           if(theInput.getMessage().equals("1")){
               // View available rides with ID to add
               state = IN_CONVERSATION;  
               theOutput = new Message ("Server", "Displaying all Rides: \n" + MultiServer.getRideList().displayAll(), SEE_ALL_RIDES);         
           }
           else if (theInput.getMessage().equals("2")){
               // Add ride with the ID
               state = GET_ON_RIDE;
               theOutput = new Message("Server", "Please enter ride ID to register", GET_ON_RIDE);
               
           }
           else if (theInput.getMessage().equals("3")){
               // Let passenger view their upcoming trips
               state = IN_CONVERSATION;
               theOutput = new Message("Server","Here are your next rides: \n",NEXT_RIDES);
           }
           //End conversation
           else if (theInput.getMessage().equals("4"))
             {
                 state = IN_CONVERSATION;
                 theOutput = new Message ("Server","end",IN_CONVERSATION);
              }
              
           //Invalid input, resend menu
           else {
               state = IN_CONVERSATION;  // if user enters invalid input we just return to the menu
               theOutput = new Message ("Server", "Invalid Input" , IN_CONVERSATION);
           }
       }
       
       
       else if (state == GET_ON_RIDE)
       {
           state = IN_CONVERSATION;
           Ride forPassenger = MultiServer.getRideList().getRide(theInput.getMessage());
           forPassenger.setNumberOfSeats(forPassenger.getNumberOfSeats()-1);
           theOutput = new Message("Server","The ride has been successfully posted to your account",forPassenger,FETCH_RIDE);
       }
       
       return theOutput;
       
       
   }
   
}
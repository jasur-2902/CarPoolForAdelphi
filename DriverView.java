package CsProject;

 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;


/**
 * @authors Jasur Shukurov && Matt Vang
 * DriverView - this class displays driver menu
 * 
 * @version 1.0 04/09/2020
 */


public class DriverView {
    
    // Different States of the Driver 
    private static final int WAITING = 0;
    private static final int IN_CONVERSATION = 10;
    
    private static final int MENU_MESSAGE_DRIVER = 20;
    private static final int ADD_NEW_RIDE = 21;
    private static final int SEE_MY_RIDES = 22;
    private static final int SEE_ALL_RIDES = 23;
    private static final int CAR_INFO = 24;


    static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    
    public static void main(String[] args) throws IOException {
        
        // Checking for the arguments (Port and Hostnames)
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String username; 
        Message messageOut = null;
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        
        // Changing state to Initial State - Waiting Result from Server 
        int state = WAITING;
        
        // Set up driver profile
        String name,ID,email,car,model,license,color;
        System.out.println("Welcome to Adelphi Carpool!");
        System.out.println("Please register as a driver here:");
        System.out.print("Enter name: ");
        name = stdIn.readLine();
        System.out.print("Enter ID: ");
        ID = stdIn.readLine();
        System.out.print("Enter contact email: ");
        email = stdIn.readLine();
        
        // Set up car profile
        System.out.print("Enter car license: ");
        license = stdIn.readLine();
        System.out.print("Enter its model: ");
        model = stdIn.readLine();
        System.out.print("Enter its color: ");
        color = stdIn.readLine();
        
        Car myCar = new Car(license,model,color);
        // Creating Driver object with specified info 
        Driver driver = new Driver(name,ID,email,myCar);


        // 2) Initiate a connection request 
        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            ObjectOutputStream out = new ObjectOutputStream(kkSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(kkSocket.getInputStream()); ) {
           
            Object fromServer;
            String fromUser = null;
          
            // Setting username to driver (Client will have username = "passenger")
            username = "driver";
            
            // 5 - Receives options from the server 
            while ((fromServer = in.readObject()) != null) {
            
                // 6 - Print message from Server. 
                 System.out.println(fromServer.toString());
                
                // Casting object from Server to Message object
                Message temp = (Message) fromServer; 
                
                //Getting state of the app from the server 
                state = temp.getState();
                                
       
                if(state == IN_CONVERSATION) {
                    
                     // This part is sending information to ther server to determine that it's driver 
                     messageOut = new Message(username, "driver"); // Matt you have to send passenger
                }
                else if(state == MENU_MESSAGE_DRIVER) {
                    
                    // This part comes after menu was printed
                    // User needs to enter which function he/she wants to use
                    fromUser = stdIn.readLine();
                    
                    // Sending user input to the server 
                    messageOut = new Message(username, fromUser);
                } 
                else if (state == ADD_NEW_RIDE) {
                    
                    //This part is firts creating new Ride object then sending the ride object to the server 
                    Ride tempRide = addNewRide(driver);
                    
                    fromUser = "newRideAdded";
                    driver.addRide(tempRide);

                    messageOut = new Message(username, fromUser, tempRide, 1); 
                }
                else if (state == SEE_MY_RIDES) {
                    
                    // Driver object was created inside DriverView, 
                    // so we don't need to get Driver Rides information from server
                    // we can get it from driver object direclty    
                    System.out.println(driver.displayAll());
                    
                    // To get back to menu we have to send request (in our case Message) to the server  
                    fromUser = "justMessage";   // so i am just sending random information to the server, 
                                                //so server can change the state 
                    messageOut = new Message(username, fromUser);
                    
                }
                else if (state == SEE_ALL_RIDES) {
                    
                    // But in this case, we don't have direct access to all rides,
                    // so we have to get it from server. 
                    // When user enters "3", server automatically sending all rides as a Strring 
                    
                    // We need this part just to change state, and get back to the menu
                    fromUser = "justMessage";
                    messageOut = new Message(username, fromUser);
                    
                }
                else if (state==CAR_INFO){
                    //SHowing Car information
                    System.out.println(myCar);
                }
                // Sending message to the server 
                out.writeObject(messageOut);
                
                
              
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    /**
     * Ride - this method will get user input and creates new Ride object 
     * 
     * @param driver - it takes as parametr a driver object 
     * @return Ride - it returns Ride object  
     */
    
    public static Ride addNewRide(Driver driver) {
        
    	// Creating a ride object 
        Ride ride = null; 
        
        // Getting User input 
         try {
            System.out.print("Pick Up Spot: ");
            String pickUpSpot = stdIn.readLine() ;
        
            System.out.print("Ride Destination: ");
            String pickUpDestination = stdIn.readLine();

/**************************************************************************************************************************************************/            
            // PickUp Day, throws exception if user enters non numeric value:
             System.out.print("Pick Up Day: ");
             int pickUpDay;
             
             try { 
            	 pickUpDay = Integer.parseInt(stdIn.readLine());
            
             	while (pickUpDay < 1 || pickUpDay > 31) {
             		System.out.print("Pick Up Day: ");
             		pickUpDay = Integer.parseInt(stdIn.readLine());
             	}
             } catch(NumberFormatException e) { 
            	 System.out.print("Invalid Value, please enter numiric value: ");
            	 pickUpDay = Integer.parseInt(stdIn.readLine());
             }
             
/**************************************************************************************************************************************************/            
             // PickUp Month, throws exception if user enters non numeric value:
             System.out.print("Pick Up Month: ");
             int pickUpMonth ;
             
             try { 
            	 pickUpMonth = Integer.parseInt(stdIn.readLine());
             
             	while (pickUpMonth < 1 || pickUpMonth > 12) {
             		System.out.print("Pick Up Month: ");
             		pickUpMonth = Integer.parseInt(stdIn.readLine());
             	}
             } catch(NumberFormatException e) { 
            	 System.out.print("Invalid Value, please enter numiric value: ");
            	 pickUpMonth = Integer.parseInt(stdIn.readLine());
             }
             
/**************************************************************************************************************************************************/            
             // PickUp Year, throws exception if user enters non numeric value:             
             System.out.print("Pick Up Year: ");
             
             int pickUpYear;
             try { 
            	 pickUpYear = Integer.parseInt(stdIn.readLine());
             
             	while(pickUpYear < Calendar.getInstance().get(Calendar.YEAR)) {
             		System.out.print("Pick Up Year: ");
             		pickUpYear = Integer.parseInt(stdIn.readLine());
             	}
             } catch(NumberFormatException e) { 
            	 System.out.print("Invalid Value, please enter numiric value: ");
            	 pickUpYear = Integer.parseInt(stdIn.readLine());
             }
             	
/**************************************************************************************************************************************************/                        	
             // PickUp Hour, throws exception if user enters non numeric value: 
             System.out.print("Pick Up Hour(Military time): ");
             int pickUpHour;
             try { 
             	 pickUpHour = Integer.parseInt(stdIn.readLine()); 
             		while(pickUpHour < 0 || pickUpHour > 24) {
             			System.out.print("Pick Up Hour(Military time): ");
             			pickUpHour = Integer.parseInt(stdIn.readLine()); 
             		}
             } catch(NumberFormatException e) { 
            	 System.out.print("Invalid Value, please enter numiric value: ");
            	 pickUpHour = Integer.parseInt(stdIn.readLine());
             }
             		
/**************************************************************************************************************************************************/                        	
             // PickUp Minute, throws exception if user enters non numeric value:              
             System.out.print("Pick Up Minutes: ");
             int pickUpMinute; 
             try { 
            	 pickUpMinute = Integer.parseInt(stdIn.readLine()); 
             		while (pickUpMinute < 0 || pickUpMinute >60) {
             			System.out.print("Pick Up Minutes: ");
             			pickUpMinute = Integer.parseInt(stdIn.readLine()); 
             		}
	         } catch(NumberFormatException e) { 
	        	 System.out.print("Invalid Value, please enter numiric value: ");
	        	 pickUpMinute = Integer.parseInt(stdIn.readLine());
	         }
/**************************************************************************************************************************************************/                        	
            // Driver information
             Driver driverInformation = driver; 
            
             //Seats 
             System.out.print("Number of Available Sits: ");
             int numberOfSeats = Integer.parseInt(stdIn.readLine()); 
        
             //Creating new Ride object 
             ride = new Ride(pickUpSpot, pickUpDay, pickUpMonth, pickUpYear, pickUpHour, pickUpMinute, driverInformation, numberOfSeats, pickUpDestination);
        
         } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
         
        return ride;
        
    }
    
    
    
    
    
}


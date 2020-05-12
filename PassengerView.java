 
package CsProject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * @authors Jasur Shukurov && Matt Vang
 * DriverView - this class displays driver menu
 * 
 * @version 1.0 04/09/2020
 */


public class PassengerView {
    
    // Different States of the Driver 
    private static final int WAITING = 0;
    private static final int IN_CONVERSATION = 10;
    

    private static final int MENU_MESSAGE_PASSENGER = 30;
    private static final int GET_ON_RIDE = 31;
    private static final int FETCH_RIDE = 311;
    private static final int NEXT_RIDES = 32;
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
        
        String name,ID,email;
        System.out.println("Welcome to Adelphi Carpool!");
        System.out.println("Please register as a passenger here:");
        System.out.print("Enter name: ");
        name = stdIn.readLine();
        System.out.print("Enter ID: ");
        ID = stdIn.readLine();
        System.out.print("Enter contact email: ");
        email = stdIn.readLine();
        
        // Creating  Passenger object with names 
        Passenger passenger = new Passenger(name,ID,email);
        

        // 2) Initiate a connection request 
        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            ObjectOutputStream out = new ObjectOutputStream(kkSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(kkSocket.getInputStream()); ) {
           
            Object fromServer;
            String fromUser = null;
          
            // Setting username to passenger (Client will have username = "passenger")
            username = "passenger";
            
            // 5 - Receives options from the server 
            while ((fromServer = in.readObject()) != null) {
            
                // 6 - Print message from Server. 
                System.out.println("" + fromServer.toString() + "");
 
                // Casting object from Server to Message object
                Message temp = (Message) fromServer; 
                
                //Getting state of the app from the server 
                state = temp.getState();
                                
       
                if(state == IN_CONVERSATION) {
                     
                     // This part is sending information to ther server to determine that it's driver 
                     messageOut = new Message(username, "driver"); // Matt you have to send passenger
                }
                else if(state == MENU_MESSAGE_PASSENGER) {
                    
                    // This part comes after menu was printed
                    // User needs to enter which function he/she wants to use
                    fromUser = stdIn.readLine();
                    
                    // Sending user input to the server 
                    messageOut = new Message(username, fromUser);
                    
                } 
                else if (state == GET_ON_RIDE) {
                    // Read in the ride ID to send
                    fromUser = stdIn.readLine();
                    // Send the ID to Server
                    messageOut = new Message(username, fromUser);          
                }
                else if(state == FETCH_RIDE){
                    // Receive ride info from server and add to list
                    passenger.addRide(temp.getRide());
                    messageOut = new Message(username,"justMessage");
                }
                else if(state == NEXT_RIDES){
                    // View upcoming rides
                    System.out.println(passenger.displayAll());
                    messageOut = new Message(username,"justMessage");
                }
                
                
                // Sending message to the server 
                out.writeObject(messageOut);
            }
            kkSocket.close();
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

   
 
    
}

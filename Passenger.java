
/**
 * Write a description of class Passenger here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.*;
import java.net.*;
public class Passenger {
    static Profile myProfile;
    public static void main(String[] args) throws IOException {
        
        if (args.length != 5) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        
        //Get passenger information and test print
        String name = args[2];
        String ID = args[3];
        String email = args[4];
        myProfile = new Profile(name,ID,email);
        
        System.out.println(myProfile);
    }
}

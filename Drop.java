package CsProject;

public class Drop {
    
    //Ride is being communicated
    private Ride tempRide;
    
    //true if a ride was taken and now box is empty
    //false if a ride was put in and box is no longer empty
    static boolean empty = true;

    public synchronized Ride take() {
        // Wait until a ride is
        // added (no longer empty)
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = true;
    
        return tempRide;
    }

    public synchronized void put(Ride tempRide) {
        // Wait until ride has
        // been taken.
        while (!empty) {
            try { 
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.tempRide = tempRide;
        notifyAll();
    }
}

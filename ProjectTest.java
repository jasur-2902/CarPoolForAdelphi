package CsProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class ProjectTest {

	private Driver newDriver;
    private Ride newRide;
    private Passenger newPassenger;

    /**
     * Default constructor for test class MoneyTest
     */
    public ProjectTest()
    {
        System.out.println("JUnit Framework Adelphi Ride Share app Test ");
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        newDriver  = new Driver();
        newPassenger = new Passenger();
        
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    	newDriver = null;
    	newPassenger = null; 
    }

    /**
     * Test methods
     */
    
 // Test Depositing
    @Test
    public void testCreate()
    {
    	 Car car = new Car("ABKING", "BMW M5","Black");
    	 newDriver  = new Driver("Jasur", "1801968","jasurshukurov@mail.adelphi.edu", car);
    	 newPassenger = new Passenger("Matt", "1741232", "mattvang@mail.adelphi.edu");
    	 
    	// Expected Result
    	assertEquals("Error in testCreate", "1801968", newDriver.getID());
    	assertEquals("Error in testCreate", "Jasur", newDriver.getName());
    	assertEquals("Error in testCreate", "BMW M5", newDriver.getCar().getModel());
    	assertTrue("Error in testCreate", newPassenger.getID().equals("1741232"));
    	
    }
    
    
 // Test Add Ride
    @Test
    public void testAddRide()
    {
    	 Car car = new Car("ABKING", "BMW M5","Black");
    	 newDriver  = new Driver("Jasur", "1801968","jasurshukurov@mail.adelphi.edu", car);
    	 newPassenger = new Passenger("Matt", "1741232", "mattvang@mail.adelphi.edu");
    	 
    	 
    	// Expected Result
    	assertEquals("Error in testCreate", 0, newDriver.numOfRides());
    	 
    	 Ride ride = new Ride("Boston", 18, 05, 2020, 9,30, newDriver,3, "New York"); 
    	 newDriver.addRide(ride); 
    	
    	 
    	// Expected Result
    	assertEquals("Error in testCreate", 1, newDriver.numOfRides());
    	
    }

}

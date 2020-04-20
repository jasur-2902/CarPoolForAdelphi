package CsProject;

import java.io.Serializable;

/**
 * @authors Jasur Shukurov && Matt Vang
 * Message object - this object is being sent between Server and User 
 * 
 * @version 1.0 04/09/2020
 */

public class Message implements Serializable {
	
	// ID number for Serializable object 
	private static final long serialVersionUID = 1L;
	
	// Message object properties 
	private String name;
	private String message;
	private Ride ride; 
	
	// State variable is responsible to keep both User and Server on the same state 
	private int state;
	
	// Message Constructor without ride Object 
	public Message(String name, String message, int state) {
		this.name = name;
		this.message = message;
		this.ride = null; 
		this.state = state; 
	}	
	
	// Message Constructor with ride Object 
	public Message(String name, String message, Ride ride, int state) {
		this.name = name;
		this.message = message;
		this.ride = ride;
		this.state = state;
	}
	
	// Message Constructor without ride Object  and state 
	public Message(String name, String message) {
		this.name = name;
		this.message = message;
		this.ride = null; 
		this.state = 10; 
	}
	
	
	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public Ride getRide() {
		return ride;
	}


	public void setRide(Ride ride) {
		this.ride = ride;
	}
	
	
	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		return name + ": " + message ;
	}
	
	
	
}

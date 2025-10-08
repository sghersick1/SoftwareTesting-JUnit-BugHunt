package edu.loyola.sghersick.A3_BugHunt;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Session
{
	// Attributes
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	
	// Constructors
    public Session(LocalDateTime checkIn) {
    	this.checkIn = checkIn;
    	checkOut = null;
    }
    
    public Session(LocalDateTime checkIn, LocalDateTime checkOut) {
    	this.checkIn = checkIn;
    	this.checkOut = checkOut;
    }
        
    // Other Methods
    /**
     * Create a shallow copy of "this" object
     * @return shallow copy of object
     */
    public Session clone() {
    	return new Session(checkIn, checkOut);
    }
    
    /**
     * Check-in to "this" session
     * @param time of action
     */
    public void checkIn(LocalDateTime time) {
    	checkIn = time;
    }
    
    /**
     * Check-out from "this" session
     * @param time of action
     */
    public void checkOut(LocalDateTime time) {
    	checkOut = time;
    }
    
    /** 
     * Calculate the duration of a session
     * @return session duration (hours) on success. Return negative on failure.
     */
    public double calcDuration() {
	    long seconds = ChronoUnit.SECONDS.between(checkIn, checkOut);
	    return seconds/3600.0;
    }
    
    /**
     * Print out the Session data
     * @return session data
     */
    @Override
    public String toString() {
    	double duration = calcDuration();
    	return "{CheckIn: "+ checkIn+", CheckOut: " +checkOut+", Duration: "+ (duration >= 0 ? duration : "N/A")+"}";
    }
}

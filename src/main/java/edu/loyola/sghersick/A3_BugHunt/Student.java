package edu.loyola.sghersick.A3_BugHunt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {
	
	//Attributes
	private int studentId;
	private String name;
	private SportsTeam sport;
	private Session activeSession; // null when not signed-in
	private HashMap<LocalDate, ArrayList<Session>> sessionHist; // Sessions organized by day
	private double bankedHours;
	
	//Constructors
	public Student(int studentId, String name, SportsTeam sport, Session activeSession, HashMap<LocalDate, ArrayList<Session>> sessionHist, double bankedHours) {
		this.studentId = studentId;
		this.name = name;
		this.sport = sport;
		this.activeSession = activeSession;
		this.sessionHist = sessionHist;
		this.bankedHours = bankedHours;
	}
	
	public Student(int studentId, String name, SportsTeam sport) {
		this.studentId = studentId;
		this.name = name;
		this.sport = sport;
		
		// Default value
		this.activeSession = null;
		this.sessionHist = new HashMap<LocalDate, ArrayList<Session>>();
		this.bankedHours = 0;
	}
	
	// Other methods
	/**
	 * Sign student into study
	 * @param time of sign-in
	 */
	public void signIn(LocalDateTime time) {
		if(activeSession != null) {
			throw new IllegalStateException("CANNOT SIGN IN: Current session already in progress");
		}
		
		// Create active session
		activeSession = new Session(time);
	}
	
	/**
	 * Sign student out of study
	 * @param time of sign-out
	 */
	public void signOut(LocalDateTime time) {
		if(activeSession == null) {
			throw new IllegalStateException("CANNOT SIGN OUT: There is not a current session");
		}
		
		// Record date for organizing session in HashMap
		LocalDate sDate = time.toLocalDate();
		
		activeSession.checkOut(time);
		Session sCopy = activeSession.clone(); // Shallow copy
	
		// Add new session to history
		ArrayList<Session> dateList = sessionHist.getOrDefault(sDate, new ArrayList<Session>());
		dateList.add(sCopy);
		sessionHist.put(sDate, dateList);
		
		// Reset active session
		activeSession = null;
	}
	
	/**
	 * Return Date object from LocalDateTime object
	 */
	
	/**
	 * Log user sessions by day
	 */
	public void printSessions() {
		for(Map.Entry<LocalDate, ArrayList<Session>> set : sessionHist.entrySet()) {
			System.out.println("Date: "+set.getKey()+"\n--------------------");
			double dHours = 0;
			
			for(Session s : set.getValue()) {
				dHours += s.calcDuration();
				System.out.println(s);
			}
			
			System.out.printf("Total hours: %.3f\n\n", dHours);
		}
	}
	
	/**
	 * Calculate hours during week until deadline (inclusive)
	 * @param deadline date (whole day included in calculated week)
	 * @param quantity required p/week
	 * @param maximum hours p/week that can go toward hoursRequired
	 * @param maximum hours p/day before excess goes into bank
	 * @return number of hours remaining for this specific week
	 */
	public double hoursRemaining(LocalDate deadline, double hoursRequired, double maxBank, double maxDayHours) {
		// Track required hours remaining
		double hoursRemaining = hoursRequired;
		
		// Apply banked hours, up to maxBank
		if(bankedHours >= maxBank) {
			hoursRemaining -= maxBank;
			bankedHours -= maxBank;
		}else {
			hoursRemaining -= bankedHours;
			bankedHours = 0;
		}
		
		// Loop through each Day
		for(Map.Entry<LocalDate, ArrayList<Session>> set : sessionHist.entrySet()) {
			// Check that current day is within deadline week
			LocalDate sessionDate = set.getKey();
			boolean inWeek = sessionDate.compareTo(deadline) <= 0 && sessionDate.compareTo(deadline.minusDays(7)) > 0;
			
			if(inWeek) {
				double dayHours = 0;
				
				// Loop through each session
				for(Session s : set.getValue()) {
					dayHours += s.calcDuration();
				}
				
				// Check exceeding daily max hours
				if(dayHours > maxDayHours) { 
					bankedHours += dayHours-maxDayHours;
					dayHours = maxDayHours;
				}
				
				// Apply daily hours towards required hours p/week
				if(dayHours > hoursRemaining) {
					hoursRemaining = 0;
					bankedHours += dayHours-hoursRemaining;
				}else {
					hoursRemaining -= dayHours;
				}
			}
		}
		
		return hoursRemaining;
	}
	
	public static void main(String[] args) {
		// Constant Variables
		final double REQUIRED_HOURS = 8.0;
		final double BANKED_PER_WEEK = 4.0;
		final double MAX_DAY_HOURS = 4.0;
		
		// Test student
		Student student = new Student(2027178, "Sam Hersick", SportsTeam.SWIMMING);
		HashMap<LocalDateTime, LocalDateTime> sesh = new HashMap<>();
		
		// Sign-in & sign-out test values
		sesh.put(LocalDateTime.of(2025, 10, 2, 10, 45), LocalDateTime.of(2025, 10, 2, 12, 45));
		sesh.put(LocalDateTime.of(2025, 10, 3, 15, 45), LocalDateTime.of(2025, 10, 3, 16, 15));
		sesh.put(LocalDateTime.of(2025, 10, 9, 15, 45), LocalDateTime.of(2025, 10, 9, 20, 15));
		
		// Create session for each test value
		for(Map.Entry<LocalDateTime, LocalDateTime> set: sesh.entrySet()) {
			student.signIn(set.getKey());
			student.signOut(set.getValue());
		}
		
		// Print Results
		student.hoursRemaining(LocalDate.of(2025, 10, 9), REQUIRED_HOURS, BANKED_PER_WEEK, MAX_DAY_HOURS);
		System.out.println();
		student.printSessions();
	}
}

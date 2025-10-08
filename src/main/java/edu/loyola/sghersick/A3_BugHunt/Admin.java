package edu.loyola.sghersick.A3_BugHunt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Admin {
	// Constant Variables
	final double REQUIRED_HOURS = 8.0;
	final double BANKED_PER_WEEK = 4.0;
	final double MAX_DAY_HOURS = 4.0;
	
	// Attributes
	private ArrayList<Student> students;
	
	// Constructors
	public Admin() {
		students = null;
	}
	
	public Admin(ArrayList<Student> students) {
		this.students = (ArrayList<Student>) students.clone(); // shallow copy
	}
	
	// Other Methods
	/**
	 * Print out weekly deadlines, and students hours remaining p/week
	 * @param student to print
	 * @param deadlines required weekly hours
	 */
	public void printStudentRecord(Student s, LocalDate[] deadlines) {
		for(int i = 0; i < deadlines.length; i++) {
			double hours = s.hoursRemaining(deadlines[i], REQUIRED_HOURS, BANKED_PER_WEEK, MAX_DAY_HOURS);
			System.out.println("Week "+(i+1)+": "+deadlines[i]+"\n"+hours+" hours remaining\n");
		}
	}
	
	/**
	 * Get list of students who haven't met weekly requirement for a deadline 
	 * @param deadline for the week
	 * @return students who haven't met required hours yet
	 */
	public ArrayList<Student> getInProgressStudents(LocalDate deadline){
		ArrayList<Student> slackers = new ArrayList<Student>();
		
		for(Student s: students) {
			if(s.hoursRemaining(deadline, REQUIRED_HOURS, BANKED_PER_WEEK, MAX_DAY_HOURS) > 0) {
				slackers.add(s);
			}
		}
		
		return slackers;
	}
	
	public static void main(String[] args) {
		// Create list of deadlines (every Thursday)
		LocalDate[] deadlines = new LocalDate[15];
		LocalDate deadline = LocalDate.of(2025, 9, 11);
		
		for(int i=0; i < deadlines.length; i++) {
			deadlines[i] = deadline;
			deadline = deadline.plus(7, ChronoUnit.DAYS);
		}
		
		// Test student
		Student student = new Student(2027178, "Sam Hersick", SportsTeam.SWIMMING);
		HashMap<LocalDateTime, LocalDateTime> sesh = new HashMap<>();
				
		// Sign-in & sign-out test values
		sesh.put(LocalDateTime.of(2025, 10, 3, 10, 45), LocalDateTime.of(2025, 10, 3, 12, 45));
		sesh.put(LocalDateTime.of(2025, 10, 3, 15, 45), LocalDateTime.of(2025, 10, 3, 16, 15));
		sesh.put(LocalDateTime.of(2025, 10, 9, 15, 45), LocalDateTime.of(2025, 10, 9, 20, 15));
			
		// Create session for each test value
		for(Map.Entry<LocalDateTime, LocalDateTime> set: sesh.entrySet()) {
			student.signIn(set.getKey());
			student.signOut(set.getValue());
		}
		
		Admin admin = new Admin(new ArrayList<Student>(Arrays.asList(student)));
		
		admin.printStudentRecord(student, deadlines);
		ArrayList<Student> slackers = admin.getInProgressStudents(deadlines[10]);
		
		// Print slackers
		for(Student s : slackers) {
			System.out.println(s);
		}
		
	}
}

package edu.loyola.sghersick.A3_BugHunt;

import java.util.ArrayList;

public class Admin {
	
	// Attributes
	private ArrayList<Student> students;
	
	//Constructors
	public Admin() {
		students = null;
	}
	
	/**
	 * Get list of students under required hours for week 
	 * @param week to check for
	 * @return list of students underRequiredHours
	 */
	public ArrayList<Student> getStudentsUnderHours(int week, double reqHours){
		ArrayList<Student> underHours = new ArrayList<Student>();
		
		for(Student student : students) {
			// Verify the student has met hours requirement for "week"
			if(!student.verifyHours(reqHours, week)) underHours.add(student);
		}
		
		return underHours;
	}
	
	public static void main(String[] args) {
		
	}
}

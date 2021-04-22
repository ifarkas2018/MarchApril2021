// project : Time Schedule, author : Ingrid Farkas, 2020 
package com.timemng.sbjsp.model;

// PersonInfo1p1 - model class (the class representing the first name, last name from the employee or customer table)
public class PersonInfo1p1 {

	private String firstName; // the employee's first name
	private String lastName; // the employee's last name  

	// constructor of the class
	public PersonInfo1p1() {
		super();
	}
	
	// constructor of the class
	public PersonInfo1p1(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// get the first name
	public String getFirstName() {
		return firstName;
	}
	
	// set the first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// get the last name
	public String getLastName() {
		return lastName;
	}
	
	// set the last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}



// project : Time Schedule, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.model;

// EmpIDName1p1 - model class (the class representing the employee ID, first name, last name, department from the employee table)
public class EmpIDNameInfo1p1 {

	private String employeeID; // the employee's ID
	private String firstName; // the employee's first name
	private String lastName; // the employee's last name  
	private String department; // the department of the employee

	// constructor of the class
	public EmpIDNameInfo1p1(String employeeID, String firstName, String lastName, String department) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
	}

	// get the employee ID
	public String getEmployeeID() {
		return employeeID;
	}

	// set the employee ID
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
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

	// set the first name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// get the department
	public String getDepartment() {
		return department;
	}

	// set the department
	public void setDepartment(String department) {
		this.department = department;
	}

}
// project : Time Schedule, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.model;

// EmpIDInfo1p1 - model class (the class representing the employee ID from the employee table)
public class EmpIDInfo1p1 {

	private String employeeID; // the employee's ID

	// constructor of the class
	public EmpIDInfo1p1( String employeeID ) {
		super();
		this.employeeID = employeeID;
	}
	
	// get the employee ID
	public String getEmployeeID() {
		return employeeID;
	}

	// set the employee ID
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

}



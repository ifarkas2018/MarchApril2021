// project : Time Schedule, author : Ingrid Farkas, 2020 
package com.timemng.sbjsp.model;

// LoginInfo1p1 - model class (the class representing the data of the record in the login table)
public class LoginInfo1p1 {

    private String employeeID; // the employee's ID (with that user name and password)
   	
	// constructor of the class
	public LoginInfo1p1(String employeeID) { 
		super();
		this.employeeID = employeeID;
	}
	
	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}	
}

// project : Time Schedule, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.model;

// EmpEmailInfo1p1 - model class (the class representing the employee ID, first name, last name, email from the employee table)
public class EmpEmailInfo1p1 extends EmpIDInfo1p1 {

	private String email; 

	// constructor of the class
	public EmpEmailInfo1p1( String employeeID ) {
		super(employeeID); 
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}


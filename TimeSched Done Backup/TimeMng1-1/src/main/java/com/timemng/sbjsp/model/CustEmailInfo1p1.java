// project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.model;

// CustEmailInfo1p1 - model class (the class representing the customer ID, first name, last name, email from the customer table)
public class CustEmailInfo1p1 extends CustNameInfo1p1 {
	
	private String email; 

	// constructor of the class
	public CustEmailInfo1p1( String customerID, String email ) {
		super(customerID);
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}



// project : Time Schedule, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.model;

// CustID1p1 - model class (the class representing the customer ID from the customer table)
public class CustIDInfo1p1 {

	private String customerID; // the employee's ID  

	// constructor of the class
	public CustIDInfo1p1( String customerID ) {
		super();
		this.customerID = customerID;
	}

	// get the customer ID
	public String getCustomerID() {
		return customerID;
	}

	// set the customer ID
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
}




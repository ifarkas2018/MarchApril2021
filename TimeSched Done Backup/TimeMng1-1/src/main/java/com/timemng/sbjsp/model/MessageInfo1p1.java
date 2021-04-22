// project : Time Schedule, author : Ingrid Farkas, 2020 
package com.timemng.sbjsp.model;

// MessageInfo1p1 - model class (the class representing the data of the record in the message table)
public class MessageInfo1p1 {
	private String personID; 
	private String message; // the message
	
	// constructor of the class 
	public MessageInfo1p1(String message, String personID) {
		super();
		this.personID = personID;
		this.message = message;
	}

}



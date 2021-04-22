// project : Time Schedule, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.model;

// ScheduleInfo1p1 - model class (the class representing the data of the record in the schedule table)
public class ScheduleInfo1p1 {

    private String scheduleID; // the schedule ID
   	
	// constructor of the class
	public ScheduleInfo1p1(String scheduleID) { 
		super();
		this.scheduleID = scheduleID;
	}

	// get the schedule ID
	public String getScheduleID() {
		return scheduleID;
	}
	
	// set the schedule ID
	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}
	
}

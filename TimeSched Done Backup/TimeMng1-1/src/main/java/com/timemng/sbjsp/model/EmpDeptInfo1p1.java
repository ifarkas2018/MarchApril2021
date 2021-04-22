// project : Time Schedule, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.model;

// EmployeeInfo - model class (the class representing the data of the record in the employee table)
public class EmpDeptInfo1p1 {

	private String firstName; // employee's first name
	private String lastName; // employee's last name
	private String department; // employeee's department
	
	// constructor of the class
	public EmpDeptInfo1p1(String firstName, String lastName, String department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
	}

	// get the first name of the employee
	public String getFirstName() {
		return firstName;
	}

	// set the first name of the employee
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// get the last name of the employee
	public String getLastName() {
		return lastName;
	}

	// set the last name of the employee
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
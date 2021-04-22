// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.dao;

// importing the classes
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.timemng.sbjsp.model.EmpIDNameInfo1p1;
import com.timemng.sbjsp.mapper.EmpIDNameMapper1p1;

@Repository
@Transactional
public class EmpIDNameDAO1p1 extends JdbcDaoSupport {
	@Autowired
	public EmpIDNameDAO1p1(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	// getEmployee - retrieving the employee's ID, first name, last name and the department
	public List<EmpIDNameInfo1p1> getEmployee() {
	 	List<EmpIDNameInfo1p1> list = new ArrayList<>(); // initialising the list
	 	
	 	// SQL_EMP - the String that contains the the part of the query on which the WHERE clause was added 
	    String sql = EmpIDNameMapper1p1.SQL_EMP;
	    try {
	     	Object[] params = new Object[] {};
	     	// EmpNameMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class (EmpIDNameInfo.java)
	     	EmpIDNameMapper1p1 mapper = new EmpIDNameMapper1p1();
	     	// running the query and retrieving the list of employee's ID, name and department (table : employee)
	     	list = this.getJdbcTemplate().query(sql, params, mapper);
	     } catch (Exception e) {
	     	e.printStackTrace();
	     }
	     return list; // return the list with the employee's ID, first name, last name and department
	}
		 
	// adds to the SQL query the WHERE clause with the ID, first name, last name (the data the user entered in the Show Employee form)
	// if the user didn't enter first name or last name (in the form) then this method returns false
	public boolean addToQueryStr(String empID, String fName, String lName) {
		boolean returnVal = true; // the value returned by the method
		boolean fNameEx; // does the first name exist
		boolean lNameEx; // does the last name exist
		boolean empIDEx; // does the employee ID exist
		
		// if the user before queried the database about some other employee then the original SQL_EMP got changed so I have to reset it to its original value 
		EmpIDNameMapper1p1.resetSQL_EMP();
		String sql = EmpIDNameMapper1p1.SQL_EMP;
		fNameEx = ((fName!=null) && (!fName.equals(""))); // does the first name exist
		lNameEx = ((lName!=null) && (!lName.equals(""))); // does the last name exist
		empIDEx = ((empID!=null) && (!empID.equals(""))); // does the empID exist 
		if (empIDEx){
			// I am changing the SQL query to return the employee's department for the entered first name, last name (and optionally ID) 
			sql += " where (emp_id='" + empID + "') ";	
			if (fNameEx) 
				sql += "and ";
		} else if (fNameEx) {
			sql += " where ";
		} else 
			returnVal = false; // the method returns false if the user didn't enter the first name
		if (fNameEx){
			// I am changing the SQL query to return the employee's department for the entered first name  
			sql += " (first_name='" + fName + "') ";
			if (lNameEx){
				// I am changing the SQL query to return the employee's department for the entered last name  
				sql += "and (last_name='" + lName + "')";
				sql += ";";
			} else
				returnVal = false; // the method returns false if the user didn't enter the last name
		} else
			returnVal = false; // the method returns false if the user didn't enter the first name
		// update the SQL_EMP to the sql ( query built above )
		EmpIDNameMapper1p1.updateSQL_EMP(sql);
		return returnVal;
	}
}
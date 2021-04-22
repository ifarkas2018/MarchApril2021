// project : Time Manager, author : Ingrid Farkas, 2020 
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
// PersonInfo1p1 - model class (the class representing the first name, last name from the employee table)
import com.timemng.sbjsp.model.PersonInfo1p1; 

// PersonMapper - a mapper class (used for mapping corresponding to 1-1 relationship between 1 column in the result of the query statement and 1 field in 
// the model class PersonInfo.java)
public class PersonMapper1p1  implements RowMapper<PersonInfo1p1> {
	
	// SQL_EMP_NAME is a SQL query used to select the first and last name of the employee with employeeID
	public static String SQL_EMP_NAME = "select first_name, last_name from employee"; 

	@Override
	public PersonInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
		// mapping 1 column in the result of the query statement and 1 field in the model class EmpNameInfo.java 
		String first_name = rs.getString("first_name");
		String last_name = rs.getString("last_name");		        
		// create and return an object of the class EmpNameInfo (which is the model)
		return new PersonInfo1p1(first_name, last_name);
	}
	
	// resetSQL_EMP_NAME sets the string SQL_EMP_NAME to its original value
	public static void resetSQL_EMP_NAME() {
		SQL_EMP_NAME = "select first_name, last_name from employee"; 
	}
	
	// updating the query string to the new query string formed in the class EmpNameDAO, method addToQueryStr
	public static void updateSQL_NAME(String sql) {
		SQL_EMP_NAME = sql; // sql - new query string
	}	
}

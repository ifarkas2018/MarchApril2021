// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.mapper;

// importing the classes
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
// EmpIDNameInfo - model class (the class representing the employee id, first name, last name, department in the employee table)
import com.timemng.sbjsp.model.EmpIDNameInfo1p1;
 
// EmpNameMapper - a mapper class (used for mapping corresponding to 1-1 relationship between 1 column in the result of the query statement and 1 field in 
// the model class EmpIDNameInfo.java)
public class EmpIDNameMapper1p1 implements RowMapper<EmpIDNameInfo1p1> {

	// SQL_EMP is a SQL query used to select employee ID, the first and last name, department of the employee with employeeID
	public static String SQL_EMP = "select emp_id, first_name, last_name, department from employee";
	
	@Override
	public EmpIDNameInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
		// mapping 1 column in the result of the query statement to 1 field in the model class EmpIDNameInfo.java 
		String emp_id = rs.getString("emp_id");
		String first_name = rs.getString("first_name");
		String last_name = rs.getString("last_name");
		String department = rs.getString("department");
			        
		// create and return an object of the class EmpIDNameInfo (which is the model)
		return new EmpIDNameInfo1p1( emp_id, first_name, last_name, department );
	}
	
	// resetSQL_EMP sets the string SQL_EMP to its original value
	public static void resetSQL_EMP() {
		SQL_EMP = "select emp_id, first_name, last_name, department from employee";
	}
	
	// updating the query string to the new query string formed in the class EmpIDNameDAO, method addToQueryStr
	public static void updateSQL_EMP(String sql) {
		SQL_EMP = sql; // sql - new query string
	}
}
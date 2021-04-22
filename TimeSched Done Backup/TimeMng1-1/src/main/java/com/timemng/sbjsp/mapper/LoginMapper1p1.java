// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.timemng.sbjsp.model.LoginInfo1p1; // LoginInfo - model class (the class representing the data of the record in the login table)
import org.springframework.jdbc.core.RowMapper;

// LoginMapper - a mapper class (used for mapping corresponding to 1-1 relationship between 1 column in the result of the query statement and 1 field in 
// the model class LoginInfo.java)
public class LoginMapper1p1 implements RowMapper<LoginInfo1p1> {

	// SQL_LOGIN is a SQL query used to select the user name and the password
	public static String SQL_LOGIN = "select emp_id from login ";    
	
	// SQL_INSERT is a SQL query used to insert a new user in the login table
	public static String SQL_INSERT = "insert into login(emp_id, user_name, password) values (";  

	@Override
	public LoginInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
		// mapping 1 column in the result of the query statement and 1 field in the model class LoginInfo.java 
		String employeeID = rs.getString("emp_id");
				        
		// create and return an object of the class LoginInfo (which is the model)
		return new LoginInfo1p1( employeeID ); 
	}
		
	// resetSQL_LOGIN sets the string SQL_LOGIN to its original value
	public static void resetSQL_LOGIN() {
		SQL_LOGIN = "select emp_id from login "; 
	}
	
	// resetSQL_INSERT sets the string SQL_INSERT to its original value
	public static void resetSQL_INSERT() {
		SQL_INSERT = "insert into login(emp_id, user_name, password) values (";
	}
	
	// updating the query string SQL_LOGIN to the new query string formed in the class LoginDAO, method addToQueryStr
	public static void updateSQL(String sql) {
		SQL_LOGIN = sql; // sql - new query string
	}

	// updating the query string SQL_INSERT to the new query string formed in the class LoginDAO, method addToQueryInsert
	public static void updateSQLInsert(String sql) {
		SQL_INSERT = sql; 
	}
}
// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
// CustEmailInfo - model class (the class which contains the customer_ID, first, last name and email from the customer table)
import com.timemng.sbjsp.model.CustEmailInfo1p1; 

// CustEmailMapper - a mapper class (used for mapping corresponding to 1-1 relationship between 1 column in the result of the query statement and 1 field in 
// the class CustEmailInfo.java)
public class CustEmailMapper1p1 implements RowMapper<CustEmailInfo1p1> {
		
	// SQL_CUST_EMAIL is a SQL query used to select first name, last name and email
	public static String SQL_CUST_EMAIL = "select cust_id, email from customer ";  
				
	// SQL_ADD_CUST is a SQL query to which I will add the first name, last name, email depending on the data the user entered in the Contact Us form
	public static String SQL_ADD_CUST = "INSERT INTO customer (first_name, last_name, email) VALUES (";
		
	// SQL_UPD_EMAIL is a SQL query to which I will add the email and the condition for the cust_id 
	public static String SQL_UPD_EMAIL = "UPDATE customer SET email="; 

	@Override
	public CustEmailInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
		// mapping 1 column in the result of the query statement and 1 field in the model class CustEmailInfo.java 
		String cust_id = rs.getString("cust_id");
		String email = rs.getString("email");		        
		// create and return an object of the class CustEmailInfo (which is the model)
		return new CustEmailInfo1p1(cust_id, email);
	}
		
	// resetSQL_CUST_EMAIL sets the string SQL_CUST_EMAIL to its original value
	public static void resetSQL_CUST_EMAIL() {
		SQL_CUST_EMAIL = "select cust_id, email from customer ";  
	}
		
	// resetSQL_ADD_CUST sets the string SQL_ADD_CUST to its original value
	public static void resetSQL_ADD_CUST() {
		SQL_ADD_CUST = "INSERT INTO customer (first_name, last_name, email) VALUES (";  
	}
				
	// resetSQL_UPD_EMAIL sets the string SQL_UPD_EMAIL to its original value
	public static void resetSQL_UPD_EMAIL() {
		SQL_UPD_EMAIL = "update customer set email=";  
	}
		
	// updating the query string to the new query string formed in the class CustEmailDAO, method addToQueryStrEmail
	public static void updateSQL_CUST_EMAIL(String sql) {
		SQL_CUST_EMAIL = sql; // sql - new query string
	}
		
	// updating the query string to the new query string formed in the class CustEmailDAO, method addToQueryAddCust
	public static void updateSQL_ADD_CUST(String sql) {
		SQL_ADD_CUST = sql; // sql - new query string
	}
				
	// updating the query string to the new query string formed in the class CustEmailDAO, method addToQueryUpdEmail
	public static void updateSQL_UPD_EMAIL(String sql) {
		SQL_UPD_EMAIL = sql; // sql - new query string
	}	
}

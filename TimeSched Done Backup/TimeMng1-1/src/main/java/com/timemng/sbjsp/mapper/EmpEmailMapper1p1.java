// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
// EmpEmailInfo - model class (the class which contains the employee id, first, last name and email from the employee table)
import com.timemng.sbjsp.model.EmpEmailInfo1p1; 


// EmpEmailMapper - a mapper class (used for mapping corresponding to 1-1 relationship between 1 column in the result of the query statement and 1 field in 
// the model class EmpEmailInfo.java )
public class EmpEmailMapper1p1 implements RowMapper<EmpEmailInfo1p1> {
		
		// SQL_USR_EMAIL is a SQL query used to select the employee ID, first name, last name and email
		public static String SQL_USR_EMAIL = "select email from employee ";  
		
		// SQL_ADD_EMAIL is a SQL query to which I will add the email and the condition for the emp_id 
		public static String SQL_UPD_EMAIL = "UPDATE employee SET email=";

		@Override
		public EmpEmailInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
			String email = rs.getString("email");
			// create and return an object of the class EmpEmailInfo (which is the model) 
			return new EmpEmailInfo1p1( email );
		}
				
		// resetSQL_USR_EMAIL sets the string SQL_EMP_ID to its original value
		public static void resetSQL_USR_EMAIL() {
			SQL_USR_EMAIL = "select email from employee ";  
		}
					
		// resetSQL_ADD_EMAIL sets the string SQL_ADD_EMAIL to its original value
		public static void resetSQL_UPD_EMAIL() {
			SQL_UPD_EMAIL = "update employee set email=";  
		}
		
		// updating the query string to the new query string formed in the class EmpEmailDAO, method addToQueryStrEmail
		public static void updateSQL_USR_EMAIL(String sql) {
			SQL_USR_EMAIL = sql; // sql - new query string
		}
		
		// updating the query string to the new query string formed in the class EmpEmailDAO, method addToQueryUpdEmail
		public static void updateSQL_UPD_EMAIL(String sql) {
			SQL_UPD_EMAIL = sql; 
		}
		
}


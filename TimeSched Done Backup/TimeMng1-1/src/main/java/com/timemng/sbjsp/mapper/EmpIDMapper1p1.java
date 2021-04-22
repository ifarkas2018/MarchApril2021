// project : Time Manager, author : Ingrid Farkas, 2020 
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
// EmpNameMapper - model class (the class which contains the employee id from the employee table)
import com.timemng.sbjsp.model.EmpIDInfo1p1; 


// EmpIDMapper - a mapper class (used for mapping corresponding to 1-1 relationship between 1 column in the result of the query statement and 1 field in 
// the model class EmpNameInfo.java)
public class EmpIDMapper1p1 implements RowMapper<EmpIDInfo1p1> {
		
		// SQL_EMP_ID is a SQL query used to select the employee ID of the employee with the given first and last name
		public static String SQL_EMP_ID = "select emp_id from employee";  

		@Override
		public EmpIDInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
			// mapping 1 column in the result of the query statement and 1 field in the model class EmpNameInfo.java 
			String emp_id = rs.getString("emp_id");
			// create and return an object of the class EmpNameInfo ( which is the model )
			return new EmpIDInfo1p1( emp_id );
		}
		
		// resetSQL_EMP_ID sets the string SQL_EMP_ID to its original value
		public static void resetSQL_EMP_ID() {
			SQL_EMP_ID = "select emp_id from employee";
		}
			
		// updating the query string to the new query string formed in the class EmpNameDAO, method addToQueryStr
		public static void updateSQL_EMP_ID(String sql) {
			SQL_EMP_ID = sql; // sql - new query string
		}
}
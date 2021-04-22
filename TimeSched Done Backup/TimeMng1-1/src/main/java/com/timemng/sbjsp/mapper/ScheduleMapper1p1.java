// project : Time Manager, author : Ingrid Farkas, 2020

// importing the classes
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.timemng.sbjsp.model.ScheduleInfo1p1; // ScheduleInfo - model class (the class representing the data of the record in the schedule table)
import org.springframework.jdbc.core.RowMapper;

// ScheduleMapper - a mapper class (used for mapping corresponding to 1-1 relationship between 1 column in the result of the query statement and 1 field in 
// the model class ScheduleInfo.java)
public class ScheduleMapper1p1 implements RowMapper<ScheduleInfo1p1> {

		// SCHED_ID_SQL is a SQL query used to return the records where the emp_id equals the employeeID of the user whose task is being added
		public static String SCHED_ID_SQL = "select sched_id from schedule ";  
		
		// INSERT_SQL : inserts the record into the table schedule (emp_id equals to the emp_id of the record added to the employee table)
		public static String INSERT_SQL = "insert into schedule(emp_id) values(";  

		@Override
		public ScheduleInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
			// mapping 1 column in the result of the query statement and 1 field in the model class ScheduleInfo.java 
			String scheduleID = rs.getString("sched_id");
			// create and return an object of the class ScheduleInfo (which is the model)
			return new ScheduleInfo1p1( scheduleID); 
		}
		
		// resetSCHED_ID_SQL sets the string SCHED_ID_SQL to its original value
		public static void resetSCHED_ID_SQL() {
			SCHED_ID_SQL = "select sched_id from schedule "; 
		}
		
		// resetSCHED_INSERT sets the string SCHED_INSERT to its original value
		public static void resetINSERT_SQL() {
			INSERT_SQL = "INSERT INTO schedule(emp_id) VALUES("; 
		}
			
		// updating the query string to the new query string formed in the class ScheduleDAO, method addToQueryStr
		public static void updateSQL(String sql) {
			SCHED_ID_SQL = sql; // sql - new query string
		}
		
		// updating the query string to the new query string formed in the class ScheduleDAO, method addToQueryInsert
		public static void updateSQLIns(String sql) {
			INSERT_SQL = sql; // sql - new query string
		}
}

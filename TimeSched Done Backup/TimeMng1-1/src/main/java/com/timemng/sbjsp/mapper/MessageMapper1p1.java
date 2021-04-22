// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.timemng.sbjsp.model.MessageInfo1p1; // MessageInfo - model class (the class representing the data of the record in the msg table)
import org.springframework.jdbc.core.RowMapper;

// MessageMapper - a mapper class (used for mapping corresponding to 1-1 relationship between 1 column in the result of the query statement and 1 field in 
// the model class MessageInfo.java)
public class MessageMapper1p1 implements RowMapper<MessageInfo1p1> {   
	// SQL_INSERT is a SQL query used to insert a new message in the msg table
	public static String SQL_INSERT = "insert into msg(person_id, message) values (";  

	@Override
	public MessageInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
		// mapping 1 column in the result of the query statement and 1 field in the model class MessageInfo.java 
		String message = rs.getString("message");
		String employeeID = rs.getString("person_id");
		// create and return an object of the class MessageInfo (which is the model)
		return new MessageInfo1p1( message, employeeID);
	}
	
	// resetSQL_INSERT sets the string SQL_INSERT to its original value
	public static void resetSQL_INSERT() {
		SQL_INSERT = "insert into msg(person_id, message) values (";
	}

	// updating the query string SQL_INSERT to the new query string formed in the class MessageDAO, method addToQueryInsert 
	public static void updateSQLINSERT(String sql) {
		SQL_INSERT = sql; // sql - new query string
	}
}

// project : Time Manager, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.timemng.sbjsp.model.MsgCustInfo1p1; // MsgCustInfo - model class (the class representing the data of the record in the msgcust table)
import org.springframework.jdbc.core.RowMapper;

// MsgCustMapper1p1 - a mapper class (used for mapping corresponding to 1-1 relationship between 1 column in the result of the query statement and 1 field in 
// the model class MsgCustInfo.java)
public class MsgCustMapper1p1 implements RowMapper<MsgCustInfo1p1> {   
	
	// SQL_INSERT is a SQL query used to insert a new message in the msgcust table
	public static String SQL_INSERT = "insert into msgcust(person_id, message) values (";  

	@Override
	public MsgCustInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
		// mapping 1 column in the result of the query statement and 1 field in the model class MessageInfo.java 
		String message = rs.getString("message");
		String personID = rs.getString("person_id");
		// create and return an object of the class MsgCustInfo (which is the model)
		return new MsgCustInfo1p1( message, personID);
	}
		
	// resetSQL_INSERT sets the string SQL_INSERT to its original value
	public static void resetSQL_INSERT() {
		SQL_INSERT = "insert into msgcust(person_id, message) values (";
	}

	// updating the query string SQL_INSERT to the new query string formed in the class MessageDAO, method addToQueryInsert
	public static void updateSQLINSERT(String sql) {
		SQL_INSERT = sql; // sql - new query string
	}
}
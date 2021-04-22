// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
// CustIDInfo - model class (the class which contains the cust_id from the customer table)
import com.timemng.sbjsp.model.CustIDInfo1p1;

// CustIDMapper - a mapper class (used for mapping corresponding to 1-1 relationship between 1 column in the result of the query statement and 1 field in 
// the class CustIDMapper.java)
public class CustIDMapper1p1 implements RowMapper<CustIDInfo1p1> {
		
		// SQL_CUST_ID is a SQL query used to select cust_id
		public static String SQL_CUST_ID = "select cust_id from customer ";   

		@Override
		public CustIDInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
			// mapping 1 column in the result of the query statement and 1 field in the model class CustEmailInfo.java 
			String cust_id = rs.getString("cust_id");
			// create and return an object of the class CustEmailInfo ( which is the model )
			return new CustIDInfo1p1( cust_id );
		}
		
		// resetSQL_CUST_ID sets the string SQL_CUST_ID to its original value
		public static void resetSQL_CUST_ID() {
			SQL_CUST_ID = "select cust_id from customer ";  
		}
		
		// updating the query string to the new query string formed in the class CustEmailDAO, method addToQueryStrEmail
		public static void updateSQL_CUST_ID(String sql) {
			SQL_CUST_ID = sql; // sql - new query string
		}
}
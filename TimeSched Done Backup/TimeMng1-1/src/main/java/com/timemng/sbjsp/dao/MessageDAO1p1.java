// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.dao;

// importing the classes
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.timemng.sbjsp.mapper.MessageMapper1p1;

@Repository
@Transactional
public class MessageDAO1p1 extends JdbcDaoSupport {
	@Autowired
	public MessageDAO1p1(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	// addMessage - adding the new message (of the employee with the employee ID) to the table 
	public int addMessage() {
	 	int numRows=-1; // number of rows affected with the insert statement
	 	// SQL_INSERT is the String that contains the query onto which later I added the employee ID, message depending on the data the user entered in the Contact Us form
	 	// in the form contact_form.jsp 
	 	String sql = MessageMapper1p1.SQL_INSERT;
	 	try {
	 	    Object[] params = new Object[] {};
	 	    // MessageMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class (MessageInfo.java)
	 	    MessageMapper1p1 mapper = new MessageMapper1p1();
	 	    // running the query of adding a new message (of the employee with employee ID) to the table message
	 	    numRows = this.getJdbcTemplate().update(sql);
	 	} catch (Exception e) {
	 	    e.printStackTrace();
	 	}   
	 	// returns the number of rows affected with the insert statement (if an exception occurred -1 is returned)
	 	return numRows; 
	}
	
	// add to the INSERT SQL query the values for the personID and the message 
	public void addToQueryInsert(String personID, String message) {
	  	// if other user (regular or admin) logged in before then the original SQL_INSERT got changed so I have to reset it to its original value 
	  	MessageMapper1p1.resetSQL_INSERT();
	  	String sql = MessageMapper1p1.SQL_INSERT;
	  	// I am changing the SQL query to return the person's first and last name (for the given person's ID) 
	  	sql += personID + ", '" + message + "')";
	  	sql += ";";
	  	// update the SQL_INSERT to the sql
	  	MessageMapper1p1.updateSQLINSERT(sql);
	}
}
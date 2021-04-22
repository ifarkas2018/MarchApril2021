// project : Time Manager, author : Ingrid Farkas, 2020 
package com.timemng.sbjsp.dao;
	
// importing the classes
import java.util.List;
import javax.sql.DataSource;
import com.timemng.sbjsp.mapper.CustIDMapper1p1;
import com.timemng.sbjsp.model.CustIDInfo1p1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustIDDAO1p1 extends JdbcDaoSupport {
	@Autowired
	public CustIDDAO1p1(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	// getCustID - retrieving the customer ID for the customer with the certain name and email (argument)
	public List<CustIDInfo1p1> getCustID() {
		List<CustIDInfo1p1> list = null; // initialising the list
		
		// SQL_CUST_ID - the String that contains the query on which the WHERE clause was added depending on the value of the first name, last name and email  
		String sql = CustIDMapper1p1.SQL_CUST_ID;
		try {
		   	Object[] params = new Object[] {};
		   	// CustIDMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class (CustIDInfo.java)
		   	CustIDMapper1p1 mapper = new CustIDMapper1p1();
		   	// running the query and retrieving the customer ID for the customer with the certain name and email
		   	list = this.getJdbcTemplate().query(sql, params, mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; // return the email for the customer with the certain name
	}
	
	// add to the SQL query the WHERE clause - where (first_name = first name of the customer) 
	// and (last_name = last name of the customer) and (email = Email)
	public void addToQueryCustID(String firstName, String lastName, String email) {
		boolean firstNameEx; // does the first name exist
		boolean lastNameEx; // does the last name exist
		boolean emailEx; // does the email exist
		
		// if the customer filled in the Contact Us form before the original SQL_CUST_ID got changed so I have to reset it to its original value 
		CustIDMapper1p1.resetSQL_CUST_ID();
		String sql = CustIDMapper1p1.SQL_CUST_ID;
		firstNameEx = ((firstName!=null) && (!firstName.equals(""))); // does the first name exist
		lastNameEx = ((lastName!=null) && (!lastName.equals(""))); // does the last name exist
		emailEx = ((email!=null) && (!email.equals("")));
		sql += " where";
		if (firstNameEx) 
			sql += " first_name='" + firstName + "'";
		if (lastNameEx) {
			sql += " and"; 
			sql += " last_name='" + lastName + "'";
		}
		if (emailEx) {
			sql += " and"; 
			sql += " email='" + email + "'";
		}
		sql += ";";
		// update the SQL_USR_EMAIL to the sql
		CustIDMapper1p1.updateSQL_CUST_ID(sql);	
	}
}

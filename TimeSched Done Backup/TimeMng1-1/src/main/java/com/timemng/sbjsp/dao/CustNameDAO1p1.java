// project : Time Manager, author : Ingrid Farkas, 2020 
package com.timemng.sbjsp.dao;

// importing the classes
import java.util.List;
import javax.sql.DataSource;
import com.timemng.sbjsp.mapper.CustNameMapper1p1;
import com.timemng.sbjsp.model.CustNameInfo1p1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustNameDAO1p1 extends JdbcDaoSupport {
	@Autowired
	public CustNameDAO1p1(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	// getCustomerID - retrieving the customer ID for the customer with the certain name 
	public List<CustNameInfo1p1> getCustomerID() {
		List<CustNameInfo1p1> list = null; // initialising the list
		// SQL_CUST_ID - the String that contains the query on which the WHERE clause was added depending on the value of the firstName, lastName  
		String sql = CustNameMapper1p1.SQL_CUST_ID;
		try {
		   	Object[] params = new Object[] {};
		   	// CustNameMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class (CustNameInfo.java)
		   	CustNameMapper1p1 mapper = new CustNameMapper1p1();
		   	// running the query and retrieving the customer ID with the certain first name and last name
		   	list = this.getJdbcTemplate().query(sql, params, mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; // return the customer ID for the customer with the certain first name and last name
	}

	// add to the SQL query the WHERE clause - where (first_name = first name of the customer) and (last_name = last name of the customer)
	public void addToQueryStrID(String firstName, String lastName) {
		boolean firstNameEx; // does the first name exist
		boolean lastNameEx; // does the last name exist
		
		// if other customer used Contact Us page before then the original SQL_CUST_ID got changed so I have to reset it to its original value 
		CustNameMapper1p1.resetSQL_CUST_ID();
		String sql = CustNameMapper1p1.SQL_CUST_ID;
		firstNameEx = ((firstName!=null) && (!firstName.equals(""))); // does the first name exist
		lastNameEx = ((lastName!=null) && (!lastName.equals(""))); // does the last name exist
		if (firstNameEx) {
			// I am changing the SQL query to return the customer's ID for the entered first and last name  
			sql += " where (first_name='" + firstName + "') ";		
			if (lastNameEx) 
				sql += "and";
		} else if (lastNameEx) {
			sql += " where";
		}
		if (lastNameEx){
			// I am changing the SQL query to return the customer's ID for the entered last name  
			sql += " (last_name='" + lastName + "') ";
		}
		sql += ";";
		// update the SQL_CUST_NAME to the sql
		CustNameMapper1p1.updateSQL_CUST_ID(sql);
	}

}
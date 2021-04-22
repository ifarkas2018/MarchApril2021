// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.dao;

// importing the classes
import java.util.List;
import javax.sql.DataSource;
import com.timemng.sbjsp.mapper.EmpIDMapper1p1;
import com.timemng.sbjsp.model.EmpIDInfo1p1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmpIDDAO1p1 extends JdbcDaoSupport {
	@Autowired
	public EmpIDDAO1p1(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	// getEmployeeID - retrieving the employee ID for the employee with the certain name (argument)
	public List<EmpIDInfo1p1> getEmployeeID( String firstName, String lastName) {
		List<EmpIDInfo1p1> list = null; // initialising the list

		// SQL_EMP_ID - the String that contains the query on which the WHERE clause was added depending on the value of the firstName, lastName  
		String sql = EmpIDMapper1p1.SQL_EMP_ID;
		try {
		   	Object[] params = new Object[] {};
		   	// EmpNameMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class ( EmpNameInfo.java )
		   	EmpIDMapper1p1 mapper = new EmpIDMapper1p1();
		   	// running the query and retrieving the employee ID with the certain first name and last name
		   	list = this.getJdbcTemplate().query(sql, params, mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; // return the employee ID for the employee with the certain first name and last name
	}

	// add to the SQL query the WHERE clause - where (first_name = first name of the employee) and (last_name = last name of the employee)
	public void addToQueryStrID(String firstName, String lastName) {
		boolean firstNameEx; // does the first name exist
		boolean lastNameEx; // does the last name exist
		
		// if the user (admin) logged in and queried the database for some other employee ID the original SQL_EMP_ID got changed so I have to reset it to its original value 
		EmpIDMapper1p1.resetSQL_EMP_ID();
		String sql = EmpIDMapper1p1.SQL_EMP_ID;
		firstNameEx = ((firstName!=null) && (!firstName.equals(""))); // does the first name exist
		lastNameEx = ((lastName!=null) && (!lastName.equals(""))); // does the last name exist
		if (firstNameEx) {
			// I am changing the SQL query to return the employee's ID for the entered first and last name  
			sql += " where (first_name='" + firstName + "') ";
			if (lastNameEx) 
				sql += "and";
		} else if (lastNameEx) {
			sql += " where";
		}
		if (lastNameEx){
			// I am changing the SQL query to return the employee's ID for the entered last name  
			sql += " (last_name='" + lastName + "') ";
		}
		sql += ";";
		// update the SQL_EMP_NAME to the sql
		EmpIDMapper1p1.updateSQL_EMP_ID(sql);
	}
}
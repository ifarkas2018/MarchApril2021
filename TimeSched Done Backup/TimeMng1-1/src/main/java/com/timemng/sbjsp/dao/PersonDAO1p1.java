// project : Time Manager, author : Ingrid Farkas, 2020 
package com.timemng.sbjsp.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.timemng.sbjsp.model.PersonInfo1p1;
import com.timemng.sbjsp.mapper.PersonMapper1p1;

@Repository
@Transactional
public class PersonDAO1p1  extends JdbcDaoSupport {
	@Autowired
	public PersonDAO1p1(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	// getName - retrieving name of the employee with the certain empID 
	public List<PersonInfo1p1> getName() {
		List<PersonInfo1p1> list = null; // initialising the list
		
		// SQL_EMP_NAME - the String that contains the part of the query onto which the WHERE clause was added 
		String sql = PersonMapper1p1.SQL_EMP_NAME;
		try {
			Object[] params = new Object[] {};
			// PersonNameMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class (PersonNameInfo.java)
			PersonMapper1p1 mapper = new PersonMapper1p1();
			// running the query and retrieving the list of employee name with the specified employee ID
			list = this.getJdbcTemplate().query(sql, params, mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; // return the name for the employee with the certain empID
	}
	
	// add to the SQL query the WHERE clause - where (emp_id = id of the employee) 
	public void addToQueryStrName(String empID) {
		// if other user (regular or admin) logged in before then the original SQL_EMP_NAME got changed so I have to reset it to its original value 
		PersonMapper1p1.resetSQL_EMP_NAME();
		String sql = PersonMapper1p1.SQL_EMP_NAME;
	
		// I am changing the SQL query to return the employee's first and last name (for the given employee's ID) 
		sql += " where emp_id='" + empID + "' ";
		sql += ";";
		// update the SQL_EMP_NAME to the sql
		PersonMapper1p1.updateSQL_NAME(sql);
	}
}
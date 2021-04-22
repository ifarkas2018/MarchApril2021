// project : Time Manager, author : Ingrid Farkas, 2020 
package com.timemng.sbjsp.dao;

// importing the classes
import java.util.List;
import javax.sql.DataSource;
import com.timemng.sbjsp.mapper.EmpEmailMapper1p1;
import com.timemng.sbjsp.model.EmpEmailInfo1p1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmpEmailDAO1p1 extends JdbcDaoSupport {
	@Autowired
	public EmpEmailDAO1p1(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
  
	// getEmpEmail - retrieving the email for the employee with the certain employee ID and name (argument)
	public List<EmpEmailInfo1p1> getEmpEmail() {
		List<EmpEmailInfo1p1> list = null; // initialising the list
		
		// SQL_EMP_ID - the String that contains the query on which the WHERE clause was added depending on the value of the employee ID, first name, last name  
		String sql = EmpEmailMapper1p1.SQL_USR_EMAIL;
		try {
		   	Object[] params = new Object[] {};
		   	// EmpEmailMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class (EmpEmailInfo.java)
		   	EmpEmailMapper1p1 mapper = new EmpEmailMapper1p1();
		   	// running the query and retrieving the email for the employee with the certain employee ID and name
		   	list = this.getJdbcTemplate().query(sql, params, mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; // return the email for the employee with the certain employee ID and name
	}

	// updateEmail - adding the email to the table employee (for the employee with emp_id) 
	public int updateEmail() {
		int numRows=-1; // number of rows affected with the update statement
		    
		// SQL_UPD_EMAIL is the string that contains the UPDATE query for updating the email in the table employee for the employee with emp_id  
		// in the form contact_form.jsp
		String sql = EmpEmailMapper1p1.SQL_UPD_EMAIL;
		try {
		    // running the query of updating the email in the table employee for the employee with emp_id
		    numRows = this.getJdbcTemplate().update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}     
		// returns the number of rows affected with the update statement (if an exception occurred -1 is returned)
		return numRows; 
	}


	// add to the SQL query the WHERE clause - where (firstName = first name of the employee) (OR firstName LIKE '%' if firstName == '')
	// and (lastName = last name of the employee) (OR lastName LIKE '%' if lastName == '')
	public void addToQueryStrEmail(String firstName, String lastName) {
		boolean empIDEx; // does the empID exist
		boolean firstNameEx; // does the first name exist
		boolean lastNameEx; // does the last name exist
		
		// if the user (admin) logged in and queried the database for some other email the original SQL_USR_EMAIL got changed so I have to reset it to its original value 
		EmpEmailMapper1p1.resetSQL_USR_EMAIL();
		String sql = EmpEmailMapper1p1.SQL_USR_EMAIL;
		firstNameEx = ((firstName!=null) && (!firstName.equals(""))); // does the first name exist
		lastNameEx = ((lastName!=null) && (!lastName.equals(""))); // does the last name exist
		sql += " where";
		if (firstNameEx){
			sql += " first_name = '" + firstName + "'";
		} else {
			sql += " first_name LIKE '%'";
		}
		sql += " and "; 
		if (lastNameEx){
			sql += " last_name = '" + lastName + "'";
		} else {
			sql += " last_name LIKE '%'";
		}
		sql += ";";
		// update the SQL_USR_EMAIL to the sql
		EmpEmailMapper1p1.updateSQL_USR_EMAIL(sql);
	}

	// add to the UPDATE query the email and the WHERE clause - where emp_id = i
	public void addToQueryUpdEmail(String empID, String email) {
		// if the user ( admin ) logged in and queried the database for some other email the original SQL_UPD_EMAIL got changed so I have to reset it to its original value 
		EmpEmailMapper1p1.resetSQL_UPD_EMAIL();
		String sql = EmpEmailMapper1p1.SQL_UPD_EMAIL;
		sql += "'" + email + "'" + " where emp_id='" + empID + "';";
		// update the SQL_USR_EMAIL to the sql
		EmpEmailMapper1p1.updateSQL_UPD_EMAIL(sql);
	}
}

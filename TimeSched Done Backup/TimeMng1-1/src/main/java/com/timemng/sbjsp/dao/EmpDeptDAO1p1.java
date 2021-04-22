// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.dao;

// importing the classes
import javax.sql.DataSource;
import com.timemng.sbjsp.mapper.EmpDeptMapper1p1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
	
@Repository
@Transactional
public class EmpDeptDAO1p1 extends JdbcDaoSupport {

	@Autowired
	 public EmpDeptDAO1p1(DataSource dataSource) {
	     this.setDataSource(dataSource);
	 }

	 // addEmployee - adding the new employee (with the first name fname, last name lname working in the department) to the table 
	 public int addEmployee(String fName, String lName, String department) {
		 int numRows=-1; // number of rows affected with the insert statement
	    
	 	 // ADD_EMP_SQL is the String that contains the query on which I added the first name, last name, department depending on the data the user entered in the Add Employee form
	 	 // in the form addempl_fcont.jsp 
	     String sql = EmpDeptMapper1p1.ADD_EMP_SQL;
	     try {
	     	 // running the query of adding a new user with the fName (first name), lName (last name), department
	     	 numRows = this.getJdbcTemplate().update(sql);
	     } catch (Exception e) {
	     	 e.printStackTrace();
	     }
	     // returns the number of rows affected with the insert statement ( if an exception occurred -1 is returned )
	     return numRows; 
	 }
	 
	 // adds to the SQL query the first name, last name, department depending on the data the user entered in the Add Employee form
	 // if the user didn't enter first name or last name (in the form) then this method returns false
	 public boolean addToQueryStr(String fName, String lName, String department) {
	 	boolean returnVal; // the value returned by the method
	 	
	  	// if the user before added a new employee then the original ADD_EMP_SQL got changed so I have to reset it to its original value 
	  	EmpDeptMapper1p1.resetADD_EMP_SQL(); 
	  	String sql = EmpDeptMapper1p1.ADD_EMP_SQL;
	    if ((!(fName.equals(null))) && (!(lName.equals(null)))) {
	        sql += "'" + fName + "',"; // add the first name to the insert
	  		sql += "'" + lName + "'"; // add the last name to the insert
	  		if (!(department.equals(null))){
	  		    sql += ",'" + department + "'"; // add the department to the insert
	  		}
	  		sql += ");"; 
	  		returnVal = true;
	  	} else {
	  		returnVal = false; // the user didn't enter first name or last name
	  	}	  	
	  	// update the ADD_EMP_SQL to the sql
	  	EmpDeptMapper1p1.updateSQL(sql);
	  	return returnVal;
	  }
}

// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.dao;

// importing the packages
import java.util.List;
import javax.sql.DataSource;
import com.timemng.sbjsp.mapper.ScheduleMapper1p1;
import com.timemng.sbjsp.model.ScheduleInfo1p1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ScheduleDAO1p1 extends JdbcDaoSupport {
	@Autowired
    public ScheduleDAO1p1(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    // getSchedID - retrieving the sched_id from the table schedule 
    public List<ScheduleInfo1p1> getSchedID() {
    	List<ScheduleInfo1p1> list = null; // initialising the list
        
    	// SCHED_ID_SQL is the String that contains the query on which the WHERE clause was added depending on the values the user entered 
    	// used in the MainController.java (/add_d_res) 
        String sql = ScheduleMapper1p1.SCHED_ID_SQL;
        try {
        	Object[] params = new Object[] {};
        	// ScheduleMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class (ScheduleInfo.java)
        	ScheduleMapper1p1 mapper = new ScheduleMapper1p1();
        	// running the query and retrieving the list of sched_id from the table schedule for the given emp_id
        	list = this.getJdbcTemplate().query(sql, params, mapper);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return list; // return the schedule ID for the certain employee
    }
    
    // addEmpID - adding the employee ID to the table schedule 
 	public int addEmpID(String empID) {
 	    int numRows=-1; // number of rows affected with the insert statement
 	    
 	 	// INSERT_SQL is the String that contains the query onto which I added the empID
 	    String sql = ScheduleMapper1p1.INSERT_SQL;
 	    try {
 	     	Object[] params = new Object[] {};
 	     	// ScheduleMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class (ScheduleInfo.java)
 	     	ScheduleMapper1p1 mapper = new ScheduleMapper1p1();
 	     	// running the query (insert the employee ID in the table schedule)
 	     	numRows = this.getJdbcTemplate().update(sql);
 	    } catch (Exception e) {
 	     	e.printStackTrace();
 	    }
 	    // returns the number of rows affected with the insert statement (if an exception occurred -1 is returned)
 	    return numRows; 
 	}
    
    // add to the SQL query the WHERE clause - where (emp_id = entered value for employeeID) 
    public void addToQueryStr(String employeeID) {
     	// if the user ran the Add Task before then the original SCHED_ID_SQL got changed so I have to reset it to its original value 
     	ScheduleMapper1p1.resetSCHED_ID_SQL();
     	String sql = ScheduleMapper1p1.SCHED_ID_SQL;
     	// I am changing the SQL query to return the records where the emp_id equals the employeeID of the user whose task is being added
     	if (!(employeeID.equals(null))) 
     		sql += "where (emp_id='" + employeeID + "')";
     	sql += ";";
      	// update the SCHED_ID_SQL to the sql
     	ScheduleMapper1p1.updateSQL(sql);	
     }
    
     // adds to the SQL query the employee ID (this is the same employee ID added to the table employee)
     // used when adding an employee (by the administrator)
     // if the empID doesn't exist then this method returns false
 	 public boolean addToQueryInsert(String empID) {
 		 boolean returnVal; // the value returned by the method
 		 
 	  	 // if the admin before added a new employee then the original INSERT_SQL got changed so I have to reset it to its original value 
 	  	 ScheduleMapper1p1.resetINSERT_SQL(); 
 	  	 String sql = ScheduleMapper1p1.INSERT_SQL;
 	     if (!(empID.equals(null))){
 	    	 sql += "'" + empID + "'"; // add the employee ID to the insert statement
 	  		 sql += ")" ; 
 	  		 returnVal = true;
 	  	 } else {
 	  		 returnVal = false; // the employee ID doesn't exist
 	  	 }	  	
 	     // update the INSERT_SQL to the sql
 	     ScheduleMapper1p1.updateSQLIns(sql);
 	     return returnVal;
 	 }
}
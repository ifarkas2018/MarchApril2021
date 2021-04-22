// project : Time Manager, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.controller;

// importing the packages
import java.util.ArrayList;
import java.util.List;
import com.timemng.sbjsp.dao.EmpDeptDAO1p1;
import com.timemng.sbjsp.dao.EmpIDNameDAO1p1;
import com.timemng.sbjsp.model.EmpIDNameInfo1p1;
import com.timemng.sbjsp.model.EmpIDInfo1p1;
import com.timemng.sbjsp.dao.EmpIDDAO1p1;
import com.timemng.sbjsp.dao.PersonDAO1p1;
import com.timemng.sbjsp.model.PersonInfo1p1;
import com.timemng.sbjsp.model.EmpEmailInfo1p1;
import com.timemng.sbjsp.dao.EmpEmailDAO1p1;
import com.timemng.sbjsp.model.CustNameInfo1p1;
import com.timemng.sbjsp.dao.CustNameDAO1p1;
import com.timemng.sbjsp.model.CustEmailInfo1p1;
import com.timemng.sbjsp.dao.CustEmailDAO1p1;
import com.timemng.sbjsp.model.CustIDInfo1p1;
import com.timemng.sbjsp.dao.CustIDDAO1p1;
import com.timemng.sbjsp.model.EmpSchedTaskInfo1p1;
import com.timemng.sbjsp.dao.EmpSchedTaskDAO1p1;
import com.timemng.sbjsp.model.ScheduleInfo1p1;
import com.timemng.sbjsp.dao.ScheduleDAO1p1;
import com.timemng.sbjsp.mapper.CustEmailMapper1p1;
import com.timemng.sbjsp.model.LoginInfo1p1;
import com.timemng.sbjsp.dao.LoginDAO1p1;
import com.timemng.sbjsp.dao.MessageDAO1p1;
import com.timemng.sbjsp.dao.MsgCustDAO1p1;
import com.timemng.sbjsp.other.TimeMngLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController1p1 {
	 
	 @Autowired
	 private EmpSchedTaskDAO1p1 empSchedTaskDAO;  
	 @Autowired
	 private ScheduleDAO1p1 schedDAO;
	 @Autowired
	 private PersonDAO1p1 personDAO;
	 @Autowired
	 private LoginDAO1p1 empLoginDAO;
	 @Autowired
	 private EmpDeptDAO1p1 empDeptDAO;
	 @Autowired
	 private EmpIDDAO1p1 empIDDAO;
	 @Autowired
	 private CustNameDAO1p1 custNameDAO;
	 @Autowired
	 private EmpIDNameDAO1p1 empIDnameDAO;
	 @Autowired
	 private EmpEmailDAO1p1 empEmailDAO;
	 @Autowired
	 private CustEmailDAO1p1 custEmailDAO;
	 @Autowired
	 private CustIDDAO1p1 custIDDAO1p1;
	 @Autowired
	 private MessageDAO1p1 messageDAO;
	 @Autowired
	 private MsgCustDAO1p1 msgcustDAO;
	    
	// if the requested URL is localhost:8080, method is GET do 
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String index(Model model) {
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before (and is still logged in)
        return "index"; 
    }
    
    // if the requested URL is localhost:8080, method is POST do 
    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public String index1(Model model) {
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before ( and is still logged in )
        return "index";
    }
    
    // if the requested URL is localhost:8080/home, method is GET do
    // used if the user before logged in 
    @RequestMapping(value = {"home"}, method = RequestMethod.GET)
    public String indhome(Model model) {
    	model.addAttribute("logged_in", "false"); // the user is not logged in
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
        return "index"; 
    }
    
    // if the requested URL is localhost:8080/home, method is POST do
    // used if the user before logged in 
    @RequestMapping(value = {"home"}, method = RequestMethod.POST)
    public String indhome_post(Model model) { 
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
        return "index"; 
    }
    
    // if the requested URL is localhost:8080/add_login, method is POST do
    // used if the administrator is adding a new login (addshw_fcont.jsp)
    @RequestMapping(value = {"add_login"}, method = RequestMethod.POST)
    public String add_login(Model model, HttpServletRequest request, @RequestParam(value="first_name", required=false) String first_name, // 
    	@RequestParam(value="last_name", required=false) String last_name, @RequestParam(value="department", required=false) String department) {
    	
    	String fName = ""; // the employee's first name
    	String lName = ""; // the employee's last name
    	String dep = ""; // department 
    	
    	fName = first_name;
    	lName = last_name;
    	dep = department;
    	
    	request.getSession().setAttribute("fName", fName);
    	request.getSession().setAttribute("lName", lName);
    	request.getSession().setAttribute("department", department);
    	
    	// adding the attributes to the model
        model.addAttribute("logged_in","true"); // the user didn't log in
    	model.addAttribute("is_admin","true"); // the user logged in as admin
    	model.addAttribute("logged_out", "false"); // the user is not logging out
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	model.addAttribute("logging_in", "false"); // the user is not trying now to log in
    	model.addAttribute("add_login", "true"); // the administrator is adding a new login
    	return "logform";
    }
    
    // if the requested URL is localhost:8080/loginfo, method is GET 
    @RequestMapping(value = {"loginfo"}, method = RequestMethod.GET)
    public String loginfo(Model model) {
    	// adding the attributes to the model
        model.addAttribute("logged_in","false"); // the user didn't log in
    	model.addAttribute("is_admin","false"); // the user didn't log in as admin 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before (and is still logged in)
    	model.addAttribute("logging_in", "true"); // the user is trying to log in
    	model.addAttribute("add_login", "false"); // the administrator is not adding a new login
    	return "loginfo"; // return the logform.jsp
    }
    
    // if the requested URL is localhost:8080/logform, method is GET 
    @RequestMapping(value = {"logform"}, method = RequestMethod.POST)
    public String logform(Model model) {
    	// adding the attributes to the model
        model.addAttribute("logged_in","false"); // the user didn't log in
    	model.addAttribute("is_admin","false"); // the user didn't log in as admin 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before (and is still logged in)
    	model.addAttribute("logging_in", "true"); // the user is trying to log in
    	model.addAttribute("add_login", "false"); // the administrator is not adding a new login
    	return "logform"; // return the logform.jsp
    }
    
    // if the requested URL is localhost:8080/login_result, method is POST (from logfcont.jsp)
    @RequestMapping(value = "login_result", method = RequestMethod.POST) 
    // user_name is an input element in logfcont.jsp. The user entered the user_name, user_passw 
	public String login_result2(Model model, HttpServletRequest request, @RequestParam(value="user_name", required=true) String user_name, // 
		@RequestParam(value="user_passw", required=true) String user_passw) {
    	
    	String loginID = ""; // the logged in user's ID
    	String empID = ""; // the logged in employee ID
    	String fName = ""; // the first name of the logging in user
    	String lName = ""; // the last name of the logging in user
    	
    	try { 
        	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    		model.addAttribute("logged_in","true"); // setting the default value for whether the user logged in
    		model.addAttribute("already_login", "false"); // did the user log in before (and is still logged in)
 
    		String usernm_noapostr = user_name; // user name with no ' 
    		user_name = TimeMngLibrary.addApostrophe(user_name);
    		user_passw = TimeMngLibrary.addApostrophe(user_passw);
    		
    		if (user_name.equals("admin")) {
    			// add to the SQL query the WHERE clause - where (user_name = "admin") and (password = entered value for password)
    			// if the user didn't enter password (in the form) then this method returns false
    			if (empLoginDAO.addToQueryStr("admin", user_passw)) {
    				// list is a list of objects of type LoginInfo (user name, password) that match the entered values of user name and password
    				List<LoginInfo1p1> list = empLoginDAO.getLogin();
    				// is in the database a user name and the password which was entered in the form
    				if (list != null && !list.isEmpty()) {
    				    model.addAttribute("is_admin", "true"); // the user logged in as admin
    				    LoginInfo1p1 log_info = list.get(0); // retrieving the object of type LoginInfo1p1 which contains the employee ID
    				    loginID = log_info.getEmployeeID(); // retrieving the employee ID
    				    request.getSession().setAttribute("loginID", loginID); // storing the login ID (of the user that is loggin in) in the session attribute
    				    return "index"; 
    				}
    				else { 
    					model.addAttribute("is_admin", "false"); // the user didn't log in as admin
    					model.addAttribute("page_title", "Log In");
						// setting the attribute logged_in to false - the user didn't log in
						model.addAttribute("logged_in", "false");
						model.addAttribute("message_shown", "The user with the user name " + usernm_noapostr + " and the entered password doesn't exist!");
						// the message should be in red (adding it to the model)
						model.addAttribute("is_red", "true");
						return "result"; // show the result.jsp     			
    				}	
    			} else { // the user didn't enter the password
    				model.addAttribute("is_admin", "false"); 
					model.addAttribute("page_title", "Log In");
					// setting the attribute logged_in to false - the user didn't log in
					model.addAttribute("logged_in", "false");
					model.addAttribute("message_shown", "The user with the user name " + usernm_noapostr + " and the entered password doesn't exist!");
					// the message should be in red (adding it to the model)
					model.addAttribute("is_red", "true");
					return "result"; // show the result.jsp  
    			}
			} else if (empLoginDAO.addToQueryStr(user_name, user_passw)) { // the user is logging in as regular user
					// add the rest of the query to the query
		    		// addToQueryStr returns FALSE - if the user didn't enter user name or password (in the form) 
					// list is a list of objects of type LoginInfo (user name, password) that match the entered values of user name and password
					List<LoginInfo1p1> list = empLoginDAO.getLogin();
					// is in the database a user name and the password which was entered in the form
					if (list != null && !list.isEmpty()) {
						model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user did log in 
						model.addAttribute("is_admin", "false"); // the user is not logged in as admin
						LoginInfo1p1 log_info = list.get(0); // retrieving the object of type LoginInfo1p1 which has the employee ID
						loginID = log_info.getEmployeeID(); // retrieving the employee ID
						request.getSession().setAttribute("loginID", loginID); // storing the login ID (of the user that is loggin in) in the session attribute
						empID = loginID; // reset the ID of the logged in employee 
						request.getSession().setAttribute("empID", empID);
						fName = ""; 
						request.getSession().setAttribute("fName", fName);
						lName = "";
						request.getSession().setAttribute("lName", lName);
						return "index"; 
					} else { // the user with that user name and password doesn't exist
						model.addAttribute("page_title", "Log In");
						// setting the attribute logged_in to false - the user didn't log in
						model.addAttribute("logged_in", "false");
						model.addAttribute("is_admin", "false"); // the user is not logged in as admin
						model.addAttribute("message_shown", "The user with the user name " + usernm_noapostr + " and the entered password doesn't exist!");
						// the message should be in red (adding it to the model)
						model.addAttribute("is_red", "true");
						return "result"; // show the result.jsp 
					}
			} else { // the user didn't enter the password
				model.addAttribute("page_title", "Log In");
				model.addAttribute("message_shown", "You didn't enter the user name and the password!"); // adding the message to the model  
				model.addAttribute("is_red", "true"); // the message should be in red (adding it to the model)
				model.addAttribute("logged_in", "false"); // set the attribute logged_in to false - the user didn't log in
				return "result"; // show the result.jsp 
			}
    	} catch (Exception e) { // if an exception occurred
        	e.printStackTrace();
        	model.addAttribute("page_title", "Log In");
        	model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
        	model.addAttribute("is_red", "true"); // the message should be in red (adding it to the model)
        	model.addAttribute("logged_in", "false"); // setting the attribute logged_in to false - the user didn't log in
        	return "result";
        }
	}
    
    // if the requested URL is localhost:8080/addempl_form, method is GET do 
    @RequestMapping(value = {"addempl_form"}, method = RequestMethod.GET)
    public String addempl_form(Model model ) { 
    	// setting the model attributes (I can access them from .jsp)
    	model.addAttribute("logged_in", "true"); // the user is logged in
    	model.addAttribute("is_admin", "true"); // the user is logged in as admin
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	model.addAttribute("is_add_emp","true"); // it is an Add Employee not Show Employee
    	model.addAttribute("is_show_emp","false"); // it isn't a Show Employee but Add Employee
    	return "addshw_form";
    }
    
    // if the requested URL is localhost:8080/showempl_form, method is GET do 
    @RequestMapping(value = {"showempl_form"}, method = RequestMethod.GET)
    public String showempl_form(Model model) { 
    	// setting the model attributes (I can access them from .jsp)
    	model.addAttribute("logged_in", "true"); // the user is not logged in
    	model.addAttribute("is_admin", "true"); // the user is logged in as admin
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before (and is still logged in)
    	model.addAttribute("is_add_emp","false"); // it isn't an Add Employee but Show Employee
    	model.addAttribute("is_show_emp","true"); // it is a Show Employee
    	return "addshw_form";
    }
    
    // if the requested URL is localhost:8080/addshow_emp, method is POST
    @RequestMapping(value = "addshow_emp", method = RequestMethod.POST)
    // first_name, last_name is an input element in addshw_fcont.jsp
    // the user entered the first name, last name of the new employee to be added to the database
	public String addshow_emp(Model model, HttpServletRequest request, 
		@RequestParam(value="user_name", required=false) String userName, @RequestParam(value="first_name", required=false) String f_Name, //
		@RequestParam(value="last_name", required=false) String l_Name, @RequestParam(value="user_passw", required=false) String password, // 
		@RequestParam(value="show_add", required=false) String show_add) {
	
    	String empID = ""; // the employee's ID
    	String fName = ""; // the employee's first name (before adding apostrophies)
    	String lName = ""; // the employee's last name (before adding apostrophies)
    	String fName_apostr = ""; // the employee's first name (after adding apostrophies)
    	String lName_apostr = ""; // the employee's last name (after adding apostrophies)

    	String department = ""; // department 
    	int numRows = -1; // how many rows were affected by the SQL statement
    	List<EmpIDNameInfo1p1> lst = new ArrayList<>(); // list of elements of type EmpIDNameInfo1p1
    	List<EmpIDInfo1p1> lstEmp = new ArrayList<>(); // list of elements of type EmpEmailInfo1p1
    	
    	try {
    		// setting the model attributes (I can access them from .jsp)
        	model.addAttribute("logged_in", "true"); // the user is logged in 
        	model.addAttribute("is_admin", "true"); // the user is logged in as admin
        	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false 
        	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
        	fName = (String)request.getSession().getAttribute("fName");
        	lName = (String)request.getSession().getAttribute("lName");
        	
        	if ((f_Name!=null) && (!f_Name.equals(""))) // if the user entered on the form the first name
        		fName = f_Name;
        	else {
        		// f_Name = fName;
        		fName =  (String)request.getSession().getAttribute("fName"); // read the first name from the session attribute
        	}
        	if ((l_Name!=null) && (!l_Name.equals("")))
        		lName = l_Name;
        	else {
        		// l_Name = lName; 
        		lName =  (String)request.getSession().getAttribute("lName"); // read the last name from the session attribute
        	}
          	fName_apostr = TimeMngLibrary.addApostrophe(fName);
        	lName_apostr = TimeMngLibrary.addApostrophe(lName);
        	if (show_add.equals("add")) { // it is Add Employee
        		userName = TimeMngLibrary.addApostrophe(userName);
            	password = TimeMngLibrary.addApostrophe(password);
            	department = (String)request.getSession().getAttribute("department");
        		// ADDING the NEW EMPLOYEE to the employee table
        		// add the rest of the query to the query - method returns FALSE - if the user didn't enter first name or last name (in the form)  
        		if (empDeptDAO.addToQueryStr(fName_apostr, lName_apostr, department)) {
        			numRows = empDeptDAO.addEmployee(fName_apostr, lName_apostr, department); // addEmployee returns a positive value if the employee was added to the database
        			// addEmployee returns a positive value if the employee was added to the database
    				if (numRows > 0) {
    					// determine the empID 
    		    		// add to the SQL query the WHERE clause - where (first_name = fName_apostr of the employee) and (last_name = lName_apostr of the employee)
    		    		empIDDAO.addToQueryStrID(fName_apostr, lName_apostr);
    		    		// list is a list of objects of type EmpNameInfo (employee ID, first name, last name) where (first_name = fName_apostr) and (last_name = lName_apostr) 
    		    		lstEmp = empIDDAO.getEmployeeID(fName_apostr, lName_apostr); // getting the name of the employee with employee ID = loginID	
    		    		if (lstEmp != null && !lstEmp.isEmpty()) { // retrieve the employee ID
    		    		    empID = lstEmp.get(0).getEmployeeID(); // retrieving the employee ID
    		    		}
    		    		// ADDING the NEW EMPLOYEE'S user name and password to the login table
    		    		// creating an INSERT SQL query for adding a new employee's user name and password to the login table
    		    		empLoginDAO.addToQueryInsert(empID, userName, password);
    		    		// adding a new employee's user name and password to the login table
    		    		numRows = empLoginDAO.addUser();
    		    		if (numRows > 0) { // if the user was added successfully to the login table
	    		    		// ADDING the NEW EMPLOYEE'S employee ID to the schedule table
	    		    		// creating an INSERT SQL query for adding a new employee's employee ID to the schedule table
	    		    		schedDAO.addToQueryInsert(empID);
	    		    		// adding a new employee's employee ID to the schedule table
	    		    		numRows = schedDAO.addEmpID(empID);
    		    		}
    		    		if (numRows > 0) { // if the new record was added successfully to the schedule table
	    					model.addAttribute("page_title", "Add Employee"); // adding the page_title variable to the model
	    					// setting the attribute logged_in to true - the user is logged in
	    					model.addAttribute("logged_in", "true");
	    					model.addAttribute("message_shown", "The employee with the name " + fName + " " + lName + " was successfully added to the database!");
	    					// the message shouldn't be in red (adding it to the model)
	    					model.addAttribute("is_red", "false");
	    					model.addAttribute("numRows", numRows);
	    					return "result"; // show the result.jsp 
    		    		} else {
    		    			model.addAttribute("page_title", "Add Employee"); // adding the page_title variable to the model
        					model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
        		        	model.addAttribute("is_red", "true"); // the message should be in red (adding  it to the model)
        		        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
        					return "result"; // show the result.jsp 
    		    		}
    				} else {
    					model.addAttribute("page_title", "Add Employee"); // adding the page_title variable to the model
    					model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
    		        	model.addAttribute("is_red", "true"); // the message should be in red (adding  it to the model)
    		        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
    					return "result"; // show the result.jsp 
    				} 
        		} else {
        			model.addAttribute("page_title", "Add Employee"); // adding the page_title variable to the model
        			model.addAttribute("message_shown", "You didn't enter the first name and the last name of the employee and the new employee wasn't added to the database!"); // adding the message to the model
    	        	model.addAttribute("is_red", "true"); // the message should be in red (adding it to the model)
    	        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
    				return "result"; // show the result.jsp 
        		}
        	} else { // it is Show Employee
        		empID = "";  
        		if (empIDnameDAO.addToQueryStr(empID, fName_apostr, lName_apostr)) {
        			lst = empIDnameDAO.getEmployee(); // getEmployee returns a positive value if the employee was read from the database
        		}
        		if (lst != null && !lst.isEmpty()) {
        			// adding to the model the attribute lstEmployee which is a list of employee ID, first name, last name and department
        			int lstsize = lst.size();
        			model.addAttribute("lstEmployee", lst);
        			return "show_emp_info";
        		} else { // in the database there is no first name and last name for the user logged in 
        			String message ="";
        			message += "The user with the "; // message shown on the "result.jsp" web page
        			if (!(empID.equals("")))
        				message += "employee id " + empID + " and the ";
        			message += "name " + fName + " " + lName + " doesn't exist in the database!"; 
                	model.addAttribute("page_title", "Show Employee"); // adding the page_title variable to the model
                	model.addAttribute("message_shown", message); // adding the message to the model
                	model.addAttribute("is_red", "true"); // the message should be in red ( adding it to the model )
                	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
                	return "result";
        		} 
        	} 
    	} catch  (Exception e) {
        	e.printStackTrace();
        	if (show_add.equals("add")) // it is Add Employee
        		model.addAttribute("page_title", "Add Employee"); // adding the page_title variable to the model
        	else // it is Show Employee
        		model.addAttribute("page_title", "Show Employee"); // adding the page_title variable to the model
        	model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
        	model.addAttribute("is_red", "true"); // the message should be in red (adding it to the model)
        	model.addAttribute("logged_in", "true"); // the user is logged in
        	return "result";
        }
	}
    
    // if the requested URL is localhost:8080/show_sched, method is GET 
    // when the user clicks on the navigation bar on Schedule, Show Schedule
    @RequestMapping(value = {"show_sched"}) 
    public String show_sched(Model model) {
    	// setting the model attributes (I can access them from .jsp)
    	model.addAttribute("logged_in", "false"); // the user is not logging in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false 
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	// is_update has to be set whether it is update (or show schedule)   
    	model.addAttribute("is_update","false"); // is it update schedule
    	model.addAttribute("is_show_sched","true"); // is it show schedule
    	model.addAttribute("is_add_task","false"); // is it add task
    	model.addAttribute("is_del_task","false"); // is it delete task
    	return "show_sched_form"; // return the show_sched_form.jsp
    }
    
    // if the requested URL is localhost:8080/update_sched, method is GET 
    // when the user clicks on the navigation bar on Schedule, Update Schedule
    @RequestMapping(value = {"update_sched"}, method = RequestMethod.GET)
    public String update_sched(Model model) {
    	// setting the model attributes (I can access them from .jsp)
    	model.addAttribute("logged_in", "false"); // the user is not logging in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false 
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	// is_update has to be set whether it is update (or show schedule)
    	model.addAttribute("is_update","true"); // it is update schedule
    	model.addAttribute("is_show_sched","false"); // is it show schedule
    	model.addAttribute("is_add_task","false"); // is it add task
    	model.addAttribute("is_del_task","false"); // is it delete task
    	return "show_sched_form"; // return the show_sched_form.jsp
    }
    
    // if the requested URL is localhost:8080/sched_table, method is POST
    @RequestMapping(value = "sched_table", method = RequestMethod.POST)
	public String sched_table(Model model, HttpServletRequest request, @RequestParam(value="first_name", required=false) String first_name, // 
    	@RequestParam(value="last_name", required=false) String last_name, @RequestParam(value="date", required=true) String enter_date) {
    	
    	String empID = ""; // the employee's ID
    	String loginID = ""; // the logged in user's ID
    	String fName = ""; // the employee's first name
    	String lName = ""; // the employee's last name
       	List<PersonInfo1p1> lst = new ArrayList<>(); // list of objects (first name, last name)
       	List<EmpIDInfo1p1> lstEmp = new ArrayList<>(); // list of objects (emp ID)
    	boolean name_entered = false; // did the user enter the employee ID, first name, last name (if the admin is logged in)
    	
    	// setting the model attributes (I can access them from .jsp)
    	model.addAttribute("logged_in", "false"); // now is the user not logging in 
    	model.addAttribute("logged_out", "false"); // now is the user not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	empID = "";
    	fName = first_name; // setting the variable fName to the value entered on the form
    	lName = last_name; // setting the variable lName to the value entered on the form
    	enter_date = TimeMngLibrary.correctDate(enter_date); // change the enter_tdate to be in the format dd/mm/yyyy 
    	
    	// if fName, lName contains ' add one more next to it (otherwise an error will occur when running the SQL query)  
    	if (fName != null && !fName.equals(""))
    		fName = TimeMngLibrary.addApostrophe(fName);
    	if (lName != null && !lName.equals(""))
    		lName = TimeMngLibrary.addApostrophe(lName);
    	if ((empID==null || empID.equals("")) && (fName==null || fName.equals("")) && (lName==null || fName.equals(""))) { // if it is a regular user (who is doing update and not show of the schedule, retrieve the name 
    		loginID = (String)request.getSession().getAttribute("loginID");
    		// add to the SQL query the WHERE clause - where emp_id = loginID of the user logged in 
	    	personDAO.addToQueryStrName(loginID);
			// list is a list of objects of type EmpNameInfo (employee ID, first name, last name) where employee ID is the ID of logged in user
			lst = personDAO.getName(); // getting the name of the employee with employee ID = loginID
    	} else { // the user is logged in as admin (and he entered the name)
    		name_entered = true; 
    		// determine the empID
    		if (empID.equals("")) {
    			// add to the SQL query the WHERE clause - where (firstName = first name of the employee) and (lastName = last name of the employee)
    			empIDDAO.addToQueryStrID(fName, lName);
    			// list is a list of objects of type EmpNameInfo (employee ID, first name, last name) where (first_name = fName) and (last_name = lName) 
    			lstEmp = empIDDAO.getEmployeeID(fName, lName); // getting the name of the employee with employee ID = loginID	
    		}
    	}
    	
    	if ((lst != null && !lst.isEmpty()) || (name_entered)) { // the employee with the first and last name is found 
    		if (lst != null && !lst.isEmpty()) { // retrieve the name  
    			fName = lst.get(0).getFirstName(); // retrieving the first name of the logged in user
    			lName = lst.get(0).getLastName(); // retrieving the last name of the logged in user
    			// first_name, last_name are used for showing the message on the results.jsp if there is NO schedule for that employee
    			first_name = fName;
    			last_name = lName;
    			// if fName, lName contains ' add one more next to it (otherwise an error will occur when running the SQL query)  
    	    	if (fName != null && !fName.equals(""))
    	    		fName = TimeMngLibrary.addApostrophe(fName);
    	    	if (lName != null && !lName.equals(""))
    	    		lName = TimeMngLibrary.addApostrophe(lName);
    			if (name_entered) // if the user is logged as an admin and he entered the first and last name then read the employee ID
    				empID = lstEmp.get(0).getEmployeeID(); // retrieving the employee ID
    			else {
    				empID = loginID; // if the user is logged in as a regular user, he can see ONLY HIS OWN records
    			}
    		}
    		// add the rest of the query to the query
			empSchedTaskDAO.addToQueryStr(empID, fName, lName, enter_date);
			// retrieve the schedule (with tasks) for the requested employee on the requested date
			List<EmpSchedTaskInfo1p1> list = empSchedTaskDAO.getSchedules();
			// there are no tasks returned for the certain employee on the certain day
			if (list == null || list.isEmpty()) {
				model.addAttribute("page_title", "Show Schedule");
				if (empID != null)
					if (!empID.equals(""))
						model.addAttribute("message_shown", "The schedule for the employee with employee ID " + empID + " named "+ first_name + " " + last_name + " on " + enter_date + " doesn't exist!");
					else
						model.addAttribute("message_shown", "The schedule for the employee named "+ first_name + " " + last_name + " on " + enter_date + " doesn't exist!");
				else
					model.addAttribute("message_shown", "The schedule for the employee named "+ first_name + " " + last_name + " on " + enter_date + " doesn't exist!");
				// the message should be in red (adding it to the model)
				model.addAttribute("is_red", "true");
				// set here some model attributes if needed
				return "result";
			} else { // there are tasks returned for the certain employee on the certain day
				// add the schedule of the employee as the attribute to the model 
				model.addAttribute("empSchedTaskInfos", list);
				// add the first name as the attribute to the model
				model.addAttribute("enter_f_name", first_name ); 
				// add the last name to the model
				model.addAttribute("enter_l_name", last_name );
				// add the date (of the schedule) to the model
				model.addAttribute("enter_date", enter_date ); 
				// show_sched_results is shown for Show Schedule and Update Schedule
				return "show_sched_results"; // show the show_sched_results.jsp
			}
		} else { // in the database there is no first name and last name for the user logged in
        	model.addAttribute("page_title", "Show Schedule"); // adding the page_title variable to the model
        	model.addAttribute("message_shown", "For your login the first and the second name is not entered!"); // adding the message to the model
        	model.addAttribute("is_red", "true"); // the message should be in red ( adding it to the model )
        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
        	return "result";
		}  
	}
    
    // if the requested URL is localhost:8080/task_update and method is POST
    @RequestMapping(value = {"/task_update"}, method = RequestMethod.POST )
    public String task_update(Model model, @RequestParam(value="taskId", required=true) String id) {  
    	// find the task whose task_id is the argument id
    	EmpSchedTaskInfo1p1 task_info = empSchedTaskDAO.findTask(Long.valueOf(id));
    	// setting the model attributes (I can access them from .jsp)
    	// add the object task_info as the attribute to the model
    	model.addAttribute("task_info", task_info);
    	model.addAttribute("is_add_del_task","false"); // it is not Add Task nor Delete Task
    	model.addAttribute("read_add_d", "true"); // whether the is_add_del_task attribute is set (used by the JSP)
    	model.addAttribute("logged_in", "false"); // now is the user not logging in 
    	model.addAttribute("logged_out", "false"); // now is the user not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	return "show_task_info"; // show the show_task_info.jsp
    }
    
    // if the requested URL is localhost:8080/show_update_results and method is POST
    @RequestMapping(value = {"/show_update_results"}, method = RequestMethod.POST)
    public String show_update_results(Model model, @RequestParam(value="task_id", required=true) String id, @RequestParam(value="task_name", required=true) String enter_tname, // 
        @RequestParam(value="task_date", required=true) String enter_tdate, @RequestParam(value="start_time", required=true) String enter_stime, //
        @RequestParam(value="end_time", required=true) String enter_etime) {
  
    	boolean update_succ = true;
    	enter_tdate = TimeMngLibrary.correctDate(enter_tdate); // change the enter_tdate to be in the format dd/mm/yyyy 

    	try {
    		// update of the task in the DB
    		empSchedTaskDAO.updateTask(Long.valueOf(id), enter_tname, enter_tdate, enter_stime, enter_etime);
    	} catch (Exception e) {
    		// if an exception occurred during the update set the update_succ
    		update_succ = false;
    	}
    	// setting the model attributes (I can access them from .jsp)
    	model.addAttribute("page_title", "Update Task"); // adding the page_title variable to the model
    	if (update_succ) {
    		model.addAttribute("message_shown", "The task was updated successfully!");
    		model.addAttribute("is_red", "false"); // adding to the model : the text needs to be shown in red
    	} else {
    		model.addAttribute("message_shown", "The task wasn't updated successfully - an error occurred while updating the task!");
    		model.addAttribute("is_red", "true"); // adding to the model : the text needs to be shown in red
    	}
    	model.addAttribute("logged_in", "false"); // the user is now not logging in 
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_out", "false"); // the user is now not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	return "result";
    }
    
    // if the requested URL is localhost:8080/add_task, method is GET do 
    // if the user (admin) chooses Task -> Add
    @RequestMapping(value = {"add_task"}, method = RequestMethod.GET)
    public String task_add(Model model) {
    	// setting the model attributes (I can access them from .jsp)
    	model.addAttribute("logged_in", "false"); // the user is not logging in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false 
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	model.addAttribute("is_update","false"); // is it update schedule
    	model.addAttribute("is_show_sched","false"); // is it show schedule
    	model.addAttribute("is_add_task","true"); // is it add task
    	model.addAttribute("is_del_task","false"); // is it delete task
    	return "show_sched_form"; // return the show_sched_form.jsp
    }
    
    // if the requested URL is localhost:8080/del_task, method is GET do 
    @RequestMapping(value = {"del_task"}, method = RequestMethod.GET)
    public String del_task(Model model) {
    	// setting the model attributes (I can access them from .jsp)
    	model.addAttribute("logged_in", "false"); // the user is not logging in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false 
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	model.addAttribute("is_update","false"); // is it update schedule
    	model.addAttribute("is_show_sched","false"); // is it show schedule
    	model.addAttribute("is_add_task","false"); // is it add task
    	model.addAttribute("is_del_task","true"); // is it delete task
    	return "show_sched_form"; // return the show_sched_form.jsp
    }
    
    // if the requested URL is localhost:8080/add_d_form, method is POST do
    // used in show_sched_fcont.jsp
     @RequestMapping(value = {"add_d_form"}, method = RequestMethod.POST)
     public String add_d_form(Model model, HttpServletRequest request, @RequestParam(value="first_name", required=false) String fName, // 
    	@RequestParam(value="last_name", required=false) String lName, @RequestParam(value="delete_task", required=true) String del_task ) {
    	
    	List<EmpIDInfo1p1> lst = new ArrayList<>(); // list of objects (employee ID)
    	String empID = ""; 
    	String first_name = ""; // first name without '
    	String last_name = ""; // last name without '
    	
    	model.addAttribute("is_add_del_task","true"); // it is Add Task or Delete Task
    	model.addAttribute("read_add_d", "true"); // whether the is_add_del_task attribute is set (used by the JSP)
    	model.addAttribute("logged_in", "false"); // now is the user not logging in 
    	model.addAttribute("logged_out", "false"); // now is the user not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	first_name = fName;
    	last_name = lName;
    	fName = TimeMngLibrary.addApostrophe(fName);
    	lName = TimeMngLibrary.addApostrophe(lName);
    	request.getSession().setAttribute("fName", fName);
    	request.getSession().setAttribute("lName", lName);
    	
    	// determine the empID
    	// add to the SQL query the WHERE clause - where (firstName = first name of the employee) and (lastName = last name of the employee)	
    	empIDDAO.addToQueryStrID(fName, lName);
    	// list is a list of objects of type EmpNameInfo (employee ID, first name, last name) where (first_name = fName) and (last_name = lName) 
    	lst = empIDDAO.getEmployeeID(fName, lName); // getting the name of the employee with employee ID	
    	if ((lst != null && !lst.isEmpty())) { // the employee with the first and last name is found 
    		empID = lst.get(0).getEmployeeID(); // retrieving the employee ID
    	} else {
    		if (del_task.equals("true")) { // it is a Delete Task
	    		// setting the model attributes (I can access them from .jsp)
		    	model.addAttribute("page_title", "Delete Task"); // adding the page_title variable to the model
			} else { // it is Add Task
				model.addAttribute("page_title", "Add Task"); 
			}
			model.addAttribute("message_shown", "The employee doesn't exist in the database. Please first add the employee " + first_name + " " + last_name + " to the database!");
    		model.addAttribute("is_red", "true"); // adding to the model : the text needs to be shown in red
    		model.addAttribute("logged_in", "false"); // the user is now not logging in 
        	model.addAttribute("logged_out", "false"); // the user is now not logging out 
        	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
        	return "result";
    	}
    	return "show_task_info";
    }
     
    // if the requested URL is localhost:8080/add_task_get, method is GET do
    // used for the regular user (on the drop down menu : Task -> Add Task)   
    @RequestMapping(value = {"add_task_get"}, method = RequestMethod.GET)
    public String add_task_get(Model model) {
    	
    	model.addAttribute("is_add_del_task", "false"); // attribute is_add_del_task is not set
    	model.addAttribute("is_add_task","true"); // it is Add Task
    	model.addAttribute("is_del_task","false"); // it isn't Delete Task
    	model.addAttribute("read_add_d", "false"); // whether the is_add_del_task attribute is set (used by the JSP)
    	model.addAttribute("logged_in", "false"); // now is the user not logging in 
    	model.addAttribute("logged_out", "false"); // now is the user not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	return "show_task_info";
    }
    
    // if the requested URL is localhost:8080/del_task_get, method is GET do
    // used for the regular user (on the drop down menu : Task -> Delete Task)   
    @RequestMapping(value = {"del_task_get"}, method = RequestMethod.GET)
    public String del_task_get(Model model) {
    	
    	List<EmpEmailInfo1p1> lst = new ArrayList<>(); // list of objects (employee ID, first name, last name)
    	model.addAttribute("is_add_del_task", "false"); // attribute is_add_del_task is not set
    	model.addAttribute("is_add_task","false"); // it isn't Add Task
    	model.addAttribute("is_del_task","true"); // it is Delete Task
    	model.addAttribute("read_add_d", "false"); // whether the is_add_del_task attribute is set (used by the JSP)
    	model.addAttribute("logged_in", "false"); // now is the user not logging in 
    	model.addAttribute("logged_out", "false"); // now is the user not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
    	return "show_task_info";
    }
    
    // if the requested URL is localhost:8080/add_d_res and method is POST
    @RequestMapping(value = {"/add_d_res"}, method = RequestMethod.POST)
    public String add_d_res(Model model, HttpServletRequest request, @RequestParam(value="task_name", required=true) String enter_tname, // 
        @RequestParam(value="task_date", required=true) String enter_tdate, @RequestParam(value="start_time", required=true) String enter_stime, //
        @RequestParam(value="end_time", required=false) String enter_etime, @RequestParam(value="delete_task", required=true) String del_task) {
    	
    	String employeeID = "";
    	String fName = ""; // the employee's first name
    	String lName = ""; // the employee's last name
    	boolean add_succ = false; // success of the adding of the record to the database
    	boolean del_query = false; // success of the building the SQL query (DELETE)
    	int numRows = -1; // number of affected rows (Delete Task) 
    	
    	String schedID = ""; // ID of the schedule (from the table schedule) for the employee with the ID employeeID
    	List<ScheduleInfo1p1> lst = new ArrayList<>(); // list of objects (scheduleID, employeeID)
    	List<EmpIDInfo1p1> lstEmp = new ArrayList<>(); // list of objects (employeeID)
    	enter_tname = TimeMngLibrary.addApostrophe(enter_tname); // if there is an ' in the task name add another one (because of the SQL query)
    	try {
    		fName = (String)request.getSession().getAttribute("fName");
    		lName = (String)request.getSession().getAttribute("lName");
    		if ((fName == null && lName == null) || (fName.equals("") && lName.equals(""))) { // regular user (there is no input field for the first and last name)
    			employeeID = (String)request.getSession().getAttribute("empID"); // read the empID session attribute from the login
    		} else { // administrator 
	    		empIDDAO.addToQueryStrID(fName, lName);
				lstEmp = empIDDAO.getEmployeeID(fName, lName);
				if (lstEmp != null && lstEmp.size() != 0) 
					employeeID = lstEmp.get(0).getEmployeeID(); // get the ID of the employee with name fName lName
    		}
    		
    		if (employeeID != null && !employeeID.equals("")) {
	    		// add to the SQL query the WHERE clause - where ((emp_id = employeeID)
	    		schedDAO.addToQueryStr(employeeID);
	    		// list is a list of objects of type ScheduleInfo (sched_id) where ((emp_id = employeeID) 
	    		lst = schedDAO.getSchedID(); // getting the sched_id of the employee with employee ID
    		}
    		if (lst != null && !lst.isEmpty()) { // if the sched_id for the given employee ID is found
    			schedID = lst.get(0).getScheduleID(); // retrieving the schedule ID
    		} else {
    			if (del_task.equals("true")) { // it is a Delete Task
    	    		// setting the model attributes (I can access them from .jsp)
    		    	model.addAttribute("page_title", "Delete Task"); // adding the page_title variable to the model
    			} else { // it is Add Task
    				model.addAttribute("page_title", "Add Task"); 
    			}
    			model.addAttribute("message_shown", "The employee doesn't exist in the database. Please first add the employee to the database!");
	    		model.addAttribute("is_red", "true"); // adding to the model : the text needs to be shown in red
	    		model.addAttribute("logged_in", "false"); // the user is now not logging in 
	        	model.addAttribute("logged_out", "false"); // the user is now not logging out 
	        	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
	        	return "result";
    		}
    		
    		enter_stime = TimeMngLibrary.correctTime(enter_stime); // change the enter_stime to be in the format hh:mm AM/PM
    		enter_tdate = TimeMngLibrary.correctDate(enter_tdate); // change the enter_tdate to be in the format dd/mm/yyyy 
    		if (del_task.equals("false")) { // it isn't Delete but Add Task
    			enter_etime = TimeMngLibrary.correctTime(enter_etime); // change the enter_etime to be in the format hh:mm AM/PM
	    		// adds to the SQL query the schedule ID, task name, task date, start time, end time depending on the data the user entered in the Add Task form
	    		empSchedTaskDAO.addToQueryStr2(schedID, enter_tname, enter_tdate, enter_stime, enter_etime);
	    		// adding the task to the DB
	    		empSchedTaskDAO.addTask(schedID, enter_tname, enter_tdate, enter_stime, enter_etime);
	    		add_succ = true;
    		} else { // it is Delete Task
    			// adds to the sql query (deletion) the where clause
    		 	// the sql query will delete the task named enter_tname (from the schedule with schedID, on the date enter_tdate, which starts at enter_stime) from the table task
    			del_query = empSchedTaskDAO.addToQueryStrDel(schedID, enter_tname, enter_tdate, enter_stime);
    			if (del_query) {
    				// deleting the task from the DB
    				numRows = empSchedTaskDAO.deleteTask();
    			}
    		}
    	} catch (Exception e) {
    		// if an exception occurred during adding the task then set the add_succ
    		 add_succ = false;
    		 del_query = false;
    	}
    	
    	if (del_task.equals("true")) { // it is a Delete Task
    		// setting the model attributes (I can access them from .jsp)
	    	model.addAttribute("page_title", "Delete Task"); // adding the page_title variable to the model
	    	if (del_query) {
	    		if (numRows > 0) { // one or more rows were deleted
		    		model.addAttribute("message_shown", "The task was deleted successfully!");
		    		model.addAttribute("is_red", "false"); // adding to the model : the text dosn't need to be shown in red
	    		} else {
	    			model.addAttribute("message_shown", "The task doesn't exist in the database - the task wasn't deleted successfully!");
		    		model.addAttribute("is_red", "true"); // adding to the model : the text needs to be shown in red
	    		}
	    	}
	    	else {
	    		model.addAttribute("message_shown", "The task wasn't deleted successfully - an error occurred while deleting the task!");
	    		model.addAttribute("is_red", "true"); // adding to the model : the text needs to be shown in red
	    	}
    	} else { // it is an Add Task
	    	// setting the model attributes (I can access them from .jsp)
	    	model.addAttribute("page_title", "Add Task"); // adding the page_title variable to the model
	    	if (add_succ) {
	    		model.addAttribute("message_shown", "The task was added successfully!");
	    		model.addAttribute("is_red", "false"); // adding to the model : the text doesn't need to be shown in red
	    	}
	    	else {
	    		model.addAttribute("message_shown", "The task wasn't added successfully - an error occurred while adding the task!");
	    		model.addAttribute("is_red", "true"); // adding to the model : the text needs to be shown in red
	    	}
    	}
    	model.addAttribute("logged_in", "false"); // the user is now not logging in 
    	model.addAttribute("logged_out", "false"); // the user is now not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	return "result";
    }
        
    // if the requested URL is localhost:8080/task_delete, method is GET do 
    @RequestMapping(value = {"task_delete"}, method = RequestMethod.GET)
    public String task_delete(Model model) {
        String message = "Task Delete";
        model.addAttribute("message", message);
        return "task_delete"; // return the task_delete.jsp
    }
 
    // if the requested URL is localhost:8080/contact_us, method is GET do 
    // used if the user before didn't log in
    @RequestMapping(value = {"contact_us"}, method = RequestMethod.GET)
    public String contact_us(Model model) {
        String message = "Contact Us";
        model.addAttribute("message", message);
        model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before (and is still logged in)
        return "contact_form"; // return the contact_us.jsp
    }
    
    // if the requested URL is localhost:8080/contact_user, method is GET do
    // used if the user before logged in 
    @RequestMapping(value = {"contact_user"}, method = RequestMethod.GET)
    public String contact_user(Model model) {
    	String message = "Contact Us";
        model.addAttribute("message", message);
        model.addAttribute("is_admin", "false"); // the user is not logged in as admin
        model.addAttribute("logged_in", "false"); // the user is not logged in
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "true"); // did the user log in before (and is still logged in)
        return "contact_form"; // return the contact_us.jsp
    }
    
    // if the requested URL is localhost:8080/emp_message method is POST do
    // used if the user before logged in 
    @RequestMapping(value = {"emp_message"}, method = RequestMethod.POST)
    // first_name, last_name, email, message is an input element on contact_info.jsp
    // the user entered first name, last name of the employee, the email and the message to be added to the database
    public String emp_message(Model model, @RequestParam(value="first_name", required=true) String f_Name, //
    	@RequestParam(value="last_name", required=true) String l_Name, @RequestParam(value="email", required=false) String email, //
    	@RequestParam(value="message", required=true) String mssg) {
    	
    	int numRowsUpd = -1; // how many rows were affected by the UPDATE SQL statement
    	int numRowsMssg = -1; // how many rows were affected by the INSERT SQL statement
    	List<EmpEmailInfo1p1> lstEmp = new ArrayList<>(); // list of elements of type EmpNameInfo1p1
    	List<EmpIDInfo1p1> lst = new ArrayList<>(); // list of objects (employee ID)
    	String employeeID = "";
    	
    	try {	
    		email = TimeMngLibrary.addApostrophe(email); // adds 1 apostrophe before the ' (for adding the string which contains ' to the DB)
    		mssg = TimeMngLibrary.addApostrophe(mssg);
    		f_Name = TimeMngLibrary.addApostrophe(f_Name);
    		l_Name = TimeMngLibrary.addApostrophe(l_Name);
    		String message = "Contact Us";
    		model.addAttribute("message", message);
    		model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    		model.addAttribute("logged_in", "false"); // the user is not logged in
    		model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    		model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    		// add to the SQL query the WHERE clause - where (firstName = first name of the employee) (OR firstName LIKE '%' if firstName == '')
    		// and (lastName = last name of the employee) (OR lastName LIKE '%' if lastName == '')
		    empEmailDAO.addToQueryStrEmail(f_Name, l_Name);
		    // retrieve the list of employees with the entered first and last name
		    lstEmp = empEmailDAO.getEmpEmail();
		    // if the employee exists in the database
		    if (lstEmp != null && !lstEmp.isEmpty()) {	 
		    	int i = 0;
		    	int lstEmpSize = lstEmp.size();
		    	String emailEmp = ""; // email read from the list
		    	boolean foundEmail = false; // was the email found
		    	// go through the list of employees and find the email
		    	while ((i<lstEmpSize) && (!foundEmail)) {
		    		emailEmp = lstEmp.get(i).getEmail(); 
		    		if (emailEmp != null && !emailEmp.isEmpty())
		    			if (emailEmp.equals(email))
		    				foundEmail = true;
		    			i++;
		    	}
		    	if (!foundEmail) { // the email doesn't exist in the database
		    		// if the employee id wasn't entered retrieve the emp_id
		    		empIDDAO.addToQueryStrID(f_Name, l_Name);
		    		lst = empIDDAO.getEmployeeID(f_Name, l_Name);
		    		if (lst != null) {
		    			employeeID = lst.get(0).getEmployeeID(); 
		    	 	} else { // the employee doesn't exist
		    			model.addAttribute("page_title", "Contact Us"); // the title of the page ( adding to the model )
		    			model.addAttribute("message_shown", "The employee with that first and last name doesn't exist and the message wasn't added to the database. Please contact the administrator!"); // adding the message to the model
		    	        model.addAttribute("is_red", "true"); // the message should be in red ( adding  it to the model )
		    	        model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
		    	        return "result";
		    		}
		    		// the query for updating the email for the employee with the employeeID
		    		empEmailDAO.addToQueryUpdEmail(employeeID, email);
		    		// running the query 
		    		numRowsUpd = empEmailDAO.updateEmail();	
		    	} else { // the email already exists in the database
		    		numRowsUpd = 1; 
		    		// determining the ID
		    		empIDDAO.addToQueryStrID(f_Name, l_Name);
		    		lst = empIDDAO.getEmployeeID(f_Name, l_Name);
		    		if (lst != null)
		    			employeeID = lst.get(0).getEmployeeID();
		    		else { // the employee doesn't exist
		    			model.addAttribute("page_title", "Contact Us"); // the title of the page (adding to the model)
		    			model.addAttribute("message_shown", "The employee with that first and last name doesn't exist and the message wasn't added to the database. Please contact the administrator!"); // adding the message to the model
		    	        model.addAttribute("is_red", "true"); // the message should be in red (adding  it to the model)
		    	        model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
		    	        return "result";
		    		}
		    	}
		    	// if the query above of updating the email for the employee with the employeeID was successful (or the email was already in the database and didn't get changed) 
		    	if (numRowsUpd>0) {
		    		messageDAO.addToQueryInsert(employeeID, mssg);
		    		// add the entered message to the table message
		    		numRowsMssg = messageDAO.addMessage();
		    	} 
		    } else {
		    	model.addAttribute("page_title", "Contact Us"); // the title of the page (adding to the model)
				model.addAttribute("message_shown", "The employee with that first and last name doesn't exist and the message wasn't added to the database. Please contact the administrator!"); // adding the message to the model
	        	model.addAttribute("is_red", "true"); // the message should be in red ( adding  it to the model )
	        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
	        	return "result";
		    }
		    // adding the email and the message was successful
		    if ((numRowsUpd>0) && (numRowsMssg>0)) {
			    model.addAttribute("page_title", "Contact Us"); // the title of the page (adding to the model)
				model.addAttribute("message_shown", "The message was successfully sent!"); // adding the message to the model
	        	model.addAttribute("is_red", "false"); // the message should be in red (adding  it to the model)
	        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
	        	return "result";
		    } else { // a problem occurred while adding the email and the message
		    	model.addAttribute("page_title", "Contact Us"); // the title of the page (adding to the model)
		    	model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
	        	model.addAttribute("is_red", "true"); // the message should be in red (adding  it to the model)
	        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
			    return "result";	
		    }
    	} catch  (Exception e) {
        	e.printStackTrace();
        	model.addAttribute("page_title", "Contact Us"); // adding the page_title variable to the model
			model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
        	model.addAttribute("is_red", "true"); // the message should be in red (adding  it to the model)
        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
        	return "result";
    	}
    }
    
    // if the requested URL is localhost:8080/cust_message method is POST do
    // used if the customer before didn't log in 
    @RequestMapping(value = {"cust_message"}, method = RequestMethod.POST)
    // first_name, last_name, email, message is an input element on contact_info.jsp
    // the user entered the first name, last name of the employee, the email and the message to be added to the database
    public String cust_message(Model model, 
    	@RequestParam(value="first_name", required=false) String f_Name, @RequestParam(value="last_name", required=false) String l_Name, //
    	@RequestParam(value="email", required=false) String email, @RequestParam(value="message", required=false) String mssg) {
    	
    	int numRowsUpd = -1; // how many rows were affected by the UPDATE SQL statement
    	int numRowsMssg = -1; // how many rows were affected by the INSERT SQL statement
    	List<CustEmailInfo1p1> lstCust = new ArrayList<>(); // list of elements of type CustNameInfo1p1
    	List<CustNameInfo1p1> lst = new ArrayList<>(); // list of objects (customer ID, first name, last name)
    	List<CustIDInfo1p1> lstID = new ArrayList<>(); // list of objects (customerID)
    	String customerID = "";
    	
    	email = TimeMngLibrary.addApostrophe(email); // adds 1 apostrophe before the ' (for adding the string which contains ' to the DB)
		mssg = TimeMngLibrary.addApostrophe(mssg);
		f_Name = TimeMngLibrary.addApostrophe(f_Name);
		l_Name = TimeMngLibrary.addApostrophe(l_Name);
    	try {
    		String message = "Contact Us";
    		model.addAttribute("message", message);
    		model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    		model.addAttribute("logged_in", "false"); // the user is not logged in
    		model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    		model.addAttribute("already_login", "false"); // did the user log in before (and is still logged in)
    		// add to the SQL query the WHERE clause - where (firstName = first name of the customer)
    		// and (lastName = last name of the customer)
		    custEmailDAO.addToQueryStrEmail(f_Name, l_Name);
		    // retrieve the list of customers with the first and last name
		    lstCust = custEmailDAO.getCustEmail();
		    // if the customer exists in the database
		    if (lstCust != null && !lstCust.isEmpty()) {
		    	int i = 0;
		    	int lstCustSize = lstCust.size();
		    	String emailCust = ""; // email read from the list
		    	boolean foundEmail = false; // was the email found
		    	// go through the list of customers and find the email and customerID
		    	if (email != null && !email.isEmpty()) // if the user entered his email on the Contact Us 
			    	while (i<lstCustSize && !foundEmail) {
			    		emailCust = lstCust.get(i).getEmail();
			    		if (emailCust != null && !emailCust.isEmpty())
			    			if (emailCust.equals(email)) {
			    				foundEmail = true;
			    				customerID = lstCust.get(0).getCustomerID();
			    			}
			    		i++;
			    	}
		    	
		    	if (!foundEmail) { // the email doesn't exist in the database (the customer exists in the DB)  
		    		// the query for inserting the customer (first name, last name, email) (the customer in the DB could be another customer)
		    		custEmailDAO.addToQueryAddCust(f_Name, l_Name, email);
		    		// running the query 
		    		numRowsUpd = custEmailDAO.updateInsertEmail(CustEmailMapper1p1.SQL_ADD_CUST);
		    		// the query for selecting the customer ID for the customer with the entered first, last name and email
		    		custIDDAO1p1.addToQueryCustID(f_Name, l_Name, email);
		    		lstID = custIDDAO1p1.getCustID();
	    			if (lstID != null) {
	    				customerID = lstID.get(0).getCustomerID(); // getting the customer ID
	    			}
		    	} else { // the email already exists in the database (the customer exists in the DB)
		    		numRowsUpd = 1; 
		    		// determining the ID
		    		custNameDAO.addToQueryStrID(f_Name, l_Name);
		    		lst = custNameDAO.getCustomerID();
		    		if (lst != null)
		    			customerID = lst.get(0).getCustomerID();
		    			numRowsUpd = 1; // the email already exists in the database
		    	}
		    	// if the query above of updating the email for the customer with the customer ID was successful (or the email was already in the database and didn't get changed) 
		    	if (numRowsUpd>0) {
		    		msgcustDAO.addToQueryInsert(customerID, mssg);
		    		// add the entered message to the table msg_cust
		    		numRowsMssg = msgcustDAO.addMessage();
		    	} 
		    } else { // the customer doesn't exist in the database
		    	// the query for inserting the new customer (first name, last name, email)
	    		custEmailDAO.addToQueryAddCust(f_Name, l_Name, email);
	    		// running the query 
	    		numRowsUpd = custEmailDAO.updateInsertEmail(CustEmailMapper1p1.SQL_ADD_CUST);
		    	// retrieve the customerID
		    	custNameDAO.addToQueryStrID(f_Name, l_Name);
    			lst = custNameDAO.getCustomerID();
    			if (lst != null && !lst.isEmpty()) 
    				customerID = lst.get(0).getCustomerID(); // getting the customer ID
    			// inserting the message in the message table (person ID = customer ID)
    			if (customerID != null && !customerID.isEmpty()) { // the customer ID was successfully retrieved from the DB
    				msgcustDAO.addToQueryInsert(customerID, mssg);
	    			// add the entered message to the table msg_customer
	    			numRowsMssg = msgcustDAO.addMessage();
    			}
		    }
		    // adding the email and the message was successful
		    if ((numRowsUpd>0) && (numRowsMssg>0)) {
			    model.addAttribute("page_title", "Contact Us"); // the title of the page (adding to the model)
				model.addAttribute("message_shown", "The message was successfully sent!"); // adding the message to the model
	        	model.addAttribute("is_red", "false"); // the message should be in red (adding  it to the model)
	        	model.addAttribute("logged_in", "false"); // setting the attribute logged_in to false - the user is not logged in
	        	return "result";
		    } else { // a problem occurred while adding the email and the message
		    	model.addAttribute("page_title", "Contact Us"); // the title of the page (adding to the model)
		    	model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
	        	model.addAttribute("is_red", "true"); // the message should be in red (adding  it to the model)
	        	model.addAttribute("logged_in", "false"); // setting the attribute logged_in to false - the user is not logged in
			    return "result";	
		    }
    	} catch  (Exception e) {
        	e.printStackTrace();
        	model.addAttribute("page_title", "Contact Us"); // adding the page_title variable to the model
			model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
        	model.addAttribute("is_red", "true"); // the message should be in red (adding  it to the model)
        	model.addAttribute("logged_in", "false"); // setting the attribute logged_in to true - the user is not logged in
        	return "result";
    	}
    }
    
    // if the requested URL is localhost:8080/log_out, method is GET do 
    @RequestMapping(value = {"log_out"}, method = RequestMethod.GET)
    public String log_out(Model model) {
    	// setting the attributes of the models ( I can access them from the .jsp )
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in
    	model.addAttribute("logged_out", "true"); // the user is logging out
    	model.addAttribute("already_login", "false"); // did the user log in before (and is still logged in)
    	model.addAttribute("page_title", "Log Out"); // the title on the page
		model.addAttribute("message_shown", "You logged out successfully!");
		// the message shouldn't be in red (adding the attribute to the model)
		model.addAttribute("is_red", "false");
		return "result"; // return the result.jsp
    }
}
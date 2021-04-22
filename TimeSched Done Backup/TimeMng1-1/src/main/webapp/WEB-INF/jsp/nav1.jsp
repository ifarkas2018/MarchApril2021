<!-- author: Ingrid Farkas; project: Time Manager, 2020 -->

<!-- navigation bar -->
<!-- w3-theme-m1 is a CSS rule from the styles1.css -->
<div class="w3-bar w3-theme-m1 w3-round-xxlarge">  
  <!-- w3-text-theme-m1 : CSS rule defined in styles1.css -->
  <span class="w3-bar-item w3-text-theme-m1">H &amp; C Web Consulting</span>
    
  <!-- importing the class Enumeration -->
  <%@ page language="java" import="java.util.Enumeration"%>
  	
  <% 
    boolean is_reg = false; // did the user log in as regular user
    boolean is_admin = false; // did the user log in as admin
    boolean is_logout = false; // did the user log out
    	
    String sess_logged = "false"; // the session variable - is the user logged in
    String sess_adm = "false"; // // the session variable - is the user logged in as administrator
    String sess_logout = "false"; // the session variable - is the user logged out
    		
    // mod_loggedin: whether the user is LOGGING IN (the attribute "logged_in" added to the model (MainController.java))
    String mod_loggedin = (String)(request.getAttribute("logged_in"));
    	
    // mod_logout: whether the user is LOGGING OUT (the attribute "logged_out" added to the model (MainController.java))
    String mod_logout = (String)(request.getAttribute("logged_out"));
    	
    // mod_already_login: did the user before log in (and is still logged in)
    String mod_already_login = (String)(request.getAttribute("already_login"));
    	
      if (mod_loggedin.equals("true")){
        // if the "logged_in" is true (variable from model), set its session equivalent sess_logged to true 
    	session.setAttribute("sess_logged", "true");
    	// set the session attribute for logout to false - the user just logged in (not out)
    	session.setAttribute("sess_logout", "false");
    	// mod_admin: whether the user is logging in as admin (the attribute "is_admin" added to the model (MainController.java))
    	String mod_admin = (String)(request.getAttribute("is_admin"));
    	// if the user is logging in as administrator
    	if (mod_admin.equals("true")) { 
    	  // set the session equivalent sess_adm to true (the user is loggging in as admmin)
    	  session.setAttribute("sess_adm", "true");
    	} else if (mod_admin.equals("false")) {
    	  session.setAttribute("sess_adm", "false");
    	}
    		
    	// if the user is logging in as admin
    	if (mod_admin.equals("true"))
    	  is_admin = true; 
    	else
    	  is_reg = true; // otherwise the user is logging in as regular user
    	} else if (mod_logout.equals("true")) {
	      // if the "logged_out" is true (variable from model), set its session equivalent sess_logout to true 
	      session.setAttribute("sess_logout", "true");
	      session.setAttribute("sess_logged", "false"); // the user is not logged in 
	      session.setAttribute("sess_adm", "false"); // the user is not admin
	      is_admin = false; // the user is not logged in admin	
	      is_reg = false; // the user is not logged in as regular user		    	
    	} else if (mod_already_login.equals("true")){ // the user did before log in or log out 
    	  // read the session attribute "sess_logged" to find out - is the user logged in
    	  sess_logged = (String)session.getAttribute("sess_logged");
    	  sess_adm = (String)session.getAttribute("sess_adm"); // did the user before log in as admin (and is still logged in)
    	  sess_logout = (String)session.getAttribute("sess_logout"); // did the user before log out
    	  if (sess_logged.equals("true")) // if the user before logged in (use the session attribute)
		    if (sess_adm.equals("true")) // if the user before logged in as admin (use the session attribute)
			  is_admin = true; // the user is logged in as admin
			else
			  is_reg = true; // the user is logged in as regular user
    		else {
    		  is_admin = false; // the user is not logged in as admin
    		  is_reg = false; // the user is not logged in as regular user
    		}
    	}
    %>
    	
    <!-- w3-text-theme-m1 is used to set the text of the link to blue -->
	<!-- w3-hover-theme-l4 when the user hovers the mouse, there is no change in the background color of the navigation bar, the text color is orange -->
	<!-- w3-mobile used to create a responsive navbar with responsive dropdown links -->
	<% if ((is_reg) || (is_admin)) { // if the user is logged in as regular user or as admin %>
		<a href="home" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">Home</a> 
	<% } else { // the user is not logged in %>
		<a href="/" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">Home</a>
	<% } %>
	
    <% 
       if (is_admin) { // the user logged in as admin		
	%>		
		 <!-- w3-dropdown-hover is used to create hoverable drop down element -->
		 <div class="w3-dropdown-hover w3-mobile">
		   <!-- w3-theme-m1 is used to set the background color of the button, w3-text-theme-m1 is used to set the color of the text on the navigation bar entry -->
		   <!-- w3-hover-theme-l4 is used to set on the hover the background color of the button and the color of the text -->
		   <a href="#" class="w3-button w3-theme-m1 w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">Employee <i class="fa fa-caret-down"></i></a> 
		   <!-- w3-card-4 adds to the container a 4px bordered shadow -->
		   <!-- w3-dropdown-content is used to create responsive dropdown links -->
		   <!-- w3-theme-m1 is a CSS rule from the colors.css (sets the color of the text and the background color) -->
		   <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-theme-m1">
        	 <a href="addempl_form" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Add Employee</a>
        	 <a href="showempl_form" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Show Employee</a> 
    	   </div>  
		 </div>
	<% 
       } 

	   // if the user logged in as a regular user or as an administrator show the Schedule submenu  
	   if (is_reg || is_admin) {  
	%>
	     <!-- w3-dropdown-hover is used to create hoverable drop down element -->
	     <div class="w3-dropdown-hover w3-mobile">
	       <!-- w3-theme-m1 is used to set the background color of the button, w3-text-theme-m1 is used to set the color of the text on the navigation bar entry -->
	       <!-- w3-hover-theme-l4 is used to set on the hover the background color of the button and the color of the text -->
	       <a href="#" class="w3-button w3-theme-m1 w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">&#128197;&nbsp;Schedule <i class="fa fa-caret-down"></i></a> 
	       <!-- w3-card-4 adds to the container a 4px bordered shadow -->
	       <!-- w3-dropdown-content is used to create responsive dropdown links -->
	       <!-- w3-theme-m1 is a CSS rule from the colors.css (sets the color of the text and the background color) -->
	       <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-theme-m1">
		     <%
			   if (is_reg) { // it is not a regular user
		     %>
			    <!-- w3-text-theme-m1  is a CSS rule from the colors.css (sets the color of the text to the blue) -->
			    <!-- w3-hover-text-orange is used to set the orange color of the text when the user hovers the mouse over the link --> 
			    <a href="show_sched" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Show Schedule</a>
		        <a href="update_sched" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Update Schedule</a> 
		     <% 
			   } else { // it is admin
		     %>
		  	     <!-- w3-text-theme-m1  is a CSS rule from the colors.css ( sets the color of the text to the blue ) -->
		         <!-- w3-hover-text-orange is used to set the orange color of the text when the user hovers the mouse over the link --> 
		         <a href="show_sched" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Show Schedule</a>
		         <a href="update_sched" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Update Schedule</a> 
		     <% } %>
  		   </div>      
		 </div>
		
		 <!-- w3-dropdown-hover is used to create hoverable drop down element -->
		 <div class="w3-dropdown-hover w3-mobile">
		   <!-- w3-theme-m1 is used to set the background color of the button, w3-text-theme-m1 is used to set the color of the text on the navigation bar entry -->
		   <!-- w3-hover-theme-l4 is used to set on the hover the background color of the button and the color of the text -->
		   <a href="#" class="w3-button w3-theme-m1 w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">Task  <i class="fa fa-caret-down"></i></a> <!-- &#128197; HTML entity for a calendar icon -->
		   <!-- w3-card-4 adds to the container a 4px bordered shadow -->
		   <!-- w3-dropdown-content is used to create responsive dropdown links -->
		   <!-- w3-theme-m1 is a CSS rule from the colors.css ( sets the color of the text and the background color ) -->
		   <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-theme-m1">
		     <%
		       if (is_admin) { // the user is logged in an admin
		     %>
				<a href="add_task" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Add Task</a>
			 <%
		       } else if (is_reg) { // the user is logged in as a regular user
		     %>
				<a href="add_task_get" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Add Task</a>
			 <%
			   } 
			 %>
			 <a href="update_sched" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Update Task</a>
			 <%
		       if (is_admin) { // the user is logged in an admin
		     %>
			    <a href="del_task" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Delete Task</a>
			 <%
		       } else if (is_reg) { // the user is logged in as a regular user
		     %>
			    <a href="del_task_get" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Delete Task</a>
			 <%
			   } 
			 %>
		   </div>      
		 </div>
	<%
	  } 
	%>
	
	<%
	  if (is_admin || is_reg) { // the user is logged in an admin or as a regular user
	%>
		<!-- w3-text-theme-m1 is used to set the text of the link to blue -->
		<!-- w3-hover-theme-l4 when the user hovers the mouse, there is no change in the background color of the navigation bar, the text color is orange -->
		<!-- w3-mobile used to create a responsive navbar with responsive dropdown links -->
		<a href="contact_user" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">Contact Us</a>
	<%
	  } else { // the user is not logged in 
	%>
	    <!-- w3-text-theme-m1 is used to set the text of the link to blue -->
		<!-- w3-hover-theme-l4 when the user hovers the mouse, there is no change in the background color of the navigation bar, the text color is orange -->
		<!-- w3-mobile used to create a responsive navbar with responsive dropdown links -->
		<a href="contact_us" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">Contact Us</a>
	<%
	  } 
	%>	
			
	<%
	  // if the user logged in as a regular user or as an admin show the Log Out (navbar)  
	  if (is_reg || is_admin) { 
	%>
		<!-- w3-hover-theme-l4 is used to set on the hover the background color of the button and the color of the text -->
		<!-- w3-mobile used to create a responsive navbar with responsive dropdown links -->
		<!-- w3-right used to right-align a specific link -->
		<a href="log_out" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile w3-right">Log Out</a>
		
	<%
		// otherwise show Log In
	 	} else {
	%>
			<a href="loginfo" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile w3-right">Log In</a>
			<!-- <a href="newlogform" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile w3-right">Log In2</a> -->
	<% 
	 	}
	%>
</div> <!-- end of class="w3-bar w3-theme-m1 w3-round-xxlarge" -->
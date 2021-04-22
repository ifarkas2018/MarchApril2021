<!-- author: Ingrid Farkas; project: Time Manager, 2020 -->
<!-- show_sched_results is shown when the URL is localhost:8080/show_sched_results  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<!-- include the link to the external style sheet -->	  
	<%@ include file="header.jsp"%>
  </head>
  
  <body>   
    <div class="content"> 
      <!-- page title -->
      <header class="w3-container w3-white w3-xlarge w3-padding-16"> 
        <span class="w3-left">Time Manager</span> 
      </header> <!-- end of header -->
      <!-- including the navigation -->
      <%@ include file="nav1.jsp"%>  
      	
      <%  
		// sess_update (session variable) : does the user want to see or update the schedule
		String sess_update = (String)(session.getAttribute("sess_update")); %>
      	<!-- include the content of the web page -->
      	<% if (sess_update.equals("true")) { // it is Update Schedule %>
      	 	 <!-- shows the table of tasks for the certain employee on the certain date (it can be updated) -->
      	 	 <%@ include file="upd_sched.jsp"%>
      	<% } else { %>
      	 	 <!-- shows the table of tasks for the certain employee on the certain date (it can't be updated) -->
      	 	 <%@ include file="show_schedule.jsp"%>
      	<% } %>
      	<br />
      	<!-- include the footer -->
      	<%@ include file="footer.jsp"%>
	</div> 
  </body> 
</html>
<!-- project : Time Manager, author : Ingrid Farkas, 2020 -->

<!-- show_emp_info is shown when the URL is localhost:8080/addshow_emp - method is POST -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<!-- include the links to the external style sheets -->	  
	<%@ include file="header.jsp"%>
  </head>

  <body>   
    <div class="content"> 
      <!-- top menu on small screens -->
      <header class="w3-container w3-white w3-xlarge w3-padding-16"> 
        <span class="w3-left">Time Manager</span> 
      </header> <!-- end of header -->
	  
	  <!-- including the navigation -->
      <%@ include file="nav1.jsp"%>   	
      <!-- include the content of the web page -->
      <!-- shows the table with the information about the employee --> 
      <%@ include file="show_emp_form.jsp"%> 
      <br />
      <!-- include the footer -->
      <%@ include file="footer.jsp"%>
	</div> 
  </body> 




<!-- project : Time Manager, author : Ingrid Farkas, 2020 -->

<!-- result.jsp is shown when the URL is login_result, addshow_emp, sched_table, show_update_results, add_d_res, log_out -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
	<!-- including the link to the external style sheet -->	  
	<%@ include file="header.jsp"%>
  </head>

<body>   
  <div class="content"> 
  
    <!-- top menu  -->
    <header class="w3-container w3-white w3-xlarge w3-padding-16"> 
      <span class="w3-left">Time Manager</span> 
    </header> <!-- end of header -->
      	
    <!-- including the navigation ( of the web page ) -->
	<%@ include file="nav1.jsp"%>   	
	<!-- including the content ( of the web page ) -->
    <%@ include file="result_content.jsp"%> <!-- shows whether the action done was successful -->  	
    <br />
    <!-- including the footer -->
    <%@ include file="footer.jsp"%>
      	
  </div> 
</body> 
<!-- author: Ingrid Farkas; project: Time Manager, 2020 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <!-- add: the meta tags, the links to the external style sheets -->	  
  <%@ include file="header.jsp"%>
</head>

<body>   
  <div class="content"> 
    <!-- Top menu on small screens -->
    <header class="w3-container w3-white w3-xlarge w3-padding-16"> 
      <span class="w3-left">Time Manager</span>
    </header> <!-- end of header -->
	  	
	<!-- add: the navigation -->
    <%@ include file="nav1.jsp"%>								   
    <!-- add: the content -->
    <%@ include file="logicont.jsp"%> 
    <br />
    <br />
    <!-- add: the footer -->
    <%@ include file="footer.jsp"%>
  </div> 
</body> 
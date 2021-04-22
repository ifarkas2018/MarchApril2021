<!-- author: Ingrid Farkas; project: Time Manager, 2020 -->

<!-- login_form is shown when the URL is localhost:8080/logform -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
  <script>
	PASSW_EQUAL = 'true'; // do the passwords in 2 input fields match 

	// equalPasswords: shows message below the second password input field when the passwords do not match
	function equalPasswords(){ 
	  password1 = document.getElementById("user_passw").value;
	  password2 = document.getElementById("user_passw2").value;
	  if (password1 != password2){
		document.getElementById("message2").innerHTML = "* Passwords Do Not Match";
		PASSW_EQUAL = 'false';
	  } else {
		document.getElementById("message2").innerHTML = "* Required Field";
		PASSW_EQUAL = 'true';
	  }
	}
	
	// checkForm: if the validation was successful returns TRUE otherwise returns FALSE
	function checkForm(){
	  if (PASSW_EQUAL === 'true') { 
	    return true;
	  } else {
	    return false;
	  }
	}
  </script>
	
  <%@ include file="header.jsp"%>
</head>

<body>  
  <div  class="content"> 
    <!-- Top menu -->
    <header class="w3-container w3-white w3-xlarge w3-padding-16"> 
      <span class="w3-left">Time Manager</span> 
    </header> <!-- end of header -->
      	
	<!-- including the navigation -->
	<!-- below the login form is included -->
    <%@ include file="nav1.jsp"%>
    <!-- including the content (of the web page) -->
    <%@ include file="logfcont.jsp"%> <!-- shows the login form for entering the user name and password -->
    <br />
    <%@ include file="footer.jsp"%>
    <!-- including the footer -->  	
  </div>
</body> 
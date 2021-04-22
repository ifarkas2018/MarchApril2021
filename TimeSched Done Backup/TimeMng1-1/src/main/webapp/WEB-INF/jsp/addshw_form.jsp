<!-- author: Ingrid Farkas; project: Time Manager, 2020 -->

<!-- showempl_form is shown when the URL is localhost:8080/addempl_form or localhost:8080/showempl_form  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
	<%@ include file="header.jsp"%>
	<script>
		FNAME_VAL = 'true'; // does the first name input field contain only letters (and spaces and apostrophes)
		LNAME_VAL = 'true'; // does the last name input field contain only letters (and spaces and apostrophes)
		PASSW_EQUAL = 'true'; // do the passwords in 2 input fields match 
		EMPID_VAL = 'true'; // is the entered Employee ID a number 
		
		// setNAME_VAL: if is_fname is 'true' sets the FNAME_VAL to val, otherwise sets the LNAME_VAL to val 
		function setNAME_VAL(is_fname, val) {
			if (is_fname === 'true') {
		    	FNAME_VAL = val;
		    } else {
		    	LNAME_VAL = val;
		    }
		}
		
		// isNum: shows a message (in the msg_field) if the user entered a value that is a non numeric value (in the input field named input_field)
		// formid: id of the form
		// num_type - is the input in the field an Employee ID 
		function isNum(formid, input_field, num_type, msg_field) {
		    var number; 
		    
		    // the number in the input field
		    number = document.getElementById(input_field).value;    
		    // if the value entered is not a nuumber 
		    if ( isNaN(number)) {
		        document.getElementById(msg_field).innerHTML = "* Can Contain Only Digits"; // show the message
		        if (num_type == 'is_empid') {
		            EMPID_VAL = 'false';
		        }
		    } else {
		        if (num_type == 'is_empid') {
		            EMPID_VAL = 'true';
		        }
		        document.getElementById(msg_field).innerHTML = ""; // show the message 
		    }
		}

		// valLetters: checks whether in the control input_field there are only letters (and spaces and apostrophes). If not in the message_span the message is shown.
		// required - if the input field was required to be filled in before showing the message, when the user corrects the mistake, below
		// is shown again that the input field is required (to be filled in)
		function valLetters(input_field, message_span,required, is_fname) { 
		    var regex = /^[a-zA-Z\x27\x20]+$/;
		    
		    if (!input_field.value == '') {
		        if (!regex.test(input_field.value)) { // if the user entered some characters which are not letters (in the input_field)
		            message_span.innerHTML = "* Can Contain only Letters, Apostrophes and Space";
		            setNAME_VAL(is_fname, 'false');
		            // set the focus in the input field where the user entered characters other than letters
		        } else { // the user entered characters which are letters (in the input_field)
		        	setNAME_VAL(is_fname, 'true');
		            if (required == 'true') {
		                message_span.innerHTML = "* Required Field";
		            } else {
		                message_span.innerHTML = "";
		            }
		        }
		    } else {
		        if (required == 'true') {
		        	setNAME_VAL(is_fname, 'false');
		            message_span.innerHTML = "* Required Field";
		        } else {
		        	setNAME_VAL(is_fname, 'true');
		            message_span.innerHTML = "";
		        }
		    } 
		}
		
		// checkForm: if the validation was successful return TRUE otherwise return FALSE
		function checkForm() {
		    if ((FNAME_VAL === 'true') && (LNAME_VAL === 'true') && (PASSW_EQUAL === 'true') && (EMPID_VAL === 'true')) { 
		        return true;
		    } else {
		        return false;
		    }
		}	
	</script>
</head>

<body>   
    <div class="content"> 
      	<!-- Top menu -->
      	<header class="w3-container w3-white w3-xlarge w3-padding-16">
        	<span class="w3-left">Time Manager</span> 
      	</header> 
      	<!-- including the navigation -->
	  	<%@ include file="nav1.jsp" %>
      	<!-- including the content (of the web page) -->
      	<%@ include file="addshw_fcont.jsp"%> <!-- shows the Add Employee form or Show Employee form -->
      	<br />
      	<!-- including the footer -->
      	<%@ include file="footer.jsp"%>
	</div> 
</body> 
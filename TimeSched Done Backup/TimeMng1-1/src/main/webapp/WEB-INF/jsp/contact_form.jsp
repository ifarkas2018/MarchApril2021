<!-- author: Ingrid Farkas; project: Time Manager, 2020 -->

<!-- contact_form is shown when the URL is contact_us -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp"%>
	
	<style type="text/css">
		textarea {
  			resize: none; // the text are is not resizeable
		}
	</style>
	
	<script>
		FNAME_VAL = 'true'; // does the first name input field contain only letters (and spaces and apostrophes)
		LNAME_VAL = 'true'; // does the last name input field contain only letters (and spaces and apostrophes)
		EMAIL_VAL = 'true'; // is the entered value a valid email address
		
		// setNAME_VAL: if is_fname is 'true' sets the FNAME_VAL to val, otherwise sets the LNAME_VAL to val 
		function setNAME_VAL(is_fname, val) {
			if (is_fname === 'true') {
		    	FNAME_VAL = val;
		    } else {
		    	LNAME_VAL = val;
		    }
		}
		
		// valLetters: checks whether in the control input_field there are only letters (and spaces and apostrophes). If it isn't in the message_span the message is shown.
		// required - if the input field was required to be filled in before showing the message, when the user corrects the mistake, bellow
		// is shown again that the input field is required (to be filled in)
		function valLetters(input_field, message_span,required, is_fname) { 
		    var regex = /^[a-zA-Z\x27\x20]+$/;
		    
		    if (!input_field.value == '') {
		        if (!regex.test(input_field.value)) { // if the user entered some characters which are not letters (in the input_field)
		            message_span.innerHTML = "* Can Contain only Letters, Apostrophes and Space";
		            setNAME_VAL(is_fname, 'false');
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
		
		// isEmail: checks whether the email address is valid. If it isn't in the message_span the message is shown.
        function isEmail(input_field, message_span) {
        	if (!input_field.value == '') {
            	// regex pattern is used for validating email 
            	var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            	if(!regex.test(input_field.value)) {
                	EMAIL_VAL = 'false';
                	message_span.innerHTML = "* Please Enter a Valid Email Address";
            	} else {
            		message_span.innerHTML = "";
            		EMAIL_VAL = 'true';
            	}
        	} else {
        		message_span.innerHTML = "";
        		EMAIL_VAL = 'true';
        	}
		}
		
		// checkForm: if the validation was successful return TRUE otherwise return FALSE
		function checkForm(){
		    if ((FNAME_VAL === 'true') && (LNAME_VAL === 'true') && (EMAIL_VAL === 'true')) { 
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
      	</header> <!-- end of header -->
      	
      	<!-- including the navigation -->
      	<%@ include file="nav1.jsp"%>
      	
      	<!-- including the content (of the web page) -->
      	<%@ include file="contact_info.jsp"%> <!-- shows the contact information and the form for entering the name, email, message -->
      	<br />
      	
      	<!-- including the footer -->
      	<%@ include file="footer.jsp"%> 
    </div>
</body> 
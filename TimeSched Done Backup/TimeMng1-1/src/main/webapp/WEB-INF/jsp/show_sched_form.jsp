<!-- author: Ingrid Farkas; project: Time Manager, 2020 -->
<!-- show_sched_form is shown when the URL is show_sched, update_sched, add_task, del_task -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
	<!-- the link to the external style sheet -->	  
	<%@ include file="header.jsp"%>
	
	<script>
	  FNAME_VAL = 'true'; // does the first name input field contain only letters (and spaces and apostrophes)
	  LNAME_VAL = 'true'; // does the last name input field contain only letters (and spaces and apostrophes) 
	  EMPID_VAL = 'true'; // is the entered Employee ID a number 
	  DATE_VAL = 'true'; // has the entered date a date format 
		
	  // dateFormat: shows a message (in the msg_field) if the user entered a date (in the input field named input_field)
	  function dateFormat(input_field, msg_field) {
		var date;
			
		// the date in the input field
		date = document.getElementById(input_field).value;
			
		if (date.indexOf("//") < 0) {
		  var arr = date.split("/"); // split the date using the character /
	
		  if (arr[0].length < 1 || arr[0].length > 2 || arr[1].length < 1 || arr[1].length > 2 || arr[2].length !=4) { // if the user didn't enter 1 or 2 digits for the day, month or 4 digits for the year 
			DATE_VAL = 'false';
			document.getElementById(msg_field).innerHTML = "* Please Enter The Date In The Required Format";
		  } else if ((isNaN(arr[0])) || (isNaN(arr[1])) || (isNaN(arr[2]))) { // if the day, month or year is not a number
			DATE_VAL = 'false';
			document.getElementById(msg_field).innerHTML = "* Can Contain Only Digits And Slash /"; // show the message
		  } else if ( arr[0] === '00' || arr[1] === '00' || arr[2] === '0000') {
			DATE_VAL = 'false';
			document.getElementById(msg_field).innerHTML = "* The Day, Month Has To Be > 00 And The Year Has To Be > 0000";
		  } else {
			if (arr[0]>31 || arr[1]>12) {
			  DATE_VAL = 'false';
			  document.getElementById(msg_field).innerHTML = "* The Day Has To Be Less Or Equal 31 And The Month Has To Be Less Or Equal 12";
			} else {
			  DATE_VAL = 'true';
		      document.getElementById(msg_field).innerHTML = "* Required Field";
			}
		  }
		} else {
		  DATE_VAL = 'false';
		  document.getElementById(msg_field).innerHTML = "* Can Contain Only Digits And Slash /"; // show the message
		}
	  }
		
	  // setNAME_VAL: if is_fname is 'true' sets the FNAME_VAL to val, otherwise sets the LNAME_VAL to val 
	  function setNAME_VAL(is_fname, val) {
		if (is_fname === 'true') {
		  FNAME_VAL = val;
		} else {
		  LNAME_VAL = val;
		}
	  }
		
	  // valLetters: checks whether in the control input_field there are only letters (and spaces and apostrophes). If not in the message_span the message is shown.
	  // required - if the input field was required to be filled in before showing the message, when the user corrects the mistake, below
	  // is shown again that the input field is required (to be filled in)
	  function valLetters( input_field, message_span,required, is_fname) { 
		var regex = /^[a-zA-Z\x27\x20]+$/;
		
		if (!input_field.value == '') {
		  if ( !regex.test(input_field.value)) { // if the user entered some characters which are not letters (in the input_field)
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
		if ((FNAME_VAL === 'true') && (LNAME_VAL === 'true') && (EMPID_VAL === 'true') && (DATE_VAL === 'true')) { 
		  return true;
		} else {
		  return false;
		}
	  }

	</script>
  </head>

  <body>   
    <div class="content"> 
      <!-- top menu -->
      <header class="w3-container w3-white w3-xlarge w3-padding-16"> 
        <span class="w3-left">Time Manager</span> 
      </header> <!-- end of header -->
      	
	  <!-- including the navigation -->
      <%@ include file="nav1.jsp"%> 	   
      <!-- including the content ( of the web page ) -->
      <%@ include file="show_sched_fcont.jsp"%> <!-- shows the form for entering the first name, last name, date of the schedule -->
      <br />
      <!-- including the footer -->
      <%@ include file="footer.jsp"%>	
	</div> 
  </body> 
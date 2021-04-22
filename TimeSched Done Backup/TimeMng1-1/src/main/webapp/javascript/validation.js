/* Project : Time Manager
 * Author : Ingrid Farkas
 * validation.js: functions used for validation
 */

/* ???????????????????????????????????????????????????????????????????????????????
 * this file is now NOT NEEDED @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */

FNAME_VAL = 'true'; // does the first name input field contain only letters (and spaces and apostrophes)
LNAME_VAL = 'true'; // does the last name input field contain only letters (and spaces and apostrophes)
PASSW_EQUAL = 'true'; // do the passwords in 2 input fields match
EMPID_VAL = 'true'; // is the entered Employee ID a number 
DATE_VAL = 'true'; // has the entered date a date format 
EMAIL_VAL = 'true'; // is the entered value a valid email address

// isNum: shows a message (in the msg_field) if the user entered a value that is a non numeric value (in the input field named input_field)
// formid: id of the form
// num_type - is the input in the field an Employee ID @@@@@@@@@@@@@@@@@@@@@@@ change this
function isNum(formid, input_field, num_type, msg_field) {
    var number; 
    
    // the number in the input field
    number = document.getElementById(input_field).value;
    
    // if the value entered is not a nuumber 
    if (isNaN(number)) {
        document.getElementById(msg_field).innerHTML = "* Can Contain Only Digits"; // show the message
        if (num_type == 'is_empid') {
            EMPID_VAL = 'false';
        }
    } else {
        if (num_type == 'is_empid') {
            EMPID_VAL  = 'true';
        }
        document.getElementById(msg_field).innerHTML = ""; // show the message 
    }   
}

// dateFormat: shows a message (in the msg_field) if the user entered a date (in the input field named input_field)
function dateFormat(input_field, msg_field) {
	var date;
	
	// the date in the input field
	date = document.getElementById(input_field).value;
	
	if (date.indexOf("//") < 0) {
		var arr = date.split("/"); // split the date using the character /

		if (arr[0].length < 1 || arr[0].length > 2 || arr[1].length < 1 || arr[1].length > 2 || arr[2].length !=4) { // if the user didn't enter 2 digits for the day, month or 4 digits for the year 
			DATE_VAL = 'false';
			document.getElementById(msg_field).innerHTML = "* Please Enter The Date In The Required Format";
		} else if ((isNaN(arr[0])) || (isNaN(arr[1])) || (isNaN(arr[2]))) { // if the day, month or year is not a number
			DATE_VAL = 'false';
			document.getElementById(msg_field).innerHTML = "* Can Contain Only Digits And Slash /"; // show the message
		} else if (arr[0] === '00' || arr[1] === '00' || arr[2] === '0000') {
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
function valLetters(input_field, message_span,required, is_fname) { 
    var regex = /^[a-zA-Z\x27\x20]+$/;
    if (!input_field.value == '') {
        if ( !regex.test(input_field.value)) { // if the user entered some characters which are not letters (in the input_field)
            message_span.innerHTML = "* Can contain only letters, apostrophes and space";
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

// equalPasswords: shows message below the second password input field when the passwords do not match
function equalPasswords() { 
	password1 = document.getElementById("user_passw").value;
	password2 = document.getElementById("user_passw2").value;
	if (password1 != password2){
		document.getElementById("message2").innerHTML = "Passwords do not match";
		PASSW_EQUAL = 'false';
	} else {
		document.getElementById("message2").innerHTML = "* Required Field";
		PASSW_EQUAL = 'true';
	}	
}

// isEmail : checks whether the email address is valid. If it isn't in the message_span the message is shown.
function isEmail(input_field, message_span) {
	if (!input_field.value == '') {
    	// regex pattern is used for validating email 
    	var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    	if (!regex.test(input_field.value)) {
        	EMAIL_VAL = 'false';
        	message_span.innerHTML = "* Please Enter a Valid Email Address";
    	} else {
    		message_span.innerHTML = "";
    	}
	} else {
		message_span.innerHTML = "";
	}
}

// checkForm: if the validation was successful return TRUE otherwise return FALSE
function checkForm() {
    if ((FNAME_VAL === 'true') && (LNAME_VAL === 'true') && (PASSW_EQUAL === 'true') && (EMPID_VAL === 'true') && (DATE_VAL === 'true') && (EMAIL_VAL === 'true')) { 
        return true;
    } else {
        return false;
    }
}
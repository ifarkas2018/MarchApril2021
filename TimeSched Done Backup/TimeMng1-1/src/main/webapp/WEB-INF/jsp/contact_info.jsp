<!-- project : Time Manager, author : Ingrid Farkas, 2020 -->
<!-- included in contact_form.jsp -->

<!-- Contact section -->
<div class="w3-content">
  <div class="w3-row w3-margin">
	<div class="w3-third">
	  &nbsp; &nbsp; &nbsp; &nbsp;
	  <br />
	  &nbsp; &nbsp; &nbsp; &nbsp;
	  <!-- first image on the left hand side from the form -->
	  <img src="../../images/woman_on_phone.jpg" style="width:100%">
	  &nbsp; &nbsp; &nbsp; &nbsp;
	  <!-- second image on the left hand side from the form -->
	  <img src="../../images/woman_with_laptop.jpg" style="width:100%"> 
	  &nbsp; &nbsp; &nbsp; &nbsp;
	  <!-- third image on the left hand side from the form -->
	  <img src="../../images/woman_on_phone.jpg" style="width:100%">
	  <br />
	  <br />
	</div>
	        
	<!-- "w3-third" class uses 66% of the parent container -->
	<div class="w3-twothird w3-container">
	  <br />
	  <br />
	  <!-- form_background CSS rule which sets the color of the container (file styles1.css) -->
	  <div class="w3-container form_background w3-padding-32 w3-padding-large" id="show_sched_info"> 
	    <div class="w3-content w3-text" style="max-width:600px">
	      <!-- w3-center centers the text -->
		  <h3 class="w3-center"><b>Contact Us</b></h3>
		  <br />
		  <br />
		  <i class="fa fa-map-marker" style="width:30px"></i> New York, USA</span><br>
    	  <i class="fa fa-phone" style="width:30px"></i> Phone: (925) 380.4794<br>
    	  <span class="email">
   		  	<i class="fa fa-envelope" style="width:30px"></i> Email: <a href="mailto:info@hcweb.com?subject=Feedback&body=Message" style="text-decoration:none !important;">info@hcweb.com</a>
		  </span>
    	  <br />
    	  <br />
		  <%  
			// did the user already login (as administrator or employee)
			String is_already_login = (String)(request.getAttribute("already_login"));
		    if (is_already_login.equals("true")) {
		  %>    
			  <form id="contact_form" name="contact_form" action="http://localhost:8080/emp_message" method="post"> <!-- when submitted the localhost:8080/emp_message is shown -->
		  <%
			} else {
		  %>	
			  <form id="contact_form" name="contact_form" action="http://localhost:8080/cust_message" method="post"> <!-- if the customer didn't already log in -->
		  <%
		    }
		  %>
    	        <div class="w3-section">
		          <label>First Name</label>  
			      <input class="w3-input w3-border" type="text" name="first_name" maxlength="30" onchange="valLetters(document.contact_form.first_name, fname_message, 'true', 'true');" required=true> 
			      <span id="fname_message" class="red_text">* Required Field</span>
		        </div>
					
		        <div class="w3-section">
				  <label>Last Name</label>
				  <input class="w3-input w3-border" type="text" name="last_name" maxlength="30" onchange="valLetters(document.contact_form.last_name, lname_message, 'true', 'false');" required=true> 
				  <span id="lname_message" class="red_text">* Required Field</span>
				</div> 
					
				<div class="w3-section">
				  <label>Email</label> 
				  <input class="w3-input w3-border" type="text" name="email" id="email" maxlength="40" onchange="isEmail(document.contact_form.email, email_message);"> 
				  <span id="email_message" class="red_text"></span>
				</div> 
					
				<div class="w3-section">
				  <label>Message</label> 
				  <textarea class="w3-input w3-border" id="message" name="message" rows="3" required=true></textarea>
				  <span id="req_mssg" class="red_text">* Required Field</span>
				</div>
				 
				<br />
				<!-- the w3-third class uses 33% of the parent container -->
    			<button type="submit" class="w3-btn w3-camo-grey w3-third" onclick="return checkForm();">Send a Message</button>
    			<br />
    			
    		  </form>
			</div>
		  </div>
		</div>
	</div>
</div>
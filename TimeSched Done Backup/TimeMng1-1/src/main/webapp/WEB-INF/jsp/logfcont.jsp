<!-- project : Time Manager, author : Ingrid Farkas, 2020 -->

<!-- included in logform.jsp -->
  <div class="w3-content">
	<div class="w3-row w3-margin">
	  <div class="w3-third">
	    &nbsp; &nbsp; &nbsp; &nbsp;
	    <br />
	    &nbsp; &nbsp; &nbsp; &nbsp;
	    <!-- first image on the left hand side on the form -->
	    <img src="../../images/woman_on_phone.jpg" style="width:100%">
	    &nbsp; &nbsp; &nbsp; &nbsp;
	    <!-- second image on the left hand side from the form -->
	    <img src="../../images/woman_with_laptop.jpg" style="width:100%"> 
	    <br />
	    <br />
	  </div>
      
      <%
		// reading from the model variable whether the user is logging in
		String logging_in = (String)(request.getAttribute("logging_in"));
		// is the administrator adding a new log in
		String add_login = (String)(request.getAttribute("add_login"));
	  %>
	  
	  <!-- "w3-third" class uses 66% of the parent container -->
	  <div class="w3-twothird w3-container">
	    <br />
	    <br />
	    <!-- form_background CSS rule which sets the color of the container (file styles1.css) -->
	    <div class="w3-container form_background w3-padding-32 w3-padding-large" id="show_sched_info"> 
	      <div class="w3-content w3-text" style="max-width:600px">
	        <% if (logging_in.equals("true")) { // the user is logging in
	        %>
		    <!-- w3-center centers the text -->
			<h3 class="w3-center"><b>Login</b></h3>
			<%
		       } else if (add_login.equals("true")) { // the administrator is adding a new log in (for the new emp.)
		    %>
		    	  <!-- w3-center centers the text -->
			      <h3 class="w3-center"><b>Add Employee</b></h3>
			<%
		       }
			%>
			<br />
		    <br />
		    
			<% if (logging_in.equals("true")) { // the user is logging in
	        %>
		         <!-- after clicking on the button localhost:8080/login_result is called using method post -->
		         <form action="/login_result" name="login" id="login" method="post">
		    <%
			   } else if (add_login.equals("true")) { // the administrator is adding a new log in (for the new emp.)
		    %>
		         <!-- after clicking on the button localhost:8080/addshow_emp is called using method post -->
		         <form action="/addshow_emp" name="addshow" id="addshow" method="post">
		    <%
			   }
		    %>         	
		           <div class="w3-section">
		             <label>User Name</label>
		             <input class="w3-input w3-border" type="text" name="user_name" id="user_name" maxlength="20" required=true> <!-- input field for entering the user name -->
		             <span class="red_text">* Required Field</span>	
		           </div> 
		           
		           <div class="w3-section">
		             <label>Password</label>
		             <input class="w3-input w3-border" type="password" name="user_passw" id="user_passw" maxlength="20" onchange="equalPasswords();" required=true> <!-- input field for entering the password -->
		             <span class="red_text">* Required Field</span>
		           </div>
		          
		           <%  if (logging_in.equals("true")) { // the user is logging in
	               %> 			
	               		 <br />
	               		 <!-- the w3-third class uses 33% of the parent container -->
		                 <button class="w3-btn w3-camo-grey w3-third" name="login_btn" id="login_btn">Login</button> 
		           <%
		               } else if (add_login.equals("true")) { // the administrator is adding a new log in (for the new emp.)
		           %>
		                 <div class="w3-section">
		                   <label>Re-enter Password</label>
		                   <input class="w3-input w3-border" type="password" name="user_passw2" id="user_passw2" maxlength="20" onchange="equalPasswords();"  required=true> <!-- input field for re-entering the password -->
		                   <span id="message2" name="message2" class="red_text">* Required Field</span>
		                 </div>
				         <input class="w3-input w3-border" type="hidden" name="show_add" value="add"> 
						 <br />
						 <!-- the w3-third class uses 33% of the parent container -->
						 <button class="w3-btn w3-camo-grey w3-third" name="login_btn" id="login_btn" onclick="return checkForm();">Add Employee</button> 
				   <%
		               }
				   %>
				  
		         </form>
	       </div>
	     </div>
	     <br />
	     <br />
	  </div>  <!-- end of class="w3-twothird w3-container" -->
	</div> <!-- end of class="w3-row w3-margin" --> 
  </div> 
</div> 

<!-- project : Time Manager, author : Ingrid Farkas, 2020 -->
<!-- included in show_sched_form.jsp -->

<div class="w3-content">
  <div class="w3-row w3-margin">
	<!-- "w3-third" class uses 33% of the parent container -->
	<div class="w3-third">
	  &nbsp; &nbsp; &nbsp; &nbsp;
	  <br />
	  &nbsp; &nbsp; &nbsp; &nbsp;
	  <!-- first image on the left hand side from the form -->
	  <img src="../../images/woman_on_phone.jpg" style="width:100%">
	  &nbsp; &nbsp; &nbsp; &nbsp;
	  <!-- second image on the left hand side from the form -->
	  <img src="../../images/woman_with_laptop.jpg" style="width:100%"> 
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
	              		
		  <% 
		    // retrieving the model variable is_update from the controller 
		    // is_update : to update the schedule
		    String is_update = (String)(request.getAttribute("is_update"));
		    // is_show_sched : to see the schedule 
		    String is_show_sched = (String)(request.getAttribute("is_show_sched"));
		    // is_add_task : to add task
		    String is_add_task = (String)(request.getAttribute("is_add_task"));
		    // is_del_task : to delete task
		    String is_del_task = (String)(request.getAttribute("is_del_task"));
		             	
		    // setting the session variable "sess_del_task" (whether the user is doing delete of the task)
		    session.setAttribute("sess_del_task", is_del_task);
		    // setting the session variable "is_update" (whether the user is doing update of the schedule)
		    session.setAttribute("sess_update", is_update);
		                
	   	    if (is_update.equals("true")){ // if Update Schedule
		  %>
			      
	          <!--  w3-center centers the text -->
			  <h3 class="w3-center"><b>Update Schedule</b></h3> <!-- title of the web page -->
		  <% 
		    } else if (is_show_sched.equals("true")){ // if Show Schedule
		  %>
			  <h3 class="w3-center"><b>Show Schedule</b></h3> <!-- title of the web page -->
		  <% 	
		    } else if (is_add_task.equals("true")){ // if Add Task
		  %>
		      <h3 class="w3-center"><b>Add Task</b></h3> <!-- title of the web page -->
	      <% 	
		    } else if (is_del_task.equals("true")){ // if Delete Task
	      %>
			  <h3 class="w3-center"><b>Delete Task</b></h3> <!-- title of the web page -->
		  <%
		    }           
    		String sess_admin = (String)session.getAttribute("sess_adm"); // did the user before log in as admin (and is still logged in)
    		boolean is_adm = false;
    		if (sess_admin.equals("true")){
    		  is_adm = true; // logged in as administrator
    		}	
    					
    		if ((is_add_task.equals("true")) || (is_del_task.equals("true"))) { // if Add or Delete Task
          %>
              <form action="/add_d_form" name="add_d_form" id="add_d_form" method="post"> <!-- shows the URL : localhost:8080/add_d_form -->
    	    <%
    	      if (is_add_task.equals("true")) { // if it is Add Task
    	    %>	    
    		    <input class="w3-input w3-border" type="hidden" name="delete_task" id="delete_task" value ="false" required=true>	
    	    <%
    	      } else { // if it is Delete Task
    	    %>
    	        <input class="w3-input w3-border" type="hidden" name="delete_task" id="delete_task" value ="true" required=true>
    	    <%
    	      }
    	    %>
    	  <% 
    		} else { // if Show Schedule or Update Schedule
          %>
    		  <form action="/sched_table" name="show_sched" id="show_sched" method="post"> <!-- shows the URL : localhost:8080/sched_table -->
		  <%
    		}
    	  %>
    	  
    	  <br />
    	  <br />
    	  
    	  <%	
		    // if the user is logged in as admin, or as an ordinary user who is not doing the update (but show of the schedule)
		    if ((is_adm) || (is_update).equals("false")){ %> 
			  <div class="w3-section">
			    <label>First Name</label>  
			    <% 
			      if ((is_add_task.equals("true")) || (is_del_task.equals("true"))) { // if Add or Delete Task
			    %>
			        <input class="w3-input w3-border" type="text" name="first_name" id="first_name" maxlength="30" onchange="valLetters(document.add_d_form.first_name, fname_message, 'true', 'true');" required=true> <!-- input field for entering the first_name -->
			    <%
			      } else {
			    %>
			        <input class="w3-input w3-border" type="text" name="first_name" id="first_name" maxlength="30" onchange="valLetters(document.show_sched.first_name, fname_message, 'true', 'true');" required=true> <!-- input field for entering the first_name -->
			    <%
			      }
			    %>
			    <span id="fname_message" class="red_text">* Required Field</span>
			  </div>
			  <div class="w3-section">
			    <label>Last Name</label>
			    <% 
			      if ((is_add_task.equals("true")) || (is_del_task.equals("true"))) { // if Add or Delete Task
			    %>
			        <input class="w3-input w3-border" type="text" name="last_name" id="last_name" maxlength="30" onchange="valLetters(document.add_d_form.last_name, lname_message, 'true', 'false');" required=true> 
			    <%
			      } else {
			    %>
			        <input class="w3-input w3-border" type="text" name="last_name" id="last_name" maxlength="30" onchange="valLetters(document.show_sched.last_name, lname_message, 'true', 'false');" required=true> 
			    <%
			      }
			    %>
			    <span id="lname_message" class="red_text">* Required Field</span>
			  </div> 
		  <%
		    } 
		  %>  
		                  	
		  <% 
		    if ((!(is_add_task.equals("true"))) && (!(is_del_task.equals("true")))){ // if it isn't Add Task or Delete Task
		  %>
			  <div class="w3-section">
			    <label>Date (format dd/mm/yyyy) </label>
			    <input class="w3-input w3-border" type="text" name="date" id="date" maxlength="10" onchange='dateFormat("date", "date_message");' required=true>
			    <span id="date_message" class="red_text">* Required Field</span>
			  </div> 
		  <% 
		    }
		  %>
		  
		  	<br />
		  
		  <% 
		  	if (is_show_sched.equals("true") || is_update.equals("true")) {
		  %>  	
		      <!-- w3-camo-grey is a CSS rule in the colors.css -->
		      <button class="w3-btn w3-camo-grey w3-third" onclick="return checkForm();">Show Schedule</button> 
		  <%
		  	} else {
		  %>
		      <!-- w3-camo-grey is a CSS rule in the colors.css -->
		      <button class="w3-btn w3-camo-grey w3-third" onclick="return checkForm();">Next</button> 
		  <%	
		  	}
		  %>    
		      <br />
		      </form>
	    </div>
	  </div>
	  <br />
	  <br />
	</div>  <!-- end of class="w3-twothird w3-container" -->
   </div> <!-- end of class="w3-row w3-margin" --> 
  </div> 
</div> 

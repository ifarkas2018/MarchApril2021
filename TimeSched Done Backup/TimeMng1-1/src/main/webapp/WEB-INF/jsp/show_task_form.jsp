<!-- project : Time Manager, author : Ingrid Farkas, 2020 -->
<!-- included in show_task_info.jsp -->

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
	</div>
	
	<%
	  // reading from the model variable whether the user is doing Add or Delete Task
	  String is_add_del_task = (String)(request.getAttribute("is_add_del_task"));
	  // read_add_d : whether the is_add_del_task attribute is set
	  String read_add_d = (String)(request.getAttribute("read_add_d"));
	  String is_add_task = "false"; // is it Add Task
	  String is_del_task = "false"; // is it Delete Task
				
	  if (read_add_d.equals("false")){ // is_add_del_task attribute is not set (in the controller) - is_add_task, is_del_task is set
		is_add_task = (String)(request.getAttribute("is_add_task")); // read from the model attribute whether it is Add Task
		is_del_task = (String)(request.getAttribute("is_del_task")); // read from the model attribute whether it is Delete Task
		is_add_del_task = "true"; // it is Add or Delete Task
	  } else {	
		String sess_del_task = (String)session.getAttribute("sess_del_task"); // session attribute whether it is Delete Task 
	
		if (sess_del_task!=null) { // if the session variable is not set (the user didn't do delete before or isn't doing it now)
		  if (sess_del_task.equals("true")){
			is_del_task = "true"; // it is Delete Task
		  } else if (is_add_del_task.equals("true")){
			is_add_task = "true"; // it is Add Task
		  }
		} else 
		  is_add_task = "true"; // it is Add Task
		}
	  %>
			
	  <!-- "w3-third" class uses 66% of the parent container -->
	  <div class="w3-twothird w3-container">
	    <br/>
	    <br/>
	    <!-- form_background CSS rule which sets the color of the container (file styles1.css) -->
	    <div class="w3-container form_background w3-padding-32 w3-padding-large" id="show_sched_info"> 
	      <div class="w3-content w3-text" style="max-width:600px">
	      <%
	        if (is_add_del_task.equals("false")) { // it is not Add or Delete Task but Update Task
	      %>
	          <!-- w3-center centers the text -->
		      <h3 class="w3-center"><b>Update Task</b></h3>
		      <!-- after clicking on the button localhost:8080/show_update_results is called using method post -->
		      <form action="/show_update_results" name="update_sched" id="update_sched" method="post">
		  <%	
		    } else if (is_add_task.equals("true")){ // it is Add Task
		  %>
			  <!-- w3-center centers the text -->
			  <h3 class="w3-center"><b>Add Task</b></h3>
		      <!-- after clicking on the button localhost:8080/add_d_res is called using method post -->
		      <form action="/add_d_res" method="post">
		  <%
		    } else { // it is Delete task
		  %>
		      <!-- w3-center centers the text -->
		      <h3 class="w3-center"><b>Delete Task</b></h3>
		      <!-- after clicking on the button localhost:8080/add_task_results is called using method post -->
		      <form action="/add_d_res" method="post">       
		  <%
		    } 
	        if (is_del_task.equals("true")) {
	      %>
	      	
		      <!-- hidden input field with the value whether the user is doing Delete Task -->
		      <input class="w3-input w3-border" type="hidden" name="delete_task" id="delete_task" value ="true" required=true>
	      <%
	        } else {
	      %>
	          <!-- hidden input field with the value whether the user is doing Delete Task -->
		      <input class="w3-input w3-border" type="hidden" name="delete_task" id="delete_task" value ="false" required=true>
		  <% 
	        }
	      %>
	      <br />
	      <br />
	      <%  
	        if (is_add_del_task.equals("false")) { // it is not Add or Delete Task but Update Task
	      %>
		      <input class="w3-input w3-border" type="hidden" name="task_id" value="${task_info.taskId}" >
		      <div class="w3-section">
		        <label>Task Name</label>
		        <input class="w3-input w3-border" type="text" name="task_name" id="task_name" maxlength="70" value="${task_info.taskName}" > <!-- input field for entering the task name -->
		      </div>
		  <%	
		    } else { // it is Add Task or Delete Task
		  %>
		      <div class="w3-section">
		        <label>Task Name</label>
		        <input class="w3-input w3-border" type="text" name="task_name" id="task_name" maxlength="70"> <!-- input field for entering the task name -->
		      </div>
		  <%
		    }
		  %> 	
		                
		  <%
	        if (is_add_del_task.equals("false")) { // it is not Add or Delete Task but Update Task
	      %>
		      <div class="w3-section">
		        <label>Date (format dd/mm/yyyy) </label>
		        <input class="w3-input w3-border" type="text" name="task_date" id="task_date" maxlength="10" onchange='dateFormat("task_date", "date_message");' value="${task_info.taskDate}" required=true> <!-- input field for entering the date of the task -->
		        <span id="date_message" class="red_text">* Required Field</span>
		      </div>
		  <%	
		    } else { // it is Add or Delete Task  
		  %>  
		      <div class="w3-section">
	            <label>Date (format dd/mm/yyyy) </label>
	            <input class="w3-input w3-border" type="text" name="task_date" id="task_date" maxlength="10" onchange='dateFormat("task_date", "date_message");' required=true> <!-- input field for entering the date of the task -->
	            <span id="date_message" class="red_text">* Required Field</span>
	          </div>
		  <%
		    }
		  %>
		                
		  <%
	        if (is_add_del_task.equals("false")) { // it is not Add or Delete Task but Update Task
	      %>	
		      <div class="w3-section">
		        <label>Start Time (format hh:mm AM/PM) </label>
		        <input class="w3-input w3-border" type="text" name="start_time" id="start_time" onchange='validTime("start_time", true, "start_msg");' maxlength="8" value="${task_info.taskStartTime}" required=true> <!-- input field for entering the start time -->
		        <span id="start_msg" class="red_text">* Required Field</span>
		      </div>
		  <%	
		    } else { // it is Add Task or Delete Task 
		  %>
		      <div class="w3-section">
		        <label>Start Time (format hh:mm AM/PM) </label>
		        <input class="w3-input w3-border" type="text" name="start_time" id="start_time" onchange='validTime("start_time", true, "start_msg");' maxlength="8" required=true> <!-- input field for entering the start time -->
		        <span id="start_msg" class="red_text">* Required Field</span>
		      </div>
		  <%
		    }
		  %> 
		                
		  <%
	        if (is_add_del_task.equals("false")) { // it is not Add or Delete Task but Update Task
	      %>	 
		      <div class="w3-section">
		        <label>End Time (format hh:mm AM/PM) </label>
		        <input class="w3-input w3-border" type="text" name="end_time" id="end_time" maxlength="8" onchange='validTime("end_time", false, "end_msg");' value="${task_info.taskEndTime}" required=true> <!-- input field for entering the end time -->
		        <span id="end_msg" class="red_text">* Required Field</span>
		      </div>
		  <%	
		    } else if (is_add_task.equals("true")) { // it is Add Task
		  %>  
		      <div class="w3-section">
		        <label>End Time (format hh:mm AM/PM) </label>
		        <input class="w3-input w3-border" type="text" name="end_time" id="end_time" maxlength="8" onchange='validTime("end_time", false, "end_msg");' required=true> <!-- input field for entering the end time -->
		        <span id="end_msg" class="red_text">* Required Field</span>
		      </div>
		  <%
		    }
		  %>	
		  	  <br />
		  <%
		    if (is_add_task.equals("true")) { // it is Add Task
		  %>
		      <!-- w3-camo-grey is a CSS rule in the colors.css -->
		      <button type="submit" class="w3-btn w3-camo-grey w3-third" onclick="return checkForm();">Add Task</button> 
		  <%
		    } else if (is_del_task.equals("true")) { // it is Delete Task
		  %>
		      <!-- w3-camo-grey is a CSS rule in the colors.css -->
		      <button type="submit" class="w3-btn w3-camo-grey w3-third" onclick="return checkForm();">Delete Task</button>
		  <%
		    } else { // it is Update Task
		  %>
		      <!-- w3-camo-grey is a CSS rule in the colors.css -->
		      <button type="submit" class="w3-btn w3-camo-grey w3-third" onclick="return checkForm();">Update Task</button>
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

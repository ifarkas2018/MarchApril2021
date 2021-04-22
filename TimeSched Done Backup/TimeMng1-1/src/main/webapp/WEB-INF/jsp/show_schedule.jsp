<!-- project : Time Manager, author : Ingrid Farkas, 2020 -->
<!-- show_schedule.jsp shows the results of the request for listing the schedule for the certain EMPLOYEE on the certain DAY (show_sched_form.jsp) -->
<!-- show_schedule.jsp is included in show_sched_results.jsp -->

<!-- include the JSTL Core library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<body>
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
	    <br/>
	    <br/>
	    <br/>
	    <br/>
	  </div>
	        
	  <!-- "w3-twothird" class uses 66% of the parent container -->
	  <div class="w3-twothird w3-container"> 
	    <br />
	    <br />
	    <!-- form_background sets the background colour of the container -->
	    <div class="w3-container form_background w3-padding-32 w3-padding-large" id="show_sched_info"> 
	      <div class="w3-content w3-text" style="max-width:600px">
	        <h3 class="w3-center"><b>Show Schedule</b></h3>
	        <br />
	        <br /> 
	 		Employee Name: ${enter_f_name} ${enter_l_name} <!-- showing the employee name -->
	 		<br />
	 		Date of the Schedule: ${enter_date} <!-- showing the date of the schedule -->
	 					
			<form action="http://localhost:8080/show_sched" method="get"> 
	 		  <div>
				<br />
				<br />
				
				<!-- creating the table with the tasks -->
				<table class="w3-table w3-centered" id="table">
				  <!-- creating the table row with the headings -->
				  <tr>
				    <th>Task Id</th>
				    <th>Task Name</th>
				    <th>Start Time</th>
				    <th>End Time</th>
		       	  </tr>
		       	  <!-- for each element of the list empSchedTaskInfos, show the id of the task, the name of the task, the start time and the end time of the task -->
		       	  <!-- empSchedTaskInfos is the attribute from the MainController.java, which was added to the model -->
		       	  <c:forEach items="${empSchedTaskInfos}" var="schedinfo"> 
		       	   	<tr class="w3--cell-bottom">
		              <td>${schedinfo.taskId}</td> <!-- show the id of the task -->
			       	  <td>${schedinfo.taskName}</td>	<!-- show the name of the task -->
			       	  <td>${schedinfo.taskStartTime}</td> <!-- show the start time of the task -->
			       	  <td>${schedinfo.taskEndTime}</td> <!-- show the end time of the task -->  
			   		</tr>
		       	  </c:forEach> <!-- end of the forEach -->
		   		</table>		
		    	<br />
		    	<br />
		    	<div class="w3-container w3-center">
  				  <!-- add the button to the page -->
  				  <button class="w3-btn w3-tiny w3-padding-small w3-camo-grey">Show Schedule</button> 
				</div>
	    	  </div>
	    	</form>
	    	
		  </div> <!-- end of the class="w3-content w3-text" -->
		</div> <!-- end of the class="w3-container w3-light-grey w3-padding-32 w3-padding-large" -->
		<br />	 		
		<br />
	  </div> <!-- end of the class=""w3-twothird w3-container" -->	
	</div> <!-- end of the class=w3-row w3-margin" -->
  </div>
 </div> 
</body> 

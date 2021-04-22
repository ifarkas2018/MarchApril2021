<!-- project : Time Manager, author : Ingrid Farkas, 2020 -->
<!-- show_emp.jsp shows the results of the request for showing the information about the employee (Employee -> Show Employee) -->
<!-- show_emp_form.jsp is included in show_emp_info.jsp -->

<!--  include the JSTL Core library -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.timemng.sbjsp.model.EmpIDNameInfo1p1" %>

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
	    <br />
	    <br />
	    <br />
	    <br />
	  </div>
	        
	  <!-- "w3-twothird" class uses 66% of the parent container -->
	  <div class="w3-twothird w3-container"> 
	    <br />
	    <br />
	    <!-- form_background CSS rule which sets the color of the container (file styles1.css) -->
	    <div class="w3-container form_background w3-padding-32 w3-padding-large" id="show_sched_info"> 
	      <div class="w3-content w3-text" style="max-width:600px">
	        <h3 class="w3-center"><b>Show Employee</b></h3> 
	 		<br />
	 		<br />			
	 		<br />
			<form action="http://localhost:8080/show_sched" method="post"> <!-- when submitted the localhost:8080/home is shown -->		
			  <div>
				<!-- creating the table with the employees -->
				<table class="w3-table w3-centered" id="table">
			      <!-- creating the table row with the headings -->
				  <tr>
					<th>Employee ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Department</th>
		       	  </tr>
		       	  <% int i = 0; // counter which is used for retrieving the department %>
		       	  <!-- for each element of the list lstEmployee, show the id of the task, the name of the task, the start time and the end time of the task -->
		       	  <!-- lstEmployee is the attribute from the MainController.java, which was added to the model -->
		       	  <c:forEach items="${lstEmployee}" var="empinfo"> 
		       	    <tr class="w3--cell-bottom">
		              <td>${empinfo.employeeID}</td> <!-- show the id of the task -->
			       	  <td>${empinfo.firstName}</td>	<!-- show the name of the task -->
			       	  <td>${empinfo.lastName}</td> <!-- show the start time of the task -->
			       	  <%
			       		List<EmpIDNameInfo1p1> listEmp = (List)(request.getAttribute("lstEmployee"));
			       		String sDepartment = listEmp.get(i).getDepartment(); // get the department from the list
			       		if (sDepartment.equals("Administ")) // change the name of the department 
			       		  sDepartment = "Administration";
			       		i++;
			       	  %>
			       	  <td><%= sDepartment %></td> <!-- show the department -->  
			   		</tr>
		       	  </c:forEach> 
		   		</table>
		   		<br />
		        <br />		
		    	<br />
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
 </div> <!-- end of class="w3-content" -->
</body> 

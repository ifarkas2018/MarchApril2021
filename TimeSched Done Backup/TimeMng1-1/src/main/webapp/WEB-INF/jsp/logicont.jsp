<!-- author: Ingrid Farkas; project: Time Manager, 2020 -->

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
      <br />
	  <br /> 
    </div>

    <div class="w3-twothird w3-container">
      <br />
      <br />
      <!-- after clicking on the button localhost:8080/logform is called using method post -->
      <form action="/logform" name="login" id="login" method="post">
        <!-- form_background CSS rule which sets the color of the container (file styles1.css) -->
	    <div class="w3-container form_background w3-padding-32 w3-padding-large" id="log_info">
	      <div class="w3-content" style="max-width:600px">
		    <h3 class="w3-center"><b>Login</b></h3>
		    <br />
		    <br />
		    To log in, use the following accounts:
		    <ul>
		      <li>
		        <span>
		          <b>administrator </b> (<b><i>username:</i></b> admin, <b><i>password:</i></b> admin)
		        </span>
		      </li> 
		      <li>
		        <span>
		          <b>employee </b> (<b><i>username:</i></b> bbea, <b><i>password:</i></b> bea123)
		        </span>
		      </li>
		      <li>
		        <span>
		          <b>employee </b> (<b><i>username:</i></b><span class="red_text"> @@@@@@@@@@</span>, <b><i>password:</i></b><span class="red_text">@@@@@@@@@@@</span>)
		        </span>
		      </li>
		      <li>
		        <span>
		          <b>employee </b> (<b><i>username:</i></b><span class="red_text"> @@@@@@@@@@</span>, <b><i>password:</i></b><span class="red_text"> @@@@@@@@@@@</span>)
		        </span>
		      </li>
		    </ul>
		    <br/>
		    You can:
		    <ul>
		      <li>
		        <span>
		          add, update and delete entered tasks (meetings or events)
		        </span>
		      </li>
		      <li>
		        <span>
		          show and update schedules
		        </span>
		      </li>
		      <li>
		        <span>
		          add employee and show employee (administrator)
		        </span>
		      </li>
		    </ul> 
	      </div>
	      <br/>
	      <!-- the w3-third class uses 33% of the parent container -->
	      <button class="w3-btn w3-camo-grey w3-padding-small w3-third" name="next_btn" id="next_btn">Next</button>
	    </div> 
	  </form>    
	  <br />
	  <br />
    </div> <!-- end of class="w3-twothird w3-container" -->
   </div> <!-- end of class="w3-row w3-margin" --> 
  </div> 
</div> 

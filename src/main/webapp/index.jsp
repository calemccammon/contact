<%@ page import="java.sql.*" %>
<%@ page import="contact.ConnectionManager" %>
<%@ page import="contact.Query" %>

<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/style.css" />
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<title>Contact</title>
</head>
<body>
	<div class="topnav">
  		<div class="search-container">
    		<form action="/Query" method="post">
      			<input type="text" placeholder="Search..." name="search" id="search">
      			<button type="submit" name="jdbc_query" id="jdbc_query">Submit</button>
    		</form>
  		</div>
	</div>
	<div class="outer-div">
	<div class="inner-div">
	<form method="post" action="Insert">
		<div class="form-content">
		<div class="form-group row">
			<label for="inputFirstName" class="col-sm-2 col-form-label">First
				Name:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="inputFirstName"
					placeholder="First Name" name="inputFirstName" required>
			</div>
		</div>
		<div class="form-group row">
			<label for="inputLastName" class="col-sm-2 col-form-label">Last
				Name:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="inputLastName" name="inputLastName"
					placeholder="Last Name"required >
			</div>
		</div>
		<div class="form-group row">
			<label for="inputStreet" class="col-sm-2 col-form-label">Street:</label>
			<div class="col-sm-10">
				<input type="text" placeholder="Street" name="inputStreet" class="form-control" required>
			</div>
		</div>
		<div class="form-group row">
			<label for="inputCity" class="col-sm-2 col-form-label">City:</label>
			<div class="col-sm-10">
				<input type="text" placeholder="City" name="inputCity" class="form-control" required>
			</div>
		</div>	
		<div class="form-group row">
			<label for="inputState" class="col-sm-2 col-form-label">State:</label>
			<div class="col-sm-10">
					<select name="inputState" class="form-control" id="selectState" required>
						<option value="" disabled selected>State</option>
						<option value="AL">Alabama</option>
						<option value="AK">Alaska</option>
						<option value="AZ">Arizona</option>
						<option value="AR">Arkansas</option>
						<option value="CA">California</option>
						<option value="CO">Colorado</option>
						<option value="CT">Connecticut</option>
						<option value="DE">Delaware</option>
						<option value="DC">District Of Columbia</option>
						<option value="FL">Florida</option>
						<option value="GA">Georgia</option>
						<option value="HI">Hawaii</option>
						<option value="ID">Idaho</option>
						<option value="IL">Illinois</option>
						<option value="IN">Indiana</option>
						<option value="IA">Iowa</option>
						<option value="KS">Kansas</option>
						<option value="KY">Kentucky</option>
						<option value="LA">Louisiana</option>
						<option value="ME">Maine</option>
						<option value="MD">Maryland</option>
						<option value="MA">Massachusetts</option>
						<option value="MI">Michigan</option>
						<option value="MN">Minnesota</option>
						<option value="MS">Mississippi</option>
						<option value="MO">Missouri</option>
						<option value="MT">Montana</option>
						<option value="NE">Nebraska</option>
						<option value="NV">Nevada</option>
						<option value="NH">New Hampshire</option>
						<option value="NJ">New Jersey</option>
						<option value="NM">New Mexico</option>
						<option value="NY">New York</option>
						<option value="NC">North Carolina</option>
						<option value="ND">North Dakota</option>
						<option value="OH">Ohio</option>
						<option value="OK">Oklahoma</option>
						<option value="OR">Oregon</option>
						<option value="PA">Pennsylvania</option>
						<option value="RI">Rhode Island</option>
						<option value="SC">South Carolina</option>
						<option value="SD">South Dakota</option>
						<option value="TN">Tennessee</option>
						<option value="TX">Texas</option>
						<option value="UT">Utah</option>
						<option value="VT">Vermont</option>
						<option value="VA">Virginia</option>
						<option value="WA">Washington</option>
						<option value="WV">West Virginia</option>
						<option value="WI">Wisconsin</option>
						<option value="WY">Wyoming</option>
					</select>
			</div>
		</div>
		<div class="form-group row">
			<label for="inputZip" class="col-sm-2 col-form-label">Zip Code:</label>
			<div class="col-sm-10">
				<input type="text" placeholder="Zip Code" name="inputZip" class="form-control" required>
			</div>
		</div>
		<div class="form-group row">
			<label for="inputPhone" class="col-sm-2 col-form-label">Phone Number:</label>
			<div class="col-sm-10">
				<input type="tel" placeholder="Phone Number" name="inputPhone" class="form-control" required>
			</div>
		</div>
		<div class="form-group row">
			<label for="inputEmail" class="col-sm-2 col-form-label">Email:</label>
			<div class="col-sm-10">
				<input type="email" placeholder="Email Address" name="inputEmail" class="form-control" required>
			</div>
		</div>	
		<div class="form-group row">
			<label for="jdbc_insert" class="col-sm-2 control-label sr-only">Insert:</label>
			<div class="col-sm-10">
				<button type="submit" name="jdbc_insert" id="jdbc_insert" class="btn btn-primary">Submit</button>
			</div>
		</div>
		</div>
	</form>
	<input type="hidden" id="myurl" name="myurl"/>
	<div class="modal fade" id="modalSuccess" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Contact submitted!</h4>
      			</div>
    		</div>
  		</div>
	</div>
	<div class="modal fade" id="modalNoRecords" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">No records found!</h4>
      			</div>
    		</div>
  		</div>
	</div>
	</div>
	</div>
	<script>
		$(document).ready(function() {
			if(window.location.href.indexOf('#modalNoRecords') != -1) {
		    $('#modalNoRecords').modal('show');
		  }
		});
	</script>
	<script>
		$(document).ready(function() {
			if(window.location.href.indexOf('#modalSuccess') != -1) {
		    $('#modalSuccess').modal('show');
		  }
		});
	</script>
	<script>
		$("#modalSuccess").on("hidden.bs.modal", function () {
	    	window.location = document.getElementById('myurl').value;
		});
	</script>
	<script>
		$(".modal").on("hidden.bs.modal", function () {
   			window.location = "index.jsp";
		});
	</script>
	<script>
		document.getElementById('myurl').value = window.location.href
	</script>
	<script>
    jQuery(function($) {
        /*function for placeholder select*/
        function selectPlaceholder(selectID){
          var selected = $(selectID + ' option:selected');
          var val = selected.val();
          $(selectID + ' option' ).css('color', '#333');
          selected.css('color', '#999');
          if (val == "") {
            $(selectID).css('color', '#999');
          };
          $(selectID).change(function(){
            var val = $(selectID + ' option:selected' ).val();
            if (val == "") {
              $(selectID).css('color', '#999');
            }else{
              $(selectID).css('color', '#333');
            };
          });
        };
        selectPlaceholder('#selectState');
      });
	</script>
</body>
</html>

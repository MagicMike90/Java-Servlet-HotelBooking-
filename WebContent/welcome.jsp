<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id = "booking" class = "beans.BookingBean" scope = "session"/>
<jsp:useBean id = "searchResult" class = "beans.SearchResultBean" scope = "session"/>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to the search page</title>
<link rel = "stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel = "stylesheet" type="text/css" href="bootstrap/css/custom.css">
</head>
<body>
<div class = "container">
<!-- Navigation -->
		<jsp:include page="header.jsp" />
	
<!--Main content-->
	<form action = 'control' method = 'POST'>
	<div class="form-group">
	<div class="row">
		<div class="col-md-2"><label for="checkInDate">Check in:</label></div>
		<div class="col-md-2"><label for="checkOutDate">Check out:</label></div>
	</div>
	<div class="row">
	  	<div class="col-md-2"><input class="form-control" type="date" name = "checkInDate" id="checkInDate" placeholder="dd.mm.yyyy"></div>
	  	<div class="col-md-2"><input class="form-control" type="date" name = "checkOutDate" id="checkOutDate" placeholder="dd.mm.yyyy"></div>
	</div>
	</div>
	<div class="form-group">
	<div class="row">
		<div class="col-md-2">
		<label for="selectCity">Select city</label>
		<select name="city" class = "form-control" id = "selectCity">
			<option value="1">Sydney</option>
			<option value="2">Brisbane</option>
			<option value="3">Melbourne</option>
			<option value="4">Adelaide</option>
			<option value="5">Hobart</option>
		</select>
		</div>
		<div class="col-md-2">
		<label for="numRooms">Number of rooms</label>
		<select name = "numRooms" class="form-control" id = "numRooms">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
			<option>6</option>
			<option>7</option>
			<option>8</option>
			<option>9+</option>
		</select>
		</div>
		<div class="col-md-4">
			<label for="maxPrice">Maximum price per room per night</label>
			<input class="form-control" name = "maxPrice" type="text" id="maxPrice" placeholder="Max price"/>
		</div>
	</div>
	</div>
	<div class="form-group">
		<input class= "btn btn-primary" type='submit' value='Search'/>
		<input type="hidden" name="action" value="search">
	</div>
	</form>
	<h4><span class="label label-warning">${deleteMessage}</span></h4>
</div>
</body>
</html>

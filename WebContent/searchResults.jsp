<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id = "booking" class = "beans.BookingBean" scope = "session"/>
<jsp:useBean id="searchResult" class = "beans.SearchResultBean" scope = "session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to the search page</title>
<link rel = "stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel = "stylesheet" type="text/css" href="bootstrap/css/custom.css">
<link rel = "stylesheet" href="bootstrap/css/bootstrap-switch.css"/>
<script src="bootstrap/js/jquery-1.11.0.js"></script>
<script src="bootstrap/js/bootstrap-switch.js"></script>

</head>
<body>
<div class = "container">
<!-- Navigation -->
	<jsp:include page="header.jsp" />
	<h3>Room ${booking.currentRoomNumber}</h3>
	<table class = "table table-hover">
	<thead>
	<tr>
		<th>Room type</th>
		<th>Description</th>
		<th>Extra bed</th>
		<th>Price</th>
		<th>Select</th>
	</tr>
	<thead>
	<tbody>
		<c:forEach var="entry" items="${searchResult.roomTypes}">
			<tr>
				
				<td>${entry.name}</td>
				<td>${entry.description}</td>
				<td><input type="checkbox" name="extraBed" value="true"/>
					<!--  
					<script>
  						$('input:checkbox').bootstrapSwitch();
					</script>
					-->
				</td>
				<td>${entry.price}</td>
				<td>
					<form action = "control" method="post">
						<input class= "btn btn-primary" type='submit' value='Select'/> 
						<input type="hidden" name="action" value="addToBooking">
						<input type="hidden" name="roomId" value="${entry.id}">
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	<div class="progress progress-striped">
		<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="${booking.percentage}" aria-valuemin="0" aria-valuemax="100" style="width: ${booking.percentage}%">
    		<span class="sr-only">${booking.percentage}% Complete (success)</span>
    		${booking.currentRoomNumber}/${booking.numRooms}
  		</div>
	</div>
	<c:if test="${(booking.currentRoomNumber) > booking.minRoom}">
	<div class="row">
		<div class="col-md-2" style="width:90px;">
			<form action = "control" method = "post" class="form-inline">
				<div class = "form-group">
					<input class= "btn btn-primary" type='submit' value='Go back'/> 
					<input type="hidden" name="action" value="goBack">
				</div>
			</form>
		</div>
	</div>
	</c:if>
</div>
</body>
</html>

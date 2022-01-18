<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  Bootstrap CSS -->
<link rel ="stylesheet" href="http://stackpath.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css"
	integrity="sha384-PDle/QlgIONtM1aqA2Qemk5gPOE7wFq8+Em+G/hmo5Iq0CCmYZLv3fVRDJ4MMwEA" crossorigin="anonymous">

</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-1" style="color:Tomato;">TazzBank </div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1">Witaj ${loggedUser.getUsername()}<br><a href="/logout">Logout</a></div>
	</div>
</div>
<br>
<br>
<br>
<br>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-2"></div>
		<div class="col-sm-2" style="color:DarkOrange;"><button><strong><a href="http://localhost:8080/bank2">Przelewy</a></strong></button></div>
		<div class="col-sm-2"></div>
		<div class="col-sm-2"></div>
	</div>
</div>
<br>
<br>
<br>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-2"></div>
		<div class="col-sm-2" style="color:DarkOrange;"><strong>MAM ${loggedUser.getAccountValue()}PLN na kontach</strong></div>
		<div class="col-sm-2"></div>
		<div class="col-sm-2"></div>
	</div>
</div>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-2"></div>
		<div class="col-sm-2" style="color:DarkOrange;">
			<strong>Dodaj srodki do konta</strong><br>
			<div style="margin: auto; width: 500px; padding-top:50px">
				<form:form method="post" modelAttribute="user">
				<div class="form-group">
					<label>Kwota:</label>
					<form:input path="accountValue" placeholder="0.00 PLN" type="number" step="0.01" min="0" class="form-control"/>
					<form:errors path="accountValue" style="color:red" class="form-number number-muted"/>
				</div>
			<form:button type="submit" class="btn btn-success">Zatwierdz</form:button>
	</form:form>
</div>
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-2"></div>
	</div>
</div>
</body>
</html>
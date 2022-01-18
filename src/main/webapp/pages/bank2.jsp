<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
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
		<div class="col-sm-1" style="color:Tomato;">TazzBank <br>Przelewy</div>
		<div class="col-sm-1"></div>
		<div class="col-sm-2">Aktualna kwota na koncie: ${loggedUser.getAccountValue()}PLN</div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1"></div>
		<div class="col-sm-1">${loggedUser.getUsername()}<br><a href="/logout">Logout</a></div>
	</div>
</div>
<br>
<br>
<br>
<br>
<div class="container-fluid">
	<div class="row">
		<div class="col-2"></div>
		<div class="col-2"></div>
		<div class="col-2">
			<div style="margin: auto; width: 500px; padding-top:50px">
	<form:form method="post" modelAttribute="transaction">
			<div class="form-group">
				<label>Numer rachunku:</label>
				<form:input path="recipientAccountNumber" placeholder="Nr. konta..." type="text" class="form-control"/>
				<form:errors path="recipientAccountNumber" style="color:red" class="form-text text-muted"/>
				<form:errors path="recipientUser" style="color:red" class="form-text text-muted"/>
			</div>
		
		<div class="form-group">
			<label>Nazwa i adres odbiorcy:</label>
			<form:input path="recipientNameAndAddress" placeholder="Text..." type="text" class="form-control"/>
			<form:errors path="recipientNameAndAddress" style="color:red" class="form-text text-muted" />
		</div>
		<div class="form-group">
			<label>Kwota:</label>
			<form:input path="amountTransaction" placeholder="Kwota PLN" type="number" step="0.01" class="form-control"/>
			<form:errors path="amountTransaction" style="color:red" class="form-number number-muted" />
		</div>
		<div class="form-group">
			<label>Tytul:</label>
			<form:input path="title" placeholder="Tytul" type="text" class="form-control"/>
			<form:errors path="title" style="color:red" class="form-text text-muted" />
		</div>
		<form:button type="submit" class="btn btn-success">Zatwierdz</form:button>
	</form:form>
	<a href="http://localhost:8080/bank">Powrot</a>
</div>
		</div>
		<div class="col-2"></div>
		<div class="col-2"></div>
		<div class="col-2"></div>
	</div>
</div>
</body>
</html>
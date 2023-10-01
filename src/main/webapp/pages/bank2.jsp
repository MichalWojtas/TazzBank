<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/headerButtonDropdownMenu.css">
<link rel="stylesheet" href="css/darkMode.css">
<link rel="stylesheet" href="css/circleButtons.css">
<link rel="stylesheet" href="css/bottomFixedDiv.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
    html, body {
        height: 100%;
        overflow: auto;
    }
    .dd1{
         width:48px;
         height:48px;
         border:2px solid #ddd;
         background-color:#000;
         border-radius:15px;
    }
    .dd1:hover{
         filter:drop-shadow(0px 0px 12px white);
    }
</style>
</head>
<body>
<jsp:include page="headerTemplate.jsp" />
<div class = "container-fluid">
    <div class ="row">
        <div class = "col-lg-12" id="welcomeText">${welcomeText}, ${loggedUser.getUsername()}</div>
    </div>
</div>
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
		<div class="col-sm-1">${loggedUser.getUsername()}</div>
	</div>
</div>
<br>
<br>
<br>
<br>
<div class="container-fluid" style="height: calc(100vh - 100px);">
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
<jsp:include page="bottomFixedDivTemplate.jsp" />
<script src="js/darkMode.js"></script>
<script
  src="https://code.jquery.com/jquery-3.7.1.slim.min.js"
  integrity="sha256-kmHvs0B+OpCW5GVHUNjv9rOmY0IvSIRcf7zGUDTDQM8="
  crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
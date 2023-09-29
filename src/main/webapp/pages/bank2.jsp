<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
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
<div class="col-sm-12 text-white p-4 text-center border-top border border-secondary border border-5 border-bottom-0 border-start-0 border-end-0" style="margin-top:10px;bottom: 0;background-color:black;position:sticky;">
    <div class="bottomFixed-button-wrapper" style="margin-bottom:0;">
    <button class="bottomFixedDivButton" type="button"><a href="http://localhost:8080/bank" style="text-decoration:none;color:white;">
        <svg xmlns="http://www.w3.org/2000/svg" width="38" height="38" fill="red" class="bi bi-house" viewBox="0 0 16 16">
          <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5 5 5Z"/>
        </svg>
        <div style="text-align:center;font-size:12px;">Start</div></a>
    </button>
    </div>
    <div class="bottomFixed-button-wrapper" style="margin-bottom:0;">
    <button class="bottomFixedDivButton" type="button" >
        <svg xmlns="http://www.w3.org/2000/svg" width="38" height="38" fill="red" class="bi bi-clock" viewBox="0 0 16 16">
          <path d="M8 3.5a.5.5 0 0 0-1 0V9a.5.5 0 0 0 .252.434l3.5 2a.5.5 0 0 0 .496-.868L8 8.71V3.5z"/>
          <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm7-8A7 7 0 1 1 1 8a7 7 0 0 1 14 0z"/>
        </svg>
        <div style="text-align:center;font-size:12px;">History</div>
    </button>
    </div>
    <div class="bottomFixed-button-wrapper" style="margin-bottom:0;border-right:0;">
    <button class="bottomFixedDivButton" type="button" ><a href="http://localhost:8080/bank2" style="text-decoration:none;color:white;">
        <svg xmlns="http://www.w3.org/2000/svg" width="38" height="38" fill="red" class="bi bi-arrow-left-right" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M1 11.5a.5.5 0 0 0 .5.5h11.793l-3.147 3.146a.5.5 0 0 0 .708.708l4-4a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 11H1.5a.5.5 0 0 0-.5.5zm14-7a.5.5 0 0 1-.5.5H2.707l3.147 3.146a.5.5 0 1 1-.708.708l-4-4a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 4H14.5a.5.5 0 0 1 .5.5z"/>
        </svg>
        <div style="text-align:center;font-size:12px;">Transaction</div>
    </button>
    </div>
</div>

<script src="js/darkMode.js"></script>
<script
  src="https://code.jquery.com/jquery-3.7.1.slim.min.js"
  integrity="sha256-kmHvs0B+OpCW5GVHUNjv9rOmY0IvSIRcf7zGUDTDQM8="
  crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
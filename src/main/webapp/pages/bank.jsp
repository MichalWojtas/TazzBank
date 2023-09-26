<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
    #dd1{
        width:48px;
        height:48px;
        border:2px solid #ddd;
        background-color:#000;
        border-radius:15px;
    }
    #dd1:hover{
        filter:drop-shadow(0px 0px 12px white);
    }
</style>
</head>
<body>
<div class = "container-fluid border-bottom border border-secondary border border-5 border-top-0 border-start-0 border-end-0">
    <div class = "row">
    <!-- Second option for color text is same as logo text color:#891212; or first white color:#fff;-->
        <div class="col-lg-8" style="background-color:#000;color:#fff;font-family:DejaVu Serif;font-weight:bold;font-size:36px;margin:auto;padding:1rem;"><img class="img-fluid" style="border:3px bold #fff;border-radius:20%;margin-right:3%;" src="<c:url value="/resources/img/Logo.png"/>"></>My TBank</div>
        <div class="col-lg-1" style="background-color:#000;"></div><div class="col-lg-1" style="background-color:#000;"></div><div class="col-lg-1" style="background-color:#000;"></div>
        <div class="col-lg-1 d-flex align-items-center dropdown" style="background-color:#000;"><button type="button" data-bs-toggle="dropdown" id="dd1"><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="gray" class="bi bi-list" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/></svg></button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">My Account</a></li>
                <li><a class="dropdown-item" href="#">Settings</a></li>
                <li><a class="dropdown-item" href="/logout">Logout</a></li>
              </ul>
        </div>
    </div>
</div>
<div class = "container-fluid">
    <div class ="row">
        <div class = "col-lg-8"></div><div class = "col-lg-1"></div><div class = "col-lg-1"></div><div class = "col-lg-1"></div>
        <div class = "col-lg-1" style="color:Tomato; background-color:#171717;">TazzBank</div>
        <div class = "col-lg-8"></div><div class = "col-lg-1"></div><div class = "col-lg-1"></div><div class = "col-lg-1"></div>
        <div class = "col-lg-1">Witaj ${loggedUser.getUsername()}</div>
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
<script
  src="https://code.jquery.com/jquery-3.7.1.slim.min.js"
  integrity="sha256-kmHvs0B+OpCW5GVHUNjv9rOmY0IvSIRcf7zGUDTDQM8="
  crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
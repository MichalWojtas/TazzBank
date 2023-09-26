<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!--  Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
    [class*="col"]{
    margin:auto;
    padding:1rem;
    background-color:#33b5e5;
    border: 2px solid #fff;
    color: #fff;
    }

</style>
</head>
<body>
<div class = "container-fluid border-bottom border border-secondary border border-5 border-top-0 border-start-0 border-end-0">
    <div class = "row">
    <!-- Second option for color text is same as logo text color:#891212; or first white color:#fff;-->
        <div class="col-lg-12" style="background-color:#000;color:#fff;font-family:DejaVu Serif;font-weight:bold;font-size:36px;margin:0;border:0;"><img class="img-fluid" style="border:3px bold #fff;border-radius:20%;margin-right:3%;" src="<c:url value="/resources/img/Logo.png"/>"></>My TBank</div>
    </div>
</div>
<div class="container-fluid" style="margin-top:35px;">
	<div class="row">
	    <div class="col-lg-8" onclick="location.href='http://localhost:8080/login';"style="cursor:pointer;text-align:center;font-weight:bold;">Login</div>
	    <div class="col-lg-8" onclick="location.href='http://localhost:8080/registration';" style="cursor:pointer;text-align:center;font-weight:bold;">Create new account</div>
	</div>
</div>
</body>
</html>
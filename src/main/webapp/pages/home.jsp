<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!--  Bootstrap CSS -->
<link rel ="stylesheet" href="http://stackpath.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css"
	integrity="sha384-PDle/QlgIONtM1aqA2Qemk5gPOE7wFq8+Em+G/hmo5Iq0CCmYZLv3fVRDJ4MMwEA" crossorigin="anonymous">
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
<div class="container-fluid">
	<div class="row">
	    <div class="col-lg-8" onclick="location.href='http://localhost:8080/login';"style="cursor:pointer;text-align:center;">Login</div>
	    <div class="col-lg-8" onclick="location.href='http://localhost:8080/registration';" style="cursor:pointer;text-align:center;">Register</div>
	</div>
</div>
<br>
</body>
</html>
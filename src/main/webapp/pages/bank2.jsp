<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/headerButtonDropdownMenu.css">
<link rel="stylesheet" href="css/darkMode.css">
<link rel="stylesheet" href="css/bottomFixedDiv.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
    .form-group{
        color:black;
    }
</style>
</head>
<body>
<jsp:include page="headerTemplate.jsp" />
<div class = "container-fluid" style="margin-bottom:20px;">
    <div class ="row">
        <div class = "col-lg-12" id="welcomeText">${welcomeText}, ${loggedUser.getUsername()}</div>
    </div>
</div>
<div class="container-fluid" style="margin-bottom:70px;padding-left:53px;">
	<div class="row">
		<div class="col-lg-12" style="font-size:36px;font-weight:bold;color:black;">Transaction</div>
		<div class="col-lg-12" style="font-size:28px;color:black;">Amount on your accounts: ${loggedUser.getAccountValue()}PLN</div>
	</div>
</div>
<div class="container-fluid" id="transactionFormBank2" style="margin-bottom:50px;padding-left:80px;padding-right:80px;">
	<div class="row">
	    <div class="col-lg-12">
	        <form:form method="post" modelAttribute="transaction">
			    <div class="form-group" style="margin-top:70px;margin-left:10px;">
				    <label>Account number:</label>
				    <form:input path="recipientAccountNumber" placeholder="Account number..." type="text" style="border:1px solid black;margin-bottom:5px;margin-top:5px;" class="form-control"/>
				    <form:errors path="recipientAccountNumber" style="color:red" class="form-text text-muted"/>
				    <form:errors path="recipientUser" style="color:red" class="form-text text-muted"/>
			    </div>
		        <div class="form-group" style="margin-top:20px;margin-left:10px;">
			        <label>Name and address of consignee:</label>
			        <form:input path="recipientNameAndAddress" placeholder="Text..." type="text" style="border:1px solid black;margin-bottom:5px;margin-top:5px;" class="form-control"/>
			        <form:errors path="recipientNameAndAddress" style="color:red" class="form-text text-muted" />
		        </div>
		        <div class="form-group" style="margin-top:20px;margin-left:10px;">
			        <label>Amount:</label>
			        <form:input path="amountTransaction" placeholder="Amount PLN" type="number" step="0.01" style="border:1px solid black;margin-bottom:5px;margin-top:5px;" class="form-control"/>
			        <form:errors path="amountTransaction" style="color:red" class="form-number number-muted" />
		        </div>
		        <div class="form-group" style="margin-top:20px;margin-left:10px;">
			        <label>Title:</label>
			        <form:input path="title" placeholder="Title" type="text" style="border:1px solid black;margin-bottom:5px;margin-top:5px;" class="form-control"/>
			        <form:errors path="title" style="color:red" class="form-text text-muted" />
		        </div>
		        <form:button type="submit" class="btn btn-success" style="margin-bottom:70px;border:1px solid black;margin-top:15px;margin-left:10px;">Confirm</form:button>
	        </form:form>
        </div>
    </div>
</div>
<jsp:include page="footerTemplate.jsp" />
<jsp:include page="bottomFixedDivTemplate.jsp" />
<script src="js/darkMode.js"></script>
</body>
</html>
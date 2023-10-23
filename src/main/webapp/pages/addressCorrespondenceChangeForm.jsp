<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!--  Bootstrap CSS -->
<link rel="stylesheet" href="../css/headerButtonDropdownMenu.css">
<link rel="stylesheet" href="../css/darkMode.css">
<link rel="stylesheet" href="../css/bottomFixedDiv.css">
<link rel="stylesheet" href="../css/separateForm.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
</style>
</head>
<body>
<jsp:include page="headerTemplate.jsp" />
<div class = "container-fluid" style="margin-bottom:20px;">
    <div class ="row">
        <div class = "col-lg-12" id="welcomeText">${welcomeText}, ${loggedUser.getUsername()}</div>
    </div>
</div>
<div class="container-fluid">
<div class="mainSeparateForm" style="margin-bottom:50px;padding-left:80px;padding-right:80px;">
    <div class="row">
        <div class="col-lg-12">
                <form:form method="post" modelAttribute="user">
                <div class="col-lg-12" style="font-size:36px;font-weight:bold;color:black;margin-top:50px;text-align:center;">Change address for correspondence</div>
                <div class="form-group" style="margin-top:50px;margin-left:10px;">
                    <label>Actual address for correspondence: ${loggedUser.getAddressForCorrespondence()}</label>
                </div>
                <div class="form-group" style="margin-top:50px;margin-left:10px;">
                    <label>New address for correspondence</label>
                    <form:input path="addressForCorrespondence" placeholder="Correspondence address" type="text" class="form-control" style="border:1px solid black;margin-bottom:5px;margin-top:5px;"/>
                    <form:errors path="addressForCorrespondence" style="color:red;" class="form-text text-muted"/>
                </div>
                <div class="form-group" style="margin-top:20px;margin-left:10px;">
                    <label>Confirm by password:</label>
                    <form:input path="confirmPassword" placeholder="" type="password" class="form-control" style="border:1px solid black;margin-bottom:5px;margin-top:5px;"/>
                    <form:errors path="confirmPassword" style="color:red;" class="form-text text-muted"/>
                </div>
                <div class="form-group" style="margin-top:40px;margin-left:10px;margin-bottom:70px;padding-left:15px;padding-right:15px;">
                    <form:button type="submit" class="btn btn-success" style="width:46%;border:1px solid black;">Confirm</form:button>
                    <a href="/bank" style="text-decoration:none;"><button type="button" class="btn btn-danger" style="width:46%;border:1px solid black;margin-left:7%;">Cancel</button></a>
                </div>
                </form:form>
        </div>
    </div>
</div>
</div>




<jsp:include page="footerTemplate.jsp" />
<jsp:include page="bottomFixedDivTemplate.jsp" />
<script src="../js/darkMode.js"></script>
<script src="../js/menuPreventingDisappearance.js"></script>
</body>
</html>
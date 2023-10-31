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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
</style>
</head>
<body>
<jsp:include page="headerTemplate.jsp" />
<div class = "container-fluid" style="margin-bottom:20px;">
    <div class ="row">
        <div class = "col-lg-12" id="welcomeText">${welcomeText}, ${loggedUser.getFirstName()}</div>
    </div>
</div>




<jsp:include page="footerTemplate.jsp" />
<jsp:include page="bottomFixedDivTemplate.jsp" />
<script src="../js/darkMode.js"></script>
<script src="../js/menuPreventingDisappearance.js"></script>
</body>
</html>
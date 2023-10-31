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
        <div class = "col-lg-12" id="welcomeText">${welcomeText}, ${loggedUser.getFirstName()}</div>
    </div>
</div>
<div class="container-fluid">
<div class="mainSeparateForm">
    <div class="row">
        <div class="col-lg-12">
                <form:form method="post" modelAttribute="user">
                <div class="col-lg-12 headerFormTemplate">Change address for correspondence</div>
                <div class="form-group formGroupTopTemplate">
                    <label class="labelFormTemplate">Actual address for correspondence: ${loggedUser.getAddressForCorrespondence()}</label>
                </div>
                <div class="form-group formGroupTopTemplate">
                    <label class="labelFormTemplate">New address for correspondence</label>
                    <form:input path="addressForCorrespondence" placeholder="Correspondence address" type="text" class="form-control inputControlFormTemplate"/>
                    <form:errors path="addressForCorrespondence" class="form-text errorTextFormTemplate"/>
                </div>
                <div class="form-group formGroupMidTemplate">
                    <label class="labelFormTemplate">Confirm by password:</label>
                    <form:input path="confirmPassword" placeholder="" type="password" class="form-control inputControlFormTemplate"/>
                    <form:errors path="confirmPassword" class="form-text errorTextFormTemplate"/>
                </div>
                <div class="form-group formGroupBotTemplate">
                    <form:button type="submit" class="btn btn-success buttonSubmitFormTemplate">Confirm</form:button>
                    <a href="/bank" class="buttonCancelLinkFormTemplate"><button type="button" class="btn btn-danger buttonCancelFormTemplate">Cancel</button></a>
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
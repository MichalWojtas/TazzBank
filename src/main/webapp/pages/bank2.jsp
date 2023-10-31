<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/headerButtonDropdownMenu.css">
<link rel="stylesheet" href="css/darkMode.css">
<link rel="stylesheet" href="css/bottomFixedDiv.css">
<link rel="stylesheet" href="../css/separateForm.css">
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
        <div class = "col-lg-12" id="welcomeText">${welcomeText}, ${loggedUser.getFirstName()}</div>
    </div>
</div>
<div class="container-fluid" style="margin-bottom:70px;padding-left:53px;">
	<div class="row">
		<div class="col-lg-12"><div class="transactionAndAmountOnAccounts" style="font-size:36px;font-weight:bold;">Transaction</div></div>
		<div class="col-lg-12"><div class="transactionAndAmountOnAccounts" style="font-size:28px;">Amount on your accounts: ${loggedUser.getAllAccountsValue()}PLN</div></div>
	</div>
</div>
<div class="container-fluid">
<div class="mainSeparateForm">
	<div class="row">
	    <div class="col-lg-12">
	        <form:form method="post" modelAttribute="transaction">
                <div class="form-group formGroupTopTemplate" style="margin-top:70px;">
                    <fieldset>
                        <label class="labelFormTemplate">Choose account</label>
                        <div class="container" style="width:100%;">
                            <div class="row">
                                <c:forEach items="${loggedUserSortedList}" var="accountItem" varStatus="loop">
                                    <div class="col-lg-6 wrapperRadioForLabelFormTemplate">
                                        <div class="innerWrapperRadioForLabelFormTemplate">
                                            <input type="radio" class="btn-check chooseAccountWrapper" name="selectedAccount" id="accountTypeOption${loop.index}" autocomplete="off" value="${accountItem.getAccountBankId()}" <c:if test="${loop.index == 0}">checked</c:if>/>
                                            <label class="chooseAccountWrapper" for="accountTypeOption${loop.index}">
                                                <label class="textLabelInsideChooseAccountWrapper"for="accountTypeOption${loop.index}">${accountItem.getAccountName()}</label>
                                                <label class="textLabelInsideChooseAccountWrapper"for="accountTypeOption${loop.index}">Value: ${accountItem.getAccountValue()} PLN</label>
                                                <label class="textLabelInsideChooseAccountWrapper"for="accountTypeOption${loop.index}">Account number:<br>${accountItem.getAccountNumber()}</label>
                                            </label>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </fieldset>
                </div>
			    <div class="form-group formGroupMidTemplate">
				    <label class="labelFormTemplate">Account number:</label>
				    <form:input path="recipientAccountNumber" placeholder="Account number..." type="text" class="form-control inputControlFormTemplate"/>
				    <form:errors path="recipientAccountNumber" style="color:red" class="form-text"/>
				    <form:errors path="recipientUser" class="form-text errorTextFormTemplate"/>
			    </div>
		        <div class="form-group formGroupMidTemplate">
			        <label class="labelFormTemplate">Name and address of consignee:</label>
			        <form:input path="recipientNameAndAddress" placeholder="Text..." type="text" class="form-control inputControlFormTemplate"/>
			        <form:errors path="recipientNameAndAddress" class="form-text errorTextFormTemplate" />
		        </div>
		        <div class="form-group formGroupMidTemplate">
			        <label class="labelFormTemplate">Amount:</label>
			        <form:input path="amountTransaction" placeholder="Amount PLN" type="number" step="0.01" class="form-control inputControlFormTemplate"/>
			        <form:errors path="amountTransaction" class="form-number errorTextFormTemplate" />
		        </div>
		        <div class="form-group formGroupMidTemplate">
			        <label class="labelFormTemplate">Title:</label>
			        <form:input path="title" placeholder="Title" type="text" class="form-control inputControlFormTemplate"/>
			        <form:errors path="title" class="form-text errorTextFormTemplate" />
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
<script src="js/darkMode.js"></script>
</body>
</html>
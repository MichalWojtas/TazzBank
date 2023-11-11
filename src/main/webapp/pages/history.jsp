<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
.accordion-button[aria-expanded="true"]:hover {
    color: #fff !important;
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
<div class="container-fluid">
    <div class="mainSeparateForm" style="padding-bottom:70px;">
        <div class="row">
            <div class="col-lg-12">
                <div class="col-lg-12 headerFormTemplate">History</div>
                <div class="form-group formGroupTopTemplate" style="margin-left:0;">
                    <div class="labelFormTemplate" style="text-align:right;">
                        <div class="btn-group" style="margin-bottom:7px;">
                            <button class="btn btn-primary accordion-button" style="background-color: #007BFF;padding-left:10px;padding-right:10px;" type="button" data-bs-toggle="collapse" data-bs-target="#sortFormDropdown" aria-expanded="false">Show Sort:</button>
                        </div>
                        <div class="accordion-collapse collapse col-lg-12" style="" id="sortFormDropdown">
                            <form:form class="col-lg-12" id="sortForm" method="post" modelAttribute="history">
                                <label>Show:</label>
                                <div class="mb-3 form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="itemGroupToShow" id="itemGroupToShowAll" value="ALL" checked>
                                  <label class="form-check-label" for="itemGroupToShowAll">
                                    All
                                  </label>
                                </div>
                                <div class="mb-3 form-check form-check-inline">
                                  <input class="form-check-input" type="radio" name="itemGroupToShow" id="itemGroupToShowTransactions" value="TRANSACTION">
                                  <label class="form-check-label" for="itemGroupToShowTransactions">
                                    Transactions
                                  </label>
                                </div>
                                <div class="mb-3 form-check form-check-inline">
                                  <input class="form-check-input" type="radio" name="itemGroupToShow" id="itemGroupToShowDeposits" value="DEPOSIT">
                                  <label class="form-check-label" for="itemGroupToShowDeposits">
                                    Deposits
                                  </label>
                                </div>
                                <div class="mb-3 form-check form-check-inline" style="clear:both;">
                                  <input class="form-check-input" type="radio" name="itemGroupToShow" id="itemGroupToShowPayouts" value="PAYOUT">
                                  <label class="form-check-label" for="itemGroupToShowPayouts">
                                    Payouts
                                  </label>
                                </div>
                                <label style="display:block;">Sort by:</label>
                                <select class="form-select form-select-inline" aria-label="Sort by" id="sortBy" name="sortBy">
                                  <option selected value="DATE">Date</option>
                                  <option value="VALUE">Value</option>
                                </select>
                                <select class="form-select form-select-inline" style="clear:both;" aria-label="Sort by" id="sortByAscDsc" name="sortByAscDsc">
                                    <option selected value="DESC">Descending</option>
                                    <option value="ASC">Ascending</option>
                                </select>
                                <select class="form-select" style="margin-top:10px;" aria-label="Sort by" id="dataRange" name="dataRange">
                                    <option value="ALL">All dates</option>
                                    <option value="THISMONTH">From first day this month</option>
                                    <option value="PREMONTH">Previous month</option>
                                    <option selected value="NINETYDAYS">Last 90 days</option>
                                    <option value="SIXMONTHS">Last 6 month</option>
                                    <option value="THISYEAR">This year</option>
                                </select>
                                <label style="margin-top:10px;display:block;">Min value(0 means no check)</label>
                                <form:input path="minValueString" type="text" class="form-control inputControlFormTemplate"/>
                                <form:errors path="minValueString" class="form-text errorTextFormTemplate" />
                                <label style="margin-top:10px;display:block;">Max value(0 means no check)</label>
                                <form:input path="maxValueString" type="text" class="form-control inputControlFormTemplate"/>
                                <form:errors path="maxValueString" class="form-text errorTextFormTemplate" />
                                <form:button type="submit" class="btn btn-success buttonSubmitFormTemplate" style="margin-top:10px;width:100%;">Search</form:button>
                            </form:form>

                        </div>
                    </div>
                </div>
                <fieldset>
                    <div class="container" style="width:100%;">
                        <div class="row">
                            <c:set var="checkIsAnyOfCreatedDivs" value="false" />
                            <c:if test="${history.getItemGroupToShow().toString().equals('ALL') || history.getItemGroupToShow().toString() == null}">
                                <c:forEach items="${sortedAllList}" var="listItem" varStatus="loop">
                                        <div class="col-lg-12 displayAllRightTypeAccounts">
                                            <div class="col-lg-12">${listItem.getDateToString()}</div>
                                            <div class="col-lg-12">Account name: ${listItem.getAccountName()}</div>
                                            <div class="col-lg-12">Operation: ${listItem.getObjectType().toString()}</div>
                                            <div class="col-lg-6" style ="display:inline-block;width:49%;">Value: ${listItem.getValueAfterChange()} PLN</div>
                                            <div class="col-lg-6" style ="display:inline-block;text-align:right;">${listItem.getValueChangeToString()} PLN</div>
                                        </div>
                                        <c:set var="checkIsAnyOfCreatedDivs" value="true" />
                                </c:forEach>
                            </c:if>
                            <c:if test="${history.getItemGroupToShow().toString().equals('TRANSACTION')}">
                                <c:forEach items="${sortedTransactionList}" var="listItem" varStatus="loop">
                                    <div class="col-lg-12 displayAllRightTypeAccounts">
                                        <div class="col-lg-12">${listItem.getDateTransactionToString()}</div>
                                        <div class="col-lg-12">Account name: ${listItem.getAccountNameForSortHistory()}</div>
                                        <div class="col-lg-12">Operation: Transaction </div>
                                        <div class="col-lg-6" style ="display:inline-block;width:49%;">Value: ${listItem.getAmountFoundAfterTransaction()} PLN</div>
                                        <div class="col-lg-6" style ="display:inline-block;text-align:right;">${listItem.getAmountTransactionToString()} PLN</div>
                                    </div>
                                    <c:set var="checkIsAnyOfCreatedDivs" value="true" />
                                </c:forEach>
                            </c:if>
                            <c:if test="${history.getItemGroupToShow().toString().equals('DEPOSIT') || history.getItemGroupToShow().toString().equals('PAYOUT')}">
                                <c:forEach items="${sortedDepositList}" var="listItem" varStatus="loop">
                                    <div class="col-lg-12 displayAllRightTypeAccounts">
                                        <div class="col-lg-12">${listItem.getDepositDateToString()}</div>
                                        <div class="col-lg-12">Account name: ${listItem.getAccountBank().getAccountName()}</div>
                                        <div class="col-lg-12">Operation: ${listItem.getDepositType().toString()}</div>
                                        <div class="col-lg-6" style ="display:inline-block;width:49%;">Value: ${listItem.getDepositValueAfterChange()} PLN</div>
                                        <div class="col-lg-6" style ="display:inline-block;text-align:right;">${listItem.getDepositValueToString()} PLN</div>
                                    </div>
                                    <c:set var="checkIsAnyOfCreatedDivs" value="true" />
                                </c:forEach>
                            </c:if>
                            <c:if test="${checkIsAnyOfCreatedDivs == false}"><div class="lg-col-12 headerFormTemplate" style="margin-top:50px;margin-bottom:50px;">You dont have any history</div></c:if>
                        </div>
                    </div>
               </fieldset>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footerTemplate.jsp" />
<jsp:include page="bottomFixedDivTemplate.jsp" />
<script>
    document.addEventListener("DOMContentLoaded", function () {
        restoreRadioState();
        restoreSelectionDataRange();
        restoreSelectionSortBy();
        restoreSelectionSortByAscDsc();


        document.getElementById("sortForm").addEventListener("submit", function () {
            saveRadioState();
            saveSelectionDataRange();
            saveSelectionSortBy();
            saveSelectionSortByAscDsc();
        });
    });

    function saveRadioState() {
        const radios = document.querySelectorAll('input[name="itemGroupToShow"]');
        radios.forEach((radio) => {
            localStorage.setItem(radio.id, radio.checked);
        });
    }

    function restoreRadioState() {
        const radios = document.querySelectorAll('input[name="itemGroupToShow"]');
        radios.forEach((radio) => {
            const isChecked = localStorage.getItem(radio.id) === "true";
            radio.checked = isChecked;
        });
    }
    function saveSelectionDataRange() {
        const selectElementDataRange = document.getElementById("dataRange");
        const selectedValueDataRange = selectElementDataRange.value;
        localStorage.setItem("selectedOptionDataRange", selectedValueDataRange);
    }

    function restoreSelectionDataRange() {
        const selectElementDataRange = document.getElementById("dataRange");
        const savedValueDataRange = localStorage.getItem("selectedOptionDataRange");

        if (savedValueDataRange !== null) {
            selectElementDataRange.value = savedValueDataRange;
        }
    }

    function saveSelectionSortBy() {
        const selectElementSortBy = document.getElementById("sortBy");
        const selectedValueSortBy = selectElementSortBy.value;
        localStorage.setItem("selectedOptionSortBy", selectedValueSortBy);
    }

    function restoreSelectionSortBy() {
        const selectElementSortBy = document.getElementById("sortBy");
        const savedValueSortBy = localStorage.getItem("selectedOptionSortBy");
        if (savedValueSortBy !== null){
            selectElementSortBy.value = savedValueSortBy;
        }
    }

    function saveSelectionSortByAscDsc() {
        const selectElementSortByAscDsc = document.getElementById("sortByAscDsc");
        const selectedValueSortByAscDsc = selectElementSortByAscDsc.value;
        localStorage.setItem("selectedOptionSortByAscDsc", selectedValueSortByAscDsc);

    }

    function restoreSelectionSortByAscDsc() {
        const selectElementSortByAscDsc = document.getElementById("sortByAscDsc");
        const savedValueSortByAscDsc = localStorage.getItem("selectedOptionSortByAscDsc");
        if (savedValueSortByAscDsc !== null){
            selectElementSortByAscDsc.value = savedValueSortByAscDsc;
        }
    }
</script>
<script src="../js/darkMode.js"></script>
<script src="../js/menuPreventingDisappearance.js"></script>
</body>
</html>
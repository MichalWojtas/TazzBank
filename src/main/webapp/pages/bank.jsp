<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!--  Bootstrap CSS -->
<link rel="stylesheet" href="css/headerButtonDropdownMenu.css">
<link rel="stylesheet" href="css/darkMode.css">
<link rel="stylesheet" href="css/circleButtons.css">
<link rel="stylesheet" href="css/bottomFixedDiv.css">
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
<div class = "container-fluid border-bottom border border-secondary border border-5 border-top-0 border-start-0 border-end-0">
    <div class = "row">
    <!-- Second option for color text is same as logo text color:#891212; or first white color:#fff;-->
        <div class="col-lg-8" style="background-color:#000;color:#fff;font-family:DejaVu Serif;font-weight:bold;font-size:36px;margin:auto;padding:1rem;"><img class="img-fluid" style="border:3px bold #fff;border-radius:20%;margin-right:3%;" src="<c:url value="/resources/img/Logo.png"/>"></>My TBank</div>
        <div class="col-lg-4 text-center d-flex align-items-center justify-content-end" style="background-color:#000;">
            <button type="button" data-bs-toggle="dropdown" data-bs-target="#dropdown-menu-2" class="dd1" style="margin-right:15px;">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="gray" class="bi bi-bell" viewBox="0 0 16 16"><path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"/>
                </svg></button>
            <div id="dropdown-menu-2" class="dropdown-menu">
                <div><a class="dropdown-item" href="#">In Developing</a></div>
            </div>
            <button type="button" data-bs-toggle="dropdown" data-bs-target="#dropdown-menu-1" class="dd1" style="margin-right:15px;">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="gray" class="bi bi-list" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
            </svg></button>
            <div id="dropdown-menu-1" class="dropdown-menu">
                <div class="dropdown-item" id="topOfMenu">
                    <div style="height:100%;display: flex;flex-direction: column;justify-content: flex-end;align-items: center;padding-bottom:18px;font-weight:bold;">
                    <div style="border: 2px solid red; border-radius:120px;height:48px;width:48px;align-content:center;justify-content:center;line-height:35px;background-color:#0f0f0f;margin-bottom:3px;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="red" class="bi bi-person-fill" viewBox="0 0 16 16">
                          <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                        </svg>
                    </div>${loggedUser.getLastName()} ${loggedUser.getUsername()}</div>
                </div>
                <div><a class="dropdown-item" href="#">
                    <div class="wrapperForIconInDropdown-itemForDropdownMenu1">
                    <div class="divIconInDropdown-itemForDropdownMenu1">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="black" class="bi bi-card-list" viewBox="0 0 16 16"><path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/><path d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm-1-5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zM4 8a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm0 2.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z"/>
                        </svg>
                    </div></div>
                    <div class="wrapperForTextInDropdown-itemForDropdownMenu1">
                    <div class="divForTextInDropdown-itemForDropdownMenu1">
                    My finances</div></div>
                </a></div>
                <div><a class="dropdown-item" href="#">
                    <div class="wrapperForIconInDropdown-itemForDropdownMenu1">
                        <div class="divIconInDropdown-itemForDropdownMenu1">
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="black" class="bi bi-person-fill" viewBox="0 0 16 16"><path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                            </svg></div></div>
                    <div class="wrapperForTextInDropdown-itemForDropdownMenu1">
                        <div class="divForTextInDropdown-itemForDropdownMenu1">
                    My data</div></div>
                </a></div>
                <div><a class="dropdown-item" href="#" id="settingsDropdown" data-bs-toggle="dropdown" data-bs-target="#settingsDropdownMenu">
                    <div class="wrapperForIconInDropdown-itemForDropdownMenu1">
                        <div class="divIconInDropdown-itemForDropdownMenu1">
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="black" class="bi bi-gear-fill" viewBox="0 0 16 16"><path d="M9.405 1.05c-.413-1.4-2.397-1.4-2.81 0l-.1.34a1.464 1.464 0 0 1-2.105.872l-.31-.17c-1.283-.698-2.686.705-1.987 1.987l.169.311c.446.82.023 1.841-.872 2.105l-.34.1c-1.4.413-1.4 2.397 0 2.81l.34.1a1.464 1.464 0 0 1 .872 2.105l-.17.31c-.698 1.283.705 2.686 1.987 1.987l.311-.169a1.464 1.464 0 0 1 2.105.872l.1.34c.413 1.4 2.397 1.4 2.81 0l.1-.34a1.464 1.464 0 0 1 2.105-.872l.31.17c1.283.698 2.686-.705 1.987-1.987l-.169-.311a1.464 1.464 0 0 1 .872-2.105l.34-.1c1.4-.413 1.4-2.397 0-2.81l-.34-.1a1.464 1.464 0 0 1-.872-2.105l.17-.31c.698-1.283-.705-2.686-1.987-1.987l-.311.169a1.464 1.464 0 0 1-2.105-.872l-.1-.34zM8 10.93a2.929 2.929 0 1 1 0-5.86 2.929 2.929 0 0 1 0 5.858z"/>
                            </svg></div></div>
                    <div class="wrapperForTextInDropdown-itemForDropdownMenu1">
                        <div class="divForTextInDropdown-itemForDropdownMenu1">
                    Settings</div></div>
                    </a>
                    <div class="dropdown-menu" id="settingsDropdownMenu">
                        <div><a class ="dropdown-item" style="color:black;font-weight:bold;font-size:18px;background-color:#ccc;padding-left:50px;border-bottom:2px solid black;text-align:left;line-height:3;">
                            Settings
                        </a></div>
                        <div class ="dropdown-item" style="display:block;padding-top:10px;border-bottom:2px solid black;">
                            <div class="settingsLeftDivText">Password</div>
                            <div class="settingsRightDivChange">
                                <div class="settingsButtonForChange"><a href="http://localhost:8080/bank/passwordChangeForm" style="text-decoration:none;color:inherit;display:block;">Change</a></div>
                                </div>
                        </div>

                        <div><a class ="dropdown-item" style="padding-top:10px;border-bottom:2px solid black;">
                            <div class="settingsLeftDivText">Limits for transfers</div>
                            <div class="settingsRightDivChange">
                                <div class="settingsButtonForChange" id="toggleLimitsChangeDropdown" aria-haspopup="true" aria-expanded="false" data-bs-toggle="dropdown" data-bs-target="#limitsChangeDropdownMenu" >Open</div>
                                </div>
                            </a>
                            <div id="limitsChangeDropdownMenu" style="display:none;">
                                <div><a class="dropdown-item">Actual limit transaction for day: </a></div>
                                <div><a class="dropdown-item">Actual limit transaction for month:</a></div>
                                <div><a class="dropdown-item">Transaction limit for day</a></div>
                                <div><a class="dropdown-item">Transaction limit for month</a></div>
                            </div>
                        </div>
                        <div class ="dropdown-item" style="display:block;padding-top:10px;border-bottom:2px solid black;">
                            <div class="settingsLeftDivText">Authorization phone</div>
                            <div class="settingsRightDivChange">
                                <div class="settingsButtonForChange"><a href="#" style="text-decoration:none;color:inherit;display:block;">Change</a></div>
                            </div>
                        </div>

                    </div>
                </div>
                <div id="toggleMode"><a class="dropdown-item" href="#">
                    <div class="wrapperForIconInDropdown-itemForDropdownMenu1">
                        <div class="divIconInDropdown-itemForDropdownMenu1">
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="black" class="bi bi-moon-fill" viewBox="0 0 16 16"><path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z"/>
                            </svg></div></div>
                    <div class="wrapperForTextInDropdown-itemForDropdownMenu1">
                        <div class="divForTextInDropdown-itemForDropdownMenu1">
                        Light/Dark Mode</div></div>
                </a></div>
                <div class="dropdown-item" id="bottomOfMenu"><a href="/logout" style="text-decoration:none;color:red;"><div id="logoutBottomOfMenu" style="display: flex; align-items: center;justify-content:center;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="black" class="bi bi-power" viewBox="0 0 16 16"><path d="M7.5 1v7h1V1h-1z"/><path d="M3 8.812a4.999 4.999 0 0 1 2.578-4.375l-.485-.874A6 6 0 1 0 11 3.616l-.501.865A5 5 0 1 1 3 8.812z"/>
                    </svg>  Logout</div></a>
                </div>
              </div>
        </div>
    </div>
</div>
<div class = "container-fluid" style="margin-bottom:4rem;">
    <div class ="row">
        <div class = "col-lg-12" id="welcomeText">${welcomeText}, ${loggedUser.getUsername()}</div>
    </div>
</div>
<div class = "container-fluid">
<div class="row">
<div class="col-lg-12 text-center">
    <div class="button-wrapper">
    <button class="circleButtonsLeft" type="button" >
    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="red" class="bi bi-wallet2" viewBox="0 0 16 16"><path d="M12.136.326A1.5 1.5 0 0 1 14 1.78V3h.5A1.5 1.5 0 0 1 16 4.5v9a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 13.5v-9a1.5 1.5 0 0 1 1.432-1.499L12.136.326zM5.562 3H13V1.78a.5.5 0 0 0-.621-.484L5.562 3zM1.5 4a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-13z"/>
    </svg></button>
    <div class="text-under-circleButtons">Accounts<br>${loggedUser.getAccountValue()}PLN</div>
    </div>
    <div class="button-wrapper">
    <button class="circleButtonsRight" type="button">
    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="red" class="bi bi-piggy-bank" viewBox="0 0 16 16"><path d="M5 6.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0zm1.138-1.496A6.613 6.613 0 0 1 7.964 4.5c.666 0 1.303.097 1.893.273a.5.5 0 0 0 .286-.958A7.602 7.602 0 0 0 7.964 3.5c-.734 0-1.441.103-2.102.292a.5.5 0 1 0 .276.962z"/><path fill-rule="evenodd" d="M7.964 1.527c-2.977 0-5.571 1.704-6.32 4.125h-.55A1 1 0 0 0 .11 6.824l.254 1.46a1.5 1.5 0 0 0 1.478 1.243h.263c.3.513.688.978 1.145 1.382l-.729 2.477a.5.5 0 0 0 .48.641h2a.5.5 0 0 0 .471-.332l.482-1.351c.635.173 1.31.267 2.011.267.707 0 1.388-.095 2.028-.272l.543 1.372a.5.5 0 0 0 .465.316h2a.5.5 0 0 0 .478-.645l-.761-2.506C13.81 9.895 14.5 8.559 14.5 7.069c0-.145-.007-.29-.02-.431.261-.11.508-.266.705-.444.315.306.815.306.815-.417 0 .223-.5.223-.461-.026a.95.95 0 0 0 .09-.255.7.7 0 0 0-.202-.645.58.58 0 0 0-.707-.098.735.735 0 0 0-.375.562c-.024.243.082.48.32.654a2.112 2.112 0 0 1-.259.153c-.534-2.664-3.284-4.595-6.442-4.595zM2.516 6.26c.455-2.066 2.667-3.733 5.448-3.733 3.146 0 5.536 2.114 5.536 4.542 0 1.254-.624 2.41-1.67 3.248a.5.5 0 0 0-.165.535l.66 2.175h-.985l-.59-1.487a.5.5 0 0 0-.629-.288c-.661.23-1.39.359-2.157.359a6.558 6.558 0 0 1-2.157-.359.5.5 0 0 0-.635.304l-.525 1.471h-.979l.633-2.15a.5.5 0 0 0-.17-.534 4.649 4.649 0 0 1-1.284-1.541.5.5 0 0 0-.446-.275h-.56a.5.5 0 0 1-.492-.414l-.254-1.46h.933a.5.5 0 0 0 .488-.393zm12.621-.857a.565.565 0 0 1-.098.21.704.704 0 0 1-.044-.025c-.146-.09-.157-.175-.152-.223a.236.236 0 0 1 .117-.173c.049-.027.08-.021.113.012a.202.202 0 0 1 .064.199z"/>
    </svg></button>
    <div class="text-under-circleButtons">Savings<br>${loggedUser.getAccountValue()}PLN</div>
    </div>
</div>
<div class = "col-lg-12 text-center">
    <div class="button-wrapper">
        <button class="circleButtonsLeft" type="button" data-bs-toggle="dropdown" data-bs-target="#addFundsDropdown" >
        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="red" class="bi bi-cash" viewBox="0 0 16 16"><path d="M8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"/><path d="M0 4a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V4zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V6a2 2 0 0 1-2-2H3z"/>
        </svg></button>
        <div class="dropdown-menu" id="addFundsDropdown">
            <div class="dropdown-item">
                <strong>Add funds into account</strong>
            </div>
            <div class="dropdown-item">
                Choose account
            </div>
            <div class="dropdown-item" id="dropdownItemForm">
                <form:form method="post" modelAttribute="user">
                    <div class="form-group">
                	    <label>Amount:</label>
                		<form:input path="accountValue" placeholder="0.00 PLN" type="number" step="0.01" min="0" class="form-control" style="width:30%;margin-top:4px;border:1px solid black;"/>
                		<form:errors path="accountValue" style="color:red" class="form-number number-muted"/>
                	</div>
                		<form:button name="addValue" type="submit" class="btn btn-success" style="margin-top:8px;border:1px solid black;">Confirm</form:button>
                	</form:form>
            </div>
        </div>
        <div class="text-under-circleButtons">Add funds</div>
        </div>
    <div class="button-wrapper">
        <button class="circleButtonsRight" type="button">
        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="red" class="bi bi-credit-card" viewBox="0 0 16 16"><path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v1h14V4a1 1 0 0 0-1-1H2zm13 4H1v5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V7z"/><path d="M2 10a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1v-1z"/>
        </svg></button>
        <div class="text-under-circleButtons">Cards and Phone<br>payments</div>
        </div>
</div>
<div class = "col-lg-12 text-center" style="margin-top:15px;">
    <div style="display: inline-block;margin-right:15px;font-size:24px;">I have</div><div style="color:orange;font-size:36px;display: inline-block;">${loggedUser.getAccountValue()}PLN</div>
</div>
</div>
</div>
<jsp:include page="footerTemplate.jsp" />
<jsp:include page="bottomFixedDivTemplate.jsp" />
<script>
<!-- This script doesnt work when is in external file with import here, i dont know why -->
    const triggerDiv2 = document.getElementById("toggleLimitsChangeDropdown");
    const item2 = document.getElementById("limitsChangeDropdownMenu");
    var innerLimitsIsOpen = false;
    triggerDiv2.addEventListener("click",function (){
        if(!innerLimitsIsOpen){
            item2.style.display = "block";
            innerLimitsIsOpen = true;
        }else{
            item2.style.display = "none";
            innerLimitsIsOpen = false;
        }
    });
</script>
<script src="js/darkMode.js"></script>
<script src="js/menuPreventingDisappearance.js"></script>
<script src="js/bankPreventingDisappearance.js"></script>
<script
  src="https://code.jquery.com/jquery-3.7.1.slim.min.js"
  integrity="sha256-kmHvs0B+OpCW5GVHUNjv9rOmY0IvSIRcf7zGUDTDQM8="
  crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
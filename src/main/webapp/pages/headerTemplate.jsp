<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<head>
<script>
  function goBack() {
    window.history.back();
  }
</script>
</head>
<div class = "container-fluid border-bottom border border-secondary border border-5 border-top-0 border-start-0 border-end-0">
    <div class = "row">
        <!-- Second option for color text is same as logo text color:#891212; or first white color:#fff;-->
        <div class="col-lg-8" style="background-color:#000;color:#fff;font-family:DejaVu Serif;font-weight:bold;font-size:36px;margin:auto;padding:1rem;"><img class="img-fluid" style="border:3px bold #fff;border-radius:20%;margin-right:3%;" src="<c:url value="/resources/img/Logo.png"/>"></>My TBank</div>
        <div class="col-lg-4 text-center d-flex align-items-center justify-content-end" style="background-color:#000;">
            <button type="button" class="dd1" onclick="goBack()" style="margin-right:15px;">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="gray" class="bi bi-chevron-left" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
            </svg></button>
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
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="red" class="bi bi-person-fill" viewBox="0 0 16 16"><path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
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
                <div><a class="dropdown-item" href="#">
                    <div class="wrapperForIconInDropdown-itemForDropdownMenu1">
                        <div class="divIconInDropdown-itemForDropdownMenu1">
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="black" class="bi bi-gear-fill" viewBox="0 0 16 16"><path d="M9.405 1.05c-.413-1.4-2.397-1.4-2.81 0l-.1.34a1.464 1.464 0 0 1-2.105.872l-.31-.17c-1.283-.698-2.686.705-1.987 1.987l.169.311c.446.82.023 1.841-.872 2.105l-.34.1c-1.4.413-1.4 2.397 0 2.81l.34.1a1.464 1.464 0 0 1 .872 2.105l-.17.31c-.698 1.283.705 2.686 1.987 1.987l.311-.169a1.464 1.464 0 0 1 2.105.872l.1.34c.413 1.4 2.397 1.4 2.81 0l.1-.34a1.464 1.464 0 0 1 2.105-.872l.31.17c1.283.698 2.686-.705 1.987-1.987l-.169-.311a1.464 1.464 0 0 1 .872-2.105l.34-.1c1.4-.413 1.4-2.397 0-2.81l-.34-.1a1.464 1.464 0 0 1-.872-2.105l.17-.31c.698-1.283-.705-2.686-1.987-1.987l-.311.169a1.464 1.464 0 0 1-2.105-.872l-.1-.34zM8 10.93a2.929 2.929 0 1 1 0-5.86 2.929 2.929 0 0 1 0 5.858z"/>
                    </svg></div></div>
                    <div class="wrapperForTextInDropdown-itemForDropdownMenu1">
                        <div class="divForTextInDropdown-itemForDropdownMenu1">
                    Settings</div></div>
                </a></div>
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
</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class = "container-fluid border-bottom border border-secondary border border-5 border-top-0 border-start-0 border-end-0">
    <div class = "row">
        <!-- Second option for color text is same as logo text color:#891212; or first white color:#fff;-->
        <div class="col-lg-8" style="background-color:#000;color:#fff;font-family:DejaVu Serif;font-weight:bold;font-size:36px;margin:auto;padding:1rem;"><img class="img-fluid" style="border:3px bold #fff;border-radius:20%;margin-right:3%;" src="<c:url value="/resources/img/Logo.png"/>"></>My TBank</div>
    <div class="col-lg-4 text-center d-flex align-items-center justify-content-end" style="background-color:#000;">
        <button type="button" data-bs-toggle="dropdown" class="dd1" style="margin-right:15px;">
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="gray" class="bi bi-bell" viewBox="0 0 16 16"><path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"/>
            </svg></button>
        <button type="button" data-bs-toggle="dropdown" class="dd1" style="margin-right:15px;">
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="gray" class="bi bi-list" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
            </svg></button>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">My Account</a></li>
            <li><a class="dropdown-item" href="#">Settings</a></li>
            <li><button id="toggleMode"><a class="dropdown-item" href="#">Light/Dark Mode</a></button></li>
            <li><a class="dropdown-item" href="/logout">Logout</a></li>
        </ul>
    </div>
</div>
</div>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  Bootstrap CSS -->
<link rel ="stylesheet" href="http://stackpath.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css"
	integrity="sha384-PDle/QlgIONtM1aqA2Qemk5gPOE7wFq8+Em+G/hmo5Iq0CCmYZLv3fVRDJ4MMwEA" crossorigin="anonymous">

</head>
<body>
<div style="margin: auto; width: 500px; padding-top:50px">
	<form:form method="post" modelAttribute="user">
			<div class="form-group">
				<label>Login</label>
				<form:input path="username" placeholder="Username" type="text" class="form-control"/>
				<form:errors path="username" style="color:red" class="form-text text-muted"/>
			</div>
		<div class="form-group">
			<label>Password</label>
			<form:input path="b4encryptPassword" placeholder="Password" type="password" class="form-control"/>
			<form:errors path="b4encryptPassword" style="color:red" class="form-text text-muted"/>
		<br/>
			<label>Confirm password</label>
			<form:input path="confirmPassword" placeholder="Confirm password" type="password" class="form-control"/>
			<form:errors path="confirmPassword" style="color:red" class="form-text text-muted"/>
		</div>
		
		<div class="form-group">
			<label>Email</label>
			<form:input path="email" placeholder="Email" type="text" class="form-control"/>
			<form:errors path="email" style="color:red" class="form-text text-muted" />
		</div>
		<div class="form-group">
			<label>First name</label>
			<form:input path="firstName" placeholder="First name" type="text" class="form-control"/>
			<form:errors path="firstName" style="color:red" class="form-text text-muted" />
			<br>
			<label>Second name</label>
			<form:input path="secondName" placeholder="Second name" type="text" class="form-control"/>
			<form:errors path="secondName" style="color:red" class="form-text text-muted" />
			<br>
			<label>Last name</label>
			<form:input path="lastName" placeholder="Last name" type="text" class="form-control"/>
			<form:errors path="lastName" style="color:red" class="form-text text-muted" />
			<br>
			<label>Pesel</label>
			<form:input path="pesel" placeholder="Pesel" type="text" class="form-control"/>
			<form:errors path="pesel" style="color:red" class="form-text text-muted" />
			<br>
			<label>Phone number</label>
			<form:input path="phoneNumber" placeholder="Phone number" type="text" class="form-control"/>
			<form:errors path="phoneNumber" style="color:red" class="form-text text-muted" />
			<br>
			<label>Address</label>
			<form:input path="address" placeholder="Address" type="text" class="form-control"/>
			<form:errors path="address" style="color:red" class="form-text text-muted" />
			<br>
			<label>Correspondence address</label>
			<form:input path="addressForCorrespondence" placeholder="Correspondence address" type="text" class="form-control"/>
			<form:errors path="addressForCorrespondence" style="color:red" class="form-text text-muted" />
			<br>
		</div>
		<form:button type="submit" class="btn btn-success">Register</form:button>
	</form:form>
</div>


</body>
</html>
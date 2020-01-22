<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html; charset=windows-1251" %>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Register form</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

</head>

<body>

<div class="container">

    <form method="POST" action="AddUser" class="form-signin">
        <h2 class="form-heading">Registration</h2>

        <div class="form-group has-error">
            User<input name="user" type="text" title="User" class="form-control"/>
            <p>Such user already exist! Take another.</p>
        </div>

        <div>
            Password  <input name="password" type="password" title="Password" class="form-control"/>
            Email <input name="email" type="email" title="Email" class="form-control"/>
            Address<input name="address" type="text" title="Address" class="form-control"/>
            Phone<input name="phone" type="text" title="Phone" class="form-control"/>
            Role <select id="disabledSelect" title="Role" class="form-control">
            <option>Teacher</option>
            <option>Student </option>
        </select>
            Enter password (only for teachers)
            <input name="teacherPassword" type="password" title="TeacherPassword"  class="form-control"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit" name="save">Sign Up</button>
            <button class="btn btn-lg btn-primary btn-block" type="submit" name="cancel">Cancel</button>
        </div>

    </form>

</div>
<!-- /container -->
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>

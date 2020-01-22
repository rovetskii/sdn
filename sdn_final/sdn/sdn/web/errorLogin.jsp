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

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

</head>

<body>

<div class="container">

    <form method="POST" action="Registration" class="form-signin">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group has-error">
            <input name="user" type="text" title="Username" class="form-control"
                   autofocus="true"/>
            <input name="password" type="password" title="Password" class="form-control has-error"/>
            <p>Password or username is incorrect</p>
            <button class="btn btn-lg btn-primary btn-block" type="submit" name="login">Log In</button>
            <h4 class="text-center"><a href="registration.jsp">Create an account</a></h4>
        </div>

    </form>

</div>
<!-- /container -->
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>

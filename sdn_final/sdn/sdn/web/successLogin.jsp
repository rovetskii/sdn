<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html; charset=windows-1251" %>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Login success</title>
    <link rel="stylesheet" href="newcss.css">
</head>

<body>

<br>
<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<h1 style="color: green" align="center"> Ви успішно зайшли в систему!  </h1>
    <form action="CoursesServlet" method="post" class="form-signin">
        
        <div class="form-group">
         
<!--         <p style="color: green">Викладач:</p>-->
       <input class="form-control" type="hidden" name="cur_user" value="<%=request.getParameter("user")%>" /> 
         
               
           
   
                    
           
            <p style="color: green">Прізвище:</p>
            <input class="form-control" type="text" value="<%=request.getAttribute("lastName")%>" id="lastname">
            <p style="color: green">Ім'я:</p>
            <input class="form-control" type="text" value="<%=request.getAttribute("firstName")%>" id="firstname">
            <p style="color: green">По-батькові:</p>
            <input class="form-control" type="text" value="<%=request.getAttribute("surname")%>" id="surname">             
            <img src = "CheckUser" width="100%" height="100%" class="form-control" >
            <p style="color: green">Науковий ступінь:</p>   
            <input class="form-control" type="text" value="<%=request.getAttribute("acTitle")%>" id="acTitle">
            <p style="color: green">Вчене звання:</p>         
            <input class="form-control" type="text" value="<%=request.getAttribute("degree")%>" id="degree">
            <p style="color: green">Організація:</p>                  
            <input class="form-control" type="text" value="<%=request.getAttribute("organization")%>" id="organization">
            <p style="color: green">Посада:</p>                  
            <input class="form-control" type="text" value="<%=request.getAttribute("post")%>" id="post">   
            </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit" name="cours">Додати курси</button>
                <button class="btn btn-lg btn-primary btn-block" type="submit" name="mater">Оновити курси</button>   
    </form> 
           <a href="view.jsp"><h4 align="center">Переглянути курси</h4> </a>
</body>

</html>

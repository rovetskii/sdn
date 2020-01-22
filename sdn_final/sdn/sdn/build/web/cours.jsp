<%-- 
    Document   : cours
    Created on : 25 трав. 2017, 17:36:49
    Author     : Ivan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="TeacherSystem.DBconnect"%>
<%@page contentType="text/html; charset=windows-1251" %>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>JSP Page</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    </head>
    <body>
        <div class="form-signin">
            <div class="form-group">
             <h1 style="color: green"> Додати курс </h1> 
             
       
<form action="Corse1Servlet" method="post" enctype="multipart/form-data"  >
    <p> <input class="form-control" type="hidden" name="cur_user" value="<%=request.getParameter("cur_user")%>" />  </p>
   <p style="color: green">Назва курсу:</p>
    <p> <input class="form-control" type="text" name="cours_name" value="" />  </p>
   
    <p style="color: green">Тип файлу:</p>
         <p> <select class="form-control" name="SelectTypeCourse">  
             <option  value="video">Відео файл </option>
               <option  value="lecture"> Текстовий файл </option>
                </select> 
    </p>
    <p> <input class="form-control" type="file" name="file"  /> </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Завантажити файл на сервер</button>
        <a href="view.jsp"><h4 align="center">Переглянути курси</h4> </a>
    </div>
    </div>
    
    
    
</form>
    </body>
</html>

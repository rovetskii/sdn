<%-- 
    Document   : materials
    Created on : 22 трав. 2017, 10:22:07
    Author     : Ivan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%
    String url = "jdbc:mysql://localhost:3306/";
        String dbName = "teacherdb";
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection(url+dbName, "root", "Fcbcntynkrf1234" );    
        %>   
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    </head>
    <body>
        <h1 style="color: green" align="center"> Додати матеріали по курсу </h1>
        
        <form action="UploadServlet" method="post"  enctype="multipart/form-data" class="form-signin">
         <div class="form-signin">
            <div class="form-group">
        <p style="color: green">Курси:</p>
       <select name="SelectCourse" class="form-control">  
           
        <%
            String id="";
 String user=request.getParameter("cur_user");
String sql="SELECT id_teacher FROM teachers WHERE login = ?";
PreparedStatement  st=con.prepareStatement(sql);
st.setString(1, user);
ResultSet  rs=st.executeQuery();
//ArrayList<Integer> a=new ArrayList<Integer>(); 

while (rs.next())
 id=rs.getString(1);

sql="SELECT id_course, course_name FROM courses WHERE id_teacher= ?"; 
st=con.prepareStatement(sql);
st.setString(1, id);
rs=st.executeQuery();
while (rs.next())
{
%>
<option name="ChoseCourse" value="<%=rs.getString(1)%> " > <%=rs.getString(2)%> </option>
<%   
}
 %>      
      </select> 
        </p>
        <p> <input type="hidden"  value="<%=request.getParameter("cur_user")%>"   /> </p>
<!--     <p> <input type="text"  value="<%=request.getParameter("cur_user")%>"   /> </p>   -->
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

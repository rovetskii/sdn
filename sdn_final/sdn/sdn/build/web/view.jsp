<%-- 
    Document   : view
    Created on : 9 трав. 2017, 15:23:02
    Author     : Ivan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@page import="java.util.ArrayList"%>
<%--<%@page import="com.mysql.jdbc.PreparedStatement"%>--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.PreparedStatement" %>
    <%
    String url = "jdbc:mysql://localhost:3306/";
        String dbName = "teacherdb";
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection(url+dbName, "root", "Fcbcntynkrf1234" );    
        %> 
        
<!DOCTYPE html>
<html>
    <head>
         <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jquery-3.2.1.min.js"> </script>
        <link rel="stylesheet" href="newcss.css">
        <script>    
    //src="jquery-3.2.1.min.js"  
    //src=" http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"
    $(document).ready(function(){
            $('#SelectTeacher').get(0).selectedIndex = 0;
            document.getElementById("firstname").value="";
            document.getElementById("surname").value="";
            document.getElementById("acTitle").value="";
            document.getElementById("degree").value="";
            document.getElementById("organization").value="";
            document.getElementById("post").value="";
             
            $('#SelectTeacher').change(function(){
         //     var SelectOpt=$('#SelectTeacher option:selected');
          // var s=SelectOpt.val();
           //var List;
          // $('#select_course').prop('disabled',true); 
      var st_v=document.getElementById("SelectTeacher").value;    
        $.ajax({
                type:"POST",
                url:'FirstViewServlet',
                 data: {st_v:st_v},
                       success:function(result)
                       {
                var listresult=$.parseJSON(result);
       var s=listresult[0].photo;
       //$('#firstname').value=listresult[0].first_name;
         document.getElementById("firstname").value=listresult[0].first_name;
        
        document.getElementById("surname").value=listresult[0].surname;
       document.getElementById("acTitle").value=listresult[0].academic_title;
        document.getElementById("degree").value=listresult[0].degree;
      document.getElementById("organization").value=listresult[0].organization;
      document.getElementById("post").value=listresult[0].post;      
               var img=document.getElementById("photo");
                    var s1="uploads\\"+s;
                          img.src=s1;
         //var result1="<option value="+result.value+">"+result.text+"</option>";
          $('#select_course').empty();
           $('#select_course').append('<option>   </option>');
        for (var i=1; i<listresult.length; i++)  
        {
             // s+='iD='+ listresult[i].id+'text='+listresult[i].name;
      //      document.getElementById('chose_courses').innerHTML=s; 
        $('#select_course').append('<option value='+ listresult[i].id+'>' + listresult[i].name +'</option>');
        }
        $('#select_course').prop('disabled',false);
               }
                   });
                   });
              
             //  $('#select_course').html(result);
           
        //  var result1 = result.split(""); 
      // $.each(result1,function(index, value){        
      // $('#select_course').append('<option>'+ value.toString() +'</option>');
     //$('#select_course').append(result);}
          //  $('#select_course').html(result);}
 
      //  $('#video').html(result);  
    //});
        //  }); 
             $('#select_course').change(function()
             {
             var st_v1=document.getElementById("select_course").value;  
              $.ajax({
                type:"POST",
                url:'SecondViewServlet',
                 data: {
                     st_v1:st_v1,
                     action: '1'    
                        },
                       success:function(result)
                       {
                        var listresult=$.parseJSON(result);              
         //var result1="<option value="+result.value+">"+result.text+"</option>";
          $('#select_video').empty();
           $('#select_video').append('<option>   </option>');
          for (var i=0; i<listresult.length; i++)  
        {
      $('#select_video').append('<option>' + listresult[i].name_video +'</option>');
        }   
          }
           });   
                   $.ajax({
                type:"POST",
                url:'SecondViewServlet',
                 data: {
                     st_v1:st_v1,
                     action: '2'              
                       },
                       success:function(result)
                       {
                        var listresult=$.parseJSON(result);              
         //var result1="<option value="+result.value+">"+result.text+"</option>";
         
           $('#select_lecture').empty();
          $('#select_lecture').append('<option>   </option>');
        for (var i=0; i<listresult.length; i++)  
        {
        $('#select_lecture').append('<option>' + listresult[i].name_video +'</option>');
        }
           }
             });
              
               });       
                $('#select_video').change(function()
                {   
                     // var video=$('#VideoPlay');
                    var video=document.getElementById('VideoPlay');
                        //video.empty();
                    var s=$('#select_video option:selected').text();
                   
                    var s1="uploads\\"+s;
                          video.src=s1;
                          //  video.append('<source src="'+s1+'">');
                               //alert(s1);
                              
                   });
                   
                  $('#link').click(function(){
               var link=document.getElementById('link');   
     var s=$('#select_lecture option:selected').text();
      if(s.length>0)
      {
             var s1="uploads\\"+s;
            
             link.href=s1;
         }
         else {alert("Файл не вибрано!");}
      
    
    }); 
         
    });      
         
 
        </script>
    
<!--        <style type="text/css">
  #select_video, #select_video option {
 width: 400px;
  margin: 0;
  padding: 0;
  }
</style>-->
        
    </head>
    
    <body>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<!--           <div id="main">-->
<div id="main">
            <header> <h1 align="center"> Матеріали курсів </h1></header>
             
<!--            <div class="leftBlock">-->
<!--        <div class="container">-->
<!--            <div class="form-group row">-->
                
                <div class="form-signin">
                <div class="form-group">
                     <p style="color: green">Викладачі:</p>
<!--                    <label for="SelectTeacher" class="col-xs-1 col-form-label" > Викладачі: </label>-->
                    <select class="form-control" id="SelectTeacher"> 
                    <option value="none" >     </option> 
             
<%String sql="SELECT id_teacher, last_name FROM teachers Where user_role=1";
PreparedStatement  st=con.prepareStatement(sql);
ResultSet  rs=st.executeQuery();
//ArrayList<Integer> a=new ArrayList<Integer>(); 
while (rs.next())
{
%>
<option id="chose_teacher" value="<%=rs.getString(1) %> " > <%=rs.getString(2) %> </option>
<%   
 }
 %>   
  </select >  
                

          <p style="color: green">Ім'я:</p>
          <input class="form-control" type="text" id="firstname" value=""/>
          <p style="color: green">По-батькові:</p>
          <p>  <input class="form-control" type="text" id="surname" value=""/> </p>
          
           <img id="photo" class="form-control" width="100%" height="100%"> 
           
          <p style="color: green">Науковий ступінь:</p>
          <input class="form-control" type="text" id="acTitle" value=""/>
          <p style="color: green">Вчене звання:</p>
          <input class="form-control" type="text" id="degree" value=""/>
          <p style="color: green">Організація:</p>
          <input class="form-control" type="text" id="organization" value=""/>
          <p style="color: green">Посада:</p>
         <p> <input class="form-control" type="text" id="post" value=""/> </p>

       
    
   
       
         
<!--  <div class="photo">-->


 

    
          <p style="color: green"> Курси: </p>  
          <select class="form-control" id="select_course" disabled > </select>  
           <p style="color: green"> Відеоуроки: </p>  
        <p>  <select class="form-control" id="select_video">  </select> </p>  

     <video id="VideoPlay" width="550" height="450" controls="controls" class="form-control"> 
       <source  id="tv_channel" >
              </video> 
            <p style="color: green"> Лекції та лабораторні: </p>
             <select  class="form-control" id="select_lecture" > </select> 
             <a id="link" href="#"  > Скачати файл </a> 
                  </div>
  </div>
  
<!--              <form action="DownloadServlet">-->
<!--              <a href="download.jsp"> Відкрити файл у браузері</a> -->
            
                <!--<a href="uploads\\FEI2LabRob4.pdf" download> Завантажити файл  з сервера</a>-->
               <!--</form>-->
             </div>              
    </body>
</html>

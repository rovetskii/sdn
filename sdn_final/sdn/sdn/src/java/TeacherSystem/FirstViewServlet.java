/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeacherSystem;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.*;
import combo.courses;
import combo.teachers;
import java.util.List;
import java.util.Spliterator;
/**
 *
 * @author Ivan
 */
public class FirstViewServlet extends HttpServlet {
  PreparedStatement  st;
  ResultSet  rs;
  List ls= new ArrayList();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //System.out.println("Hello");
        response.setContentType("text/html;charset=UTF-8");
       //response.setContentType("application/json");
        String url = "jdbc:mysql://localhost:3306/teacherdb";
          //  String dbName = "teacherdb";
        String driver = "com.mysql.jdbc.Driver";
        String username = "root";
        String passwordDB = "Fcbcntynkrf1234";
       // JsonArray data_json=new JsonArray();  
        try{
         Class.forName(driver);
          Connection con  = DriverManager.getConnection(url, username, passwordDB);
        
          String chose_teacher=request.getParameter("st_v");
          System.out.println("c="+chose_teacher);
          String sql="";
           String action = request.getParameter("action");
                PrintWriter out=response.getWriter();
                
                //List<teachers> ls1= new ArrayList<teachers>();
                Gson n=new Gson();
          // if (action.equals("1"))
        //{
  sql="SELECT first_name, surname, academic_title, degree, organization, post, photo FROM teachers WHERE id_teacher=?";
            st=con.prepareStatement(sql);
          st.setString(1, chose_teacher);
             rs=st.executeQuery();
                  while (rs.next())
        {
            ls.add(new teachers (rs.getString("first_name"), 
                    rs.getString("surname"), rs.getString("academic_title"), rs.getString("degree"), rs.getString("organization"), rs.getString("post"), rs.getString("photo")));
        // System.out.println(ls);
         
        }
  
          
   
           //else if (action.equals("2")) 
           //{
  sql="SELECT id_course, course_name FROM courses WHERE id_teacher="+chose_teacher;
          st=con.prepareStatement(sql);
          rs=st.executeQuery();
        while (rs.next())
        {
            ls.add(new courses (rs.getString("id_course"), rs.getString("course_name")));
      //   System.out.println(ls);
        }
     
         
 System.out.println(ls);

String json=n.toJson(ls);
System.out.println(json);
out.println(json);
ls.clear();
//out.close();
      
        }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(FirstViewServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        
        
        }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

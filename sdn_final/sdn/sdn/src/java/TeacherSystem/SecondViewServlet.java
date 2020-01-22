/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeacherSystem;

import com.google.gson.Gson;
import combo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ivan
 */
public class SecondViewServlet extends HttpServlet {
 PreparedStatement st, st1;
    ResultSet  rs, rs1;
    String sql;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      try {
        DBconnect db=new DBconnect();
     Connection con= db.getConnection();
            
          String chose_teacher=request.getParameter("st_v1");
          String action = request.getParameter("action");
            System.out.println("c="+chose_teacher);
            PrintWriter out=response.getWriter();
              List ls= new ArrayList();
        if (action.equals("1"))
        {
      sql="SELECT material_name  FROM materials WHERE id_course = "+ chose_teacher + " AND material_type = 'video' ";
          
       }  
        else if(action.equals("2"))
        {
     sql="SELECT material_name  FROM materials WHERE id_course = "+ chose_teacher + " AND material_type = 'lecture' ";
        }
              st=con.prepareStatement(sql);
                rs=st.executeQuery();
        while (rs.next())
          ls.add(new courses (rs.getString(1)));
        
        System.out.println(ls);
Gson n=new Gson();
String json=n.toJson(ls);
//String json1=n.toJson(ls1);
System.out.println(json);
out.println(json);
      }
            catch (SQLException e) {
            e.printStackTrace();
        }      
   
        }
        


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
        processRequest(request, response);
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
        processRequest(request, response);
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

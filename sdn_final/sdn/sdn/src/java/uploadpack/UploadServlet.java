/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uploadpack;

import TeacherSystem.DBconnect;
import java.io.*;
import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Ivan
 */
public class UploadServlet extends HttpServlet {
private List<String> list = new LinkedList<String>();
    private boolean isMultipart;
    
   private String filePath;
   private    int maxFileSize = 100 * 1024*1024;
  // private int maxMemSize = 4 * 1024*1024;
   //private File file ;
   String fileName;
    String id_course;
      String type_course;
/*
   public void init( ){
      // Get the file location where it would be stored.
      filePath = 
             getServletContext().getInitParameter("file-upload"); 
   }
*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
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
        //processRequest(request, response);
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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//           request.setCharacterEncoding("windows-1251");
//        response.setCharacterEncoding("windows-1251");
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        
        isMultipart = ServletFileUpload.isMultipartContent(request);
          java.io.PrintWriter out = response.getWriter( );
      DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
         upload.setSizeMax( maxFileSize );

      try { 
      // Parse the request to get file items.
      List<FileItem> items = upload.parseRequest(request);

      for (FileItem item : items) {
          if (item.isFormField()) {
                    list.add(item.getString("windows-1251"));
                 } 
          else if ( !item.isFormField () )	
         {
             upload.setSizeMax(1024 * 1024 * 100);
                    byte[] data = item.get();
                    fileName = item.getName();
                    String applicationPath = request.getServletContext().getRealPath("");
                    String uploadFilePath = applicationPath + File.separator + "uploads";
                    File file = new File(uploadFilePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File savedFile = new File(uploadFilePath + File.separator + fileName);
                    FileOutputStream fos = new FileOutputStream(savedFile);
                    InputStream is = item.getInputStream();
                    int x;

                    while ((x = is.read(data)) != -1) {
                        fos.write(data, 0, x);
                    }
                    fos.flush();
                    fos.close();
                }
            }
     
      
           }    //out.println("Uploaded Filename: " + fileName + "<br>");
      
        catch(Exception ex) {
       System.out.println(ex);}
      
   
       id_course=list.get(0);
      type_course=list.get(1);
        System.out.println("id_course="+id_course);
         System.out.println("type_course="+type_course);
           System.out.println("filename="+fileName);
             if ( fileName.equals(""))
          {
   out.print("<h1>Виберіть файл для завантаження на сервер <h1>");
          }
           else
             {
         try{
         DBconnect db=new DBconnect();
     Connection con= db.getConnection();
     String sql="Insert into materials (id_course,material_type,material_name)"
             + "values(?,?,?)";
    PreparedStatement st=con.prepareStatement(sql);
    st.setString(1,id_course);
    st.setString(2,type_course);
    st.setString(3,fileName);
    st.executeUpdate();
        out.println("<h1> Файл успішно завантажено на сервер.</h1>");
         
         } catch (SQLException e) {
            e.printStackTrace();}
             }
         
        list.clear();
        // request.setAttribute("f", fileName );
          //  request.getRequestDispatcher("1.jsp").forward(request, response);
      }
      
   }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */





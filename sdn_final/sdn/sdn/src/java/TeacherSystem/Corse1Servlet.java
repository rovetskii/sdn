/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeacherSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import uploadpack.UploadServlet;

/**
 *
 * @author Ivan
 */
public class Corse1Servlet extends HttpServlet {
    private boolean isMultipart;
    
   private String filePath;
   private    int maxFileSize = 100 * 1024*1024;
  // private int maxMemSize = 4 * 1024*1024;
   //private File file ;
   String fileName;  
    String cours_name;
  String  id_course;
   String sql="";
    PreparedStatement st;
    ResultSet rs;
         Connection con;
    String type_course;
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
       // processRequest(request, response);
//       response.setContentType("text/html;charset=windows-1251");
    List<String> list= new ArrayList<String>();
    List<String> list2= new ArrayList<String>();
    
    List<String> idteacher= new ArrayList<String>();
    
//      request.setCharacterEncoding("windows-1251");
//        response.setCharacterEncoding("windows-1251");
//        response.setContentType("text/html");
       
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

      for (FileItem item : items)
      {
           if (item.isFormField()) {
                  list.add(item.getString("windows-1251"));
                        System.out.println(list);
                   
                  
                 } 
          
        else if ( !item.isFormField () )	
         {
             upload.setSizeMax(1024 * 1024 * 100);
                    byte[] data = item.get();
                    fileName = item.getName();
                     System.out.println("f="+fileName);
                    
                    
                   
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
       
       }catch(Exception ex) {
       System.out.println(ex);}
      System.out.println("course_name="+list.get(1));     
      if ( (list.get(1).equals(""))|(fileName.equals("")))
          {
   out.print("<h1>Вкажіть назву курсу і виберіть файл для завантаження на сервер <h1>");
          }
      
           else
           {
      
      try {
         
    // response.setContentType("text/html;charset=UTF-8");
     PreparedStatement  st1;
    ResultSet   rs1;
  
     String id_teacher="";
        DBconnect db=new DBconnect();
     con= db.getConnection();
         String user=request.getParameter("cur_user");
        System.out.println("user="+ user);
       //cours_name=request.getParameter("cours_name");
        //System.out.println("course_name="+ cours_name);
        
        sql="Select course_name from courses";
        st=con.prepareStatement(sql);
        rs=st.executeQuery();
         while (rs.next()){
         list2.add(rs.getString(1));
         }
         
         if (list2.contains(list.get(1)))
         {
         out.print("<h1> Курс з таким іменем вже існує <h1>");
         }
         else
         {
         sql="SELECT id_teacher FROM teachers WHERE login = ?";
             
     st=con.prepareStatement(sql);
     st.setString(1, list.get(0));
                rs=st.executeQuery();
            while (rs.next()){
         idteacher.add(rs.getString(1));
         }
            System.out.println(idteacher);
            
            
           
       String sql1 =  "INSERT INTO courses (id_teacher, course_name) VALUES (?,?)";
        st1=con.prepareStatement(sql1); 
       st1.setString(1,  idteacher.get(0));
        st1.setString(2, list.get(1));
        st1.executeUpdate();
       
        //UploadServlet up=new UploadServlet();
        //up.doPost(request, response);
       sql="Select id_course From courses Where course_name=?";
        st=con.prepareStatement(sql);
        st.setString(1,list.get(1));
        rs=st.executeQuery();
       while (rs.next()){
         id_course=rs.getString(1);
         }
      sql="Insert into materials (id_course,material_type,material_name)"
             + "values(?,?,?)";
     st=con.prepareStatement(sql);
    st.setString(1,id_course);
    st.setString(2,list.get(2));
    st.setString(3,fileName);
    st.executeUpdate();
    
  out= response.getWriter();
       out.println("<h1>Курс додано в базу даних.</h1>");
         
      }
      }

           
            catch (SQLException e) {
            e.printStackTrace();}
     }
    
      // try{
          
     
//catch (SQLException e) {
//            e.printStackTrace();}
      
      
   
    }

    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uploadpack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ivan
 */
public class DownloadServlet extends HttpServlet {

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
         response.setContentType("text/html");
                PrintWriter out = response.getWriter();
              String filename = request.getParameter("s");
              System.out.println("f="+filename);
        //String filename="JavaFX.pdf";
        //String filename="Vasiliev_Java_oop_2011.zip";
        String addpath=request.getServletContext().getRealPath("");
       String filepath=addpath+File.separator+"uploads\\";
           //String filename="Servlets - Download file.mp4";
           // String filename="FEI 2 LabRob 4.pdf";       
// String filepath="D:\\1\\sdn_1\\web\\uploads\\";
       //response.setContentType("audio/mpeg");
      // response.setContentType("video/mp4");
     // response.setContentType("image/jpg");
        response.setContentType("APPLICATION/PDF");
        response.setHeader("Content-Disposition", "attachment; filename=\""+ filename+ "\"");
      //  response.setHeader("Content-Disposition", "inline; filename=\""+ filename+ "\"");
      
       FileInputStream f= new FileInputStream(filepath+filename);
       
       int i;
        while ((i=f.read())!=-1)
         out.write(i);
        f.close();
        out.close();

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
            //   response.setContentType("text/html;charset=UTF-8");
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

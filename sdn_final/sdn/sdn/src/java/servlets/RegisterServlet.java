package servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yuriy on 17.05.2017.
 */
public class RegisterServlet extends Dispatcher {
    private List<String> list = new LinkedList<String>();
    private List<String> registeredUsers = new LinkedList<String>();
    private String fotoName;
    private String teacherPassword;
    private static final String UPLOAD_DIR = "uploads";
    static String  user;
    //static String user; 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("windows-1251");
        resp.setCharacterEncoding("windows-1251");
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024);
        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(req);
            // Process the uploaded items
            for (FileItem item : items) {
                if (item.isFormField()) {
                    list.add(item.getString("windows-1251"));
                } else {
                    upload.setSizeMax(1024 * 1024 * 10);
                    byte[] data = item.get();
                    fotoName = item.getName();
                    String applicationPath = req.getServletContext().getRealPath("");
                    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
                    File file = new File(uploadFilePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File savedFile = new File(file.getAbsolutePath() + File.separator + item.getName());
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
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
         user = list.get(0);
        String password = list.get(1);
        String lastname = list.get(2);
        String firstname = list.get(3);
        String surname = list.get(4);
        String academicTitle = list.get(5);
        String degree = list.get(6);
        String organization = list.get(7);
        String post = list.get(8);
        String teacherPas = list.get(9);
        list.clear();
        try {
            DBconnect dBconnect = queryGetLoginsAndAdminPass();
            if (!registeredUsers.contains(user) & !user.equals("") &
                    !password.equals("") &
                    teacherPassword.equals(teacherPas)) {
                queryRegisterTeacher(user, password, lastname, firstname, surname, academicTitle, degree, organization, post, dBconnect, "INSERT INTO teachers(login, password, last_name, first_name, surname, academic_title,degree,organization,post,photo,user_role) VALUES (?,?,?,?,?,?,?,?,?,?,1 )", fotoName);
                this.forward("/successRegistration.jsp", req, resp);
            } else {
                if (!registeredUsers.contains(user) & !user.equals("") &
                        !password.equals("") &
                        !teacherPassword.equals(teacherPas)) {

                    queryRegisterStudent(user, password, lastname, firstname, surname, academicTitle, degree, organization, post, dBconnect);
                    this.forward("/successRegistration.jsp", req, resp);
                }else{
                    out.println("<h1>Такий користувач існує! Введіть інший логін.</h1>");
                    // this.forward("/registration.jsp", req, resp);
                }
            }
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (req.getParameter("cancel") != null) {
            this.forward("/index.jsp", req, resp);
        }
    }


    private DBconnect queryGetLoginsAndAdminPass() throws SQLException {
        DBconnect dBconnect = new DBconnect();
        PreparedStatement pst = dBconnect.getConnection().prepareStatement("SELECT login FROM teachers ");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            registeredUsers.add(rs.getString("login"));
        }
        pst = dBconnect.getConnection().prepareStatement("SELECT password FROM teachers WHERE id_teacher=1");
        rs = pst.executeQuery();
        while (rs.next()) {
            teacherPassword = rs.getString("password");
        }
        return dBconnect;
    }

    private void queryRegisterTeacher(String user, String password, String lastname, String firstname, String surname, String academicTitle, String degree, String organization, String post, DBconnect dBconnect, String sql, String fotoName) throws SQLException {
        PreparedStatement pst;
        pst = dBconnect.getConnection().prepareStatement(sql);
        pst.setString(1, user);
        pst.setString(2, password);
        pst.setString(3, lastname);
        pst.setString(4, firstname);
        pst.setString(5, surname);
        pst.setString(6, academicTitle);
        pst.setString(7, degree);
        pst.setString(8, organization);
        pst.setString(9, post);
        pst.setString(10, fotoName);
        pst.executeUpdate();
    }

    private void queryRegisterStudent(String user, String password, String lastname, String firstname, String surname, String academicTitle, String degree, String organization, String post, DBconnect dBconnect) throws SQLException {
        PreparedStatement pst;
        pst = dBconnect.getConnection().prepareStatement("INSERT INTO teachers(login, password, last_name, first_name, surname, academic_title,degree,organization,post,photo,user_role) VALUES (?,?,?,?,?,?,?,?,?,?,0 )");
        pst.setString(1, user);
        pst.setString(2, password);
        pst.setString(3, lastname);
        pst.setString(4, firstname);
        pst.setString(5, surname);
        pst.setString(6, academicTitle);
        pst.setString(7, degree);
        pst.setString(8, organization);
        pst.setString(9, post);
        pst.setString(10, fotoName);
        pst.executeUpdate();
    }

}

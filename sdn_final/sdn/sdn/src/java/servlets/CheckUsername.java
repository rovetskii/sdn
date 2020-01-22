package servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Created by Yuriy on 24.04.2017.
 */
public class CheckUsername extends Dispatcher {
    private List<String> users = new LinkedList<String>();
    private List<String> passwords = new LinkedList<String>();

    private String firstName;
    private String lastName;
    private String surname;
    private String acTitle;
    private String degree;
    private String organization;
    private String post;
    private String photo;
    private int role;

    public String getServletInfo() {
        return "Registration servlet";
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("windows-1251");
        response.setCharacterEncoding("windows-1251");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        try {
            DBconnect dBconnect = new DBconnect();
            PreparedStatement pst = dBconnect.getConnection().prepareStatement("SELECT login,password FROM teachers");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                users.add(rs.getString("login"));
                passwords.add(rs.getString("password"));
            }
            pst = dBconnect.getConnection().prepareStatement("SELECT user_role FROM teachers WHERE login=?");
            String user = request.getParameter("user");
            pst.setString(1, user);
            rs = pst.executeQuery();
            while (rs.next()) {
                role = rs.getInt("user_role");
            }
            pst = dBconnect.getConnection().prepareStatement("SELECT last_name,first_name,surname,academic_title,degree,organization,post,photo FROM teachers WHERE login=?");
            pst.setString(1, user);
            rs = pst.executeQuery();
            while (rs.next()) {
                lastName = rs.getString("last_name");
                firstName = rs.getString("first_name");
                surname = rs.getString("surname");
                acTitle = rs.getString("academic_title");
                degree = rs.getString("degree");
                organization = rs.getString("organization");
                post = rs.getString("post");
                photo = rs.getString("photo");
            }
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("surname", surname);
            request.setAttribute("acTitle", acTitle);
            request.setAttribute("degree", degree);
            request.setAttribute("organization", organization);
            request.setAttribute("post", post);
            request.setAttribute("photo", photo);
            if (users.contains(request.getParameter("user"))
                    & passwords.contains(request.getParameter("password")) & role == 1) {
                this.forward("/successLogin.jsp", request, response);
            } else {
                if (users.contains(request.getParameter("user"))
                        & passwords.contains(request.getParameter("password")) & role == 0) {
                    this.forward("/view.jsp", request, response);
                } else {
                   out.println("<h1>Невірний логін або пароль!</h1>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        ServletOutputStream out;
        out = resp.getOutputStream();
        String applicationPath = req.getServletContext().getRealPath("");
         String uploadFilePath = applicationPath + File.separator + "uploads"+File.separator+photo;
        FileInputStream flinp = new FileInputStream(uploadFilePath);
        BufferedInputStream buffinp = new BufferedInputStream(flinp);
        BufferedOutputStream buffoup = new BufferedOutputStream(out);
        int ch;
        while ((ch=buffinp.read()) != -1) {
            buffoup.write(ch);
        }
        buffinp.close();
        flinp.close();
        buffoup.close();
        out.close();
    }
}

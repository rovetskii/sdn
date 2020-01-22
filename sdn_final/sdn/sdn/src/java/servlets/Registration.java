package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Created by Yuriy on 23.04.2017.
 */
public class Registration extends Dispatcher {
    public String getServletInfo(){
        return "Registration servlet";
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("windows-1251");
        response.setCharacterEncoding("windows-1251");
        response.setContentType("text/html");
        if (request.getParameter("login") != null) {
            this.forward("/CheckUser", request, response);
        } else {
            if (request.getParameter("registration") != null) {
                this.forward("/registration.jsp", request, response);
            }else {
                this.forward("/registration.jsp", request, response);
            }
        }
    }
}

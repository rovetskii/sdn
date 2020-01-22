package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yuriy on 15.05.2017.
 */
public class CancelServlet extends Dispatcher {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("cancel") != null) {
            this.forward("/index.jsp", req, resp);
        }
    }
}

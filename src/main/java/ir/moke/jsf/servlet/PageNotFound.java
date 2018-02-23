package ir.moke.jsf.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@WebServlet("/pageNotFound.do")
public class PageNotFound extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal principal = req.getUserPrincipal();
        if (principal == null) {
            resp.sendRedirect("/index.xhtml");
        } else {
            resp.sendRedirect("/panel/dashboard.xhtml");
        }
    }
}

package view.UserServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/userModify")
public class UserModifyServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String account = "", password = "";
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("account"))
                account = cookie.getValue();
            if(cookie.getName().equals("password"))
                password = cookie.getValue();
        }

        req.setAttribute("account", account);
        req.setAttribute("password", password);
        req.getRequestDispatcher("userModify.jsp").forward(req, resp);
    }
    
}

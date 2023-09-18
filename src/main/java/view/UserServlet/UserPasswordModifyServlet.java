package view.UserServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = "/userPassWordModify")
public class UserPasswordModifyServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ServletContext sc = this.getServletContext();
        
        String account = "";
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("account"))
                account = cookie.getValue();
        }
        UserService userService = new UserServiceImpl();
        String res = req.getParameter("password");
        try {
            userService.modifyUser("password", res, account);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("account", account);
        req.setAttribute("password", res);
        req.getRequestDispatcher("userModify.jsp").forward(req, resp);
    }
    
}

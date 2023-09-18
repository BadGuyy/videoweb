package view.UserServlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = "/userDelete")
public class UserDeleteServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UserService userService = new UserServiceImpl();
        String account = "";
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("account"))
                account = cookie.getValue();
        }

        //删除头像
        File userIcon = new File("D:\\Develop\\tomcatRresources\\image\\profile\\" + account + ".png");
        if(userIcon.exists())
            userIcon.delete();
        //从数据库里删除账户
        try {
            userService.cancelAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("index.jsp");
    }
    
}

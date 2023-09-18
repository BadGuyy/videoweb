package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import entity.User;
import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = "/searchAccount")
public class SearchAccountServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");

		String account = req.getParameter("userAccount");
        UserService userService = new UserServiceImpl();
        User user = null;
        try {
            user = userService.queryUser(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String jUser = JSON.toJSONString(user);	
        PrintWriter pw = resp.getWriter();
		pw.print(jUser);
    }
    
}

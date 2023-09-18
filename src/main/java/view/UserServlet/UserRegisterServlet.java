package view.UserServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = "/userRegister")
public class UserRegisterServlet extends HttpServlet
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
		String account = req.getParameter("account").trim();
		String password = req.getParameter("password").trim();

		try {
			userService.userRegister(account, password);
			resp.sendRedirect("index.jsp");
		} catch (SQLException e) {
			//以后可能会重定向到其他页面来显示注册失败
			resp.sendRedirect("register.html");
		}
	}

}

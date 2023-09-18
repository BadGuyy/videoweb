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

@WebServlet(urlPatterns = "/userLogin")
public class UserLoginServlet extends HttpServlet
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
		if(req.getParameter("account").equals("") || req.getParameter("password").equals(""))
		{
			String msg = "用户名或密码为空";
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		String account = req.getParameter("account").trim();
		String password = req.getParameter("password").trim();
		String msg = null, icon = null;
		ServletContext sc = this.getServletContext();
		if(account.equals("admin"))
		{
			msg = "不能使用管理员登录";
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}

		try {
			msg = userService.userLogin(account, password);
			icon = userService.queryUser(account).getIcon();
		} catch (SQLException e) {
			msg = "";
		}
		
		if(msg.equals("登录成功"))
		{
			Cookie cookie = new Cookie("account", account);
			cookie.setMaxAge(60*60*24*7);
			resp.addCookie(cookie);
			cookie = new Cookie("password", password);
			cookie.setMaxAge(60*60*24*7);
			resp.addCookie(cookie);
			cookie = new Cookie("icon", icon);
			cookie.setMaxAge(60*60*24*7);
			resp.addCookie(cookie);

			sc.setAttribute("account", account);
			sc.setAttribute("password", password);
			sc.setAttribute("icon", icon);
			req.setAttribute("icon", icon);
			req.getRequestDispatcher("/userHome").forward(req, resp);
		}
		else
		{
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
	
}

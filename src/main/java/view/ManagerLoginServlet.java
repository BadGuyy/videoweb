package view;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ManagerService;
import service.ManagerServiceImpl;

@WebServlet(urlPatterns = "/managerLogin")
public class ManagerLoginServlet extends HttpServlet 
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		ManagerService managerService = new ManagerServiceImpl();
		String account = req.getParameter("account").trim();
		String password = req.getParameter("password").trim();
		String msg = null;

		try {
			msg = managerService.managerLogin(account, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(msg.equals("登录成功"))
		{
			req.getRequestDispatcher("WEB-INF/jsp/managerMenu.jsp").forward(req, resp);
		}
		else
		{
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("manager.jsp").forward(req, resp);
		}
	}
	
}

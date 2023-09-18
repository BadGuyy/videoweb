package view.UserServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Anime;
import service.AnimeService;
import service.AnimeServiceImpl;

@WebServlet(name = "UserHomeServlet", urlPatterns = "/userHome")
public class UserHomeServlet extends HttpServlet
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

        AnimeService animeService = new AnimeServiceImpl();
        ServletContext sc = this.getServletContext();
        String icon = "";
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("icon"))
                icon = cookie.getValue();
        }
        ArrayList<Anime> animes = null;
        int elementsPerPage = 2, numOfPages = 0, currentPage = 1, total = 0;
        try {
            animes = animeService.queryPartAnimes(currentPage, elementsPerPage);
            total = animeService.queryAnimes().size();
        } catch (SQLException e) {
            e.printStackTrace();
        }

		//确定总共该有多少页
		numOfPages = total / elementsPerPage;
		if(total % elementsPerPage != 0)
			++numOfPages;

        req.setAttribute("animes", animes);
		sc.setAttribute("currentPage", currentPage);
		req.setAttribute("currentPage", currentPage);
		sc.setAttribute("numOfPages", numOfPages);
		req.setAttribute("numOfPages", numOfPages);
		sc.setAttribute("elementsPerPage", elementsPerPage);
        req.setAttribute("icon", icon);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
    
}

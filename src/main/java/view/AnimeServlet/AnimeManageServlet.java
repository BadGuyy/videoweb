package view.AnimeServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Anime;
import service.AnimeService;
import service.AnimeServiceImpl;

@WebServlet(urlPatterns = "/animeManage")
public class AnimeManageServlet extends HttpServlet
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

        AnimeService animeService = new AnimeServiceImpl();
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
        req.getRequestDispatcher("WEB-INF/jsp/animeManage.jsp").forward(req, resp);
    }
    
}

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

@WebServlet(urlPatterns = "/animeManagePageUp")
public class AnimePageUpServlet extends HttpServlet
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

		int currentPage = (Integer) sc.getAttribute("currentPage"), 
			numOfPages = (Integer) sc.getAttribute("numOfPages"), 
			elementsPerPage = (Integer) sc.getAttribute("elementsPerPage");
        if(currentPage != 1)
            --currentPage;
		AnimeService animeService = new AnimeServiceImpl();
		ArrayList<Anime> animes = new ArrayList<Anime>();

		try {
			animes = animeService.queryPartAnimes(currentPage, elementsPerPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("animes", animes);
		sc.setAttribute("currentPage", currentPage);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("numOfPages", numOfPages);
        req.getRequestDispatcher("WEB-INF/jsp/animeManage.jsp").forward(req, resp);
    }
    
}

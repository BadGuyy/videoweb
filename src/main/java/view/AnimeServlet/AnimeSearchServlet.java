package view.AnimeServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Anime;
import service.AnimeService;
import service.AnimeServiceImpl;

@WebServlet(urlPatterns = "/searchAnime")
public class AnimeSearchServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String anime = req.getParameter("anime");
        if(anime == null || anime.equals(""))
        {
            req.getRequestDispatcher("/userHome").forward(req, resp);
            return;
        }
        String[] text = anime.split(" ");
        ArrayList<Anime> animes = new ArrayList<Anime>();
        AnimeService animeService = new AnimeServiceImpl();
        try {
            animes = animeService.searchAnimes(text);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("animes", animes);
        req.getRequestDispatcher("searchAnime.jsp").forward(req, resp);
    }
    
}

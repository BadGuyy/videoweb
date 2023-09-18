package view;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/playvideo")
public class PlayVideoServlet extends HttpServlet
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

        String animeString = (String)sc.getAttribute("animeString");
        String episode = (String)req.getParameter("episode");

        req.setAttribute("animeString", animeString);
        req.setAttribute("episode", episode);

        req.getRequestDispatcher("playvideo.jsp").forward(req, resp);
    }
    
}

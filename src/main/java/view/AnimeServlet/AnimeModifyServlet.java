package view.AnimeServlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/animeModify")
public class AnimeModifyServlet extends HttpServlet
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

        String animeName = req.getParameter("animeName");
        ServletContext sc = this.getServletContext();
        sc.setAttribute("animeName", animeName);

        File filefolder = new File("D:\\Develop\\tomcatRresources\\video\\" + animeName);
        ArrayList<String> episodes = new ArrayList<String>();
        for (File file : filefolder.listFiles())
        {
            String name = file.getName();
            if(!name.equals("cover.png"))
                episodes.add(name.split("\\.")[0]);
        }
        req.setAttribute("episodes", episodes);
        req.setAttribute("animeName", animeName);
        req.getRequestDispatcher("WEB-INF/jsp/animeModify.jsp").forward(req, resp);
    }
    
}

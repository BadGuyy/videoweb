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

@WebServlet(urlPatterns = "/modifyEpisodeName")
public class AnimeEpisodeNameModifyServlet extends HttpServlet
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

        String animeName = (String) sc.getAttribute("animeName");
        String episode = req.getParameter("episode");
        String res = req.getParameter("res");
        File file = new File("D:\\Develop\\tomcatRresources\\video\\" + animeName + "\\" + episode + ".mp4");
        File tar = new File("D:\\Develop\\tomcatRresources\\video\\" + animeName + "\\" + res + ".mp4");
        file.renameTo(tar);

        File filefolder = new File("D:\\Develop\\tomcatRresources\\video\\" + animeName);
        ArrayList<String> episodes = new ArrayList<String>();
        for (File f : filefolder.listFiles())
        {
            String name = f.getName();
            if(!name.equals("cover.png"))
                episodes.add(name.split("\\.")[0]);
        }
        
        req.setAttribute("episodes", episodes);
        req.setAttribute("animeName", animeName);
        req.getRequestDispatcher("WEB-INF/jsp/animeModify.jsp").forward(req, resp);
    }
    
}

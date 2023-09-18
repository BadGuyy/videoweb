package view.AnimeServlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AnimeService;
import service.AnimeServiceImpl;

@WebServlet(urlPatterns = "/animeDelete")
public class AnimeDeleteServlet extends HttpServlet
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
        AnimeService animeService = new AnimeServiceImpl();

        //删除动漫文件夹
        File animeFolder = new File("D:\\Develop\\tomcatRresources\\video\\" + animeName);
        if(animeFolder.listFiles().length != 0)
            for (File f : animeFolder.listFiles()) {
                f.delete();
            }
        animeFolder.delete();
        //从数据库删除动漫
        try {
            animeService.deleteAnime(animeName);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        req.getRequestDispatcher("/animeManage").forward(req, resp);
    }
    
}

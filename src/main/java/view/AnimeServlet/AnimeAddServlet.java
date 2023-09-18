package view.AnimeServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AnimeService;
import service.AnimeServiceImpl;

@WebServlet(urlPatterns = "/animeAdd")
public class AnimeAddServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String animeName = req.getParameter("animeName");

        //传件本地文件夹及封面
        File file = new File("D:\\Develop\\tomcatRresources\\video\\" + animeName);
        if(file.exists())
        {
            req.setAttribute("msg", "此动漫已存在");
            req.getRequestDispatcher("WEB-INF\\jsp\\animeAdd.jsp").forward(req, resp);
            return;
        }
        file.mkdirs();
        FileInputStream fis = new FileInputStream("D:\\Develop\\tomcatRresources\\video\\cover.png");
        FileOutputStream fos = new FileOutputStream("D:\\Develop\\tomcatRresources\\video\\" + animeName + "\\cover.png");
        byte[] bbuf = new byte[1024];
        int len;
        while((len = fis.read(bbuf)) != -1)
            fos.write(bbuf, 0, len);
        
        fis.close();
        fos.close();

        //上传至数据库
        AnimeService animeService = new AnimeServiceImpl();
        try {
            animeService.addAnime(animeName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/animeManage").forward(req, resp);
    }
    
}

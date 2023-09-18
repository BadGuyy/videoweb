package view.AnimeServlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = "/uploadCover")
public class AnimeUploadCover extends HttpServlet
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

        //上传到本地
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setFileSizeMax(1024 * 1024 * 1024);

        List<FileItem> filelist = null;
        try {
            filelist = sfu.parseRequest(req);

            FileItem fi = filelist.get(0);
            InputStream fis = fi.getInputStream();
            File file = new File("D:\\Develop\\tomcatRresources\\video\\" + animeName + "\\cover.png");
            fi.write(file);
            
            fis.close();
        } catch (FileSizeLimitExceededException e){
            String covermsg = "文件过大";
            req.setAttribute("covermsg", covermsg);
            req.getRequestDispatcher("/animeModify").forward(req, resp);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("covermsg", "上传成功");
        req.getRequestDispatcher("/animeModify").forward(req, resp);
    }
    
}

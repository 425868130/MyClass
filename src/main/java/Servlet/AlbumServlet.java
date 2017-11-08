package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Album_Dao;
import Entities.Album;
import Entities.SimpleAlbum;
import com.google.gson.Gson;

/**
 * Servlet implementation class AlbumServlet
 */
@WebServlet("/AlbumServlet")
public class AlbumServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("application/json;charset=utf-8");
        String action = request.getParameter("action");
        switch (action) {
            case "AlbumSimpleList":
                AlbumSimpleList(request, response);
                break;
            case "LoadAlbum":
                LoadAlbum(request, response);
                break;
            case "NewAlbum":
                NewAlbum(request, response);
                break;
            case "DeleteAlbum":
                DeleteAlbum(request, response);
                break;
            case "Album_Photo":
                Album_Photo(request, response);
                break;
            case "Cover_Photo":
                Cover_Photo(request, response);
                break;
            case "AllAlbum":
                AllAlbum(request, response);
                break;
            case "GetAlbumName":
                GetAlbumName(request, response);
                break;
            case "AlbumChange":
                AlbumChange(request, response);
            default:
                break;
        }
    }

    private void AlbumChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text;charset=utf-8");
        int AlbumId = Integer.parseInt(request.getParameter("AlbumID"));
        String newName = request.getParameter("NewAlbumName");
        String newAlbumText = request.getParameter("AlbumText");
        response.getWriter().println(Album_Dao.AlbumChange(AlbumId, newName, newAlbumText));
    }

    /*获取所有相册信息*/
    private void AllAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO Auto-generated method stub
        List<Album> albumList = Album_Dao.AllAlbum();
        Gson gson = new Gson();
        String RspAlbum = gson.toJson(albumList);
        response.getWriter().println(RspAlbum);
    }

    /*获取相册列表*/
    protected void AlbumSimpleList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Album> albumList = Album_Dao.AllAlbum();
        List<SimpleAlbum> list = new ArrayList<>();
        /*获取一个简单相册列表*/
        for (Album album : albumList) {
            SimpleAlbum simpleAlbum = new SimpleAlbum();
            simpleAlbum.setAlbum_id(album.getAlbum_id());
            simpleAlbum.setAlbum_name(album.getAlbum_name());
            list.add(simpleAlbum);
        }
        /*转换为Json字符串*/
        Gson gson = new Gson();
        String SimpleList = gson.toJson(list);
        /*返回前台*/
        response.getWriter().println(SimpleList);
    }

    /*获取相册封面*/
    private void Cover_Photo(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        int AlbumID = Integer.parseInt(request.getParameter("AlbumID"));
        Album_Dao.CoverPhoto(AlbumID);
    }

    /*获取相册里的所有照片*/
    private void Album_Photo(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        int AlbumID = Integer.parseInt(request.getParameter("AlbumID"));
        Album_Dao.AlbumPhoto(AlbumID);
    }

    /*删除相册*/
    private void DeleteAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO Auto-generated method stub
        response.setContentType("text;charset=utf-8");
        int AlbumID = Integer.parseInt(request.getParameter("AlbumID"));
        response.getWriter().println(Album_Dao.DeleteAlbum(AlbumID));
    }

    /*新建相册*/
    private void NewAlbum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text;charset=utf-8");
        String UserID = UserServlet.IsUerOnline(request, response);
        if (UserID == null) {
            response.getWriter().println("offline");
            return;
        }
        String AlbumName = request.getParameter("AlbumName");
        String AlbumDescription = request.getParameter("AlbumDescription");
        response.getWriter().print(Album_Dao.AddAlbum(UserID, AlbumName, AlbumDescription));
    }

    /*获取单个相册信息*/
    private Album LoadAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO Auto-generated method stub
        response.setContentType("application/json;charset=utf-8");
        int AlbumID = Integer.parseInt(request.getParameter("AlbumID").trim());
        Album album = Album_Dao.getAlbum(AlbumID);
         /*如果是前台直接的请求，就返回json*/
        if (request.getParameter("action").equals("LoadAlbum")) {
            Gson gson = new Gson();
            String Rsp = gson.toJson(album);
            response.getWriter().println(Rsp);
        }/*否则直接返回相册对象*/
        return album;
    }

    /*获取相册名*/
    private void GetAlbumName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String AlbumName = LoadAlbum(request, response).getAlbum_name();
        response.setContentType("text;charset=utf-8");
        response.getWriter().println(AlbumName);
        System.out.println(AlbumName);
    }
}

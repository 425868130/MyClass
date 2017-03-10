package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Album_Dao;
import Entities.Album;
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
        String action = request.getParameter("action");
        switch (action) {
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
                AllAibum(request, response);
                break;
            default:
                break;
        }
    }

    /*获取所有相册信息*/
    private void AllAibum(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        List<Album> albumList = Album_Dao.AllAlbum();
        Gson gson = new Gson();
        String RspAlbum = gson.toJson(albumList);
        try {
            response.getWriter().println(RspAlbum);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
    private void DeleteAlbum(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        int AlbumID = Integer.parseInt(request.getParameter("AlbumID"));
        Album_Dao.DeleteAlbum(AlbumID);
    }

    /*新建相册*/
    private void NewAlbum(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        String UserID = request.getParameter("UserID");
        String AlbumName = request.getParameter("AlbumName");
        String AlbumDescription = request.getParameter("AlbumDescription");
        Album_Dao.AddAlbum(UserID, AlbumName, AlbumDescription);
    }
    /*获取单个相册信息*/
    private void LoadAlbum(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        int AlbumID = Integer.parseInt(request.getParameter("AlbumID"));
        Album_Dao.getAlbum(AlbumID);
    }

}

package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Dynamic_Dao;
import DAO.Photo_Dao;
import Entities.Photo;
import com.google.gson.Gson;

/**
 * Servlet implementation class PhotoServlet
 */
@WebServlet("/PhotoServlet")
public class PhotoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoServlet() {
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
            case "DeletePhoto":
                DeletePhoto(request, response);
                break;
            case "getPhoto":
                getPhoto(request, response);
                break;
            default:
                break;
        }
    }

    private void getPhoto(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        int PhotoID = Integer.parseInt(request.getParameter("PhotoID"));
        Photo photo = Photo_Dao.getPhoto(PhotoID);
        Gson gson = new Gson();
        String RspPhoto = gson.toJson(photo);
        try {
            response.getWriter().println(RspPhoto);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*删除照片*/
    private void DeletePhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text;charset=utf-8");
        int PhotoID = Integer.parseInt(request.getParameter("PhotoID"));
        /*权限审核*/
        Photo PreDelete = Photo_Dao.getPhoto(PhotoID);
        String UserId = UserServlet.IsUerOnline(request, response);
        if (UserId == null) {
            response.getWriter().println("offline");
            return;
        }
        if (!PreDelete.getUser_id().equals(UserId)) {
            response.getWriter().println("NoPermission");
            return;
        }
        response.getWriter().println(Photo_Dao.DeletePhoto(PhotoID));
    }
}

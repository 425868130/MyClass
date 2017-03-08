package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		switch (action) {
		case "UpdatePhoto":
			UpdatePhoto(request,response);
			break;
		case "DeletePhoto":
			DeletePhoto(request,response);
			break;
		case "getPhoto":
			getPhoto(request,response);
			break;
		default:
			break;
		}
	}

	private void getPhoto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int PhotoID=Integer.parseInt(request.getParameter("PhotoID"));
		Photo photo = Photo_Dao.getPhoto(PhotoID);
		Gson gson=new Gson();
		String RspPhoto = gson.toJson(photo);
		try {
			response.getWriter().println(RspPhoto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void DeletePhoto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int PhotoID=Integer.parseInt(request.getParameter("PhotoID"));
		Photo_Dao.DeletePhoto(PhotoID);
	}

	private void UpdatePhoto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int DynamicID=Integer.parseInt(request.getParameter("DynamicID"));
		String UserID=request.getParameter("UserID");
		int AlbumID=Integer.parseInt(request.getParameter("AlbumID"));
		String Save=request.getParameter("Save");
		Photo_Dao.AddPhoto(DynamicID,UserID,AlbumID,Save);
	}

}

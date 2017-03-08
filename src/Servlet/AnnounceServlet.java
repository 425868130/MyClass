package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Announce_Dao;
import DAO.User_Dao;
import Entities.Announce;
import com.google.gson.Gson;

/**
 * Servlet implementation class AnnounceServlet
 */
@WebServlet("/AnnounceServlet")
public class AnnounceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnounceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		switch (action) {
		case "publishAnnounce":
			publishAnnounce(request,response);
			break;
		case "LoadAnnounce":
			LoadAnnounce(request,response);
			break;
		case "NewAnnounce":
			NewAnnounce(request,response);
			break;
		case "DeleteAnnounce":
			DeleteAnnounce(request,response);
			break;
		default:
			break;
		}
		
	}
	
	protected void publishAnnounce(HttpServletRequest request, HttpServletResponse response) {
		String UserID=request.getParameter("UserID");
		String AnnounceContent=request.getParameter("AnnounceContent");
		String Theme=request.getParameter("Theme");
		List<String> AdminUser = User_Dao.AdminUser();
		for(String user: AdminUser){
			if(user.equals(UserID)){
				Announce_Dao.AddAnnounce(UserID, AnnounceContent, Theme);
				return;
			}
		}
		try {
			response.getWriter().print("fail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void LoadAnnounce(HttpServletRequest request, HttpServletResponse response) {
		List<Announce> announceList = Announce_Dao.AllAnnounce();
				Gson gson=new Gson();
				String RspAnnounce= gson.toJson(announceList);
				try {
					response.getWriter().println(RspAnnounce);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    }
	protected void NewAnnounce(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Announce announce = Announce_Dao.getAnnounce();
		Gson gson=new Gson();
		String RspAnnounce= gson.toJson(announce);
		response.getWriter().println(RspAnnounce);
}
	protected void DeleteAnnounce(HttpServletRequest request, HttpServletResponse response) {
		int AnnounceID=Integer.parseInt(request.getParameter("AnnounceID"));
		String UserID=request.getParameter("UserID");
		List<String> AdminUser = User_Dao.AdminUser();
		for(String user: AdminUser){
			if(user.equals(UserID)){
				Announce_Dao.DeleteAnnounce(AnnounceID);
				return;
			}
		}
		try {
			response.getWriter().print("fail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
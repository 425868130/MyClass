package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Dynamic_Dao;
import Entities.Dynamic;
import Entities.Message;
import Entities.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class DynamicServlet
 */
@WebServlet("/DynamicServlet")
public class DynamicServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DynamicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (String) request.getAttribute("action");
        switch (action) {
            case "getDynamicComment":
                getDynamicComment(request, response);
                break;
            case "LoadDynamic":
                LoadDynamic(request, response);
                break;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String action = request.getParameter("action");
        response.setContentType("application/json;charset=utf-8");
        switch (action) {
            case "LoadDynamic":
                LoadDynamic(request, response);
                break;
            case "getHotDynamic":
                getHotDynamic(request, response);
                break;
            case "SendDynamic":
                SendDynamic(request, response);
                break;
            case "DynamicDelete":
                DynamicDelete(request, response);
                break;
            case "ThumbsUp":
                ThumbsUp(request, response);
                break;
            case "getDynamicComment":
                getDynamicComment(request, response);
                break;
            case "UserDynamic":
                UserDynamic(request, response);
                break;
            default:
                break;
        }
    }

    /*用户个人动态*/
    private void UserDynamic(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        String UserID = request.getParameter("UserID");
        List<Dynamic> dynamicList = Dynamic_Dao.PersonalDynamic(UserID);
        Gson gson = new Gson();
        String RspDynamic = gson.toJson(dynamicList);
        try {
            response.getWriter().println(RspDynamic);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*获取评论*/
    private void getDynamicComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO Auto-generated method stub
        int DynamicID = Integer.parseInt(request.getParameter("DynamicID"));
        List<Message> messageList = Dynamic_Dao.DynamicMessage(DynamicID);
        Gson gson = new Gson();
        String RspMessage = gson.toJson(messageList);
        response.getWriter().println(RspMessage);
    }

    /*点赞*/
    private void ThumbsUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text;charset=utf-8");
        int DynamicID = Integer.parseInt(request.getParameter("DynamicID"));
        User user = (User) request.getSession().getAttribute("UserSession");
        String UserID = user.getUser_id();
        if (UserID == null) {
            response.getWriter().print("offline");
            return;
        }
        response.getWriter().println(Dynamic_Dao.DynamicLike(DynamicID, UserID));
    }

    /*删除动态*/
    private void DynamicDelete(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        int DynamicID = Integer.parseInt(request.getParameter("DynamicID"));
        Dynamic_Dao.DeleteDynamic(DynamicID);
    }

    /*发送动态*/
    private void SendDynamic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text;charset=utf-8");
        System.out.println("SendDynamic");
        String UserID = UserServlet.IsUerOnline(request, response);
        if (UserID == null) {
            response.getWriter().println("offline");
            return;
        }
        int AlbumID = Integer.parseInt(request.getParameter("AlbumID").trim());
        String DynamicContent = request.getParameter("DynamicContent");
        response.getWriter().println(Dynamic_Dao.AddDynamic(UserID, AlbumID, DynamicContent));
        System.out.println(AlbumID);
    }

    /*获取热门公告*/
    private void getHotDynamic(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        List<Dynamic> dynamicList = Dynamic_Dao.HotDynamic();
        Gson gson = new Gson();
        String RspDynamic = gson.toJson(dynamicList);
        try {
            response.getWriter().println(RspDynamic);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*加载全部动态*/
    private void LoadDynamic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // TODO Auto-generated method stub
        response.setContentType("application/json;charset=utf-8");
        String backURL = (String) request.getAttribute("backURL");
        List<Dynamic> dynamicList = Dynamic_Dao.AllDynamic();
        request.setAttribute("DynamicList", dynamicList);
        request.getRequestDispatcher("/homePage.jsp").forward(request, response);

        Gson gson = new Gson();
        String RspDynamic = gson.toJson(dynamicList);
        response.getWriter().println(RspDynamic);
    }

}

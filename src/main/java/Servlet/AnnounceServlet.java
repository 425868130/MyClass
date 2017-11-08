package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    private static HttpSession session;

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
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String action = request.getParameter("action");
        switch (action) {
            case "publishAnnounce":
                publishAnnounce(request, response);
                break;
            case "LoadAnnounce":
                LoadAnnounce(request, response);
                break;
            case "NewAnnounce":
                NewAnnounce(request, response);
                break;
            case "DeleteAnnounce":
                DeleteAnnounce(request, response);
                break;
            default:
                break;
        }

    }

    /*发布公告*/
    protected void publishAnnounce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        String userID = UserServlet.IsUerOnline(request, response);
        response.setContentType("text;charset=utf-8");
        if (userID == null) {
            response.getWriter().println("offline");
            return;
        }
        String AnnounceContent = request.getParameter("AnnounceContent");
        String Theme = request.getParameter("Theme");
        /*判断是否为管理员*/
        if (UserServlet.isAdmin(request, response)) {
            Announce_Dao.AddAnnounce(userID, AnnounceContent, Theme);
            response.getWriter().println(true);
        } else {
            response.getWriter().println(false);
        }
    }

    /*加载公告*/
    protected void LoadAnnounce(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        /*每页加载6个公告*/
        final int count = 6;
        /*获取页码*/
        int page = Integer.parseInt(request.getParameter("NoticePage").trim());
        /*全部公告*/
        List<Announce> announceList = Announce_Dao.AllAnnounce();
        /*当前加载位置*/
        int position = page * count;
        /*单页公告列表*/
        List<Announce> announcePages = new ArrayList<>();
        /*三目运算判定逻辑:如果下的元素数目大于于每一页的元素个数，则循环上线为当前位置加上单页元素个数，否则即不足一页，上限为列表元素总数*/
        for (int i = position; i < ((announceList.size() - position) >= count ? position + count : announceList.size()); i++) {
            announcePages.add(announceList.get(i));
        }
        if (announcePages.size() == 0) {
            /*没有公告时返回一个空数组供前台判断*/
            response.getWriter().println("[]");
            return;
        }
        Gson gson = new Gson();
        String RspAnnounce = gson.toJson(announcePages);
        response.getWriter().println(RspAnnounce);
    }

    /*获取最新一条公告*/
    protected void NewAnnounce(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        Announce announce = Announce_Dao.getAnnounce();
        Gson gson = new Gson();
        String RspAnnounce = gson.toJson(announce);
        response.getWriter().println(RspAnnounce);
    }

    /*删除公告*/
    protected void DeleteAnnounce(HttpServletRequest request, HttpServletResponse response) {
        int AnnounceID = Integer.parseInt(request.getParameter("AnnounceID"));
        String UserID = request.getParameter("UserID");
        List<String> AdminUser = User_Dao.AdminUser();
        for (String user : AdminUser) {
            if (user.equals(UserID)) {
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
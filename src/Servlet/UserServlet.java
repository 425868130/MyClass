package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.User_Dao;
import Entities.User;
import com.google.gson.Gson;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static HttpSession session;
    private final static String UserSession = "UserSession";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        switch (action) {
            case "User_Login":
                UserLogin(req, resp);
                break;
            case "UserLogout":
                UserLogout(req, resp);
                break;
            case "psdReset":
                psdReset(req, resp);
                break;
            case "UserIfo":
                UserChengeIfo(req, resp);
                break;
            case "UserCheck":
                UserCheck(req, resp);
                break;
            case "UserReg":
                UserReg(req, resp);
                break;
            case "UserStatus":
                UserStatus(req, resp);
                break;
            case "isAdmin":
                isAdmin(req, resp);
                break;
            default:
                break;
        }
    }

    /*用户登录*/
    protected void UserLogin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String UserID = req.getParameter("UserID");
        String Psd = req.getParameter("Psd");
        session = req.getSession();
        /*判断用户是否可以登录*/
        Boolean status = User_Dao.UserLogin_Check(UserID, Psd);
        if (status) {
            /*如果用户可以登录则执行dao的登录方法修改用户在线信息并设置session*/
            User_Dao.UserLogin(UserID);
            User user = User_Dao.getUser(UserID);
            session.setAttribute(UserSession, user);
            resp.getWriter().println("true");
        } else {
            resp.getWriter().println("false");
        }
    }

    /**
     * 检测用户在线状态，通过session进行判断，若用户在线则返回在线用户id,否则返回null
     */
    protected String IsUerOnline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        User user = (User) session.getAttribute(UserSession);
        if (user == null) {
            out.println("<script>alert('登录已失效,请重新登录！';window.location.href = \"Login.html\";)<script>");
            return null;
        }
        return user.getUser_id();
    }

    /*用户注销*/
    protected void UserLogout(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String UserID = req.getParameter("UserID");
        User_Dao.UserLogout(UserID);
        /*销毁session*/
        session.invalidate();
        /*转向回登录界面*/
        resp.sendRedirect("Login.html");
    }

    /*密码重置的具体动作只限管理员执行*/
    protected void psdReset(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (isAdmin(req, resp)) {
            String UserID = req.getParameter("UserID");
            User_Dao.UserResetPsd(UserID);
        }
    }

    /*用户个人信息修改*/
    protected void UserChengeIfo(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String UserID = req.getParameter("UserID");
        String Nickname = req.getParameter("Nickname");
        String Telephone = req.getParameter("Telephone");
        String Signature = req.getParameter("Signature");
        String Sex = req.getParameter("Sex");
        String Head_portiait = req.getParameter("Head_portiait");
        User_Dao.UserChange_Info(UserID, Nickname, Telephone, Signature, Sex, Head_portiait);
    }

    /*用户审核*/
    protected void UserCheck(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String UserID = req.getParameter("UserID");
        User_Dao.UserCheck(UserID);
    }

    /*用户注册*/
    protected void UserReg(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String UserID = req.getParameter("UserID");
        String Psd = req.getParameter("Psd");
        String Telephone = req.getParameter("Telephone");
        User_Dao.UserReg(UserID, Psd, Telephone);
    }


    /*获取所有用户的在线状态*/
    protected void UserStatus(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        List<User> UserList = User_Dao.OnlineUser();
        String userinfo = "";
        Gson gson = new Gson();
        userinfo = gson.toJson(UserList);
        out.println(userinfo);
    }

    /*判断用户权限等级*/
    protected boolean isAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        User user = (User) session.getAttribute(UserSession);
        List<String> AdminList = User_Dao.AdminUser();
        for (String string : AdminList) {
            if (string.equals(user)) {
                return true;
            }
        }
        return false;
    }
}

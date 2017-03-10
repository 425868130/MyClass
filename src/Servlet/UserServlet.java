package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Message_Dao;
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
        System.out.println("action:" + action);
        switch (action) {
            case "UserLogin":
                UserLogin(req, resp);
                break;
            case "UserLogout":
                UserLogout(req, resp);
                break;
            case "ForgetPsd":
                ForgetPsd(req, resp);
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
            case "IsUerOnline":
                IsUerOnline(req, resp);
                break;
            default:
                break;
        }
    }

    /*用户登录*/
    protected void UserLogin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String UserID = req.getParameter("UserID");
        String Psd = req.getParameter("Psd");
        session = req.getSession();
        /*判断用户是否可以登录*/
        Boolean status = User_Dao.UserLogin_Check(UserID, Psd);
        if (status) {
            /*如果用户可以登录则执行dao的登录方法修改用户在线信息并设置session*/
            User_Dao.UserLogin(UserID);
            User user = null;
            try {
                user = User_Dao.getUser(UserID);
                session.setAttribute(UserSession, user);
            } catch (SQLException e) {
                System.out.println("获取用户信息失败！");
            }
        }
        resp.getWriter().println(status);
    }

    /**
     * 检测用户在线状态，通过session进行判断，若用户在线则返回在线用户id,否则返回null
     */
    protected String IsUerOnline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        System.out.println("isUserOnline");
        User user = (User) session.getAttribute(UserSession);
        if (user == null) {
            out.println("<script>alert('登录已失效,请重新登录！';window.location.href = \"index.html\";)<script>");
            return null;
        }
        return user.getUser_id();
    }

    /*忘记密码*/
    protected void ForgetPsd(HttpServletRequest request, HttpServletResponse respons) throws IOException {
        respons.setContentType("text;utf-8");
        String userId = request.getParameter("userName");
        String userTel = request.getParameter("userTell");
        if (User_Dao.HasUser(userId)) {
            respons.getWriter().println("true");
            String msg = "用户" + userId + "请求重置密码,是否同意！附加消息，联系方式：" + userTel;
            List<String> adminUser = User_Dao.AdminUser();
        /*向所有管理员发送消息*/
            for (String admin : adminUser) {
                Message_Dao.AddMessage(admin, "SYSTEM", 0, msg, "SYSTEM");
            }
        } else {
            respons.getWriter().println("same");
        }
    }

    /*用户注销*/
    protected void UserLogout(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text;charset=utf-8");
        System.out.println("UserLogout");
        /*从session中获取已登录的用户id*/
        session = req.getSession();
        String UserID = ((User) session.getAttribute(UserSession)).getUser_id();
        if (UserID != null) {
             /*通过Dao方法修改数据库中用户数据*/
            User_Dao.UserLogout(UserID);
        }
        /*清空session*/
        session.invalidate();
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
    protected void UserReg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text;charset=UTF-8");
        String UserID = req.getParameter("UserID");
        String Psd = req.getParameter("Psd");
        String Telephone = req.getParameter("Telephone");
        /*注册前先判断用户是否存在*/
        boolean hasUser = User_Dao.HasUser(UserID);
        if (hasUser) {
            resp.getWriter().println("same");
            System.out.println("same");
            return;
        } else {
            /* 返回DAO的操作结果给前台*/

            System.out.println("UserReg OK");
            User_Dao.UserReg(UserID, Psd, Telephone);
            resp.getWriter().println("true");

            String msg = "用户 " + UserID + " 提交了注册申请,是否通过？";
            List<String> adminUser = User_Dao.AdminUser();
        /*向所有管理员发送消息*/
            for (String admin : adminUser) {
                Message_Dao.AddMessage(admin, "SYSTEM", 0, msg, "SYSTEM");
            }
        }

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

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
import Entities.Simple.UserStatus;
import Entities.User;
import Utils.TransTime;
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
        //System.out.println("action:" + action);
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
            case "UserInformation":
                UserInformation(req, resp);
                break;
            case "UserChangeIfo":
                UserChangeIfo(req, resp);
                break;
            case "ChangePsd":
                ChangePsd(req, resp);
                break;
            case "UserSimpleInfo":
                UserSimpleInfo(req, resp);
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

    /*修改密码*/
    private void ChangePsd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text;charset=utf-8");
        String UserId = IsUerOnline(req, resp);
        String OldPsd = req.getParameter("oldPwd");
        String NewPsd = req.getParameter("newPwd");
        if (UserId != null) {
            /*返回操作结果*/
            resp.getWriter().println(User_Dao.ChangePsd(UserId, OldPsd, NewPsd));
        } else {
            resp.getWriter().println("offline");
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
        boolean status = User_Dao.UserLogin_Check(UserID, Psd);
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
    public static String IsUerOnline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        User user = (User) session.getAttribute(UserSession);
        if (user == null) {
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
        /*向所有管理员发送消息,接受人为SYSTEM，标志发送给所有管理员，发送人为待审核的用户ID*/
            Message_Dao.AddMessage("SYSTEM", userId, 0, msg, "SYSTEM_ADMIN_P");
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

    /*获取用户详细消息*/
    protected void UserInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        IsUerOnline(request, response);
        session = request.getSession();
        User user = (User) session.getAttribute(UserSession);
        /*通过登陆时间获取在线时长*/
        user.setOnlineTime(TransTime.getOnlineTime(user.getlogin_time()));
        session.setAttribute(UserSession, user);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        response.getWriter().println(json);
    }

    /*获取用户简单信息*/
    protected void UserSimpleInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        /*从session中获取当前在线的用户信息*/
        User user = (User) session.getAttribute(UserSession);
        /*构造用户简单信息json字符串*/
        String userInfo = "{\"nickname\":\"" + user.getnickname().trim() + "\",\"signature\":\"" + user.getsignature().trim() + "\",\"head_Img\":\"" + user.gethead_portiait().trim() + "\"}";
        /*返回给前端*/
        response.getWriter().println(userInfo);
    }

    /*用户个人信息修改*/
    protected void UserChangeIfo(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text;charset=utf-8");
        session = req.getSession();
        User user = (User) session.getAttribute(UserSession);
        String Nickname = req.getParameter("Nickname");
        String Telephone = req.getParameter("Telephone");
        String Signature = req.getParameter("Signature");
        String Sex = req.getParameter("Sex");
        boolean status = User_Dao.UserChange_Info(user.getUser_id(), Nickname, Telephone, Signature, Sex, user.gethead_portiait());
        System.out.println(status);
        resp.getWriter().println(status);
        /*更新session中的用户信息*/
        if (status) {
            user.setnickname(Nickname);
            user.settelephone(Telephone);
            user.setsex(Sex);
            user.setsignature(Signature);
            session.setAttribute(UserSession, user);
        }
    }

    /*用户审核操作*/
    protected void UserCheck(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text;charset=utf-8");
        if (IsUerOnline(req, resp) == null) {
            resp.getWriter().println("offline");
            return;
        }
        String HandUserID = req.getParameter("HandUserID");
        int messageID = Integer.parseInt(req.getParameter("MissageID"));
        String HandleResult = req.getParameter("HandleResult");
        if (HandleResult.equals("PASS")) {
             /*修改用户审核状态*/
            User_Dao.UserCheck(HandUserID.trim());
            /*删除对应消息*/
            Message_Dao.DeleteMessage(messageID);
            resp.getWriter().println("true");
            return;
        } else if (HandleResult.equals("REFUSE")) {
            User_Dao.DeleteUncheck_User(HandUserID.trim());
            Message_Dao.DeleteMessage(messageID);
            resp.getWriter().println("true");
            return;
        }
        resp.getWriter().println("false");

    }

    /*用户注册申请*/
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
        /*向所有管理员发送消息*/
            Message_Dao.AddMessage("SYSTEM", UserID, 0, msg, "SYSTEM_ADMIN_R");
        }

    }


    /*获取所有用户的在线状态*/
    protected void UserStatus(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        List<UserStatus> UserList = User_Dao.OnlineUser();
        String RspInfo;
        Gson gson = new Gson();
        RspInfo = gson.toJson(UserList);
        out.println(RspInfo);
    }

    /*判断用户权限等级*/
    public static boolean isAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text;charset=utf-8");
        String user = IsUerOnline(request, response);
        /*通过动作参数判断是Servlet内部调用还是前台直接调用*/
        String action = request.getParameter("action");
        /*若用户不在线且是前台请求则返回不在线状态*/
        if (user == null && action.equals("isAdmin")) {
            out.println("offline");
        } else {
            List<String> AdminList = User_Dao.AdminUser();
            for (String string : AdminList) {
                if (string.equals(user)) {
                    if (action.equals("isAdmin")) {
                        /*是管理员且是前台要求判断*/
                        out.println(true);
                    }
                    /*是管理员但为Servlet内部调用*/
                    return true;
                }
            }/*for循环执行完没有找到，说明不是管理员*/
            if (action.equals("isAdmin")) {
                out.println(false);
            }
        }
        return false;
    }

    /*通过用户ID获取用户名*/
    public static String getUserName(String UID) {
        String nick = null;
        try {
            nick = User_Dao.getUser(UID).getnickname();
        } catch (SQLException e) {
            return null;
        }
        return nick;
    }
}

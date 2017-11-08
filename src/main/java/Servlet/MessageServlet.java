package Servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.Dynamic_Dao;
import DAO.Message_Dao;
import DAO.User_Dao;
import Entities.Dynamic;
import Entities.Message;
import com.google.gson.Gson;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String JspAction = (String) req.getAttribute("action");
        if (JspAction != null) {
            switch (JspAction) {
                case "PersonalMessage":
                    PersonalMessage(req, resp);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        switch (action) {
            case "SendMessage":
                SendMessage(req, resp);
                break;
            case "MessageToAdmin":
                MessageToAdmin(req, resp);
                break;
            case "MessageDelete":
                MessageDelete(req, resp);
            case "PersonalMessage":
                PersonalMessage(req, resp);
                break;
            default:
                break;
        }
    }

    private void PersonalMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("PersonalMessage");
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        String UserId = UserServlet.IsUerOnline(req, resp);
        if (UserId == null) {
            resp.sendRedirect("/index.jsp");
            return;
        }
        List<Message> messageList = Message_Dao.getMessage(UserId);
        String backURL = (String) req.getAttribute("backURL");
        req.setAttribute("PersonalMessage", messageList);
        req.getRequestDispatcher(backURL).forward(req, resp);

       /* Gson gson = new Gson();
        String RspMessage = gson.toJson(messageList);
        try {
            ServletResponse response = null;
            response.getWriter().println(RspMessage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }

    /*发送消息*/
    protected void SendMessage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text;charset=utf-8");
        String MessageContent = req.getParameter("MessageContent");
        String MessageType = req.getParameter("MessageType");
        int DynamicID = Integer.parseInt(req.getParameter("DynamicID").trim());
        /*获取在线状态*/
        String SendUserID = UserServlet.IsUerOnline(req, resp).trim();
        if (SendUserID == null) {
            resp.getWriter().print("offline");
            return;
        }
        /*获取动态*/
        Dynamic dynamic = Dynamic_Dao.getDynamic(DynamicID);
        String ReceivedID = dynamic.getUser_id().trim();
        System.out.println("rec:" + ReceivedID + "\nsend:" + SendUserID + "\nDynamicID:" + DynamicID + "\nmessage:" + MessageContent + "\nMessageType:" + MessageType);
        boolean status = Message_Dao.AddMessage(ReceivedID, SendUserID, DynamicID, MessageContent, MessageType);
        resp.getWriter().print(status);
    }

    public static void MessageToAdmin(HttpServletRequest req, HttpServletResponse resp) {
        String Msg = req.getParameter("AdminMessageContent");
        System.out.println(Msg);
        List<String> adminUser = User_Dao.AdminUser();
        for (String admin : adminUser) {
            Message_Dao.AddMessage(admin, "SYSTEM", 0, Msg, "SYSTEM");
        }

    }

    protected void MessageDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String MissageID = req.getParameter("MissageID");
        //resp.setContentType("text/html;charset=UTF-8");
        Message_Dao.DeleteMessage(Integer.parseInt(MissageID));
    }

}

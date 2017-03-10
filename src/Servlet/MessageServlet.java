package Servlet;

import DAO.Message_Dao;
import DAO.User_Dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
                break;
            default:
                break;
        }
    }

    protected void SendMessage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String SendUserID = req.getParameter("SendID");
        String ReceivedID = req.getParameter("ReceiveID");
        String MessageContent = req.getParameter("MessageContent");
        String MessageType = req.getParameter("MessageType");
        String DynamicID = req.getParameter("DynamicID");

        resp.setContentType("text/html;charset=UTF-8");

        Message_Dao.AddMessage(ReceivedID, SendUserID, Integer.parseInt(DynamicID), MessageContent, MessageType);
    }

    public static void MessageToAdmin(HttpServletRequest req, HttpServletResponse resp) {
        String Msg = req.getParameter("AdminMessageContent");
        System.out.println(Msg);
        List<String> adminUser = User_Dao.AdminUser();
        /*向所有管理员发送消息*/
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

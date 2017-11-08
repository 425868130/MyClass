package DAO;

import Entities.Message;
import Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Message_Dao {
    private static Message message;

    //获取个人的所有消息
    public static List<Message> getMessage(String ReceiveID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(ReceiveID);
        List<Message> messageList = new ArrayList<>();
        String sql = "exec [dbo].[Pce_Personal_Message] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
                Message message = new Message();
                message.setMessage_id(JDBCUtils.rs.getInt(1));
                message.setreceive_id(JDBCUtils.rs.getString(2).trim());
                message.setsend_id(JDBCUtils.rs.getString(3).trim());
                message.setDynamic_id(JDBCUtils.rs.getInt(4));
                message.setMessage_time(JDBCUtils.rs.getString(5).substring(0, 16));
                message.setMessage_content(JDBCUtils.rs.getString(6));
                message.setMessage_type(JDBCUtils.rs.getString(7).trim());
                messageList.add(message);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return messageList;
    }

    //添加消息
    public static boolean AddMessage(String ReceiveID, String SendID, int DynamicID, String MessageContent, String MessageType) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        /*参数必须按次序添加,若动态id为0说明不是评论*/
        paramList.add(ReceiveID);
        paramList.add(SendID);
        String sql;
        if (DynamicID == 0) {
            sql = "exec Pce_Add_Message ?,?,null,?,?";
        } else {
            paramList.add(DynamicID);
            System.out.println(DynamicID);
            sql = "exec Pce_Add_Message ?,?,?,?,?";
        }
        paramList.add(MessageContent);
        paramList.add(MessageType);
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //删除消息
    public static boolean DeleteMessage(int MessageID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(MessageID);
        String sql = "exec Pce_Delete_Message ?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*获取发送给管理员的审核消息*/
    public static List<Message> getAdminMessage() {
        JDBCUtils.getConnection();
        List<Message> messageList = new ArrayList<>();
        String sql = "select * from View_AdminMessage";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                Message message = new Message();
                message.setMessage_id(JDBCUtils.rs.getInt(1));
                message.setreceive_id(JDBCUtils.rs.getString(2).trim());
                message.setsend_id(JDBCUtils.rs.getString(3).trim());
                message.setDynamic_id(JDBCUtils.rs.getInt(4));
                message.setMessage_time(JDBCUtils.rs.getString(5).substring(0, 16));
                message.setMessage_content(JDBCUtils.rs.getString(6));
                message.setMessage_type(JDBCUtils.rs.getString(7).trim());
                messageList.add(message);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return messageList;
    }

}

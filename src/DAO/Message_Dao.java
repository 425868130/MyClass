package DAO;

import Entities.Message;
import Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Message_Dao {
    private static Message message;

    /*获取全部消息*/
    public static List<Message> getMessage(String ReceiveID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(ReceiveID);
        List<Message> messageList = new ArrayList<Message>();
        String sql = "exec [dbo].[Pce_Personal_Message] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
                Message message = new Message();
                message.setMessage_id(JDBCUtils.rs.getInt(1));
                message.setreceive_id(JDBCUtils.rs.getString(2));
                message.setsend_id(JDBCUtils.rs.getString(3));
                message.setDynamic_id(JDBCUtils.rs.getInt(4));
                message.setMessage_time(JDBCUtils.rs.getString(5));
                message.setMessage_content(JDBCUtils.rs.getString(6));
                message.setMessage_type(JDBCUtils.rs.getString(7));
                messageList.add(message);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return messageList;
    }

    /*创建消息*/
    public static void AddMessage(String ReceiveID, String SendID, int DynamicID, String MessageContent, String MessageType) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(ReceiveID);
        paramList.add(SendID);
        paramList.add(DynamicID);
        paramList.add(MessageContent);
        paramList.add(MessageType);
        String sql = "exec Pce_Add_Message ?,?,?,?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*删除消息*/
    public static void DeleteMessage(int MessageID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(MessageID);
        String sql = "exec Pce_Delete_Message ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

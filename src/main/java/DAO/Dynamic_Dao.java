package DAO;

import Entities.Dynamic;
import Entities.Message;
import Entities.Photo;
import Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dynamic_Dao {
    private static Dynamic dynamic;

    //获取单条动态的信息
    public static Dynamic getDynamic(int DynamicID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(DynamicID);
        dynamic = new Dynamic();
        String sql = "exec [dbo].[Pce_A_Dynamic] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            if (JDBCUtils.rs.next()) {
                dynamic.setDynamic_id(JDBCUtils.rs.getInt(1));
                dynamic.setUser_id(JDBCUtils.rs.getString(2));
                dynamic.setAlbum_id(JDBCUtils.rs.getInt(3));
                dynamic.setDynamic_time(JDBCUtils.rs.getString(4).substring(0, 16));
                dynamic.setDynamic_content(JDBCUtils.rs.getString(5));
                dynamic.setPage_view(JDBCUtils.rs.getInt(6));
                dynamic.setLike(JDBCUtils.rs.getInt(7));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dynamic;
    }

    //发表动态
    public static boolean AddDynamic(String UserID, int AlbumID, String DynamicContent) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(AlbumID);
        paramList.add(DynamicContent);
        String sql = "exec Pce_Add_Dynamic ?,?,?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //删除动态
    public static boolean DeleteDynamic(int DynamicID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(DynamicID);
        String sql = "exec Pce_Delete_Dynamic ?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //动态点赞
    public static boolean DynamicLike(int DynamicID, String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(DynamicID);
        paramList.add(UserID);
        String sql = "exec Pce_Dynamic_Like ?,?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //动态浏览量
    public static void DynamicPageView(int DynamicID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(DynamicID);
        String sql = "exec Pce_Dynamic_Pageview ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //获取所有动态
    public static List<Dynamic> AllDynamic() {
        JDBCUtils.getConnection();
        List<Dynamic> dynamicList = new ArrayList<>();
        String sql = "select * from view_Dynamic";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                Dynamic dynamic = new Dynamic();
                dynamic.setDynamic_id(JDBCUtils.rs.getInt(1));
                dynamic.setUser_id(JDBCUtils.rs.getString(2));
                dynamic.setAlbum_id(JDBCUtils.rs.getInt(3));
                dynamic.setDynamic_time(JDBCUtils.rs.getString(4).substring(0, 16));
                dynamic.setDynamic_content(JDBCUtils.rs.getString(5));
                dynamic.setPage_view(JDBCUtils.rs.getInt(6));
                dynamic.setLike(JDBCUtils.rs.getInt(7));
                dynamicList.add(dynamic);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dynamicList;
    }

    //获取个人的所有动态
    public static List<Dynamic> PersonalDynamic(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        List<Dynamic> dynamicList = new ArrayList<Dynamic>();
        String sql = "exec [dbo].[Pce_Personal_Dynamic] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
                Dynamic dynamic = new Dynamic();
                dynamic.setDynamic_id(JDBCUtils.rs.getInt(1));
                dynamic.setUser_id(JDBCUtils.rs.getString(2));
                dynamic.setAlbum_id(JDBCUtils.rs.getInt(3));
                dynamic.setDynamic_time(JDBCUtils.rs.getString(4).substring(0, 16));
                dynamic.setDynamic_content(JDBCUtils.rs.getString(5));
                dynamic.setPage_view(JDBCUtils.rs.getInt(6));
                dynamic.setLike(JDBCUtils.rs.getInt(7));
                dynamicList.add(dynamic);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dynamicList;
    }

    //获取热门动态（点赞数排前五）
    public static List<Dynamic> HotDynamic() {
        JDBCUtils.getConnection();
        List<Dynamic> dynamicList = new ArrayList<>();
        String sql = "select * from view_Hot_Dynamic";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                Dynamic dynamic = new Dynamic();
                dynamic.setDynamic_id(JDBCUtils.rs.getInt(1));
                dynamic.setUser_id(JDBCUtils.rs.getString(2).trim());
                dynamic.setAlbum_id(JDBCUtils.rs.getInt(3));
                dynamic.setDynamic_time(JDBCUtils.rs.getString(4).substring(0, 16));
                dynamic.setDynamic_content(JDBCUtils.rs.getString(5));
                dynamic.setPage_view(JDBCUtils.rs.getInt(6));
                dynamic.setLike(JDBCUtils.rs.getInt(7));
                dynamicList.add(dynamic);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dynamicList;
    }

    //获取单条动态相关的所有消息
    public static List<Message> DynamicMessage(int DynamicID) {
        List paramList = new ArrayList();
        paramList.add(DynamicID);
        List<Message> messageList = new ArrayList<Message>();
        String sql = "exec [dbo].[Pce_Dynamic_Message] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
                Message message = new Message();
                message.setMessage_id(JDBCUtils.rs.getInt(1));
                message.setreceive_id(JDBCUtils.rs.getString(2));
                message.setsend_id(JDBCUtils.rs.getString(3));
                message.setDynamic_id(JDBCUtils.rs.getInt(4));
                message.setMessage_time(JDBCUtils.rs.getString(5).substring(0, 16));
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

    //获取单条动态的所有照片
    public static List<Photo> DynamicPhoto(int DynamicID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(DynamicID);
        List<Photo> photoList = new ArrayList<>();
        String sql = "exec [dbo].[Pce_Dynamic_Photo] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
                Photo photo = new Photo();
                photo.setPhoto_id(JDBCUtils.rs.getInt(1));
                photo.setDynamic_id(JDBCUtils.rs.getInt(2));
                photo.setUser_id(JDBCUtils.rs.getString(3).trim());
                photo.setAlbum_id(JDBCUtils.rs.getInt(4));
                photo.setPhoto_time(JDBCUtils.rs.getString(5).substring(0, 16).trim());
                photo.setsave(JDBCUtils.rs.getString(6).trim());
                photoList.add(photo);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return photoList;
    }

    /*获取动态点赞数*/
    public static int getDynamicLik(int DynamicId) {
        String sql = "select count(*) from Thumb_Up where Dynamic_id = ?";
        JDBCUtils.getConnection();
        int num = 0;
        List paramList = new ArrayList();
        paramList.add(DynamicId);
        JDBCUtils.queryData(sql, paramList);
        try {
            if (JDBCUtils.rs.next()) {
                num = JDBCUtils.rs.getInt(1);
                return num;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /*获取最新的动态id*/
    public static int getNewDynamicID() {
        String sql = "select * from View_New_Dynamic";
        JDBCUtils.getConnection();
        JDBCUtils.queryData(sql, null);
        int dynamicId = 0;
        try {
            if (JDBCUtils.rs.next()) {
                dynamicId = JDBCUtils.rs.getInt(1);
            }
        } catch (SQLException e) {
            dynamicId = 0;
        }
        return dynamicId;
    }
}

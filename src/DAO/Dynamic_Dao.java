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

    //获取单条动态详情
    public static Dynamic getDynamic(int DynamicID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(DynamicID);
        dynamic = new Dynamic();
        String sql = "exec [dbo].[Pce_A_Dynamic] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
                dynamic.setDynamic_id(JDBCUtils.rs.getInt(1));
                dynamic.setUser_id(JDBCUtils.rs.getString(2));
                dynamic.setAlbum_id(JDBCUtils.rs.getInt(3));
                dynamic.setDynamic_time(JDBCUtils.rs.getString(4));
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

    /*添加动态*/
    public static void AddDynamic(String UserID, int AlbumID, String DynamicContent) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(AlbumID);
        paramList.add(DynamicContent);
        String sql = "exec Pce_Add_Dynamic ?,?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*删除新动态*/
    public static void DeleteDynamic(int DynamicID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(DynamicID);
        String sql = "exec Pce_Delete_Dynamic ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*动态点赞*/
    public static void DynamicLike(int DynamicID, String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(DynamicID);
        paramList.add(UserID);
        String sql = "exec Pce_Dynamic_Like ?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**/
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

    /*获取所有动态*/
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
                dynamic.setDynamic_time(JDBCUtils.rs.getString(4));
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

    /*个人动态列表*/
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
                dynamic.setDynamic_time(JDBCUtils.rs.getString(4));
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

    /*热门动态*/
    public static List<Dynamic> HotDynamic() {
        JDBCUtils.getConnection();
        List<Dynamic> dynamicList = new ArrayList<>();
        String sql = "select * from view_Hot_Dynamic";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                Dynamic dynamic = new Dynamic();
                dynamic.setDynamic_id(JDBCUtils.rs.getInt(1));
                dynamic.setUser_id(JDBCUtils.rs.getString(2));
                dynamic.setAlbum_id(JDBCUtils.rs.getInt(3));
                dynamic.setDynamic_time(JDBCUtils.rs.getString(4));
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

    /*单个动态的全部消息*/
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

    /*单个动态的全部照片*/
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
                photo.setUser_id(JDBCUtils.rs.getString(3));
                photo.setAlbum_id(JDBCUtils.rs.getInt(4));
                photo.setPhoto_time(JDBCUtils.rs.getString(5));
                photo.setsave(JDBCUtils.rs.getString(6));
                photoList.add(photo);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return photoList;
    }
}

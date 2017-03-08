package DAO;

import Entities.Photo;
import Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Photo_Dao {
    private static Photo photo;

    //获取单张照片
    public static Photo getPhoto(int PhotoID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(PhotoID);
        photo = new Photo();
        String sql = "exec [dbo].[Pce_A_Photo] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
                photo.setPhoto_id(JDBCUtils.rs.getInt(1));
                photo.setDynamic_id(JDBCUtils.rs.getInt(2));
                photo.setUser_id(JDBCUtils.rs.getString(3));
                photo.setAlbum_id(JDBCUtils.rs.getInt(4));
                photo.setPhoto_time(JDBCUtils.rs.getString(5));
                photo.setsave(JDBCUtils.rs.getString(6));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return photo;
    }

    /*添加照片*/
    public static void AddPhoto(int DynamicID, String UserID, int AlbumID, String Save) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(DynamicID);
        paramList.add(UserID);
        paramList.add(AlbumID);
        paramList.add(Save);
        String sql = "exec Pce_Add_Photo ?,?,?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*删除照片*/
    public static void DeletePhoto(int PhotoID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(PhotoID);
        String sql = "exec Pce_Delete_Photo ?";
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

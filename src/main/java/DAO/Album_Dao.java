package DAO;

import Entities.Album;
import Entities.Photo;
import Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Album_Dao {
    private static Album album;

    //获取相册信息
    public static Album getAlbum(int AlbumID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(AlbumID);
        album = new Album();
        String sql = "exec [dbo].[Pce_A_Album] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            if (JDBCUtils.rs.next()) {
                album.setAlbum_id(JDBCUtils.rs.getInt(1));
                album.setUser_id(JDBCUtils.rs.getString(2).trim());
                album.setAlbum_name(JDBCUtils.rs.getString(3).trim());
                album.setAlbum_time(JDBCUtils.rs.getString(4).substring(0, 16));
                album.setAlbum_description(JDBCUtils.rs.getString(5).trim());
                album.setphoto_num(JDBCUtils.rs.getInt(6));
                album.setCover_photo("images/Default/DefaultAlbumCover.jpg");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return album;
    }

    //创建新的相册
    public static boolean AddAlbum(String UserID, String AlbumName, String AlbumDescription) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(AlbumName);
        paramList.add(AlbumDescription);
        String sql = "exec Pce_Add_Album ?,?,?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //相册信息修改
    public static boolean AlbumChange(int AlbumID, String AlbumName, String AlbumDescription) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(AlbumID);
        paramList.add(AlbumName);
        paramList.add(AlbumDescription);
        String sql = "exec Pce_Album_Change ?,?,?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }

    }

    //删除相册
    public static boolean DeleteAlbum(int AlbumID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(AlbumID);
        String sql = "exec Pce_Delete_Album ?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }

    }

    //获取相册里的每张照片
    public static List<Photo> AlbumPhoto(int AlbumID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(AlbumID);
        List<Photo> photoList = new ArrayList<>();
        String sql = "exec [dbo].[Pce_Album_Photo] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
                Photo photo = new Photo();
                photo.setPhoto_id(JDBCUtils.rs.getInt(1));
                photo.setDynamic_id(JDBCUtils.rs.getInt(2));
                photo.setUser_id(JDBCUtils.rs.getString(3).trim());
                photo.setAlbum_id(JDBCUtils.rs.getInt(4));
                photo.setPhoto_time(JDBCUtils.rs.getString(5).substring(0, 16));
                photo.setsave(JDBCUtils.rs.getString(6).trim());
                photoList.add(photo);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return photoList;
    }

    //获取相册里的第一张照片，如果没有返回系统给的一张照片
    public static String CoverPhoto(int AlbumID) {
        List<Photo> list = AlbumPhoto(AlbumID);
        if (list.isEmpty()) {
            return "images/Default/DefaultAlbumCover.jpg";
        }
        return list.get(0).getsave();
    }

    //获取所有相册信息
    public static List<Album> AllAlbum() {
        JDBCUtils.getConnection();
        List<Album> albumList = new ArrayList<>();
        String sql = "select * from view_Album";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                Album album = new Album();
                album.setAlbum_id(JDBCUtils.rs.getInt(1));
                album.setUser_id(JDBCUtils.rs.getString(2).trim());
                album.setAlbum_name(JDBCUtils.rs.getString(3).trim());
                album.setAlbum_time(JDBCUtils.rs.getString(4).substring(0, 16));
                album.setAlbum_description(JDBCUtils.rs.getString(5).trim());
                album.setphoto_num(JDBCUtils.rs.getInt(6));
                album.setCover_photo("images/Default/DefaultAlbumCover.jpg");
                albumList.add(album);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return albumList;
    }
}
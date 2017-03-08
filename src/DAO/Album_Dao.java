package DAO;

import Entities.Album;
import Entities.Photo;
import Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Album_Dao{
    private static Album album;

    /*获取单个相册*/
    public static Album getAlbum(int AlbumID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(AlbumID);
        album = new Album();
        String sql = "exec [dbo].[Pce_A_Album] ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
                album.setAlbum_id(JDBCUtils.rs.getInt(1));
                album.setUser_id(JDBCUtils.rs.getString(2));
                album.setAlbum_name(JDBCUtils.rs.getString(3));
                album.setAlbum_time(JDBCUtils.rs.getString(4));
                album.setAlbum_description(JDBCUtils.rs.getString(5));
                album.setphoto_num(JDBCUtils.rs.getInt(6));
                album.setCover_photo(CoverPhoto(AlbumID));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/*        System.out.println(album.getAlbum_id());
        System.out.println(album.getUser_id());
        System.out.println(album.getAlbum_name());
        System.out.println(album.getAlbum_time());
        System.out.println(album.getAlbum_description());
        System.out.println(album.getphoto_num());
        System.out.println(album.getCover_photo());*/
        return album;
    }

    /*创建相册*/
    public static void AddAlbum(String UserID, String AlbumName, String AlbumDescription) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(AlbumName);
        paramList.add(AlbumDescription);
        String sql = "exec Pce_Add_Album ?,?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*修改相册信息*/
    public static void AlbumChange(int AlbumID, String AlbumName, String AlbumDescription) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(AlbumID);
        paramList.add(AlbumName);
        paramList.add(AlbumDescription);
        String sql = "exec Pce_Album_Change ?,?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*删除相册*/
    public static void DeleteAlbum(int AlbumID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(AlbumID);
        String sql = "exec Pce_Delete_Album ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*获取一个相册内的所有照片*/
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


    /*获取相册封面*/
    public static String CoverPhoto(int AlbumID) {
        List<Photo> list = AlbumPhoto(AlbumID);
        if (list.isEmpty()) {
            return "imgages/emptyAlbum.jpg";
        }
        return list.get(0).getsave();
    }

    /*获取所有相册列表*/
    public static List<Album> AllAlbum() {
        JDBCUtils.getConnection();
        List<Album> albumList = new ArrayList<>();
        String sql = "select * from view_Album";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                Album album = new Album();
                album.setAlbum_id(JDBCUtils.rs.getInt(1));
                album.setUser_id(JDBCUtils.rs.getString(2));
                album.setAlbum_name(JDBCUtils.rs.getString(3));
                album.setAlbum_time(JDBCUtils.rs.getString(4));
                album.setAlbum_description(JDBCUtils.rs.getString(5));
                album.setphoto_num(JDBCUtils.rs.getInt(6));
                albumList.add(album);
/*                System.out.println(album.getAlbum_id());
                System.out.println(album.getUser_id());
                System.out.println(album.getAlbum_name());
                System.out.println(album.getAlbum_time());
                System.out.println(album.getAlbum_description());
                System.out.println(album.getphoto_num());*/
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return albumList;
    }
}

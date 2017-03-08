package DAO;

import Entities.Announce;
import Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Announce_Dao {
    private static Announce announce;
    /*获取最新公告*/
    public static Announce getAnnounce() {
        JDBCUtils.getConnection();
        announce = new Announce();
        String sql = "select * from View_New_Announce";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                announce.setAnnounce_id(JDBCUtils.rs.getInt(1));
                announce.setUser_id(JDBCUtils.rs.getString(2));
                announce.setcontent(JDBCUtils.rs.getString(3));
                announce.settheme(JDBCUtils.rs.getString(4));
                announce.setAnnounce_time(JDBCUtils.rs.getString(5));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/*        System.out.println(announce.getAnnounce_id());
        System.out.println(announce.getUser_id());
        System.out.println(announce.getcontent());
        System.out.println(announce.gettheme());
        System.out.println(announce.getAnnounce_time());*/
        return announce;
    }

    /*发布公告*/
    public static void AddAnnounce(String UserID, String Content, String Theme) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(Content);
        paramList.add(Theme);
        String sql = "exec Pce_Add_Announce ?,?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*删除公告*/
    public static void DeleteAnnounce(int AnnounceID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(AnnounceID);
        String sql = "exec Pce_Delete_Announce ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*获取所有公告*/
    public static List<Announce> AllAnnounce() {
        JDBCUtils.getConnection();
        List<Announce> announceList = new ArrayList<>();
        String sql = "select * from view_Announce";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                Announce announce = new Announce();
                announce.setAnnounce_id(JDBCUtils.rs.getInt(1));
                announce.setUser_id(JDBCUtils.rs.getString(2));
                announce.setcontent(JDBCUtils.rs.getString(3));
                announce.settheme(JDBCUtils.rs.getString(4));
                announce.setAnnounce_time(JDBCUtils.rs.getString(5));
                announceList.add(announce);
                System.out.println(announce.getAnnounce_id());
                System.out.println(announce.getUser_id());
                System.out.println(announce.getcontent());
                System.out.println(announce.gettheme());
                System.out.println(announce.getAnnounce_time());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return announceList;
    }
}

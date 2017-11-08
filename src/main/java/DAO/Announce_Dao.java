package DAO;

import Entities.Announce;
import Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Announce_Dao {
    private static Announce announce;

    //获取最新一条公告到首页
    public static Announce getAnnounce() {
        JDBCUtils.getConnection();
        announce = new Announce();
        String sql = "select * from View_New_Announce";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                announce.setAnnounce_id(JDBCUtils.rs.getInt(1));
                announce.setUser_id(JDBCUtils.rs.getString(2));
                announce.setAnnounceContent(JDBCUtils.rs.getString(3));
                announce.setTheme(JDBCUtils.rs.getString(4));
                announce.setAnnounce_time(JDBCUtils.rs.getString(5).substring(0, 16).trim());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return announce;
    }

    //发布新的公告
    public static boolean AddAnnounce(String UserID, String Content, String Theme) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(Content);
        paramList.add(Theme);
        String sql = "exec Pce_Add_Announce ?,?,?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //删除公告
    public static boolean DeleteAnnounce(int AnnounceID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(AnnounceID);
        String sql = "exec Pce_Delete_Announce ?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //获取所有公告
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
                announce.setAnnounceContent(JDBCUtils.rs.getString(3));
                announce.setTheme(JDBCUtils.rs.getString(4));
                announce.setAnnounce_time(JDBCUtils.rs.getString(5).substring(0, 16).trim());
                announceList.add(announce);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return announceList;
    }
}

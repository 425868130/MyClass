package DAO;

import Entities.Simple.UserStatus;
import Entities.User;
import Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_Dao {
    private static User user;

    //获取用户个人信息
    public static User getUser(String UserID) throws SQLException {
        JDBCUtils.getConnection();
        List<String> paramList = new ArrayList<>();
        paramList.add(UserID);
        user = new User();
        String sql = "exec Pce_User_Detail ?";
        JDBCUtils.queryData(sql, paramList);
        if (JDBCUtils.rs.next()) {
            user.setUser_id(JDBCUtils.rs.getString(1).trim());
            user.setnickname(JDBCUtils.rs.getString(2).trim());
            user.sethead_portiait(JDBCUtils.rs.getString(3).trim());
            user.setsignature(JDBCUtils.rs.getString(4).trim());
            user.setsex(JDBCUtils.rs.getString(5).trim());
            user.settelephone(JDBCUtils.rs.getString(6).trim());
            user.setlogin_num(JDBCUtils.rs.getInt(7));
            String loginTime = JDBCUtils.rs.getString(8).substring(0, 16).trim();
            user.setlogin_time(loginTime);
            user.setonline(JDBCUtils.rs.getBoolean(9));
            user.setlevel(JDBCUtils.rs.getBoolean(10));
            user.setcheck(JDBCUtils.rs.getBoolean(11));
        }
        return user;
    }

    /*获取用户昵称*/
    public static String getUserName(String UserID) {
        String name;
        try {
            name = getUser(UserID).getnickname();
        } catch (SQLException e) {
            return null;
        }
        return name;
    }

    /*判断该用户是否已经存在*/
    public static boolean HasUser(String UserID) {
        JDBCUtils.getConnection();
        List<String> paramList = new ArrayList<>();
        paramList.add(UserID);
        String sql = "exec Pce_User_Simpleinfo ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            if (JDBCUtils.rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("hasUser:" + false);
        return false;
    }

    //修改密码
    public static boolean ChangePsd(String UserID, String psdOld, String psdNew) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(psdOld);
        paramList.add(psdNew);
        String sql = "exec Pce_User_Change_psd ?,?,?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //修改用户权限等级
    public static boolean ChangeLevel(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_User_Changelevel ?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //用户审核
    public static boolean UserCheck(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_User_Check ?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //用户信息修改
    public static boolean UserChange_Info(String UserID, String Nickname, String Telephone, String Signature, String Sex, String Head_portiait) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(Nickname);
        paramList.add(Telephone);
        paramList.add(Signature);
        paramList.add(Sex);
        paramList.add(Head_portiait);
        String sql = "exec Pce_User_Change ?,?,?,?,?,?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //用户登录
    public static boolean UserLogin(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_User_Login ?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }

    }

    //用户注销
    public static boolean UserLogout(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_User_Logout ?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //重置密码
    public static boolean UserResetPsd(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_User_ResetPsd ?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //删除未审核的用户
    public static boolean DeleteUncheck_User(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_Delete_Uncheck_User ?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //用户登录信息的审核
    public static boolean UserLogin_Check(String UserID, String Psd) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(Psd);
        String sql = "exec Pce_User_Login_Check ?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            if (JDBCUtils.rs.next()) {
                JDBCUtils.closeAll();
                return true;
            } else {
                JDBCUtils.closeAll();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //用户注册
    public static boolean UserReg(String UserID, String Psd, String Telephone) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(Psd);
        paramList.add(Telephone);
        String sql = "exec Pce_User_Reg ?,?,?";
        int Rfcount = JDBCUtils.updateData(sql, paramList);
        try {
            JDBCUtils.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (Rfcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    //获取用户在线情况统计
    public static List<UserStatus> OnlineUser() {
        JDBCUtils.getConnection();
        List<UserStatus> userList = new ArrayList<>();
        String sql = "select * from view_Online_User";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                UserStatus user = new UserStatus();
                user.setUser_id(JDBCUtils.rs.getString(1).trim());
                user.setNickname(JDBCUtils.rs.getString(2).trim());
                user.setLogin_time(JDBCUtils.rs.getString(3).substring(0, 16));
                user.setLogin_num(JDBCUtils.rs.getInt(4));
                user.setOnline(JDBCUtils.rs.getBoolean(5));
                userList.add(user);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userList;
    }

    //获取管理人员的账号id
    public static List<String> AdminUser() {
        JDBCUtils.getConnection();
        List<String> stringList = new ArrayList<>();
        String sql = "select * from view_Admin_User";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                String string = JDBCUtils.rs.getString(1).trim();
                stringList.add(string);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stringList;
    }
}

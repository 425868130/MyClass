package DAO;

import Entities.User;
import Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_Dao {
    private static User user;

    /*获取单个用户*/
    public static User getUser(String UserID) {
        JDBCUtils.getConnection();
        List<String> paramList = new ArrayList<>();
        paramList.add(UserID);
        user = new User();
        String sql = "exec Pce_User_Detail ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
                user.setUser_id(JDBCUtils.rs.getString(1));
                user.setnickname(JDBCUtils.rs.getString(2));
                user.sethead_portiait(JDBCUtils.rs.getString(3));
                user.setsignature(JDBCUtils.rs.getString(4));
                user.setsex(JDBCUtils.rs.getString(5));
                user.settelephone(JDBCUtils.rs.getString(6));
                user.setlogin_num(JDBCUtils.rs.getInt(7));
                user.setlogin_time(JDBCUtils.rs.getString(8));
                user.setonline(JDBCUtils.rs.getByte(9));
                user.setlevel(JDBCUtils.rs.getByte(10));
                user.setcheck(JDBCUtils.rs.getByte(11));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }

    /*修改密码*/
    public static void ChangePsd(String UserID, String PSD) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(PSD);
        String sql = "exec Pce_User_Change_psd ?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*改变用户权限等级*/
    public static void ChangeLevel(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_User_Changelevel ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*用户审核*/
    public static void UserCheck(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_User_Check ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*修改用户信息*/
    public static void UserChange_Info(String UserID, String Nickname, String Telephone, String Signature, String Sex, String Head_portiait) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(Nickname);
        paramList.add(Telephone);
        paramList.add(Signature);
        paramList.add(Sex);
        paramList.add(Head_portiait);
        String sql = "exec Pce_User_Change ?,?,?,?,?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*用户登录*/
    public static void UserLogin(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_User_Login ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /*用户注销*/
    public static void UserLogout(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_User_Logout ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*重置密码*/
    public static void UserResetPsd(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_User_ResetPsd ?";
        JDBCUtils.queryData(sql, paramList);
    }

    /*删除未审核的用户*/
    public static void DeleteUncheck_User(String UserID) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        String sql = "exec Pce_Delete_Uncheck_User ?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*用户登录检测*/
    public static boolean UserLogin_Check(String UserID, String Psd) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(Psd);
        String sql = "exec Pce_User_Login_Check ?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            if (JDBCUtils.rs.getRow() == 0) {
                return false;
            } else {
                return true;
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /*用户注册*/
    public static void UserReg(String UserID, String Psd, String Telephone) {
        JDBCUtils.getConnection();
        List paramList = new ArrayList();
        paramList.add(UserID);
        paramList.add(Psd);
        paramList.add(Telephone);
        String sql = "exec Pce_User_Reg ?,?,?";
        JDBCUtils.queryData(sql, paramList);
        try {
            while (JDBCUtils.rs.next()) {
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*在线用户列表*/
    public static List<User> OnlineUser() {
        JDBCUtils.getConnection();
        List<User> userList = new ArrayList<>();
        String sql = "select * from view_Online_User";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                User user = new User();
                user.setUser_id(JDBCUtils.rs.getString(1));
                user.setnickname(JDBCUtils.rs.getString(2));
                user.setlogin_time(JDBCUtils.rs.getString(3));
                user.setlogin_num(JDBCUtils.rs.getInt(4));
                user.setonline(JDBCUtils.rs.getByte(5));
                userList.add(user);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userList;
    }

    /*管理员用户表*/
    public static List<String> AdminUser() {
        JDBCUtils.getConnection();
        List<String> stringList = new ArrayList<>();
        String sql = "select * from view_Admin_User";
        JDBCUtils.queryData(sql, null);
        try {
            while (JDBCUtils.rs.next()) {
                String string = JDBCUtils.rs.getString(1);
                stringList.add(string);
                System.out.println(string);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stringList;
    }

}

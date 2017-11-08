package Entities.Simple;

/**
 * Created by Dream Sky on 2017/3/10.
 */
public class UserStatus {
    private String User_id;
    private String nickname;
    private String login_time;
    private boolean online;
    private int login_num;

    public String getUser_id() {
        return User_id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLogin_time() {
        return login_time;
    }

    public boolean getOnline() {
        return online;
    }

    public int getLogin_num() {
        return login_num;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setLogin_num(int login_num) {
        this.login_num = login_num;
    }
}

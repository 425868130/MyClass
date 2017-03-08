package Entities;

public class User {
    private String User_id;
    private String psd;
    private String nickname;
    private String telephone;
    private String signature;
    private String sex;
    private String head_portiait;
    private String login_time;
    private byte level;
    private byte check;
    private byte online;
    private int login_num;

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String User_id) {
        this.User_id = User_id;
    }

    public String getpsd() {
        return psd;
    }

    public void setpsd(String psd) {
        this.psd = psd;
    }

    public String getnickname() {
        return nickname;
    }

    public void setnickname(String nickname) {
        this.nickname = nickname;
    }

    public String gettelephone() {
        return telephone;
    }

    public void settelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getsignature() {
        return signature;
    }

    public void setsignature(String signature) {
        this.signature = signature;
    }

    public String getsex() {
        return sex;
    }

    public void setsex(String sex) {
        this.sex = sex;
    }

    public String gethead_portiait() {
        return head_portiait;
    }

    public void sethead_portiait(String head_portiait) {
        this.head_portiait = head_portiait;
    }

    public String getlogin_time() {
        return login_time;
    }

    public void setlogin_time(String login_time) {
        this.login_time = login_time;
    }

    public byte getlevel() {
        return level;
    }

    public void setlevel(byte level) {
        this.level = level;
    }

    public byte getcheck() {
        return check;
    }

    public void setcheck(byte check) {
        this.check = check;
    }

    public byte getonline() {
        return online;
    }

    public void setonline(byte online) {
        this.online = online;
    }

    public int getlogin_num() {
        return login_num;
    }

    public void setlogin_num(int login_num) {
        this.login_num = login_num;
    }
}
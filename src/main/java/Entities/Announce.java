package Entities;

public class Announce {
    private int Announce_id;
    private String User_id;
    private String AnnounceContent;
    private String theme;
    private String Announce_time;

    public int getAnnounce_id() {
        return Announce_id;
    }

    public void setAnnounce_id(int Announce_id) {
        this.Announce_id = Announce_id;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String User_id) {
        this.User_id = User_id;
    }

    public void setAnnounceContent(String announceContent) {
        AnnounceContent = announceContent;
    }

    public String getAnnounceContent() {
        return AnnounceContent;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAnnounce_time() {
        return Announce_time;
    }

    public void setAnnounce_time(String Announce_time) {
        this.Announce_time = Announce_time;
    }

}

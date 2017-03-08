package Entities;

public class Photo {
    private int Photo_id;
    private int Dynamic_id;
    private int Album_id;
    private String User_id;
    private String Photo_time;
    private String save;

    public int getPhoto_id() {
        return Photo_id;
    }

    public void setPhoto_id(int Photo_id) {
        this.Photo_id = Photo_id;
    }

    public int getDynamic_id() {
        return Dynamic_id;
    }

    public void setDynamic_id(int Dynamic_id) {
        this.Dynamic_id = Dynamic_id;
    }

    public int getAlbum_id() {
        return Album_id;
    }

    public void setAlbum_id(int Album_id) {
        this.Album_id = Album_id;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String User_id) {
        this.User_id = User_id;
    }

    public String getPhoto_time() {
        return Photo_time;
    }

    public void setPhoto_time(String Photo_time) {
        this.Photo_time = Photo_time;
    }

    public String getsave() {
        return save;
    }

    public void setsave(String save) {
        this.save = save;
    }
}

package Entities;

public class Album {
    private int Album_id;
    private String User_id;
    private String Album_name;
    private String Album_description;
    private String Album_time;
    private int photo_num;
    private String Cover_photo;


    public String getCover_photo() {
        return Cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        Cover_photo = cover_photo;
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

    public String getAlbum_name() {
        return Album_name;
    }

    public void setAlbum_name(String Album_name) {
        this.Album_name = Album_name;
    }

    public String getAlbum_description() {
        return Album_description;
    }

    public void setAlbum_description(String Album_description) {
        this.Album_description = Album_description;
    }

    public String getAlbum_time() {
        return Album_time;
    }

    public void setAlbum_time(String Album_time) {
        this.Album_time = Album_time;
    }

    public int getphoto_num() {
        return photo_num;
    }

    public void setphoto_num(int photo_num) {
        this.photo_num = photo_num;
    }

}
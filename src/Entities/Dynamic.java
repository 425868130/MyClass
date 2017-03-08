package Entities;

public class Dynamic {
    private int Dynamic_id;
    private int Album_id;
    private String User_id;
    private String Dynamic_time;
    private String Dynamic_content;
    private int page_view;
    private int like;

    public void setDynamic_id(int dynamic_id) {
        Dynamic_id = dynamic_id;
    }

    public void setAlbum_id(int album_id) {
        Album_id = album_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public void setDynamic_time(String dynamic_time) {
        Dynamic_time = dynamic_time;
    }

    public void setDynamic_content(String dynamic_content) {
        Dynamic_content = dynamic_content;
    }

    public void setPage_view(int page_view) {
        this.page_view = page_view;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDynamic_id() {
        return Dynamic_id;
    }

    public int getAlbum_id() {
        return Album_id;
    }

    public String getUser_id() {
        return User_id;
    }

    public String getDynamic_time() {
        return Dynamic_time;
    }

    public String getDynamic_content() {
        return Dynamic_content;
    }

    public int getPage_view() {
        return page_view;
    }

    public int getLike() {
        return like;
    }
}

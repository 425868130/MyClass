package Entities;

/**
 * Created by Dream Sky on 2017/3/10.
 * 相册列表类
 */
public class SimpleAlbum {
    private int Album_id;
    private String Album_name;

    public void setAlbum_id(int album_id) {
        Album_id = album_id;
    }

    public void setAlbum_name(String album_name) {
        Album_name = album_name;
    }

    public int getAlbum_id() {

        return Album_id;
    }

    public String getAlbum_name() {
        return Album_name;
    }
}

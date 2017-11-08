package Entities;

public class Message {
    private int Message_id;
    private int Dynamic_id;
    private String receive_id;
    private String send_id;
    private String Message_content;
    private String Message_time;
    private String Message_type;

    public int getMessage_id() {
        return Message_id;
    }

    public void setMessage_id(int Message_id) {
        this.Message_id = Message_id;
    }

    public int getDynamic_id() {
        return Dynamic_id;
    }

    public void setDynamic_id(int Dynamic_id) {
        this.Dynamic_id = Dynamic_id;
    }

    public String getreceive_id() {
        return receive_id;
    }

    public void setreceive_id(String receive_id) {
        this.receive_id = receive_id;
    }

    public String getsend_id() {
        return send_id;
    }

    public void setsend_id(String send_id) {
        this.send_id = send_id;
    }

    public String getMessage_content() {
        return Message_content;
    }

    public void setMessage_content(String Message_content) {
        this.Message_content = Message_content;
    }

    public String getMessage_time() {
        return Message_time;
    }

    public void setMessage_time(String Message_time) {
        this.Message_time = Message_time;
    }

    public String getMessage_type() {
        return Message_type;
    }

    public void setMessage_type(String Message_type) {
        this.Message_type = Message_type;
    }
}

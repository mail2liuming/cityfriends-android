package nz.co.liuming.cityfriends.home.model;

import nz.co.liuming.cityfriends.common.rest.model.BaseModel;

/**
 * Created by liuming on 1/07/16.
 */
public class MessageFeed implements BaseModel {

    public static final int TYPE_REQUEST_FRIEND=1;
    public static final int TYPE_REQUEST_FRIEND_ACCEPTED=2;
    public static final int TYPE_REQUEST_FRIEND_REJECTED=3;
    public static final int TYPE_COMMON_TEXT=4;

    public static final int STATUS_UNREAD= 15;
    public static final int STATUS_READ= 16;

    /**
     * receiver_id : 2
     * msg_type : 4
     * msg_content : Hi
     */

    private int receiver_id;
    private int msg_type;
    private String msg_content;
    /**
     * id : 16
     * status : null
     * sender_id : 1
     * receiver_name : Example User2
     * sender_name : Example User
     */

    private int id;
    private Object status;
    private int sender_id;
    private String receiver_name;
    private String sender_name;

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public int getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(int msg_type) {
        this.msg_type = msg_type;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }
}

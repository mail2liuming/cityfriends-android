package nz.co.liuming.cityfriends.home.model;

import nz.co.liuming.cityfriends.common.rest.model.BaseModel;

/**
 * Created by liuming on 1/07/16.
 */
public class CalendarFeed implements BaseModel {

    /**
     * id : 1
     * calendar_type : 1
     * exact_time : 2016-06-11T00:00:00.000Z
     * feed_content : 5kg available
     * user_name : Example User
     * user_id : 1
     */

    private int id;
    private int calendar_type;
    private String exact_time;
    private String feed_content;
    private String user_name;
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalendar_type() {
        return calendar_type;
    }

    public void setCalendar_type(int calendar_type) {
        this.calendar_type = calendar_type;
    }

    public String getExact_time() {
        return exact_time;
    }

    public void setExact_time(String exact_time) {
        this.exact_time = exact_time;
    }

    public String getFeed_content() {
        return feed_content;
    }

    public void setFeed_content(String feed_content) {
        this.feed_content = feed_content;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

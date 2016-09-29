package nz.co.liuming.cityfriends.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import nz.co.liuming.cityfriends.common.rest.model.BaseModel;

/**
 * Created by liuming on 30/06/16.
 */
public class Feed implements Parcelable , BaseModel {

    /**
     * id : 15
     * feed_type : 1
     * start_time : 2016-06-10T00:00:00.000Z
     * start_place : hongkong
     * content : 5
     * user_id : 3
     * user_name : Example User3
     */

    private int id;
    private int feed_type;
    private String start_time;
    private String start_place;
    private String content;
    private int user_id;
    private String user_name;
    /**
     * feed_content : 5kg available
     */

    private String feed_content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFeed_type() {
        return feed_type;
    }

    public void setFeed_type(int feed_type) {
        this.feed_type = feed_type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_place() {
        return start_place;
    }

    public void setStart_place(String start_place) {
        this.start_place = start_place;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }



    public String getFeed_content() {
        return feed_content;
    }

    public void setFeed_content(String feed_content) {
        this.feed_content = feed_content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.feed_type);
        dest.writeString(this.start_time);
        dest.writeString(this.start_place);
        dest.writeString(this.content);
        dest.writeInt(this.user_id);
        dest.writeString(this.user_name);
        dest.writeString(this.feed_content);
    }

    public Feed() {
    }

    protected Feed(Parcel in) {
        this.id = in.readInt();
        this.feed_type = in.readInt();
        this.start_time = in.readString();
        this.start_place = in.readString();
        this.content = in.readString();
        this.user_id = in.readInt();
        this.user_name = in.readString();
        this.feed_content = in.readString();
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel source) {
            return new Feed(source);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };
}

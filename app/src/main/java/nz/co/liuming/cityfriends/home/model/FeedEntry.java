package nz.co.liuming.cityfriends.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import nz.co.liuming.cityfriends.common.rest.model.BaseModel;

/**
 * Created by liuming on 30/06/16.
 */
public class FeedEntry implements Parcelable , BaseModel {

    /**
     * id : 15
     * feed_type : 1
     * start_time : 2016-06-10T00:00:00.000Z
     * start_place : hongkong
     * end_place : auckland
     * end_time : 2016-06-29T00:00:00.000Z
     * available : 5
     * user_id : 3
     * user_name : Example User3
     */

    public static  final int TYPE_PROVIDER = 1;
    public static  final int TYPE_CONSUMER = 2;

    private int id;
    private int feed_type;
    private String start_time;
    private String start_place;
    private String end_place;
    private String end_time;
    private String available;
    private int user_id;
    private String user_name;

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

    public String getEnd_place() {
        return end_place;
    }

    public void setEnd_place(String end_place) {
        this.end_place = end_place;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
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
        dest.writeString(this.end_place);
        dest.writeString(this.end_time);
        dest.writeString(this.available);
        dest.writeInt(this.user_id);
        dest.writeString(this.user_name);
    }

    public FeedEntry() {
    }

    protected FeedEntry(Parcel in) {
        this.id = in.readInt();
        this.feed_type = in.readInt();
        this.start_time = in.readString();
        this.start_place = in.readString();
        this.end_place = in.readString();
        this.end_time = in.readString();
        this.available = in.readString();
        this.user_id = in.readInt();
        this.user_name = in.readString();
    }

    public static final Parcelable.Creator<FeedEntry> CREATOR = new Parcelable.Creator<FeedEntry>() {
        @Override
        public FeedEntry createFromParcel(Parcel source) {
            return new FeedEntry(source);
        }

        @Override
        public FeedEntry[] newArray(int size) {
            return new FeedEntry[size];
        }
    };
}

package nz.co.liuming.cityfriends.users.model;

import android.os.Parcel;
import android.os.Parcelable;

import nz.co.liuming.cityfriends.common.rest.model.BaseModel;

/**
 * Created by liuming on 29/09/16.
 */

public class Friend implements BaseModel,Parcelable,Userable{

    /**
     * id : 2
     * name : Example User2
     */

    private int id;
    private String name;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.email);
    }

    public Friend() {
    }

    protected Friend(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel source) {
            return new Friend(source);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };
}

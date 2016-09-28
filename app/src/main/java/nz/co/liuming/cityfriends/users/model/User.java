package nz.co.liuming.cityfriends.users.model;

import android.os.Parcel;
import android.os.Parcelable;

import nz.co.liuming.cityfriends.common.rest.model.BaseModel;

/**
 * Created by liuming on 17/06/16.
 */
public class User implements BaseModel, Parcelable {

    /**
     * id : 1
     * name : Example User
     * email : example@railstutorial.org
     * token : bzQ-YX_0FtBuAzdO6J8RBw
     */

    private int id;
    private String name;
    private String email;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        dest.writeString(this.token);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
        this.token = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

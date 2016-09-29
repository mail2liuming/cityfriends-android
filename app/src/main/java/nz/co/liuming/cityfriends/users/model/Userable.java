package nz.co.liuming.cityfriends.users.model;

import android.os.Parcelable;

/**
 * Created by liuming on 30/09/16.
 */

public interface Userable extends Parcelable {
    public int getId();

    public String getName();

    public String getEmail();
}

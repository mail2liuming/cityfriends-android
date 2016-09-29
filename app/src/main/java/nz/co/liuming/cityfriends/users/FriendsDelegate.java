package nz.co.liuming.cityfriends.users;

import java.util.ArrayList;
import java.util.List;

import nz.co.liuming.cityfriends.users.model.Friend;

/**
 * Created by liuming on 28/09/16.
 */

public class FriendsDelegate {

    private List<Friend> mFriendsList = new ArrayList<>();

    public void loadFriends() {

    }

    public List<Friend> getFriends() {
        return mFriendsList;
    }

    public boolean isFriend(int otherID) {
        for (Friend f : mFriendsList) {
            if (f.getId() == otherID) {
                return true;
            }
        }
        return false;
    }

}

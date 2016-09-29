package nz.co.liuming.cityfriends.users;

import java.util.ArrayList;
import java.util.List;

import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.users.model.Friend;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 28/09/16.
 */

public class FriendsDelegate {

    private List<Friend> mFriendsList = new ArrayList<>();

    public void loadFriends() {
        RestModule.getApis().getFriends().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Friend>>() {
            @Override
            public void call(List<Friend> friends) {
                mFriendsList = friends;
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtil.d(throwable.toString());
            }
        });
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

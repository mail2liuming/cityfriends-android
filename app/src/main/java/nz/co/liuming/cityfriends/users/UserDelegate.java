package nz.co.liuming.cityfriends.users;

import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import nz.co.liuming.cityfriends.CityFreindsApplication;
import nz.co.liuming.cityfriends.common.interfaces.Loadable;
import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.common.utils.PreferencesUtil;
import nz.co.liuming.cityfriends.users.events.LoginEvent;
import nz.co.liuming.cityfriends.users.model.Friend;
import nz.co.liuming.cityfriends.users.model.User;
import nz.co.liuming.cityfriends.users.model.UserRequest;
import nz.co.liuming.cityfriends.users.model.Userable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 17/06/16.
 */
public class UserDelegate implements Loadable {

    private int mId = 0;
    private String mToken;

    private FriendsDelegate mFriendDelegate;

    public UserDelegate(FriendsDelegate friendsDelegate) {
        mFriendDelegate = friendsDelegate;
    }

    public void login(String aEmail, String aPassword) {
        JsonObject jsonObject = null;
        jsonObject = new JsonObject();

        jsonObject.addProperty("email", aEmail);
        jsonObject.addProperty("password", aPassword);

        RestModule.getApis().doLogin(jsonObject).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                storeUser(user);

                EventBus.getDefault().post(new LoginEvent());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                EventBus.getDefault().post(new LoginEvent(throwable.getMessage()));
            }
        });
    }

    public void logout() {
        PreferencesUtil.saveInt(CityFreindsApplication.get(), PreferencesUtil.KEY_USER_ID, 0);
        PreferencesUtil.saveString(CityFreindsApplication.get(), PreferencesUtil.KEY_USER_TOKEN, "");
    }

    public void signup(String aName, String aEmail, String aPwd, String aConfirmPwd) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(aName);
        userRequest.setEmail(aEmail);
        userRequest.setPassword(aPwd);
        userRequest.setPassword_confirmation(aConfirmPwd);

        RestModule.getApis().doSignup(userRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                storeUser(user);
                EventBus.getDefault().post(new LoginEvent());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                EventBus.getDefault().post(new LoginEvent(throwable.getMessage()));
            }
        });

    }

    public boolean isFriend(Userable otherUser) {
        if (mFriendDelegate != null) {
            return mFriendDelegate.isFriend(otherUser.getId());
        }
        return false;
    }

    public void loadFriends() {
        if (mFriendDelegate != null) {
            mFriendDelegate.loadFriends();
        }
    }

    public List<Friend> getFriends() {
        if (mFriendDelegate != null) {
            return mFriendDelegate.getFriends();
        }
        return new ArrayList<>();
    }

    private void storeUser(User user) {
        mId = user.getId();
        mToken = user.getToken();

        if (mFriendDelegate != null) {
            mFriendDelegate.loadFriends();
        }

        store();
        LogUtil.d("id: " + mId + "  token: " + mToken);
    }

    @Override
    public void load() {
        mId = PreferencesUtil.getInt(CityFreindsApplication.get(), PreferencesUtil.KEY_USER_ID, 0);
        mToken = PreferencesUtil.getString(CityFreindsApplication.get(), PreferencesUtil.KEY_USER_TOKEN, "");
        LogUtil.d("load user --- id: " + mId + "  token: " + mToken);
        List<Friend> list = getFriends();
        if (list.size() <= 0) {
            loadFriends();
        }
    }

    public void store() {
        PreferencesUtil.saveInt(CityFreindsApplication.get(), PreferencesUtil.KEY_USER_ID, mId);
        PreferencesUtil.saveString(CityFreindsApplication.get(), PreferencesUtil.KEY_USER_TOKEN, mToken);
    }

    public boolean isLogin() {
        return (mId != 0);
    }

    public int getId() {
        return mId;
    }

    public String getToken() {
        return mToken;
    }

    private void updateUser(int id, String token) {
        mId = id;
        mToken = token;
    }


}

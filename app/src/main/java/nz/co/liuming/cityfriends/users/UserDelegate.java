package nz.co.liuming.cityfriends.users;

import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import nz.co.liuming.cityfriends.CityFreindsApplication;
import nz.co.liuming.cityfriends.common.interfaces.Loadable;
import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.common.utils.PreferencesUtil;
import nz.co.liuming.cityfriends.users.events.LoginEvent;
import nz.co.liuming.cityfriends.users.model.User;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 17/06/16.
 */
public class UserDelegate implements Loadable {

    private int mId = 0;
    private String mToken;

    public void login(String aEmail, String aPassword) {
        JsonObject jsonObject = null;
        jsonObject = new JsonObject();

        jsonObject.addProperty("email", aEmail);
        jsonObject.addProperty("password", aPassword);

        RestModule.getApis().doLogin(jsonObject).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                mId = user.getId();
                mToken = user.getToken();

                store();
                LogUtil.d("id: " + mId + "  token: " + mToken);

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

    public void signup() {
    }

    @Override
    public void load() {
        mId = PreferencesUtil.getInt(CityFreindsApplication.get(), PreferencesUtil.KEY_USER_ID, 0);
        mToken = PreferencesUtil.getString(CityFreindsApplication.get(), PreferencesUtil.KEY_USER_TOKEN, "");
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
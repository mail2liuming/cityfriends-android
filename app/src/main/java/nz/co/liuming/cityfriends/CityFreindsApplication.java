package nz.co.liuming.cityfriends;

import android.app.Application;

import com.facebook.stetho.Stetho;

import nz.co.liuming.cityfriends.users.UserDelegate;

/**
 * Created by liuming on 15/06/16.
 */
public class CityFreindsApplication extends Application {
    private static CityFreindsApplication sInstance;

    private UserDelegate mUserDelegate;

    public static CityFreindsApplication get() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        Stetho.initializeWithDefaults(this);
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
        mUserDelegate = new UserDelegate();
    }

    public UserDelegate getUserDelegate() {
        return mUserDelegate;
    }
}

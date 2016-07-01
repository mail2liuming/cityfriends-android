package nz.co.liuming.cityfriends.common.utils;

import android.util.Log;

import nz.co.liuming.cityfriends.BuildConfig;


/**
 * Created by liuming on 4/05/16.
 */
public class LogUtil {

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d("CityFreinds", msg);
        }
    }
}

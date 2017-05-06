package com.ojiofong.splash;

import android.content.Context;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;

/**
 * Created by ojiofong on 7/21/16.
 */

public class Util {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo() != null) && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }



    public static void setUserLoginStatus(boolean didUserLogin, Context context){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(Const.PREF_USER_LOGIN_STATUS, didUserLogin)
                .apply();
    }

    public static boolean isUserLoggedIn(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(Const.PREF_USER_LOGIN_STATUS, false);
    }
}

package com.example.baobang.gameduangua.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.login.view.LoginActivity;
import com.google.gson.Gson;

/**
 * Created by baobang on 3/20/18.
 */

public class AppUtils {

    public static void setValueToSharedPreferences(Context context,
                                                    String keyPreferences,
                                                    int mode,
                                                    String keyValue,
                                                    String value
    ){
        SharedPreferences preferences = context.getSharedPreferences(
                keyPreferences,
                mode);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(keyValue, value);
        editor.apply();
    }

    public static String getValueToSharedPreferences(Activity activity,
                                                     String keyPreferences,
                                                     int mode,
                                                     String keyValue){

        SharedPreferences preferences = activity.getSharedPreferences(
                keyPreferences,
                mode);

        return preferences.getString(keyValue, "");
    }
}

package com.oktaysadoglu.memofication.preferences;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public class ReversePreferences {

    private static String REVERSE = "reverse";

    public static boolean isReverse(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(REVERSE,false);

    }

    public static void setIsReverse(Context context,boolean isReverse){

        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(REVERSE,isReverse).apply();

    }

}

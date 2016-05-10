package com.oktaysadoglu.memofication.preferences;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by oktaysadoglu on 27/04/16.
 */
public class TestPreferences {

    private static final String NAME = "name";

    public static String getName(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context).getString(NAME,"null");

    }

    public static void setName(Context context,String name){

        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(NAME,name).apply();

    }

}

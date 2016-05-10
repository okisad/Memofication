package com.oktaysadoglu.memofication.preferences;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by oktaysadoglu on 26/04/16.
 */
public class NotificationPreferences {

    private static final String NOTIFICATION_ON_OFF="notificationOnOff";

    private static final String NOTIFICATION_NUMBER = "numberOfNotification";

    public static boolean getNotificationOnOff(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(NOTIFICATION_ON_OFF,false);

    }

    public static void setNotificationOnOff(Context context,boolean onOff){

        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(NOTIFICATION_ON_OFF,onOff).apply();

    }

    public static int getNotificationNumber(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context).getInt(NOTIFICATION_NUMBER,0);

    }

    public static void setNotificationNumber(Context context,int count){

        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(NOTIFICATION_NUMBER,count).apply();

    }

}
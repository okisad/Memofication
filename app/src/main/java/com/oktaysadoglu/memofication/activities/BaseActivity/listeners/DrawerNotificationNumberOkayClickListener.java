package com.oktaysadoglu.memofication.activities.baseActivity.listeners;

import android.content.DialogInterface;
import android.widget.NumberPicker;

import com.oktaysadoglu.memofication.activities.baseActivity.BaseActivity;
import com.oktaysadoglu.memofication.preferences.NotificationPreferences;

/**
 * Created by oktaysadoglu on 26/04/16.
 */
public class DrawerNotificationNumberOkayClickListener implements DialogInterface.OnClickListener {

    private BaseActivity baseActivity;

    private NumberPicker numberPicker;

    public DrawerNotificationNumberOkayClickListener(BaseActivity baseActivity, NumberPicker numberPicker) {
        setBaseActivity(baseActivity);
        setNumberPicker(numberPicker);
    }

    public NumberPicker getNumberPicker() {
        return numberPicker;
    }

    public void setNumberPicker(NumberPicker numberPicker) {
        this.numberPicker = numberPicker;
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        NotificationPreferences.setNotificationNumber(getBaseActivity(),getNumberPicker().getValue());

        int value = NotificationPreferences.getNotificationNumber(getBaseActivity());

        baseActivity.getNumberOfNotificationButton().setText(String.valueOf(value));


    }
}

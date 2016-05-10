package com.oktaysadoglu.memofication.activities.baseActivity.listeners;

import android.widget.CompoundButton;

import com.oktaysadoglu.memofication.activities.baseActivity.BaseActivity;
import com.oktaysadoglu.memofication.preferences.NotificationPreferences;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public class NavigationNotificationSwitchButtonCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

    private BaseActivity baseActivity;

    public NavigationNotificationSwitchButtonCheckedChangeListener(BaseActivity baseActivity) {
        setBaseActivity(baseActivity);
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        NotificationPreferences.setNotificationOnOff(getBaseActivity(),isChecked);
    }
}

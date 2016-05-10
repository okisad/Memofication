package com.oktaysadoglu.memofication.activities.baseActivity.listeners;

import android.widget.CompoundButton;

import com.oktaysadoglu.memofication.activities.baseActivity.BaseActivity;
import com.oktaysadoglu.memofication.preferences.ReversePreferences;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public class NavigationReverseSwitchButtonListener implements CompoundButton.OnCheckedChangeListener {

    private BaseActivity baseActivity;

    public NavigationReverseSwitchButtonListener(BaseActivity baseActivity) {
        setBaseActivity(baseActivity);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ReversePreferences.setIsReverse(getBaseActivity(),isChecked);
    }


    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }
}

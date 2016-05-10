package com.oktaysadoglu.memofication.activities.baseActivity.toggle;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.baseActivity.BaseActivity;
import com.oktaysadoglu.memofication.fragments.BaseFragment;
import com.oktaysadoglu.memofication.preferences.NotificationPreferences;
import com.oktaysadoglu.memofication.preferences.ReversePreferences;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public class MemoficationActionBarDrawerToggle extends ActionBarDrawerToggle {

    private BaseActivity baseActivity;

    private NavigationView navigationView;

    private Switch notificationSwitchButton,reverseSwitchButton;

    public MemoficationActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);

        setBaseActivity((BaseActivity) activity);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        super.onDrawerSlide(drawerView, slideOffset);

        BaseFragment baseFragment = (BaseFragment) getBaseActivity().getSupportFragmentManager().findFragmentById(R.id.frame_layout);

        baseFragment.setNavigationCheckedItem(getNavigationView());

        getNotificationSwitchButton().setChecked(NotificationPreferences.getNotificationOnOff(getBaseActivity()));

        getReverseSwitchButton().setChecked(ReversePreferences.isReverse(getBaseActivity()));

    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    public void setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
    }

    public Switch getNotificationSwitchButton() {
        return notificationSwitchButton;
    }

    public void setNotificationSwitchButton(Switch notificationSwitchButton) {
        this.notificationSwitchButton = notificationSwitchButton;
    }

    public Switch getReverseSwitchButton() {
        return reverseSwitchButton;
    }

    public void setReverseSwitchButton(Switch reverseSwitchButton) {
        this.reverseSwitchButton = reverseSwitchButton;
    }
}

package com.oktaysadoglu.memofication.activities.baseActivity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Switch;

import com.birbit.android.jobqueue.TagConstraint;
import com.firebase.client.Firebase;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.baseActivity.listeners.NavigationItemSelectedListener;
import com.oktaysadoglu.memofication.activities.baseActivity.listeners.NavigationNotificationSwitchButtonCheckedChangeListener;
import com.oktaysadoglu.memofication.activities.baseActivity.listeners.NavigationNumberOfNotificationButtonClickListener;
import com.oktaysadoglu.memofication.activities.baseActivity.listeners.NavigationReverseSwitchButtonListener;
import com.oktaysadoglu.memofication.activities.baseActivity.toggle.MemoficationActionBarDrawerToggle;
import com.oktaysadoglu.memofication.fragments.BaseFragment;
import com.oktaysadoglu.memofication.fragments.LevelListFragment;
import com.oktaysadoglu.memofication.preferences.NotificationPreferences;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by oktaysadoglu on 04/04/16.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private static String TAG = "BaseActivity";

    @Bind(R.id.activity_main_navigation_view_notification_switch)
    Switch notificationSwitchButton;
    @Bind(R.id.activity_main_navigation_view_is_reverse_switch)
    Switch reverseSwitchButton;
    @Bind(R.id.activity_main_navigation_view_number_of_notification_button)
    Button numberOfNotificationButton;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.activity_level_navigation_view)
    NavigationView navigationView;

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        setContentView(getLayoutResourceId());

        ButterKnife.bind(this);

        setToolbar();

        setDrawerLayout();

    }

    @Override
    protected void onStart() {
        super.onStart();

        registerListeners();

        sessionId = UUID.randomUUID().toString();
    }

    private void registerListeners() {

        getNavigationView().setNavigationItemSelectedListener(new NavigationItemSelectedListener(this));

        getNumberOfNotificationButton().setOnClickListener(new NavigationNumberOfNotificationButtonClickListener(this));

        getNotificationSwitchButton().setOnCheckedChangeListener(new NavigationNotificationSwitchButtonCheckedChangeListener(this));

        getReverseSwitchButton().setOnCheckedChangeListener(new NavigationReverseSwitchButtonListener(this));

    }

    private void setToolbar() {

        setSupportActionBar(toolbar);

    }

    private void setDrawerLayout() {

        MemoficationActionBarDrawerToggle toggle = new MemoficationActionBarDrawerToggle(this,getDrawerLayout(),getToolbar(),R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        toggle.setNavigationView(getNavigationView());

        toggle.setNotificationSwitchButton(getNotificationSwitchButton());

        toggle.setReverseSwitchButton(getReverseSwitchButton());

        getDrawerLayout().addDrawerListener(toggle);

        toggle.syncState();

        getNumberOfNotificationButton().setText(String.valueOf(NotificationPreferences.getNotificationNumber(BaseActivity.this)));

    }

    public void setFragment(BaseFragment baseFragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,baseFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

    }

    @Override
    public void onBackPressed() {

        BaseFragment baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout);

        if (getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            getDrawerLayout().closeDrawer(GravityCompat.START);
        }else if (!baseFragment.isLevelListFragment()){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, LevelListFragment.newInstance()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        Memofication.getJobManager().cancelJobsInBackground(null, TagConstraint.ANY,getSessionId());
    }

    public Switch getNotificationSwitchButton() {
        return notificationSwitchButton;
    }

    public Button getNumberOfNotificationButton() {
        return numberOfNotificationButton;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    public Switch getReverseSwitchButton() {
        return reverseSwitchButton;
    }

    public void setReverseSwitchButton(Switch reverseSwitchButton) {
        this.reverseSwitchButton = reverseSwitchButton;
    }

    protected abstract int getLayoutResourceId();
}

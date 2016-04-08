package com.oktaysadoglu.memofication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.fragments.AchievementFragment;
import com.oktaysadoglu.memofication.fragments.LevelListFragment;
import com.oktaysadoglu.memofication.tools.DialogTools;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by oktaysadoglu on 04/04/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private static String TAG = "BaseActivity";

    @Bind(R.id.activity_main_navigation_view_notification_switch)
    Switch mNotificationOnOffSwitch;
    @Bind(R.id.activity_main_navigation_view_notification_number_button)
    Button mNotificationNumberButton;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.activity_level_navigation_view)
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(getLayoutResourceId());

        ButterKnife.bind(this);

        setToolbar();

        setDrawerLayout();

    }

    @Override
    protected void onStart() {
        super.onStart();

        registerListeners();
    }

    private void registerListeners() {

        mNotificationNumberButton.setOnClickListener(this);

        mNotificationOnOffSwitch.setOnCheckedChangeListener(this);

    }

    private void setToolbar() {

        setSupportActionBar(mToolbar);

    }

    private void setDrawerLayout() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.setDrawerListener(toggle);

        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

    }

    private void setFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        Log.e(TAG, String.valueOf(id));

        if (id == R.id.nav_play) {
            setFragment(LevelListFragment.newInstance());
        } else if (id == R.id.nav_achievement) {
            /*setFragment(AchievementFragment.newInstance());*/
            Intent intent = new Intent(this,PlacementTestActivity.class);
            startActivity(intent);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(BaseActivity.this, "changedCheck", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppDialogTheme);

        View view = LayoutInflater.from(this).inflate(R.layout.navigation_change_number_of_notification_dialog, null);

        NumberPicker picker = (NumberPicker) view.findViewById(R.id.navigation_change_number_of_notification_dialog_number_picker);

        picker.setValue(0);
        picker.setMaxValue(10);
        picker.setMinValue(0);

        DialogTools dialogTools = new DialogTools();

        dialogTools.setNumberPickerTextColor(picker, ContextCompat.getColor(this, R.color.grey_900));
        dialogTools.setDividerColor(picker, ContextCompat.getColor(this, R.color.grey_700));

        final AlertDialog alertDialog = builder
                .setView(view)
                .setTitle("Number Of Notifications")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BaseActivity.this, "okay", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();


        alertDialog.show();

    }

    protected abstract int getLayoutResourceId();

}

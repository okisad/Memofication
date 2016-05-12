package com.oktaysadoglu.memofication.activities.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.baseActivity.BaseActivity;
import com.oktaysadoglu.memofication.fragments.LevelListFragment;

public class MainActivity extends BaseActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setFirstFragment();

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    private void setFirstFragment() {

        LevelListFragment levelListFragment = LevelListFragment.newInstance();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, levelListFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

    }


}

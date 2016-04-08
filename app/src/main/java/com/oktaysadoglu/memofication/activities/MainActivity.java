package com.oktaysadoglu.memofication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.oktaysadoglu.memofication.R;
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

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, LevelListFragment.newInstance()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if (requestCode == LevelListRecyclerViewAdapter.START_LEVEL_REQUEST_CODE){

            mLevel = data.getIntExtra("level",-1);

            setFragment(new LevelListFragment().newInstance());

        }*/
    }

}

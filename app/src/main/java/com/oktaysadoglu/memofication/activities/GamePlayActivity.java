package com.oktaysadoglu.memofication.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.fragments.GamePlayFragment;
import com.oktaysadoglu.memofication.fragments.LevelListFragment;
import com.oktaysadoglu.memofication.pagerAdapters.OneOfLevelPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by oktaysadoglu on 05/04/16.
 */
public class GamePlayActivity extends BaseActivity {

    private static String TAG = "GamePlayActivity";

    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        setWhichLevelIs();

        setFirstFragment();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_level;
    }

    private void setFirstFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_level_frame_layout, GamePlayFragment.newInstance(level)).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

    }

    private void setWhichLevelIs(){

        Bundle bundle = getIntent().getExtras();

        this.level = bundle.getInt("level");

        Log.e(TAG, String.valueOf(level));

    }

}

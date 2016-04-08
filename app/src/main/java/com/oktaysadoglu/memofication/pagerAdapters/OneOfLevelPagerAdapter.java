package com.oktaysadoglu.memofication.pagerAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oktaysadoglu.memofication.activities.GamePlayActivity;
import com.oktaysadoglu.memofication.fragments.GamePlayFragment;
import com.oktaysadoglu.memofication.fragments.LevelListFragment;
import com.oktaysadoglu.memofication.fragments.WordListFragment;

/**
 * Created by oktaysadoglu on 07/02/16.
 */
public class OneOfLevelPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 5;

    private int level;

    public OneOfLevelPagerAdapter(FragmentManager fm,int level) {
        super(fm);

        this.level = level;
    }

    @Override
    public Fragment getItem(int position) {

        /*return WordListFragment.newInstance(level,position);*/
        return GamePlayFragment.newInstance(level);

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){

            case 0:
                return "1-10";
            case 1:
                return "11-20";
            case 2:
                return "21-30";
            case 3:
                return "31-40";
            case 4:
                return "41-50";
            default:
                return level+".*";

        }

    }
}

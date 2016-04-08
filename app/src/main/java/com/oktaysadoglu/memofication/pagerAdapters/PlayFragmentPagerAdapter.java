package com.oktaysadoglu.memofication.pagerAdapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oktaysadoglu.memofication.fragments.LevelListFragment;

/**
 * Created by oktaysadoglu on 05/02/16.
 */
public class PlayFragmentPagerAdapter extends FragmentPagerAdapter{

    private static int NUM_ITEMS = 10;

    public PlayFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return LevelListFragment.newInstance();
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Newbie";
            case 1:
                return "Novice";
            case 2:
                return "Rookie";
            case 3:
                return "Beginner";
            case 4:
                return "Learning";
            case 5:
                return "Proficient";
            case 6:
                return "Experienced";
            case 7:
                return "Advanced";
            case 8:
                return "Senior";
            case 9:
                return "Expert";
            default:
                return "null";
        }
    }


}

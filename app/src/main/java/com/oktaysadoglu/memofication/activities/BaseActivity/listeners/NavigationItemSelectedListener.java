package com.oktaysadoglu.memofication.activities.baseActivity.listeners;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.baseActivity.BaseActivity;
import com.oktaysadoglu.memofication.fragments.AchievementFragment;
import com.oktaysadoglu.memofication.fragments.LevelListFragment;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public class NavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {

    private BaseActivity baseActivity;

    public NavigationItemSelectedListener(BaseActivity baseActivity){

        setBaseActivity(baseActivity);

    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_play){

            getBaseActivity().setFragment(LevelListFragment.newInstance());

        }else if (id == R.id.nav_achievement){

            getBaseActivity().setFragment(AchievementFragment.newInstance());

        }

        getBaseActivity().getDrawerLayout().closeDrawer(GravityCompat.START);

        return true;
    }

}

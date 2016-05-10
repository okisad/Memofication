package com.oktaysadoglu.memofication.fragments;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public abstract class BaseFragment extends Fragment{

    public abstract void setNavigationCheckedItem(NavigationView navigationView);

    public abstract String getFragmentName();

    public abstract boolean isLevelListFragment();

    public abstract void setLevelListFragment(boolean isLevelListFragment);
}

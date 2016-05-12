package com.oktaysadoglu.memofication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.db.LastWordNumber;
import com.oktaysadoglu.memofication.db.LastWordNumberDao;
import com.oktaysadoglu.memofication.db.Word;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by oktaysadoglu on 20/04/16.
 */
public class AchievementFragment extends BaseFragment {

    public static String fragmentName = "AchievementFragment";

    @Bind(R.id.textt)
    TextView mTextView;

    private boolean isLevelListFragment;

    public static AchievementFragment newInstance(){

        return new AchievementFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLevelListFragment(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achievement,container,false);

        ButterKnife.bind(this,view);

        LastWordNumberDao lastWordNumberDao = Memofication.getLastWordNumberDao();

        LastWordNumber lastWordNumber = lastWordNumberDao.load((long) 1);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {

        super.onStop();
    }

    @Override
    public void setNavigationCheckedItem(NavigationView navigationView) {
        navigationView.setCheckedItem(R.id.nav_achievement);
    }

    @Override
    public String getFragmentName() {
        return fragmentName;
    }

    @Override
    public boolean isLevelListFragment() {
        return this.isLevelListFragment;
    }

    @Override
    public void setLevelListFragment(boolean isLevelListFragment) {
        this.isLevelListFragment = isLevelListFragment;
    }
}

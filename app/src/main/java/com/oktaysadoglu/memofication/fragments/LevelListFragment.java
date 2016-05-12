package com.oktaysadoglu.memofication.fragments;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.recyclerViewAdapters.LevelListRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by oktaysadoglu on 05/02/16.
 */
public class LevelListFragment extends BaseFragment {

    public static String fragmentName = "LevelListFragment";

    private static int GRID_LAYOUT_COLUMN_NUMBER = 4;

    private boolean isLevelListFragment;

    @Bind(R.id.fragment_level_list_recycler_view)
    RecyclerView mRecyclerView;

    public static LevelListFragment newInstance() {

        LevelListFragment fragmentLevelList = new LevelListFragment();

        return fragmentLevelList;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLevelListFragment(true);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_level_list, container, false);

        ButterKnife.bind(this, view);

        setAdapterRecyclerView();

        setLevelListFragment(true);

        return view;

    }

    private void setAdapterRecyclerView(){

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), GRID_LAYOUT_COLUMN_NUMBER);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                int p = position+1;

                if ((p+1)%10 == 0)
                    return 2;
                else if (p%10 == 0)
                    return 2;
                return 1;
            }
        });

        mRecyclerView.setLayoutManager(layoutManager);

        LevelListRecyclerViewAdapter levelListRecyclerViewAdapter = new LevelListRecyclerViewAdapter(getActivity());

        mRecyclerView.setAdapter(levelListRecyclerViewAdapter);

    }


    @Override
    public void setNavigationCheckedItem(NavigationView navigationView) {
        navigationView.setCheckedItem(R.id.nav_play);
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

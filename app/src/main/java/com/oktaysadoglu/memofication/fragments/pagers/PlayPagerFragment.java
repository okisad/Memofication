package com.oktaysadoglu.memofication.fragments.pagers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.pagerAdapters.PlayFragmentPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by oktaysadoglu on 04/02/16.
 */
public class PlayPagerFragment extends Fragment {

    @Bind(R.id.fragment_play_sliding_tabs)
    TabLayout mTabLayout;
    @Bind(R.id.fragment_play_view_pager)
    ViewPager mViewPager;

    FragmentPagerAdapter adapterViewPager;

    private int mLevel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){

            mLevel = getArguments().getInt("level");

            Log.e("my","level ayarlandı : "+mLevel);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.fragment_play_pager,container,false);

        ButterKnife.bind(this, view);

        setAdapterViewPager();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onResume() {
        super.onResume();

        int position = 1;

        if(mLevel % 10 == 0){

            position = (mLevel / 10) -1;

        }else {

            position = mLevel / 10;

        }

        Log.e("my","pozisyon ayarlamadayız : "+position);

        mViewPager.setCurrentItem(position, true);
    }

    private void setAdapterViewPager(){

        adapterViewPager = new PlayFragmentPagerAdapter(getChildFragmentManager());

        mViewPager.setAdapter(adapterViewPager);

    }

    public static Fragment newInstance(int level){

        PlayPagerFragment playPagerFragment = new PlayPagerFragment();

        Bundle bundle = new Bundle();

        bundle.putInt("level",level);

        playPagerFragment.setArguments(bundle);

        return playPagerFragment;

    }

}

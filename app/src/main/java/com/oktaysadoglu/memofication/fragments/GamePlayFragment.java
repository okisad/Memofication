package com.oktaysadoglu.memofication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.db.Word;
import com.oktaysadoglu.memofication.db.WordDao;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by oktaysadoglu on 05/04/16.
 */
public class GamePlayFragment extends Fragment {

    private static String TAG = "GamePlayFragment";

    @Bind(R.id.fragment_word_list_recycler_view)
    RecyclerView mRecyclerView;

    private int level;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        level = getArguments().getInt("level");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word_list,container,false);

        ButterKnife.bind(this, view);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static GamePlayFragment newInstance(int level){

        GamePlayFragment gamePlayFragment = new GamePlayFragment();

        Bundle bundle = new Bundle();

        bundle.putInt("level", level);

        gamePlayFragment.setArguments(bundle);

        return gamePlayFragment;

    }


}

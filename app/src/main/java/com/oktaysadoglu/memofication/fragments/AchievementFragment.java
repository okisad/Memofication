package com.oktaysadoglu.memofication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.jobs.WriteWordCardJob;
import com.oktaysadoglu.memofication.tools.GetColorFilter;

/**
 * Created by oktaysadoglu on 04/02/16.
 */
public class AchievementFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achievement,container,false);

        ImageView imageView = (ImageView) view.findViewById(R.id.card_view_level_list_card_tick_image);

        imageView.setImageResource(R.mipmap.ic_tick);

        Button button = (Button) view.findViewById(R.id.mmm_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 1 ; i<4908 ; i++){

                    Memofication.getJobManager().addJobInBackground(new WriteWordCardJob((long) i));

                }

            }
        });

        return view;
    }

    public static Fragment newInstance(){

        return new AchievementFragment();

    }
}

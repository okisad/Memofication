package com.oktaysadoglu.memofication.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.db.UserWords;
import com.oktaysadoglu.memofication.db.UserWordsDao;
import com.oktaysadoglu.memofication.db.Word;
import com.oktaysadoglu.memofication.db.WordDao;
import com.oktaysadoglu.memofication.events.WordCardViewEvent;
import com.oktaysadoglu.memofication.fragments.GamePlayFragment;
import com.oktaysadoglu.memofication.jobs.WriteWordCardJob;
import com.oktaysadoglu.memofication.model.WordCard;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlacementTestActivity extends AppCompatActivity {


    @Bind(R.id.swipe_deck)
    SwipeDeck cardStack;

    SwipeDeckAdapter swipeDeckAdapter;

    ArrayList<WordCard> wordCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_test);

        ButterKnife.bind(this);

        for (int i = 1 ; i<=20 ; i++){

            Memofication.getJobManager().addJobInBackground(new WriteWordCardJob(i));

        }

        swipeDeckAdapter = new SwipeDeckAdapter(wordCards,this);

        cardStack.setAdapter(swipeDeckAdapter);

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + position);
            }

            @Override
            public void cardSwipedRight(int position) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + position);
            }

            @Override
            public void cardsDepleted() {
                Log.i("MainActivity", "no more cards");
            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);

        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWordCardViewEvent(WordCardViewEvent event){

        WordCard wordCard = event.getWordCard();

        wordCards.add(wordCard);

        swipeDeckAdapter.notifyDataSetChanged();

    }

    public class SwipeDeckAdapter extends BaseAdapter {

        private List<WordCard> data;
        private Context context;

        public SwipeDeckAdapter(List<WordCard> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if(v == null){
                LayoutInflater inflater = getLayoutInflater();
                // normally use a viewholder
                v = inflater.inflate(R.layout.ex_card_view, parent, false);
            }
            ((TextView) v.findViewById(R.id.textView2)).setText(data.get(position).toString());

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item = (String)getItem(position);
                    Log.i("MainActivity", item);
                }
            });

            return v;
        }
    }

}

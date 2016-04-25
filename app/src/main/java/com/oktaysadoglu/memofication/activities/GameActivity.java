package com.oktaysadoglu.memofication.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.events.WordCardViewEvent;
import com.oktaysadoglu.memofication.jobs.EvaluationWordCardJob;
import com.oktaysadoglu.memofication.jobs.WriteWordCardJob;
import com.oktaysadoglu.memofication.model.WordCard;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity{


    @Bind(R.id.swipe_deck)
    SwipeDeck cardStack;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    SwipeDeckAdapter swipeDeckAdapter;

    ArrayList<WordCard> wordCards = new ArrayList<>();

    int level;


    private static int SWIPE_CARD_SPEED = 250;

    private static int SWIPE_TRUE_CARD_DELAY = 750;

    private static int SWIPE_FALSE_CARD_DELAY = 1250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_toolbar_and_frame);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Memofication");

        level = getIntent().getIntExtra("level",0);

        setSwipeDeckAdapter(level);

    }

    public void setSwipeDeckAdapter(int level) {

        int startPoint = (level-1)*50 + 1;

        Memofication.getJobManager().addJobInBackground(new WriteWordCardJob(startPoint,startPoint+50));

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


        cardStack.setLeftImage(R.id.left_image);

        cardStack.setRightImage(R.id.right_image);
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

        Log.e("my",wordCards.toString());

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



            View view = convertView;
            if(view == null){
                LayoutInflater inflater = getLayoutInflater();
                // normally use a viewholder
                view = inflater.inflate(R.layout.word_card_card_layout, parent, false);
            }

            TextView orderText = (TextView) view.findViewById(R.id.word_card_card_layout_order_text);

            orderText.setText((position+1)+"/50");

            TextView mainWordText = (TextView) view.findViewById(R.id.word_card_card_layout_main_word_text);

            mainWordText.setText(data.get(position).getMainWord().getWord());

            final WordCard wordCard = data.get(position);

            final Button firstOption = (Button) view.findViewById(R.id.word_card_card_layout_first_button);
            final Button secondOption = (Button) view.findViewById(R.id.word_card_card_layout_second_button);
            final Button thirdOption = (Button) view.findViewById(R.id.word_card_card_layout_third_button);
            final Button fourthOption = (Button) view.findViewById(R.id.word_card_card_layout_fourth_button);



            firstOption.setText(data.get(position).getWords().get(0).getMean());
            secondOption.setText(data.get(position).getWords().get(1).getMean());
            thirdOption.setText(data.get(position).getWords().get(2).getMean());
            fourthOption.setText(data.get(position).getWords().get(3).getMean());

            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Button trueAnswerWhichOption = null;

                    Handler handler = new Handler();

                    int mainID = (int) (long) wordCard.getMainWord().getId();

                    int firstID = (int) (long) wordCard.getWords().get(0).getId();

                    int secondID =(int) (long) wordCard.getWords().get(1).getId();

                    int thirdID = (int) (long) wordCard.getWords().get(2).getId();

                    int fourthID = (int) (long) wordCard.getWords().get(3).getId();

                    if (mainID == firstID){

                        trueAnswerWhichOption = firstOption;

                    }else if (mainID == secondID){

                        trueAnswerWhichOption = secondOption;

                    }else if (mainID == thirdID){

                        trueAnswerWhichOption = thirdOption;

                    }else if (mainID == fourthID){

                        trueAnswerWhichOption = fourthOption;

                    }

                    if(v.getId() == R.id.word_card_card_layout_first_button){

                        if (mainID == firstID){

                            Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,true));

                            firstOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_true_background));

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cardStack.swipeTopCardLeft(SWIPE_CARD_SPEED);
                                }
                            },SWIPE_TRUE_CARD_DELAY);

                        }else {

                            Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,false));

                            firstOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_false_background));

                            if( trueAnswerWhichOption != null){

                                trueAnswerWhichOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_true_background));

                            }

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cardStack.swipeTopCardRight(SWIPE_CARD_SPEED);
                                }
                            },SWIPE_FALSE_CARD_DELAY);
                        }

                    }else if (v.getId() == R.id.word_card_card_layout_second_button){

                        if (mainID == secondID){

                            Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,true));

                            secondOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_true_background));

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cardStack.swipeTopCardLeft(SWIPE_CARD_SPEED);
                                }
                            },SWIPE_TRUE_CARD_DELAY);


                        }else {

                            Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,false));
                            secondOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_false_background));

                            if( trueAnswerWhichOption != null){

                                trueAnswerWhichOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_true_background));

                            }

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cardStack.swipeTopCardRight(SWIPE_CARD_SPEED);
                                }
                            },SWIPE_FALSE_CARD_DELAY);

                        }

                    }else if (v.getId() == R.id.word_card_card_layout_third_button){

                        if (mainID == thirdID){

                            Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,true));

                            thirdOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_true_background));

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cardStack.swipeTopCardLeft(SWIPE_CARD_SPEED);
                                }
                            },SWIPE_TRUE_CARD_DELAY);


                        }else {

                            Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,false));

                            thirdOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_false_background));

                            if( trueAnswerWhichOption != null){

                                trueAnswerWhichOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_true_background));

                            }

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cardStack.swipeTopCardRight(SWIPE_CARD_SPEED);
                                }
                            },SWIPE_FALSE_CARD_DELAY);

                        }

                    }else if (v.getId() == R.id.word_card_card_layout_fourth_button){

                        if (mainID == fourthID){

                            Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,true));

                            fourthOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_true_background));

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cardStack.swipeTopCardLeft(SWIPE_CARD_SPEED);
                                }
                            },SWIPE_TRUE_CARD_DELAY);


                        }else {

                            Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,false));
                            fourthOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_false_background));

                            if( trueAnswerWhichOption != null){

                                trueAnswerWhichOption.setBackground(ContextCompat.getDrawable(GameActivity.this,R.drawable.card_true_background));

                            }

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cardStack.swipeTopCardRight(SWIPE_CARD_SPEED);
                                }
                            },SWIPE_FALSE_CARD_DELAY);

                        }

                    }

                }
            };

            firstOption.setOnClickListener(onClickListener);
            secondOption.setOnClickListener(onClickListener);
            thirdOption.setOnClickListener(onClickListener);
            fourthOption.setOnClickListener(onClickListener);

            return view;
        }
    }

}

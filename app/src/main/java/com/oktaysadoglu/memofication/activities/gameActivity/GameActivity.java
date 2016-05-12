package com.oktaysadoglu.memofication.activities.gameActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.gameActivity.adapters.SwipeDeckAdapter;
import com.oktaysadoglu.memofication.db.Word;
import com.oktaysadoglu.memofication.events.WordCardViewDeclarationEvent;
import com.oktaysadoglu.memofication.model.WordCard;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public abstract class GameActivity extends AppCompatActivity {

    @Bind(R.id.swipe_deck)
    SwipeDeck cardStack;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private SwipeDeckAdapter swipeDeckAdapter;

    private ArrayList<WordCard> wordCards;

    private int level;

    public abstract void setSwipeDeckAdapter(int level);

    public abstract void fillWordAndOptions(View view, int position);

    public static Class newClass(boolean reverse){

        if (reverse)
            return GameActivityReverse.class;
        else
            return GameActivityStraight.class;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_toolbar_and_frame);

        ButterKnife.bind(this);

        setToolbar();

        setInitialVariables();

        setSwipeDeckAdapter(getLevel());

    }

    private void setToolbar(){

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Memofication");

    }

    private void setInitialVariables(){

        wordCards = new ArrayList<>();

        /*for (int i = 1 ; i <= 50; i++){

            wordCards.add(new WordCard(new Word(Long.getLong("1"),"a","a","a")));

        }*/

        setLevel(getIntent().getIntExtra("level",0));

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
    public void onWordCardViewEvent(WordCardViewDeclarationEvent event){

        Log.e("my","handle edildi");

        for (WordCard wordCard:event.getWordCards()){

            Log.e("my",wordCard.toString());

            getWordCards().add(wordCard);

            swipeDeckAdapter.notifyDataSetChanged();

        }

    }

    public SwipeDeckAdapter getSwipeDeckAdapter() {
        return swipeDeckAdapter;
    }

    public ArrayList<WordCard> getWordCards() {
        return wordCards;
    }

    public void setWordCards(ArrayList<WordCard> wordCards) {
        this.wordCards = wordCards;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public SwipeDeck getCardStack() {
        return cardStack;
    }

    public void setSwipeDeckAdapter(SwipeDeckAdapter swipeDeckAdapter) {
        this.swipeDeckAdapter = swipeDeckAdapter;
    }

}

package com.oktaysadoglu.memofication.activities.gameActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.gameActivity.adapters.SwipeDeckAdapter;
import com.oktaysadoglu.memofication.events.WordCardViewEvent;
import com.oktaysadoglu.memofication.jobs.WriteWordCardJob;
import com.oktaysadoglu.memofication.model.WordCard;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

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

    private ArrayList<WordCard> wordCards = new ArrayList<>();

    private int level;

    public SwipeDeckAdapter getSwipeDeckAdapter() {
        return swipeDeckAdapter;
    }

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

        setLevel(getIntent().getIntExtra("level",0));

    }

    public abstract void setSwipeDeckAdapter(int level);

    public abstract void fillWordAndOptions(View view, int position);

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

    /*public class SwipeDeckAdapter extends BaseAdapter {

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
                view = inflater.inflate(R.layout.word_card_card_layout, parent, false);
            }

            TextView orderText = (TextView) view.findViewById(R.id.word_card_card_layout_order_text);

            TextView mainWordText = (TextView) view.findViewById(R.id.word_card_card_layout_main_word_text);

            WordCard wordCard = data.get(position);

            Button firstOption = (Button) view.findViewById(R.id.word_card_card_layout_first_button);
            Button secondOption = (Button) view.findViewById(R.id.word_card_card_layout_second_button);
            Button thirdOption = (Button) view.findViewById(R.id.word_card_card_layout_third_button);
            Button fourthOption = (Button) view.findViewById(R.id.word_card_card_layout_fourth_button);

            List<Button> buttonsBundle = new ArrayList<>();
            buttonsBundle.add(firstOption);
            buttonsBundle.add(secondOption);
            buttonsBundle.add(thirdOption);
            buttonsBundle.add(fourthOption);

            orderText.setText((position+1)+"/50");

            if (reverse){

                mainWordText.setText(data.get(position).getMainWord().getMean());

                firstOption.setText(data.get(position).getWords().get(0).getWord());
                secondOption.setText(data.get(position).getWords().get(1).getWord());
                thirdOption.setText(data.get(position).getWords().get(2).getWord());
                fourthOption.setText(data.get(position).getWords().get(3).getWord());


            }else {

                mainWordText.setText(data.get(position).getMainWord().getWord());

                firstOption.setText(data.get(position).getWords().get(0).getMean());
                secondOption.setText(data.get(position).getWords().get(1).getMean());
                thirdOption.setText(data.get(position).getWords().get(2).getMean());
                fourthOption.setText(data.get(position).getWords().get(3).getMean());
            }

            View.OnClickListener onClickListener = new CardButtonClickListener(wordCard,buttonsBundle,parent,GameActivityStraight.this,cardStack);

            firstOption.setOnClickListener(onClickListener);
            secondOption.setOnClickListener(onClickListener);
            thirdOption.setOnClickListener(onClickListener);
            fourthOption.setOnClickListener(onClickListener);

            return view;
        }
    }*/

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

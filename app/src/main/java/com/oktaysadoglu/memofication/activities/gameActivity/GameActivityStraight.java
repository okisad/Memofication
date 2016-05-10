package com.oktaysadoglu.memofication.activities.gameActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.gameActivity.adapters.SwipeDeckAdapter;
import com.oktaysadoglu.memofication.jobs.WriteWordCardJob;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public class GameActivityStraight extends GameActivity {


    @Override
    public void setSwipeDeckAdapter(int level) {

            int startPoint = (level-1)*50 + 1;

            Memofication.getJobManager().addJobInBackground(new WriteWordCardJob(startPoint,startPoint+50));

            setSwipeDeckAdapter(new SwipeDeckAdapter(getWordCards(),this));

            cardStack.setAdapter(getSwipeDeckAdapter());

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

    public void fillWordAndOptions(View view,int position){

        TextView mainWordText = (TextView) view.findViewById(R.id.word_card_card_layout_main_word_text);

        Button firstOption = (Button) view.findViewById(R.id.word_card_card_layout_first_button);
        Button secondOption = (Button) view.findViewById(R.id.word_card_card_layout_second_button);
        Button thirdOption = (Button) view.findViewById(R.id.word_card_card_layout_third_button);
        Button fourthOption = (Button) view.findViewById(R.id.word_card_card_layout_fourth_button);

        mainWordText.setText(getWordCards().get(position).getMainWord().getWord());

        firstOption.setText(getWordCards().get(position).getWords().get(0).getMean());
        secondOption.setText(getWordCards().get(position).getWords().get(1).getMean());
        thirdOption.setText(getWordCards().get(position).getWords().get(2).getMean());
        fourthOption.setText(getWordCards().get(position).getWords().get(3).getMean());

    }

}

package com.oktaysadoglu.memofication.listeners;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.GameActivity;
import com.oktaysadoglu.memofication.db.Word;
import com.oktaysadoglu.memofication.jobs.EvaluationWordCardJob;
import com.oktaysadoglu.memofication.model.WordCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oktaysadoglu on 25/04/16.
 */
public class CardButtonClickListener implements View.OnClickListener {

    private WordCard wordCard;

    private List<Button> buttonsBundle;

    private SwipeDeck cardStack;

    private ViewGroup parent;

    private Context context;

    private static int SWIPE_CARD_SPEED = 250;

    private static int SWIPE_TRUE_CARD_DELAY = 500;

    private static int SWIPE_FALSE_CARD_DELAY = 1250;

    public CardButtonClickListener(WordCard wordCard,List<Button> buttonsBundle,ViewGroup parent,Context context,SwipeDeck cardStack) {
        this.wordCard = wordCard;
        this.buttonsBundle=buttonsBundle;
        this.parent = parent;
        this.context = context;
        this.cardStack = cardStack;
    }

    @Override
    public void onClick(View v) {

        Button trueAnswerWhichOption = null;

        Handler handler = new Handler();

        int mainID = (int) (long) wordCard.getMainWord().getId();

        int firstID = (int) (long) wordCard.getWords().get(0).getId();

        int secondID =(int) (long) wordCard.getWords().get(1).getId();

        int thirdID = (int) (long) wordCard.getWords().get(2).getId();

        int fourthID = (int) (long) wordCard.getWords().get(3).getId();

        Button firstOption = buttonsBundle.get(0);
        Button secondOption = buttonsBundle.get(1);
        Button thirdOption = buttonsBundle.get(2);
        Button fourthOption = buttonsBundle.get(3);

        //Doğru cevabın hangi butonda olduğunun set edilmesi
        Map<Integer,Button> lookup = new HashMap<>();

        lookup.put(firstID,firstOption);
        lookup.put(secondID,secondOption);
        lookup.put(thirdID,thirdOption);
        lookup.put(fourthID,fourthOption);

        trueAnswerWhichOption = lookup.get(mainID);

        //Hangi butona basıldığının kontrolü
        if(v.getId() == R.id.word_card_card_layout_first_button){
            //Cevap doğruluk kontrolü
            if (mainID == firstID){

                Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,true));

                firstOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_true_background));

                enableDisableViewGroup(parent,false);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enableDisableViewGroup(parent,true);
                        cardStack.swipeTopCardLeft(SWIPE_CARD_SPEED);
                    }
                },SWIPE_TRUE_CARD_DELAY);

            }else {

                Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,false));

                firstOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_false_background));

                if( trueAnswerWhichOption != null){

                    trueAnswerWhichOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_true_background));

                }

                enableDisableViewGroup(parent,false);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enableDisableViewGroup(parent,true);
                        cardStack.swipeTopCardRight(SWIPE_CARD_SPEED);
                    }
                },SWIPE_FALSE_CARD_DELAY);
            }

        }else if (v.getId() == R.id.word_card_card_layout_second_button){

            if (mainID == secondID){

                Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,true));

                secondOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_true_background));

                enableDisableViewGroup(parent,false);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enableDisableViewGroup(parent,true);
                        cardStack.swipeTopCardLeft(SWIPE_CARD_SPEED);
                    }
                },SWIPE_TRUE_CARD_DELAY);


            }else {

                Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,false));
                secondOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_false_background));

                if( trueAnswerWhichOption != null){

                    trueAnswerWhichOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_true_background));

                }

                enableDisableViewGroup(parent,false);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enableDisableViewGroup(parent,true);
                        cardStack.swipeTopCardRight(SWIPE_CARD_SPEED);
                    }
                },SWIPE_FALSE_CARD_DELAY);

            }

        }else if (v.getId() == R.id.word_card_card_layout_third_button){

            if (mainID == thirdID){

                Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,true));

                thirdOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_true_background));

                enableDisableViewGroup(parent,false);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enableDisableViewGroup(parent,true);
                        cardStack.swipeTopCardLeft(SWIPE_CARD_SPEED);
                    }
                },SWIPE_TRUE_CARD_DELAY);


            }else {

                Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,false));

                thirdOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_false_background));



                if( trueAnswerWhichOption != null){

                    Log.e("my","firds");

                    trueAnswerWhichOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_true_background));

                }else {

                    Log.e("my","null true");

                }

                enableDisableViewGroup(parent,false);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enableDisableViewGroup(parent,true);
                        cardStack.swipeTopCardRight(SWIPE_CARD_SPEED);
                    }
                },SWIPE_FALSE_CARD_DELAY);

            }

        }else if (v.getId() == R.id.word_card_card_layout_fourth_button){

            if (mainID == fourthID){

                Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,true));

                fourthOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_true_background));

                enableDisableViewGroup(parent,false);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enableDisableViewGroup(parent,true);
                        cardStack.swipeTopCardLeft(SWIPE_CARD_SPEED);
                    }
                },SWIPE_TRUE_CARD_DELAY);


            }else {

                Memofication.getJobManager().addJobInBackground(new EvaluationWordCardJob((long) mainID,false));
                fourthOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_false_background));

                if( trueAnswerWhichOption != null){

                    trueAnswerWhichOption.setBackground(ContextCompat.getDrawable(context,R.drawable.card_true_background));

                }

                enableDisableViewGroup(parent,false);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enableDisableViewGroup(parent,true);
                        cardStack.swipeTopCardRight(SWIPE_CARD_SPEED);
                    }
                },SWIPE_FALSE_CARD_DELAY);

            }

        }

    }

    public static void enableDisableViewGroup(ViewGroup viewGroup, boolean enabled) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(enabled);
            if (view instanceof ViewGroup) {
                enableDisableViewGroup((ViewGroup) view, enabled);
            }
        }
    }
}

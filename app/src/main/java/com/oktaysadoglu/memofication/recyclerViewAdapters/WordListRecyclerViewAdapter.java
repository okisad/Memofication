package com.oktaysadoglu.memofication.recyclerViewAdapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.db.Word;
import com.oktaysadoglu.memofication.events.WordCardViewOnClickEvent;
import com.oktaysadoglu.memofication.model.WordCard;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by oktaysadoglu on 08/02/16.
 */
public class WordListRecyclerViewAdapter extends RecyclerView.Adapter<WordListRecyclerViewAdapter.CardViewWordListHolder> {

    private ArrayList<WordCard> mWordCards;

    private int section;

    public WordListRecyclerViewAdapter(ArrayList<WordCard> wordCards,int section) {
        mWordCards = wordCards;
        this.section = section;
    }

    @Override
    public CardViewWordListHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_level_base_layout_card_view_word_test_card, parent, false);

        return new CardViewWordListHolder(view,section);
    }

    @Override
    public void onBindViewHolder(CardViewWordListHolder holder, int position) {

        int positionInArrayList = (section * 10) + position;

        try {
            mWordCards.get(positionInArrayList);
        } catch (IndexOutOfBoundsException e) {
            return;
        }

        WordCard wordCard = mWordCards.get(positionInArrayList);

        holder.setWordCardModel(wordCard);

        holder.setTextView(wordCard.getMainWord().getWord() + " (" + wordCard.getMainWord().getType() + ")");

        holder.setButtons(wordCard.getWords());

    }

    @Override
    public int getItemCount() {

            int size = 0;

            int lowerBound = section*10;

            int upperBound = lowerBound + 10;

            if(mWordCards.size() == 0){

                return 1;

            }else{

                for (int i = 0; i < mWordCards.size() ; i++){

                    long wordId = mWordCards.get(i).getMainWord().getId();

                    if(wordId % 50 > lowerBound && wordId % 50 <=upperBound){

                        size++;

                    }

                }

                if(upperBound==50){

                    size++;

                }

                return size;

            }
        /*else if (section == 1){

            if (mWordCards.size() > 10 && mWordCards.size() < 20){

                int value = mWordCards.size() % 10;

                return value;

            }else if (mWordCards.size() == 0){

                return 1;

            }else if (mWordCards.size() >= 20){

                return 10;

            }else {

                return 1;

            }

        }else if (section == 2){

            if (mWordCards.size() > 20 && mWordCards.size() < 30){

                int value = mWordCards.size() % 10;

                return value;

            }else if (mWordCards.size() == 0){

                return 1;

            }else if (mWordCards.size() >= 30){

                return 10;

            }else {

                return 1;

            }

        }else if (section == 3){

            if (mWordCards.size() > 30 && mWordCards.size() < 40){

                int value = mWordCards.size() % 10;

                return value;

            }else if (mWordCards.size() == 0){

                return 1;

            }else if (mWordCards.size() >= 40){

                return 10;

            }else {

                return 1;

            }

        }else if (section == 4){

            if (mWordCards.size() > 40 && mWordCards.size() < 50){

                int value = mWordCards.size() % 10;

                return value;

            }else if (mWordCards.size() == 0){

                return 1;

            }else if (mWordCards.size() >= 50){

                return 10;

            }else {

                return 1;

            }

        }else {

            return 10;

        }*/


    }

    public ArrayList<WordCard> getWordCards() {
        return mWordCards;
    }

    public void setWordCards(ArrayList<WordCard> wordCards) {
        mWordCards = wordCards;
    }

    public static class CardViewWordListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.card_view_level_list_card_word_text)
        TextView mMainWordTextView;
        @Bind(R.id.activity_level_base_layout_card_view_word_test_card_button_option_1)
        Button mOptionButton1;
        @Bind(R.id.activity_level_base_layout_card_view_word_test_card_button_option_2)
        Button mOptionButton2;
        @Bind(R.id.activity_level_base_layout_card_view_word_test_card_button_option_3)
        Button mOptionButton3;
        @Bind(R.id.activity_level_base_layout_card_view_word_test_card_button_option_4)
        Button mOptionButton4;

        int section;

        WordCard mWordCard;

        List<Word> optionWords;

        public CardViewWordListHolder(View itemView,int section) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setClickable(false);

            buttonRegisterListeners();

            this.section = section;

        }

        private void buttonRegisterListeners() {

            mOptionButton1.setOnClickListener(this);
            mOptionButton2.setOnClickListener(this);
            mOptionButton3.setOnClickListener(this);
            mOptionButton4.setOnClickListener(this);

        }


        public void setTextView(String text) {

            mMainWordTextView.setText(text);

        }

        public void setButtons(List<Word> words) {

            Log.e("my","id : "+mWordCard.getMainWord().getId());

            optionWords = words;

            mOptionButton1.setText(words.get(0).getMean());
            mOptionButton2.setText(words.get(1).getMean());
            mOptionButton3.setText(words.get(2).getMean());
            mOptionButton4.setText(words.get(3).getMean());


        }

        @Override
        public void onClick(View v) {

            Word word = null;

            try {
                optionWords.size();
            }catch (NullPointerException e){
                return;
            }

            if (v == mOptionButton1){

                word = optionWords.get(0);

            }else if (v == mOptionButton2){

                word = optionWords.get(1);

            }else if (v == mOptionButton3){

                word = optionWords.get(2);

            }else if (v == mOptionButton4){

                word = optionWords.get(3);

            }

            if (word != null){

                if (word.getId().equals(mWordCard.getMainWord().getId())){

                    EventBus.getDefault().post(new WordCardViewOnClickEvent(true,getAdapterPosition(),section,mWordCard.getMainWord().getId()));

                }else {

                    EventBus.getDefault().post(new WordCardViewOnClickEvent(false,getAdapterPosition(),section,mWordCard.getMainWord().getId()));

                }

            }

        }

        public void setWordCardModel(WordCard wordCard){

            mWordCard = wordCard;

        }
    }

}

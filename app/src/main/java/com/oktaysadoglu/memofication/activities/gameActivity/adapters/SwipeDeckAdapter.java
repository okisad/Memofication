package com.oktaysadoglu.memofication.activities.gameActivity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.gameActivity.GameActivity;
import com.oktaysadoglu.memofication.listeners.CardButtonClickListener;
import com.oktaysadoglu.memofication.model.WordCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public class SwipeDeckAdapter extends BaseAdapter {

    private List<WordCard> wordCards;
    private GameActivity gameActivity;

    public SwipeDeckAdapter(List<WordCard> wordCards, GameActivity gameActivity) {
        setWordCards(wordCards);
        setGameActivity(gameActivity);
    }

    @Override
    public int getCount() {
        return getWordCards().size();
    }

    @Override
    public Object getItem(int position) {
        return wordCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){
            LayoutInflater inflater = getGameActivity().getLayoutInflater();
            view = inflater.inflate(R.layout.word_card_card_layout, parent, false);
        }

        TextView orderText = (TextView) view.findViewById(R.id.word_card_card_layout_order_text);

        TextView mainWordText = (TextView) view.findViewById(R.id.word_card_card_layout_main_word_text);

        WordCard wordCard = wordCards.get(position);

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

        getGameActivity().fillWordAndOptions(view,position);

        View.OnClickListener onClickListener = new CardButtonClickListener(wordCard,buttonsBundle,parent, getGameActivity(), getGameActivity().getCardStack());

        firstOption.setOnClickListener(onClickListener);
        secondOption.setOnClickListener(onClickListener);
        thirdOption.setOnClickListener(onClickListener);
        fourthOption.setOnClickListener(onClickListener);

        return view;
    }

    public List<WordCard> getWordCards() {
        return wordCards;
    }

    public void setWordCards(List<WordCard> wordCards) {
        this.wordCards = wordCards;
    }

    public GameActivity getGameActivity() {
        return gameActivity;
    }

    public void setGameActivity(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }
}
package com.oktaysadoglu.memofication.activities.gameActivity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.gameActivity.GameActivity;
import com.oktaysadoglu.memofication.activities.gameActivity.listeners.CardButtonClickListener;
import com.oktaysadoglu.memofication.activities.gameActivity.listeners.FirstCardButtonClickListener;
import com.oktaysadoglu.memofication.activities.gameActivity.listeners.FourthCardButtonClickListener;
import com.oktaysadoglu.memofication.activities.gameActivity.listeners.SecondCardButtonClickListener;
import com.oktaysadoglu.memofication.activities.gameActivity.listeners.ThirdCardButtonClickListener;
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

        setOrderText(view,position);

        List<Button> buttons = getButtons(view);

        getGameActivity().fillWordAndOptions(view,position);

        setButtonsClickListeners(buttons,parent,position);

        return view;
    }

    private List<Button> getButtons(View view){

        Button firstOption = (Button) view.findViewById(R.id.word_card_card_layout_first_button);
        Button secondOption = (Button) view.findViewById(R.id.word_card_card_layout_second_button);
        Button thirdOption = (Button) view.findViewById(R.id.word_card_card_layout_third_button);
        Button fourthOption = (Button) view.findViewById(R.id.word_card_card_layout_fourth_button);

        List<Button> buttonsBundle = new ArrayList<>();
        buttonsBundle.add(firstOption);
        buttonsBundle.add(secondOption);
        buttonsBundle.add(thirdOption);
        buttonsBundle.add(fourthOption);

        return buttonsBundle;

    }

    private void setButtonsClickListeners(List<Button> buttons,ViewGroup parent,int position){

        buttons.get(0).setOnClickListener(new FirstCardButtonClickListener(getGameActivity(),parent,getGameActivity().getCardStack(),buttons,getWordCards().get(position)));
        buttons.get(1).setOnClickListener(new SecondCardButtonClickListener(getGameActivity(),parent,getGameActivity().getCardStack(),buttons,getWordCards().get(position)));
        buttons.get(2).setOnClickListener(new ThirdCardButtonClickListener(getGameActivity(),parent,getGameActivity().getCardStack(),buttons,getWordCards().get(position)));
        buttons.get(3).setOnClickListener(new FourthCardButtonClickListener(getGameActivity(),parent,getGameActivity().getCardStack(),buttons,getWordCards().get(position)));

    }

    private void setOrderText(View view,int position){

        TextView orderText = (TextView) view.findViewById(R.id.word_card_card_layout_order_text);

        orderText.setText((position+1)+"/50");

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
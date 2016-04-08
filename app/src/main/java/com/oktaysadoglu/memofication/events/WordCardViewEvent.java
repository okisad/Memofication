package com.oktaysadoglu.memofication.events;

import com.oktaysadoglu.memofication.model.WordCard;

/**
 * Created by oktaysadoglu on 12/02/16.
 */
public class WordCardViewEvent {

    private WordCard mWordCard;

    public WordCardViewEvent(WordCard wordCard) {
        mWordCard = wordCard;
    }

    public WordCard getWordCard() {
        return mWordCard;
    }

    public void setWordCard(WordCard wordCard) {
        mWordCard = wordCard;
    }
}

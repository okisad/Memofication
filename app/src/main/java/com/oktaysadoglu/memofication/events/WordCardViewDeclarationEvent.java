package com.oktaysadoglu.memofication.events;

import com.oktaysadoglu.memofication.model.WordCard;

import java.util.ArrayList;

/**
 * Created by oktaysadoglu on 12/02/16.
 */
public class WordCardViewDeclarationEvent {

    private ArrayList<WordCard> wordCards;

    public WordCardViewDeclarationEvent(ArrayList<WordCard> wordCards) {
        this.wordCards = wordCards;
    }

    public ArrayList<WordCard> getWordCards() {
        return wordCards;
    }

    public void setWordCards(ArrayList<WordCard> wordCards) {
        this.wordCards = wordCards;
    }
}

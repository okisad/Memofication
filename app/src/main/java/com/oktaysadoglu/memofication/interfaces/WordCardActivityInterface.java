package com.oktaysadoglu.memofication.interfaces;

import com.oktaysadoglu.memofication.model.WordCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktaysadoglu on 12/02/16.
 */
public interface WordCardActivityInterface {

    ArrayList<WordCard> getWordCard();

    void controlOfAnswer(int position,int section,boolean truth,long wordId);

}

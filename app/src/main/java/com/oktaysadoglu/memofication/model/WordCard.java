package com.oktaysadoglu.memofication.model;

import com.oktaysadoglu.memofication.db.Word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by oktaysadoglu on 12/02/16.
 */
public class WordCard implements Serializable{

    private Word mainWord;

    private Word firstOptionWord;

    private Word secondOptionWord;

    private Word thirdOptionWord;

    private Word fourthOptionWord;

    private List<Word> mWords;

    public Word getMainWord() {
        return mainWord;
    }

    public void setMainWord(Word mainWord) {
        this.mainWord = mainWord;
    }

    public Word getFirstOptionWord() {
        return firstOptionWord;
    }

    public void setFirstOptionWord(Word firstOptionWord) {
        this.firstOptionWord = firstOptionWord;
    }

    public Word getSecondOptionWord() {
        return secondOptionWord;
    }

    public void setSecondOptionWord(Word secondOptionWord) {
        this.secondOptionWord = secondOptionWord;
    }

    public Word getThirdOptionWord() {
        return thirdOptionWord;
    }

    public void setThirdOptionWord(Word thirdOptionWord) {
        this.thirdOptionWord = thirdOptionWord;
    }

    public Word getFourthOptionWord() {
        return fourthOptionWord;
    }

    public void setFourthOptionWord(Word fourthOptionWord) {
        this.fourthOptionWord = fourthOptionWord;
    }

    public List<Word> getWords() {
        return mWords;
    }

    public void setWords(List<Word> words) {
        mWords = words;
    }

    public void shuffle(){

        mWords = new ArrayList<>();

        mWords.add(getFirstOptionWord());
        mWords.add(getSecondOptionWord());
        mWords.add(getThirdOptionWord());
        mWords.add(getFourthOptionWord());

        Collections.shuffle(mWords);

    }

    @Override
    public String toString() {
        return "WordCard{" +
                "mainWord=" + mainWord.getWord() +
                "} - ["+ mWords.get(0).getWord()+","+mWords.get(1).getWord()+","+mWords.get(2).getWord()+","+mWords.get(3).getWord()+"]";
    }
}

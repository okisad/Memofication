package com.oktaysadoglu.memofication.model;

import android.util.Log;

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

    private List<Word> words;

    public WordCard() {
    }

    public WordCard(Word mainWord) {
        this.mainWord = mainWord;
        this.firstOptionWord = mainWord;
        this.secondOptionWord = mainWord;
        this.thirdOptionWord = mainWord;
        this.fourthOptionWord = mainWord;

        shuffle();

    }

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
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void shuffle(){

        words = new ArrayList<>();

        words.add(getFirstOptionWord());
        words.add(getSecondOptionWord());
        words.add(getThirdOptionWord());
        words.add(getFourthOptionWord());

        Collections.shuffle(words);

    }

    @Override
    public String toString() {
        return "WordCard{" +
                "mainWord=" + mainWord +
                ", words=" + words +
                '}';
    }
}

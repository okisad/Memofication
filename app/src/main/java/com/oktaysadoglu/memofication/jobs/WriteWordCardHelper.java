package com.oktaysadoglu.memofication.jobs;

import android.util.Log;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.db.Word;
import com.oktaysadoglu.memofication.db.WordDao;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.greenrobot.dao.query.Query;

/**
 * Created by oktaysadoglu on 10/02/16.
 */
public class WriteWordCardHelper {
    
    private ArrayList<String> mWordTypes;

    private Word mWord;

    public WriteWordCardHelper(Word word) {

        this.mWord = word;

        mWordTypes = new ArrayList<String>(){

            {
                add("inter.");
                add("u.");
                add("x");
            }

        };

    }


    public List<Word> getWordsOptions(){

        List<Word> wordsForOptions = null;

        if (filterWordTypes()) {

            wordsForOptions = getWordsForOptions();

        }

        return wordsForOptions;

    }

    private boolean filterWordTypes(){

        if (!mWordTypes.contains(mWord.getType())){

            return true;

        }else {

            return false;

        }

    }

    private List<Word> getWordsForOptions(){

        List<Word> wordsForOptions = new ArrayList<>();

        WordDao wordDao = Memofication.getWordDao();

        Query query = wordDao.queryBuilder().where(WordDao.Properties.Type.eq(mWord.getType())).build();

        List<Word> words = query.list();

        if(words.size() < 4)
            return null;

        int i = 0;

        while (i < 3){

            Random random = new Random();

            int randomId = random.nextInt(words.size());

            Word word = words.get(randomId);

            if(!mWord.getId().equals(word.getId())){

                wordsForOptions.add(word);

                i++;

                if (i == 3){

                    wordsForOptions.add(mWord);

                }

            }

        }

        if (wordsForOptions.size() == 4){

            return wordsForOptions;

        }else {

            return null;

        }

    }
}

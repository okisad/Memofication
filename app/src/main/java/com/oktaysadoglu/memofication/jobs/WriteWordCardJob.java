package com.oktaysadoglu.memofication.jobs;

import android.util.Log;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.birbit.android.jobqueue.TagConstraint;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.db.Word;
import com.oktaysadoglu.memofication.db.WordDao;
import com.oktaysadoglu.memofication.events.WordCardViewDeclarationEvent;
import com.oktaysadoglu.memofication.model.WordCard;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.util.ExceptionToResourceMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by oktaysadoglu on 12/02/16.
 */
public class WriteWordCardJob extends Job {

    private static String TAG = "WriteWordCardJob";

    private WordDao wordDao;

    private int startPoint;

    private int endPoint;

    public WriteWordCardJob(int startPoint,int endPoint) {

        super(new Params(1).setRequiresNetwork(false).addTags("writeWordCard").setSingleId("writeWordCard").groupBy("Deck").persist());

        setStartPoint(startPoint);

        setEndPoint(endPoint);

    }


    @Override
    public void onAdded() {
    }

    @Override
    public void onRun() throws Throwable {

        ArrayList<WordCard> wordCards = new ArrayList<>();

        Log.e("my","onRun");

        while (startPoint<endPoint) {

            WordCard wordCard = new WordCard();

            wordCard.setMainWord(Memofication.getWordDao().load((long) startPoint));

            if (wordCard.getMainWord() == null)
                throw new Exception("Main Word is null");

            wordCards.add(prepareWordCard(wordCard));

            startPoint++;

        }

        EventBus.getDefault().post(new WordCardViewDeclarationEvent(wordCards));

    }


    @Override
    protected void onCancel(int cancelReason) {


    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return RetryConstraint.CANCEL;
    }

    private WordCard prepareWordCard(WordCard wordCard){

        List<Word> wordsForOptions = getWordsForOptions(wordCard);

        wordCard.setFirstOptionWord(wordsForOptions.get(0));
        wordCard.setSecondOptionWord(wordsForOptions.get(1));
        wordCard.setThirdOptionWord(wordsForOptions.get(2));
        wordCard.setFourthOptionWord(wordsForOptions.get(3));

        wordCard.shuffle();

        return wordCard;

    }

    private List<Word> getWordsForOptions(WordCard wordCard) {

        setWordDao(Memofication.getWordDao());

        List<Word> wordsForOptions = new ArrayList<>();

        int i = 0;

        wordsForOptions.add(wordCard.getMainWord());

        List<Word> candidateWords = new ArrayList<>();

        while (i < 3) {

            boolean suitableForArray = true;

            Query suitableCandidateWordQuery = getWordDao().queryBuilder().where(WordDao.Properties.Type.eq(wordCard.getMainWord().getType()), WordDao.Properties.Id.notEq(wordCard.getMainWord().getId())).build();

            candidateWords = suitableCandidateWordQuery.list();

            Word candidateWord = (Word) candidateWords.get(new Random().nextInt(candidateWords.size()));

            for (int m = 0; m < wordsForOptions.size(); m++) {

                if (candidateWord.getId().equals(wordsForOptions.get(m).getId())) {

                    suitableForArray = false;

                }

            }

            if (suitableForArray) {

                wordsForOptions.add(candidateWord);

                i++;

            }

        }

        return wordsForOptions;

    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public WordDao getWordDao() {
        return wordDao;
    }

    public void setWordDao(WordDao wordDao) {
        this.wordDao = wordDao;
    }
}

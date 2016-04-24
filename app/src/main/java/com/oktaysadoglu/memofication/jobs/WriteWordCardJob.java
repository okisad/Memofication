package com.oktaysadoglu.memofication.jobs;

import android.util.Log;

import com.birbit.android.jobqueue.CancelReason;
import com.birbit.android.jobqueue.CancelResult;
import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.birbit.android.jobqueue.TagConstraint;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.db.Word;
import com.oktaysadoglu.memofication.db.WordDao;
import com.oktaysadoglu.memofication.events.WordCardViewEvent;
import com.oktaysadoglu.memofication.model.WordCard;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.ThreadMode;

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

    private Word mMainWord;

    WordCard mWordCard;

    private long id;

    private int startPoint;

    private int endPoint;

    public WriteWordCardJob(int startPoint,int endPoint) {

        super(new Params(1).setRequiresNetwork(false).addTags("writeWordCard").setSingleId("writeWordCard").groupBy("Deck").persist());

        /*this.id = id;*/

        this.startPoint = startPoint;

        this.endPoint = endPoint;
    }


    @Override
    public void onAdded() {
    }

    @Override
    public void onRun() throws Throwable {

        while (startPoint<endPoint) {

            mWordCard = new WordCard();

            setMainWord();

            WordCard wordCard = getWordCard();

            startPoint++;

            EventBus.getDefault().post(new WordCardViewEvent(wordCard));

        }

    }


    @Override
    protected void onCancel(int cancelReason) {


    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return RetryConstraint.CANCEL;
    }

    private void setMainWord() throws Throwable {

        mMainWord = Memofication.getWordDao().load((long) startPoint);

        if (mMainWord != null) {

            mWordCard.setMainWord(mMainWord);

        } else {

            Memofication.getJobManager().cancelJobsInBackground(null,TagConstraint.ANY,"writeWordCard");

            if (isCancelled()){

                Log.e(TAG,"cancelled");

                throw new Throwable();

            }

        }

    }

    /*private void listControl(List<Integer> integers) {

        if (id % 100 == 0) {

            Log.e("test", id + " : control ediliyor ");

        }

        for (int v = 0; v < integers.size(); v++) {

            int s = integers.get(v);

            for (int k = v + 1; k < integers.size(); k++) {

                if (s == integers.get(k)) {

                    Log.e("PROBLEM", "PROBLEM");

                }

            }

        }

    }*/

    public WordCard getWordCard() {

        List<Word> wordsForOptions = getWordsForOptions();

        mWordCard.setFirstOptionWord(wordsForOptions.get(0));
        mWordCard.setSecondOptionWord(wordsForOptions.get(1));
        mWordCard.setThirdOptionWord(wordsForOptions.get(2));
        mWordCard.setFourthOptionWord(wordsForOptions.get(3));

        mWordCard.shuffle();

        return mWordCard;

    }


    private List<Word> getWordsForOptions() {

        List<Word> wordsForOptions = new ArrayList<>();

        WordDao wordDao = Memofication.getWordDao();

        int i = 0;

        wordsForOptions.add(mMainWord);

        List<Word> candidateWords = new ArrayList<>();

        while (i < 3) {

            boolean suitableForArray = true;

            QueryBuilder queryBuilder = wordDao.queryBuilder();

            queryBuilder.where(WordDao.Properties.Type.eq(mMainWord.getType()), WordDao.Properties.Id.notEq(mMainWord.getId()));

            Query query = queryBuilder.build();

            Random random = new Random();

            candidateWords = query.list();

            Word candidateWord = (Word) candidateWords.get(random.nextInt(candidateWords.size()));

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

        /*Log.e(TAG, wordsForOptions.toString());*/

        return wordsForOptions;

    }

    public long getWordId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}

package com.oktaysadoglu.memofication.jobs.evaluationWordCard;

import android.util.Log;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.db.UserWords;
import com.oktaysadoglu.memofication.db.UserWordsDao;


/**
 * Created by oktaysadoglu on 11/02/16.
 */
public abstract class EvaluationWordCardJob extends Job {

    public static String TAG = "EvaluationWordCardJob";

    public static final int PRIORITY = 1;

    private long mainWordId;

    public EvaluationWordCardJob(long mainWordId) {
        super(new Params(PRIORITY).setRequiresNetwork(false).addTags(TAG).persist());

        this.mainWordId = mainWordId;

    }

    @Override
    public void onAdded() {

    }



   /* @Override
    public void onRun() throws Throwable {

        UserWordsDao userWordsDao = Memofication.getUserWordsDao();

        UserWords userWords = userWordsDao.loadByRowId(mainWordId);

        userWords.setWord(Memofication.getWordDao().load(mainWordId));

        if (truth){

            if (userWords.getCount() == -1){

                userWords.setCount(0);

            }else if (userWords.getCount() == 0){

                userWords.setCount(0);

            }else {

                userWords.setCount(userWords.getCount()-1);

            }

        }else {

            userWords.setIs_on_notification(1);

            if (userWords.getCount() == -1){

                userWords.setCount(2);

            }else if (userWords.getCount() == 0){

                userWords.setCount(2);

            }else {

                userWords.setCount(userWords.getCount()+1);

            }

        }

        userWords.update();

        Log.e("my",userWordsDao.loadByRowId(mainWordId).toString());
    }*/

    @Override
    protected void onCancel(int cancelReason) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }


    public long getMainWordId() {
        return mainWordId;
    }

    public void setMainWordId(long mainWordId) {
        this.mainWordId = mainWordId;
    }
}

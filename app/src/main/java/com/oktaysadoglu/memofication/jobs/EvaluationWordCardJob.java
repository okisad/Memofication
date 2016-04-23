package com.oktaysadoglu.memofication.jobs;

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
public class EvaluationWordCardJob extends Job {

    public static String TAG = "EvaluationWordCardJob";

    public static final int PRIORITY = 1;

    private long mMainWordId;

    private boolean truth;

    public EvaluationWordCardJob(long mainWordId,boolean truth) {
        super(new Params(PRIORITY).setRequiresNetwork(false).addTags(TAG).persist());

        mMainWordId = mainWordId;

        this.truth = truth;

    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {

        UserWordsDao userWordsDao = Memofication.getUserWordsDao();

        UserWords userWords = userWordsDao.loadByRowId(mMainWordId);

        userWords.setWord(Memofication.getWordDao().load(mMainWordId));

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

        Log.e("my",userWordsDao.loadByRowId(mMainWordId).toString());
    }

    @Override
    protected void onCancel(int cancelReason) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }

}

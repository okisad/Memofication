package com.oktaysadoglu.memofication.jobs;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.oktaysadoglu.memofication.preferences.TestPreferences;

/**
 * Created by oktaysadoglu on 27/04/16.
 */
public class TestJob extends Job {


    public TestJob() {
        super(new Params(1));
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        TestPreferences.setName(getApplicationContext(),"oktay");
    }

    @Override
    protected void onCancel(int cancelReason) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }
}

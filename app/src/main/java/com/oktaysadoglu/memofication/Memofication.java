package com.oktaysadoglu.memofication;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.oktaysadoglu.memofication.db.DaoMaster;
import com.oktaysadoglu.memofication.db.DaoSession;
import com.oktaysadoglu.memofication.db.DictionaryHelper;
import com.oktaysadoglu.memofication.db.LastWordNumberDao;
import com.oktaysadoglu.memofication.db.UserWordsDao;
import com.oktaysadoglu.memofication.db.WordDao;

import java.io.IOException;

/**
 * Created by oktaysadoglu on 04/02/16.
 */
public class Memofication extends Application{

    private static WordDao wordDao;

    private static UserWordsDao userWordsDao;

    private static LastWordNumberDao lastWordNumberDao;

    private static JobManager jobManager;

    public static WordDao getWordDao() {
        return wordDao;
    }

    public static UserWordsDao getUserWordsDao() {
        return userWordsDao;
    }

    public static JobManager getJobManager() {
        return jobManager;
    }

    public static LastWordNumberDao getLastWordNumberDao() {
        return lastWordNumberDao;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        configureJobManager();

        prepareDatabase();

    }

    private void configureJobManager(){

        Configuration configuration = new Configuration.Builder(this)
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";
                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }
                })
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minute
                .build();

        jobManager = new JobManager(configuration);

    }

    public static void setJobManager(JobManager jobManager) {
        Memofication.jobManager = jobManager;
    }

    private void prepareDatabase(){

        DictionaryHelper helper = DictionaryHelper.getInstance(this);

        try {
            helper.createDataBase();
            helper.openDataBase();
        } catch (IOException|java.sql.SQLException e) {
            e.printStackTrace();
        }

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);

        DaoSession daoSession = daoMaster.newSession();

        assignDaoClasses(daoSession);

    }

    private void assignDaoClasses(DaoSession daoSession){

        wordDao = daoSession.getWordDao();

        userWordsDao = daoSession.getUserWordsDao();

        lastWordNumberDao = daoSession.getLastWordNumberDao();

    }



}

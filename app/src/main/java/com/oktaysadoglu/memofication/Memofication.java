package com.oktaysadoglu.memofication;

import android.app.Application;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.oktaysadoglu.memofication.db.DaoMaster;
import com.oktaysadoglu.memofication.db.DaoSession;
import com.oktaysadoglu.memofication.db.DictionaryHelper;
import com.oktaysadoglu.memofication.db.UserWordsDao;
import com.oktaysadoglu.memofication.db.WordDao;

import java.io.IOException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by oktaysadoglu on 04/02/16.
 */
public class Memofication extends Application{

    private static WordDao mWordDao;

    private static UserWordsDao mUserWordsDao;

    private static JobManager mJobManager;

    public static WordDao getWordDao() {
        return mWordDao;
    }

    public static UserWordsDao getUserWordsDao() {
        return mUserWordsDao;
    }

    public static JobManager getJobManager() {
        return mJobManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /*prepareCalligraphy();*/

        configureJobManager();

        prepareDatabase();

    }

    /*private void prepareCalligraphy(){

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

    }*/

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
        mJobManager = new JobManager(configuration);

    }

    private void prepareDatabase(){

        DictionaryHelper helper = DictionaryHelper.getInstance(this);

        try {
            helper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            helper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);

        DaoSession daoSession = daoMaster.newSession();

        mWordDao = daoSession.getWordDao();

        mUserWordsDao = daoSession.getUserWordsDao();

    }



}

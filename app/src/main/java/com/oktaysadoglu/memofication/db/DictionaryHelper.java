package com.oktaysadoglu.memofication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by oktaysadoglu on 01/02/16.
 */
public class DictionaryHelper extends SQLiteOpenHelper {

    private static volatile DictionaryHelper mDictionaryHelper;

    private static String DB_PATH = "";

    private static String DATABASE_NAME = "memofication-db.db";

    private Context mContext;

    private SQLiteDatabase mDatabase;

    private static final int VERSION = 1;


    public static DictionaryHelper getInstance(Context context){

        if (mDictionaryHelper == null){

            synchronized (DictionaryHelper.class){

                if(mDictionaryHelper==null){

                    mDictionaryHelper = new DictionaryHelper(context.getApplicationContext());

                }

            }

        }

        return mDictionaryHelper;

    }


    private DictionaryHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);

        if(android.os.Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }

        mContext=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDataBase() throws IOException {

        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist)
        {
            this.getReadableDatabase();
            this.close();
            try
            {
                //Copy the database from assests
                copyDataBase();
                Log.e("my", "createDatabase database created");
            }
            catch (IOException mIOException)
            {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DATABASE_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException
    {
        InputStream mInput = mContext.getAssets().open(DATABASE_NAME);
        String outFileName = DB_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DATABASE_NAME;

        mDatabase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        return mDatabase != null;

    }


    @Override
    public synchronized void close()
    {
        if(mDatabase != null)

            mDatabase.close();

        super.close();
    }



}

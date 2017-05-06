package com.ojiofong.splash.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ojiofong on 12/17/15.
 */
public class MyDbHelper extends SQLiteOpenHelper {
    private static final String TAG = MyDbHelper.class.getSimpleName();
    private static MyDbHelper mDBHelper;

    private static final String SQL_CREATE_FEED_DATA_TABLE = "CREATE TABLE IF NOT EXISTS "
            + FeedTable.TABLE_NAME + " ( "
            + FeedTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FeedTable.COLUMN_ID + " INTEGER, "
            + FeedTable.COLUMN_TITLE + " TEXT, "
            + FeedTable.COLUMN_BODY + " TEXT, "
            + FeedTable.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL "
            + " );";

    public MyDbHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
        mDBHelper = this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        db.execSQL(SQL_CREATE_FEED_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        switch(oldVersion) {
            default:
                db.execSQL("DROP TABLE IF EXISTS " + FeedTable.TABLE_NAME);
                onCreate(db);
                break;
        }
    }

    public static SQLiteDatabase getReadableDB(){
        return mDBHelper.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDB(){
        return mDBHelper.getWritableDatabase();
    }

}

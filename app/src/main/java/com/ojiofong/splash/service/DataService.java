package com.ojiofong.splash.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.IntDef;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ojiofong.splash.Const;
import com.ojiofong.splash.Util;
import com.ojiofong.splash.db.FeedTable;
import com.ojiofong.splash.model.MyData;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ojiofong on 7/23/16.
 * .
 */

public class DataService extends IntentService {

   // private static final String TAG = DataService.class.getSimpleName();


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_OK, STATUS_SERVER_DOWN, STATUS_SERVER_INVALID, STATUS_NO_INTERNET, STATUS_NO_DATA})
    @interface Status {

    }

    public static final int STATUS_OK = 0;
    public static final int STATUS_SERVER_DOWN = 1;
    public static final int STATUS_SERVER_INVALID = 2;
    public static final int STATUS_NO_INTERNET = 3;
    public static final int STATUS_NO_DATA = 4;

    private void setStatus(@Status int status) {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .edit().putInt(Const.PREF_STATUS_KEY, status).apply();
    }


    private static OkHttpClient client = new OkHttpClient();


    public DataService() {
        super(DataService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        setStatus(STATUS_OK);

        if (!Util.isNetworkConnected(this)){
            setStatus(STATUS_NO_INTERNET);
            return;
        }

        sendLocalBroadCast(Const.ACTION_SHOW_PROGRESS_DIALOG);


        try {
            String result = run();

            Type collectionType = new TypeToken<List<MyData>>() {
            }.getType();
            List<MyData> list = new Gson().fromJson(result, collectionType);

            if (list == null) {
                setStatus(STATUS_SERVER_INVALID);
                return;

            } else if (list.isEmpty()) {
                setStatus(STATUS_NO_DATA);
                return;
            }

            addToDatabase(this, list);


        } catch (IOException e) {
            e.printStackTrace();
            setStatus(STATUS_SERVER_DOWN);
            sendLocalBroadCast(Const.ACTION_DISMISS_PROGRESS_DIALOG);

        }

        sendLocalBroadCast(Const.ACTION_DISMISS_PROGRESS_DIALOG);

    }

    private static String run() throws IOException {

        String url = "https://jsonplaceholder.typicode.com/posts";

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private static void addToDatabase(Context context, List<MyData> list) {

        if (list == null || list.isEmpty()) return;

        context.getContentResolver().delete(FeedTable.CONTENT_URI, null, null);

        ContentValues values[] = new ContentValues[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ContentValues value = new ContentValues();
            value.put(FeedTable.COLUMN_ID, list.get(i).getUserId());
            value.put(FeedTable.COLUMN_TITLE, list.get(i).getTitle());
            value.put(FeedTable.COLUMN_BODY, list.get(i).getBody());
            values[i] = value;
        }

        context.getContentResolver().bulkInsert(FeedTable.CONTENT_URI, values);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sendLocalBroadCast(Const.ACTION_DISMISS_PROGRESS_DIALOG);
    }

    private void sendLocalBroadCast(String action){
        Intent intent = new Intent(action);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}

package com.ojiofong.splash.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ojiofong on 12/17/15.
 * .
 */
public class FeedTable {

    public static final String TABLE_NAME = "feed_data_table";
    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_ID = "feed_data_id";
    public static final String COLUMN_TITLE = "feed_data_title";
    public static final String COLUMN_BODY = "feed_data_body";
    public static final String COLUMN_TIMESTAMP = "feed_data_time_stamp";
    public static final String DEFAULT_ORDER = COLUMN_TIMESTAMP + " DESC";
    public static final String ATOZ_ORDER = COLUMN_TITLE + " COLLATE NOCASE";

}

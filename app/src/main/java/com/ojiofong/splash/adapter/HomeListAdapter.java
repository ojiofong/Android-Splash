package com.ojiofong.splash.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ojiofong.splash.R;
import com.ojiofong.splash.db.FeedTable;

/**
 * Created by ojiofong on 7/21/16.
 */

public class HomeListAdapter extends CursorAdapter {


    public HomeListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        convertView.setTag(new ViewHolder(convertView));
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        int id  = cursor.getInt(cursor.getColumnIndexOrThrow(FeedTable.COLUMN_ID));
        String title  = cursor.getString(cursor.getColumnIndexOrThrow(FeedTable.COLUMN_TITLE));
        String body  = cursor.getString(cursor.getColumnIndexOrThrow(FeedTable.COLUMN_BODY));

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.idText.setText(String.valueOf(id));
        holder.titleText.setText(title);
        holder.bodyText.setText(body.replace("\n", " "));
    }

    private static class ViewHolder {
        TextView idText, titleText, bodyText;

        ViewHolder(View convertView) {
            idText = (TextView) convertView.findViewById(R.id.text_view_id);
            titleText = (TextView) convertView.findViewById(R.id.text_view_title);
            bodyText = (TextView) convertView.findViewById(R.id.text_view_body);

        }
    }
}

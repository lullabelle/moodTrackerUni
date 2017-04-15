package com.kainos.joannec.moodtrackeruni;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class EntryController extends DatabaseConnection {

    public EntryController(Context context) {
        super(context);
    }

    public boolean create(Entry entry) {
        ContentValues values = new ContentValues();
//adds new entry to the database
        values.put("moodName", entry.moodName);
        values.put("location", entry.location);
        values.put("timeStamp", getDateTime());
        values.put("moodRating", entry.moodRating);
        String timeStamp = getDateTime();
        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("moodEntry", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    //gets local system time to add to date field
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    //counts records in the database
    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM moodEntry";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

    // reads records from the database
    public List<Entry> read() {
        List<Entry> entryList = new ArrayList<Entry>();
        String sql = "SELECT * FROM moodEntry ORDER BY timeStamp DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String moodName = cursor.getString(cursor.getColumnIndex("moodName"));
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String timeStamp = cursor.getString(cursor.getColumnIndex("timeStamp"));
                int moodRating = Integer.parseInt(cursor.getString(cursor.getColumnIndex("moodRating")));
                Entry entry = new Entry();
                entry.moodName = moodName;
                entry.location = location;
                entry.timeStamp = timeStamp;
                entry.moodRating = moodRating;

                entryList.add(entry);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return entryList;

    }
}
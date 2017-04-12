package com.kainos.joannec.moodtrackeruni;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class EntryController extends DatabaseConnection {

    public EntryController(Context context) {
        super(context);
    }
    public boolean create(Entry entry) {


        ContentValues values = new ContentValues();

        values.put("moodName", entry.moodName);
        values.put("location", entry.location);
        values.put("timeStamp", getDateTime());

        //add mood category /label

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("moodEntry", null, values) > 0;
        db.close();

        return createSuccessful;
    }
    //gets local system time to add to date field
    private String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM moodEntry";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

}
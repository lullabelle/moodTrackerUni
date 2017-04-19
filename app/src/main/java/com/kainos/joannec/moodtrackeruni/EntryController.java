package com.kainos.joannec.moodtrackeruni;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class EntryController extends DatabaseConnection  {
    public EntryController(Context context) {
        super(context);
    }


    public boolean create(Entry entry) {
        ContentValues values = new ContentValues();

//adds new entry to the database
        values.put("moodName", entry.moodName);
        values.put("location", entry.location);
        values.put("moodRating", entry.moodRating);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("moodEntry", null, values) > 0;
        db.close();

        return createSuccessful;
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
    Context c = App.getContext();
    //reads moodRatings from the database as a total count for each mood type for date == today
    public int[] todayPie() {

        int[] today_data = new int[]{0, 0, 0, 0, 0};
        SQLiteDatabase db = this.getWritableDatabase();
        /*String sql1 = "select count(moodRating) AS counter from moodEntry where moodRating = 1 and date('now');";
        String sql2 = "select count(moodRating) from moodEntry where moodRating = 2 and date('now');";
        String sql3 = "select count(moodRating) from moodEntry where moodRating = 3 and date('now');";
        String sql4 = "select count(moodRating) from moodEntry where moodRating = 4 and date('now');";
        String sql5 = "select count(moodRating) from moodEntry where moodRating = 5 and date('now');";

        Cursor cursor1 = db.rawQuery(sql1, null);
        cursor1.moveToFirst();
        System.out.println(cursor1.getInt(0));
        today_data[0] = Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("counter")));
        cursor1.close();
     Cursor cursor2 = db.rawQuery(sql2, null);
        cursor2.moveToFirst();
        today_data[1] = cursor2.getInt(0);
        cursor2.close();
        Cursor cursor3 = db.rawQuery(sql3, null);
        cursor3.moveToFirst();
        today_data[2] = cursor3.getInt(0);
        cursor3.close();
        Cursor cursor4 = db.rawQuery(sql4, null);
        cursor4.moveToFirst();
        today_data[3] = cursor4.getInt(0);
        cursor4.close();
        Cursor cursor5 = db.rawQuery(sql5, null);
        cursor5.moveToFirst();
        today_data[4] = cursor5.getInt(0);
        cursor5.close();*/

       for (int i = 0; i < 5; i++) {
           int temp = i + 1;
           String sql = "select count(moodRating) from moodEntry where moodRating = " + temp + " and date('now');";
           Cursor cursor = db.rawQuery(sql, null);
           cursor.moveToFirst();
           today_data[i] = cursor.getInt(0);
           cursor.close();
       }
       db.close();
        return today_data;
    }


}


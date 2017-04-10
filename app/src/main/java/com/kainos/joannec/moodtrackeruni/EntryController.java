package com.kainos.joannec.moodtrackeruni;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class EntryController extends DatabaseConnection {

    public EntryController(Context context) {
        super(context);
    }
    public boolean create(Entry entry) {

        ContentValues values = new ContentValues();

        values.put("moodName", entry.moodName);
        values.put("location", entry.location);
        //add date field capture
        //add image picker

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("entry", null, values) > 0;
        db.close();

        return createSuccessful;
    }

}
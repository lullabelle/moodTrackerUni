package com.kainos.joannec.moodtrackeruni;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnection extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "MoodDatabase";

    public DatabaseConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE entry " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "moodName TEXT, " +
                "location TEXT ) ";
        //add date field capture
        //add image picker

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS entry";
        db.execSQL(sql);

        onCreate(db);
    }

}
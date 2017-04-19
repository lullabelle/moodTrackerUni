package com.kainos.joannec.moodtrackeruni;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnection extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MoodDatabase1";

    public DatabaseConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // create mood entry table
    String sql_create = "CREATE TABLE moodEntry " +
            "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "moodName TEXT, " +
            "location TEXT," +
            "timeStamp DATETIME DEFAULT CURRENT_TIMESTAMP," +
            "moodRating INT ) ";



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sql_create);

        }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlEnt = "DROP TABLE IF EXISTS moodEntry";
        db.execSQL(sqlEnt);


        //creates tables again
        onCreate(db);
    }

        }
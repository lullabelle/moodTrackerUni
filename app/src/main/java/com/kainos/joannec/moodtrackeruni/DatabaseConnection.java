package com.kainos.joannec.moodtrackeruni;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnection extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "MoodDatabase2";

    public DatabaseConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create mood entry table
        String sql = "DROP TABLE IF EXISTS moodEntry";
        db.execSQL(sql);
        String sql_create = "CREATE TABLE moodEntry " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "moodName TEXT, " +
                "location TEXT," +
                "timeStamp TEXT ) ";

        db.execSQL(sql_create);

/*        // create moodCategory table
        String sql_create_categories ="CREATE TABLE moodCategory "+
                                "keyId INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                "moodLabel TEXT ) ";

        db.execSQL(sql_create_categories);*/

        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlEnt = "DROP TABLE IF EXISTS moodEntry";
        db.execSQL(sqlEnt);

        /*String sqlCat = "DROP TABLE IF EXISTS moodCategory";
        db.execSQL(sqlCat);*/

        //creates tables again
        onCreate(db);
    }

}
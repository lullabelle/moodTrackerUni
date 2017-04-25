package com.kainos.joannec.moodtrackeruni;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnection extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "MoodDatabase2";

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
        db.execSQL("INSERT INTO moodEntry(moodName, location, timeStamp, moodRating) VALUES('Happy','Work','2017-04-17 12:00:00', 4)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('Awesome','Holiday','2017-04-19 12:00:00', 5)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('Tired','Bed','2017-04-18 12:00:00', 2)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('Anxious','Work','2017-04-20 12:00:00', 1)");
        db.execSQL("INSERT INTO moodEntry  (moodName, location, timeStamp, moodRating) VALUES('Bored','Studying at Uni','2017-04-21 12:00:00', 3)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating) VALUES('Flat','Uni','2017-04-21 12:00:00', 3)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating) VALUES('Joy','Wedding','2017-04-22 12:00:00', 5)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('sad','funeral','2017-04-23 12:00:00', 2)");
        db.execSQL("INSERT INTO moodEntry(moodName, location, timeStamp, moodRating) VALUES('Super','Work','2017-04-19 12:00:00', 4)");
        db.execSQL("INSERT INTO moodEntry(moodName, location, timeStamp, moodRating) VALUES('Unbelievable','Shopping','2017-04-22 12:00:00', 4)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating) VALUES('Fear','interview','2017-04-21 12:00:00', 1)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating) VALUES('Angry','Work','2017-04-20 12:00:00', 1)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('bored','cinema','2017-04-19 12:00:00', 3)");
        db.execSQL("INSERT INTO moodEntry(moodName, location, timeStamp, moodRating) VALUES('Great!','concert','2017-04-18 12:00:00', 4)");
        db.execSQL("INSERT INTO moodEntry(moodName, location, timeStamp, moodRating) VALUES('Fearless','playing sport','2017-04-22 12:00:00', 4)");
        db.execSQL("INSERT INTO moodEntry(moodName, location, timeStamp, moodRating) VALUES('Happy','Work','2017-04-17 12:00:00', 4)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('Really Happy','cinema','2017-04-24 12:00:00', 5)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('Exhausted','Sofa','2017-04-24 12:00:00', 2)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('Afraid','Hospital','2017-04-24 12:00:00', 1)");
        db.execSQL("INSERT INTO moodEntry(moodName, location, timeStamp, moodRating) VALUES('Sick','In bed','2017-04-25 12:00:00', 2)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('Anxiety high','uni','2017-04-25 12:00:00', 1)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('tired','Sofa','2017-04-25 12:00:00', 3)");
        db.execSQL("INSERT INTO moodEntry (moodName, location, timeStamp, moodRating)  VALUES('Excited','Holiday','2017-04-25 12:00:00', 5)");

        }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlEnt = "DROP TABLE IF EXISTS moodEntry";
        db.execSQL(sqlEnt);


        //creates tables again
        onCreate(db);
    }

        }
package com.kainos.joannec.moodtrackeruni;


public class Entry {


    String moodName;
    String location;
    String timeStamp;
    int moodRating;


    public String getLocation() {
        return location;
    }

      public String getMoodName() {

        return moodName;
    }

    public int getMoodRating() {
        return moodRating;
    }

    public Entry(String moodName, String location, int moodRating) {

        this.moodName = moodName;
        this.location = location;
        this.moodRating = moodRating;
    }

    public Entry() {

        this.moodName = "Content";
        this.location = "Home";
        this.moodRating = 3;
    }


}


package com.kainos.joannec.moodtrackeruni;

public class MoodCategory {

    int keyId;
    String moodLabel;


    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public String getMoodLabel() {
        return moodLabel;
    }

    public void setMoodLabel(String moodLabel) {
        this.moodLabel = moodLabel;
    }

    public MoodCategory(int keyId, String moodLabel) {
        this.keyId = keyId;
        this.moodLabel = moodLabel;
    }
}

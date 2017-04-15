package com.kainos.joannec.moodtrackeruni;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton buttonCreateEntry = (FloatingActionButton) findViewById(R.id.buttonCreateEntry);
        buttonCreateEntry.setOnClickListener(new OnClickListenerCreateEntry());
        countRecords();
        readEntries();
    }
    public void countRecords() {
        int recordCount = new EntryController(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " mood entries found");
    }
    //displays the current mood entries in the database in scroll view
    public void readEntries() {

        LinearLayout linearLayoutEntries = (LinearLayout) findViewById(R.id.linearLayoutEntries);
        linearLayoutEntries.removeAllViews();

        List<Entry> entries = new EntryController(this).read();
        if(entries.size() >0){
            for(Entry ent : entries){
                String moodName = ent.moodName;
                String location = ent.location;
                String timeStamp = ent.timeStamp;
                int moodRating = ent.moodRating;

                String textViewEntries = moodName + " - " + location + " - " + timeStamp + " - " + moodRating;
                TextView textViewMoodEntries = new TextView(this);
                textViewMoodEntries.setPadding(0,10,0,10);
                textViewMoodEntries.setText(textViewEntries);

                linearLayoutEntries.addView(textViewMoodEntries);
            }

        }else {
            TextView emptyItem = new TextView(this);
            emptyItem.setPadding(8,8,8,8);
            emptyItem.setText("Nothing here to see! How are you feeling?");

            linearLayoutEntries.addView(emptyItem);


        }

    }
}

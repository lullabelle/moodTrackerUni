package com.kainos.joannec.moodtrackeruni;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//variables
    private TextView mTextMessage;
// adds navigation location text to top of each screen
 private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_word_clouds);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add entry button
        FloatingActionButton buttonCreateEntry = (FloatingActionButton) findViewById(R.id.buttonCreateEntry);
        buttonCreateEntry.setOnClickListener(new OnClickListenerCreateEntry());
        // call create records method
        countRecords();
        //call read entries method
        readEntries();
        //bottom navigation bar
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        findViewById(R.id.navigation_home).setOnClickListener(this);
        findViewById(R.id.navigation_dashboard).setOnClickListener(this);
        findViewById(R.id.navigation_notifications).setOnClickListener(this);
    }
//METHODS
// bottom navigation bar
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    @Override
    public void onClick(View v) {
        Class view_class = null;

        switch(v.getId()){
            case R.id.navigation_home:
                view_class = MainActivity.class;
                break;
            case R.id.navigation_dashboard:
                view_class = Pie2Activity.class;
                break;
            case R.id.navigation_notifications:
                view_class = Settings.class;
                break;
        }
        startActivity(new Intent(this, view_class));
    }
// count records in the database and display in text view
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
            for(Entry ent : entries) {
                String moodName = ent.moodName;
                String location = ent.location;
                String timeStamp = ent.timeStamp;
                int moodRating = ent.moodRating;

                String textViewEntries = moodName + " - " + location + " - " + timeStamp;
                TextView textViewMoodEntries = new TextView(this);
                textViewMoodEntries.setPadding(0, 20, 0, 20);
                //add image to correspond to mood category
                       if (moodRating == 5) {
                            textViewMoodEntries.setCompoundDrawablesWithIntrinsicBounds(
                                    R.mipmap.amazing, 0, 0, 0);
                        } else if (moodRating == 4){
                            textViewMoodEntries.setCompoundDrawablesWithIntrinsicBounds(
                                    R.mipmap.happy, 0, 0, 0);
                        }else if (moodRating == 3) {
                        textViewMoodEntries.setCompoundDrawablesWithIntrinsicBounds(
                                R.mipmap.soso, 0, 0, 0);
                        }else if (moodRating == 2) {
                        textViewMoodEntries.setCompoundDrawablesWithIntrinsicBounds(
                                R.mipmap.down, 0, 0, 0);
                        }else textViewMoodEntries.setCompoundDrawablesWithIntrinsicBounds(
                            R.mipmap.terrible, 0, 0, 0);

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

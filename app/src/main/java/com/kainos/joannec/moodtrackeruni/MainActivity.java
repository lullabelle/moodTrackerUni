package com.kainos.joannec.moodtrackeruni;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public Button chartButton;
    public void init(){

        chartButton  = (Button) findViewById(R.id.btn_charts);
        chartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, Pie2Activity.class);
                startActivity(i);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton buttonCreateEntry = (FloatingActionButton) findViewById(R.id.buttonCreateEntry);
        buttonCreateEntry.setOnClickListener(new OnClickListenerCreateEntry());
        countRecords();
        readEntries();
        init();
    }
   /* private void chartButton(){
        Button chartButton = (Button) findViewById(R.id.btn_charts);
        chartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), Pie2Activity.class);
                startActivity(i);
            }
        });
    }*/
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

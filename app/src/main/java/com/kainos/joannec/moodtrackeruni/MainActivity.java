package com.kainos.joannec.moodtrackeruni;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton buttonCreateEntry = (FloatingActionButton) findViewById(R.id.buttonCreateEntry);
        buttonCreateEntry.setOnClickListener(new OnClickListenerCreateEntry());
        countRecords();
    }
    public void countRecords() {
        int recordCount = new EntryController(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " mood entries found");
    }
}

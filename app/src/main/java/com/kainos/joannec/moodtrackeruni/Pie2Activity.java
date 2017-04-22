package com.kainos.joannec.moodtrackeruni;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class Pie2Activity extends Activity implements View.OnClickListener {
    //variables
    PieChart pieChart;
    private static String TAG = "Pie2Activity";
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
        setContentView(R.layout.activity_pie2);
        new App().setContext(this);
        // add pie chart view for today
        pieChart =(PieChart) findViewById(R.id.today_chart);
        Description desc = new Description();
        desc.setText("Mood Summary for today");
        pieChart.setDescription(desc);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Today");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        addDataSet();
        //bottom navigation bar
        mTextMessage = (TextView) findViewById(R.id.pie_message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationPie);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        findViewById(R.id.navigation_home).setOnClickListener(this);
        findViewById(R.id.navigation_dashboard).setOnClickListener(this);
        findViewById(R.id.navigation_notifications).setOnClickListener(this);

    }
//METHODS
    // bottom navigation bar
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
                view_class = WordCloud.class;
                break;
        }
        startActivity(new Intent(this, view_class));
    }
    private void addDataSet(){
        String xData []= {"terrible","down","so-so","happy","amazing"};
        int yData[] = new EntryController(this).todayPie();
        //int yData[] = { 5, 10, 15, 30, 40 };
        Log.d(TAG, "addDataSet started");
        ArrayList <PieEntry> yEntrys = new ArrayList<PieEntry>();
        ArrayList <String> xEntrys = new ArrayList<String>();

        for (int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i], i));
        }


        for (int i = 0; i < 5; i++){
            xEntrys.add(xData[i]);
        }
        // create Pie Data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys,"Mood Cats");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        // colours
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(144, 164, 174));
        colors.add(Color.rgb(3, 155, 229));
        colors.add(Color.rgb(142, 101, 255));
        colors.add(Color.rgb(59, 255, 162));
        colors.add(Color.rgb(255, 209, 33));
        colors.add(Color.rgb(144, 164, 174));

        pieDataSet.setColors(colors);

        //add legend to a chart

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }
}

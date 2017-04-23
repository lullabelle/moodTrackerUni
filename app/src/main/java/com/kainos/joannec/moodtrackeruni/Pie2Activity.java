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
import java.util.Calendar;

public class Pie2Activity extends Activity implements View.OnClickListener {
    //variables
    PieChart pieChart;
    PieChart pieChart_yesterday;
    PieChart pieChart2;
    PieChart pieChart3;
    PieChart pieChart4;
    PieChart pieChart5;
    PieChart pieChart_week;
    private static String TAG = "Pie2Activity";
    private TextView mTextMessage;
    Calendar calendar = Calendar.getInstance(); // this would default to now
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
        pieChart = (PieChart) findViewById(R.id.today_chart);
        Description desc = new Description();
        desc.setText("Mood Summary for today");
        pieChart.setDescription(desc);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Today");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        addDataSet();
//add Pie Chart view for yesterday
        pieChart_yesterday = (PieChart) findViewById(R.id.yesterday_chart);
        Description desc2 = new Description();
        desc2.setText("Mood Summary for yesterday");
        pieChart_yesterday.setDescription(desc2);
        pieChart_yesterday.setRotationEnabled(true);
        pieChart_yesterday.setHoleRadius(50f);
        pieChart_yesterday.setTransparentCircleAlpha(0);
        pieChart_yesterday.setCenterText("Yesterday");
        pieChart_yesterday.setCenterTextSize(10);
        pieChart_yesterday.setDrawEntryLabels(true);
        addYestDataSet();
        //add Pie Chart view for  2days ago
        pieChart2 = (PieChart) findViewById(R.id.twodays_chart);
        Description desc3 = new Description();
        desc3.setText("Mood Summary for 2 days ago");
        pieChart2.setDescription(desc3);
        pieChart2.setRotationEnabled(true);
        pieChart2.setHoleRadius(50f);
        pieChart2.setTransparentCircleAlpha(0);
        pieChart2.setCenterText("2 days ago");
        pieChart2.setCenterTextSize(10);
        pieChart2.setDrawEntryLabels(true);
        add2dayDataSet();
        //add Pie Chart view for 3 days ago
        pieChart3 = (PieChart) findViewById(R.id.threedays_chart);
        Description desc4 = new Description();
        desc4.setText("Mood Summary for 3 days ago");
        pieChart3.setDescription(desc4);
        pieChart3.setRotationEnabled(true);
        pieChart3.setHoleRadius(50f);
        pieChart3.setTransparentCircleAlpha(0);
        pieChart3.setCenterText("3 days ago");
        pieChart3.setCenterTextSize(10);
        pieChart3.setDrawEntryLabels(true);
        add3DayDataSet();
        //add Pie Chart view for yesterday
        pieChart4 = (PieChart) findViewById(R.id.fourdays_chart);
        Description desc5 = new Description();
        desc5.setText("Mood Summary for 4 days ago");
        pieChart4.setDescription(desc5);
        pieChart4.setRotationEnabled(true);
        pieChart4.setHoleRadius(50f);
        pieChart4.setTransparentCircleAlpha(0);
        pieChart4.setCenterText("4 days ago");
        pieChart4.setCenterTextSize(10);
        pieChart4.setDrawEntryLabels(true);
        add4DayDataSet();
        //add Pie Chart view for yesterday
        pieChart5 = (PieChart) findViewById(R.id.fivedays_chart);
        Description desc6 = new Description();
        desc6.setText("Mood Summary for 5 days ago");
        pieChart5.setDescription(desc6);
        pieChart5.setRotationEnabled(true);
        pieChart5.setHoleRadius(50f);
        pieChart5.setTransparentCircleAlpha(0);
        pieChart5.setCenterText("5 days ago");
        pieChart5.setCenterTextSize(10);
        pieChart5.setDrawEntryLabels(true);
        add5DayDataSet();
//add PieChart view for previous week
        pieChart_week = (PieChart) findViewById(R.id.week_chart);
        Description desc7 = new Description();
        desc7.setText("Weekly Mood Summary");
        pieChart_week.setDescription(desc7);
        pieChart_week.setRotationEnabled(true);
        pieChart_week.setHoleRadius(50f);
        pieChart_week.setTransparentCircleAlpha(0);
        pieChart_week.setCenterText("This Week");
        pieChart_week.setCenterTextSize(10);
        pieChart_week.setDrawEntryLabels(true);
        addWeekDataSet();

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
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    @Override
    public void onClick(View v) {
        Class view_class = null;

        switch (v.getId()) {
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
//add data set to pie chart for today's mood entries
    private void addDataSet() {
        String xData[] = {"terrible", "down", "so-so", "happy", "amazing"};
        int yData[] = new EntryController(this).generatePie("today");
        //int yData[] = { 5, 10, 15, 30, 40 };
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<PieEntry>();
        ArrayList<String> xEntrys = new ArrayList<String>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }


        for (int i = 0; i < 5; i++) {
            xEntrys.add(xData[i]);
        }
        // create Pie Data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Mood Categories");
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
//add data set to pie chart for yesterday's mood entries
    private void addYestDataSet() {
        String xData[] = {"terrible", "down", "so-so", "happy", "amazing"};
        int yData[] = new EntryController(this).generatePie("yesterday");
        //int yData[] = { 5, 10, 15, 30, 40 };
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<PieEntry>();
        ArrayList<String> xEntrys = new ArrayList<String>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }


        for (int i = 0; i < 5; i++) {
            xEntrys.add(xData[i]);
        }
        // create Pie Data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Mood Cats");
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

        Legend legend = pieChart_yesterday.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart_yesterday.setData(pieData);
        pieChart_yesterday.invalidate();
    }
//add data set to pie chart for previous week's mood entries
    private void addWeekDataSet() {
        String xData[] = {"terrible", "down", "so-so", "happy", "amazing"};
        int yData[] = new EntryController(this).generatePie("1 week");
        //int yData[] = { 5, 10, 15, 30, 40 };
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<PieEntry>();
        ArrayList<String> xEntrys = new ArrayList<String>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }


        for (int i = 0; i < 5; i++) {
            xEntrys.add(xData[i]);
        }
        // create Pie Data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Mood Cats");
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

        Legend legend = pieChart_week.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart_week.setData(pieData);
        pieChart_week.invalidate();

    }
//add data set to pie chart for 2 days ago mood entries
    private void add2dayDataSet() {
    String xData[] = {"terrible", "down", "so-so", "happy", "amazing"};
    int yData[] = new EntryController(this).generatePie("2 days ago");
    //int yData[] = { 5, 10, 15, 30, 40 };
    Log.d(TAG, "addDataSet started");
    ArrayList<PieEntry> yEntrys = new ArrayList<PieEntry>();
    ArrayList<String> xEntrys = new ArrayList<String>();

    for (int i = 0; i < yData.length; i++) {
        yEntrys.add(new PieEntry(yData[i], i));
    }


    for (int i = 0; i < 5; i++) {
        xEntrys.add(xData[i]);
    }
    // create Pie Data set
    PieDataSet pieDataSet = new PieDataSet(yEntrys, "Mood Cats");
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

    Legend legend = pieChart2.getLegend();
    legend.setForm(Legend.LegendForm.CIRCLE);
    legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
    legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
    legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
    legend.setDrawInside(false);

    // create pie data object
    PieData pieData = new PieData(pieDataSet);
    pieChart2.setData(pieData);
    pieChart2.invalidate();

}
//add data set to pie chart for 3 days ago mood entries
    private void add3DayDataSet() {
        String xData[] = {"terrible", "down", "so-so", "happy", "amazing"};
        int yData[] = new EntryController(this).generatePie("3 days ago");
        //int yData[] = { 5, 10, 15, 30, 40 };
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<PieEntry>();
        ArrayList<String> xEntrys = new ArrayList<String>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }


        for (int i = 0; i < 5; i++) {
            xEntrys.add(xData[i]);
        }
        // create Pie Data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Mood Cats");
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

        Legend legend = pieChart3.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart3.setData(pieData);
        pieChart3.invalidate();

}
//add data set to pie chart for 4 days ago mood entries
    private void add4DayDataSet() {
        String xData[] = {"terrible", "down", "so-so", "happy", "amazing"};
        int yData[] = new EntryController(this).generatePie("4 days ago");
        //int yData[] = { 5, 10, 15, 30, 40 };
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<PieEntry>();
        ArrayList<String> xEntrys = new ArrayList<String>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }


        for (int i = 0; i < 5; i++) {
            xEntrys.add(xData[i]);
        }
        // create Pie Data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Mood Cats");
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

        Legend legend = pieChart4.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart4.setData(pieData);
        pieChart4.invalidate();

    }
//add data set to pie chart for 5 days ago mood entries
    private void add5DayDataSet() {
        String xData[] = {"terrible", "down", "so-so", "happy", "amazing"};
        int yData[] = new EntryController(this).generatePie("5 days ago");
        //int yData[] = { 5, 10, 15, 30, 40 };
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<PieEntry>();
        ArrayList<String> xEntrys = new ArrayList<String>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }


        for (int i = 0; i < 5; i++) {
            xEntrys.add(xData[i]);
        }
        // create Pie Data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Mood Cats");
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

        Legend legend = pieChart5.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart5.setData(pieData);
        pieChart5.invalidate();

    }

}

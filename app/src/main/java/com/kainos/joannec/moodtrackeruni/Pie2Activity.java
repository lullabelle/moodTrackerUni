package com.kainos.joannec.moodtrackeruni;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class Pie2Activity extends AppCompatActivity {

    private static String TAG = "Pie2Activity";

    private int yData[] = new EntryController(this).todayPie();
    //int yData[] = getIntent().getIntArrayExtra("pieArray");
    String xData []= {"terrible","down","so-so","happy","amazing"};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie2);

        Log.d(TAG, "onCreate: starting to create chart");

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
    }
    private void addDataSet(){
        Log.d(TAG, "addDataSet started");
        ArrayList <PieEntry> yEntrys = new ArrayList<>();
        ArrayList <String> xEntrys = new ArrayList<>();

        for (int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for (int i = 0; i < xData.length; i++){
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

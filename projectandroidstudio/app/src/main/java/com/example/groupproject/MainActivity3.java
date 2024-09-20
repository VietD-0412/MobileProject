// com.example.groupproject.MainActivity.java
package com.example.groupproject;
import android.graphics.Color;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.components.YAxis;




public class MainActivity extends AppCompatActivity {




    private LineChart lineChart;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        lineChart = findViewById(R.id.lineChart);
        loadLineChart();




        Log.i("onCreate", "onCreate");
    }




    private void loadLineChart() {
        ArrayList<Entry> entries = new ArrayList<>();


        // Sample data
        entries.add(new Entry(0, 2297));
        entries.add(new Entry(1, 2422));
        entries.add(new Entry(2, 2540));
        entries.add(new Entry(3, 2647));
        entries.add(new Entry(4, 2714));
        entries.add(new Entry(5, 2703));
        entries.add(new Entry(6, 2718));
        entries.add(new Entry(7, 2737));
        entries.add(new Entry(8, 2713));
        entries.add(new Entry(9, 2709));
        entries.add(new Entry(10, 2731));


        LineDataSet lineDataSet = new LineDataSet(entries, "Chess Result");
        LineData lineData = new LineData(lineDataSet);


        lineDataSet.setColor(Color.RED);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setCircleColor(Color.BLACK);


        final String[] years = new String[] {"2004", "2006", "2008", "2010", "2012", "2014", "2016", "2018", "2020", "2022", "2024"};


        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return years[(int) value];
            }
        };


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(formatter);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(2f);


        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.removeAllLimitLines();
        yAxisLeft.setDrawLimitLinesBehindData(true);






        lineChart.getDescription().setText("Year:");
        lineChart.getDescription().setTextColor(Color.BLACK);
        lineChart.getDescription().setTextSize(12f);
        lineChart.getAxisRight().setEnabled(false);
        yAxisLeft.setEnabled(true);
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setAxisLineWidth(2f);


        lineChart.setData(lineData);
        lineChart.invalidate(); // refresh
    }}


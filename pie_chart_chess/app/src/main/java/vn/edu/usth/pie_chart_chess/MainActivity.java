package vn.edu.usth.pie_chart_chess;



import android.graphics.Color;
import android.os.Bundle;
import android.widget.NumberPicker;


import androidx.appcompat.app.AppCompatActivity;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;


import java.util.ArrayList;
import java.util.Formatter;


public class MainActivity extends AppCompatActivity {
    private PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pieChart = findViewById(R.id.activity_main_piechart);
        setupPieChart();
        loadPieChartData(300,57,65);
    }


    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);


        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);






        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }


    private void loadPieChartData(int wins, int losses, int draws) {
        int totalMatches = wins + draws + losses;
        pieChart.setCenterText("" +totalMatches);
        pieChart.setCenterTextSize(24);


        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(wins, "Wins"));
        entries.add(new PieEntry(draws, "Draws"));
        entries.add(new PieEntry(losses, "Losses"));


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);


        PieDataSet dataSet = new PieDataSet(entries, "Total matches");
        dataSet.setColors(colors);


        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);


        pieChart.setData(data);
        pieChart.invalidate();


        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}

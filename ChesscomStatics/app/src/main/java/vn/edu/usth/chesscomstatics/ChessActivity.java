package vn.edu.usth.chesscomstatics;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ChessActivity extends AppCompatActivity {

    private static final String TAG = "ChessActivity";
    private LineChart lineChart;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lineChart = findViewById(R.id.lineChart);
        lineChart.setVisibility(View.GONE);
        pieChart = findViewById(R.id.pieChart);
        pieChart.setVisibility(View.GONE);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_profile, new FragmentProfile())
                .commit();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.current_fragment, new FragmentRanking())
                    .commit();
        }

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i(TAG, "onCreate() called");
    }

    public void updateLineChart(ArrayList<Entry> entries, String[] years) {
        LineDataSet lineDataSet = new LineDataSet(entries, "Rating");
        lineDataSet.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        lineDataSet.setValueTextColor(getResources().getColor(android.R.color.black));
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setCircleColor(getResources().getColor(android.R.color.black));

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        // Custom X-Axis labels
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return years[(int) value];
            }
        };

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(formatter);
        xAxis.setGranularity(1f);  // One label per entry
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(2f);
        YAxis yAxisleft = lineChart.getAxisLeft();
        yAxisleft.removeAllLimitLines();
        yAxisleft.setDrawLimitLinesBehindData(true);
        lineChart.getDescription().setText("Year:");
        lineChart.getDescription().setTextColor(Color.BLACK);
        lineChart.getDescription().setTextSize(12f);
        lineChart.getAxisRight().setEnabled(false);
        yAxisleft.setEnabled(true);
        yAxisleft.setDrawGridLines(false);
        yAxisleft.setAxisLineWidth(2f);

        lineChart.invalidate();
        lineChart.setVisibility(View.VISIBLE);
        lineChart.animateX(1400, Easing.EaseInSine);
    }

    public void hideLineChart() {
        lineChart.setVisibility(View.GONE);
    }

    public void updatePieChart(int wins, int losses, int draws) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(wins, "Wins"));
        entries.add(new PieEntry(losses, "Losses"));
        entries.add(new PieEntry(draws, "Draws"));

        PieDataSet pieDataSet = new PieDataSet(entries, "Game Results");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#ADD8E6"));
        colors.add(Color.parseColor("#FFA07A"));
        colors.add(Color.parseColor("#D3D3D3"));
        pieDataSet.setColors(colors);

        int totalGames = wins + losses + draws;
        pieChart.setCenterText(String.valueOf(totalGames));
        pieChart.setCenterTextSize(24);

        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(12f);


        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);

        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(55f);
        Legend legend = pieChart.getLegend();

        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        legend.setTextColor(Color.BLACK);

        pieChart.invalidate();
        pieChart.setVisibility(View.VISIBLE);
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

    public void hidePieChart() {
        pieChart.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.refresh) {
            Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.action_sth) {
            Toast.makeText(this, "Doing Something", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

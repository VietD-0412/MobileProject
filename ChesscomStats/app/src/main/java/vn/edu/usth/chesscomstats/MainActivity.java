package vn.edu.usth.chesscomstats;


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
        entries.add(new Entry(0, 1000));
        entries.add(new Entry(1, 1200));
        entries.add(new Entry(2, 1100));
        entries.add(new Entry(3, 1400));
        entries.add(new Entry(4, 1450));


        LineDataSet lineDataSet = new LineDataSet(entries, "Chess Result");
        LineData lineData = new LineData(lineDataSet);


        lineDataSet.setColor(Color.RED);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setCircleColor(Color.BLACK);


        lineChart.getDescription().setText("Chess Result by Month played"); // Set description text
        lineChart.getDescription().setTextColor(Color.BLACK); // Set text color
        lineChart.getDescription().setTextSize(12f);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setEnabled(true);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setAxisLineWidth(2f);
        lineChart.getAxisLeft().setAxisLineWidth(2f);
        lineChart.setData(lineData);
        lineChart.invalidate(); // refresh
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart", "onStart");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", "onResume");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause", "onPause");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.i("onStop", "onStop");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", "onDestroy");
    }
}

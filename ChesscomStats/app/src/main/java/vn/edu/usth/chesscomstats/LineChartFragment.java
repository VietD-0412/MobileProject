package vn.edu.usth.chesscomstats;

import android.os.Bundle;
import android.graphics.Color;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LineChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LineChartFragment extends Fragment {

    private LineChart lineChart;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LineChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LineChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LineChartFragment newInstance(String param1, String param2) {
        LineChartFragment fragment = new LineChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        lineChart = findViewById(R.id.lineChart);
        loadLineChart();

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_line_chart, container, false);
    }
}
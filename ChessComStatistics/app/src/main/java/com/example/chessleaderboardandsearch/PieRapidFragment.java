package com.example.chessleaderboardandsearch;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PieRapidFragment extends Fragment {
    private PieChart pieChart;
    private TextView detailsTextView;
    private static final String ARG_USERNAME = "username";

    public static PieRapidFragment newInstance(String username) {
        PieRapidFragment fragment = new PieRapidFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie_rapid, container, false);

        pieChart = view.findViewById(R.id.pieRapid);
        detailsTextView = view.findViewById(R.id.detailsTextView);

        if (getArguments() != null) {
            String username = getArguments().getString(ARG_USERNAME);
            fetchRecord(username);
        }

        setupPieChart();
        return view;
    }

    private void fetchRecord(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chess.com/pub/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChessApiService apiService = retrofit.create(ChessApiService.class);
        apiService.getRapidStats(username).enqueue(new Callback<RapidStats>() {
            @Override
            public void onResponse(Call<RapidStats> call, Response<RapidStats> response) {
                if (response.isSuccessful() && response.body() != null) {
                    getRecord(response.body());
                }
            }

            @Override
            public void onFailure(Call<RapidStats> call, Throwable t) {

            }
        });
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(false);
        pieChart.setEntryLabelTextSize(20);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(true);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void getRecord(RapidStats rapidStats) {
        int wins = rapidStats.getWin();
        int losses = rapidStats.getLoss();
        int draws = rapidStats.getDraw();
        int totalMatches = wins + draws + losses;

        String detailsText = "Total games played:" + totalMatches + "\nWins: " + wins + "\nDraws: " + draws + "\nLosses: " + losses;
        detailsTextView.setText(detailsText);

        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry((float) wins / totalMatches * 100, "Wins"));
        entries.add(new PieEntry((float) draws / totalMatches * 100, "Draws"));
        entries.add(new PieEntry((float) losses / totalMatches * 100, "Losses"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.GRAY);
        colors.add(Color.RED);

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}

package com.example.chessleaderboardandsearch;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class PieRapid extends Fragment {
    private PieChart pieChart;
    private static final String ARG_USERNAME = "username";

    public static PieRapid newInstance(String username){
        PieRapid fragment = new PieRapid();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie_rapid, container, false);

        pieChart = view.findViewById(R.id.pieRapid);

        if(getArguments() != null){
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


    private void getRecord(RapidStats rapidStats) {
        int wins = rapidStats.getWin();
        int losses = rapidStats.getLoss();
        int draws = rapidStats.getDraw();
        int totalMatches = wins + draws + losses;

        pieChart.setCenterText("" +totalMatches);
        pieChart.setCenterTextSize(24);


        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(wins));
        entries.add(new PieEntry(draws));
        entries.add(new PieEntry(losses));


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

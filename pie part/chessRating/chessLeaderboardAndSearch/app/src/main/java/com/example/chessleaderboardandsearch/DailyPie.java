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

import java.util.ArrayList;

public class DailyPie extends Fragment {


    public DailyPie() {
        // Required empty public constructor
    }

    private PieChart pieChart;

    public void updatePieChart(int wins, int losses, int draws) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(wins, ));
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
}

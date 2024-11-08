package com.example.chessleaderboardandsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RapidRatingFragment extends Fragment {

    private static final String ARG_USERNAME = "username";
    private TextView bestRatingTextView;
    private TextView currentRatingTextView;
    private TextView bestRatingDateTextView;
    private TextView currentRatingDateTextView;

    public static RapidRatingFragment newInstance(String username) {
        RapidRatingFragment fragment = new RapidRatingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rapid_rating, container, false);
        bestRatingTextView = view.findViewById(R.id.bestRatingTextView);
        currentRatingTextView = view.findViewById(R.id.currentRatingTextView);
        bestRatingDateTextView = view.findViewById(R.id.bestRatingDateTextView);
        currentRatingDateTextView = view.findViewById(R.id.currentRatingDateTextView);

        if (getArguments() != null) {
            String username = getArguments().getString(ARG_USERNAME);
            fetchRapidRatings(username);
        }

        return view;
    }

    private void fetchRapidRatings(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chess.com/pub/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChessApiService apiService = retrofit.create(ChessApiService.class);
        apiService.getRapidStats(username).enqueue(new Callback<RapidStats>() {
            @Override
            public void onResponse(Call<RapidStats> call, Response<RapidStats> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displayRapidRatings(response.body());
                }
            }

            @Override
            public void onFailure(Call<RapidStats> call, Throwable t) {
                bestRatingTextView.setText("Failed to load rapid ratings");
                currentRatingTextView.setText("Failed to load rapid ratings");
            }
        });
    }

    private void displayRapidRatings(RapidStats rapidStats) {
        bestRatingTextView.setText("Best Rating: " + rapidStats.getBestRating());
        currentRatingTextView.setText("Current Rating: " + rapidStats.getCurrentRating());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        bestRatingDateTextView.setText("Best Rating Date: " + sdf.format(new Date(rapidStats.getBestRatingDate() * 1000)));
        currentRatingDateTextView.setText("Current Rating Date: " + sdf.format(new Date(rapidStats.getCurrentRatingDate() * 1000)));
    }
}
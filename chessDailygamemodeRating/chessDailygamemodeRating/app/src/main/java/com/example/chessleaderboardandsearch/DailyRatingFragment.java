package com.example.chessleaderboardandsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyRatingFragment extends Fragment {

    private static final String ARG_USERNAME = "username";
    private TextView bestRatingTextView;
    private TextView currentRatingTextView;

    public static DailyRatingFragment newInstance(String username) {
        DailyRatingFragment fragment = new DailyRatingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_rating, container, false);
        bestRatingTextView = view.findViewById(R.id.bestRatingTextView);
        currentRatingTextView = view.findViewById(R.id.currentRatingTextView);
        ImageButton goBackButton = view.findViewById(R.id.goBackButton);

        if (getArguments() != null) {
            String username = getArguments().getString(ARG_USERNAME);
            fetchDailyRatings(username);
        }

        goBackButton.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        return view;
    }

    private void fetchDailyRatings(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chess.com/pub/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChessApiService apiService = retrofit.create(ChessApiService.class);
        apiService.getDailyStats(username).enqueue(new Callback<DailyStats>() {
            @Override
            public void onResponse(Call<DailyStats> call, Response<DailyStats> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displayDailyRatings(response.body());
                }
            }

            @Override
            public void onFailure(Call<DailyStats> call, Throwable t) {
                bestRatingTextView.setText("Failed to load daily ratings");
                currentRatingTextView.setText("Failed to load daily ratings");
            }
        });
    }

    private void displayDailyRatings(DailyStats dailyStats) {
        bestRatingTextView.setText("Best Rating: " + dailyStats.getBestRating());
        currentRatingTextView.setText("Current Rating: " + dailyStats.getCurrentRating());
    }
}
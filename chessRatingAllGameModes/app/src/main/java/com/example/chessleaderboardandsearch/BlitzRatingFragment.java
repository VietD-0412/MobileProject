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

public class BlitzRatingFragment extends Fragment {

    private static final String ARG_USERNAME = "username";
    private TextView bestRatingTextView;
    private TextView currentRatingTextView;

    public static BlitzRatingFragment newInstance(String username) {
        BlitzRatingFragment fragment = new BlitzRatingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blitz_rating, container, false);
        bestRatingTextView = view.findViewById(R.id.bestRatingTextView);
        currentRatingTextView = view.findViewById(R.id.currentRatingTextView);

        if (getArguments() != null) {
            String username = getArguments().getString(ARG_USERNAME);
            fetchBlitzRatings(username);
        }

        return view;
    }

    private void fetchBlitzRatings(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chess.com/pub/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChessApiService apiService = retrofit.create(ChessApiService.class);
        apiService.getBlitzStats(username).enqueue(new Callback<BlitzStats>() {
            @Override
            public void onResponse(Call<BlitzStats> call, Response<BlitzStats> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displayBlitzRatings(response.body());
                }
            }

            @Override
            public void onFailure(Call<BlitzStats> call, Throwable t) {
                bestRatingTextView.setText("Failed to load blitz ratings");
                currentRatingTextView.setText("Failed to load blitz ratings");
            }
        });
    }

    private void displayBlitzRatings(BlitzStats blitzStats) {
        bestRatingTextView.setText("Best Rating: " + blitzStats.getBestRating());
        currentRatingTextView.setText("Current Rating: " + blitzStats.getCurrentRating());
    }
}
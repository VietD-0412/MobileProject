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

public class BulletRatingFragment extends Fragment {

    private static final String ARG_USERNAME = "username";
    private TextView bestRatingTextView;
    private TextView currentRatingTextView;
    private TextView bestRatingDateTextView;
    private TextView currentRatingDateTextView;

    public static BulletRatingFragment newInstance(String username) {
        BulletRatingFragment fragment = new BulletRatingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bullet_rating, container, false);
        bestRatingTextView = view.findViewById(R.id.bestRatingTextView);
        currentRatingTextView = view.findViewById(R.id.currentRatingTextView);
        bestRatingDateTextView = view.findViewById(R.id.bestRatingDateTextView);
        currentRatingDateTextView = view.findViewById(R.id.currentRatingDateTextView);

        if (getArguments() != null) {
            String username = getArguments().getString(ARG_USERNAME);
            fetchBulletRatings(username);
        }

        return view;
    }

    private void fetchBulletRatings(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chess.com/pub/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChessApiService apiService = retrofit.create(ChessApiService.class);
        apiService.getBulletStats(username).enqueue(new Callback<BulletStats>() {
            @Override
            public void onResponse(Call<BulletStats> call, Response<BulletStats> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displayBulletRatings(response.body());
                }
            }

            @Override
            public void onFailure(Call<BulletStats> call, Throwable t) {
                bestRatingTextView.setText("Failed to load bullet ratings");
                currentRatingTextView.setText("Failed to load bullet ratings");
            }
        });
    }

    private void displayBulletRatings(BulletStats bulletStats) {
        bestRatingTextView.setText("Best Rating: " + bulletStats.getBestRating());
        currentRatingTextView.setText("Current Rating: " + bulletStats.getCurrentRating());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        bestRatingDateTextView.setText("Best Rating Date: " + sdf.format(new Date(bulletStats.getBestRatingDate() * 1000)));
        currentRatingDateTextView.setText("Current Rating Date: " + sdf.format(new Date(bulletStats.getCurrentRatingDate() * 1000)));
    }
}
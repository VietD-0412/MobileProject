package com.example.chessleaderboardandsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private static final String ARG_USERNAME = "username";

    private ImageView avatarImageView;
    private TextView usernameTextView;
    private TextView titleTextView;
    private TextView statusTextView;
    private TextView followersTextView;
    private TextView joinedTextView;
    private TextView lastOnlineTestView;
    private TextView countryTextView;

    public static ProfileFragment newInstance(String username) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        avatarImageView = view.findViewById(R.id.avatarImageView);
        usernameTextView = view.findViewById(R.id.usernameTextView);
        titleTextView = view.findViewById(R.id.titleTextView);
        statusTextView = view.findViewById(R.id.statusTextView);
        followersTextView = view.findViewById(R.id.followersTextView);
        joinedTextView = view.findViewById(R.id.joinedTextView);
        lastOnlineTestView = view.findViewById(R.id.lastOnlineTextView);
        countryTextView = view.findViewById(R.id.countryTextView);

        if (getArguments() != null) {
            String username = getArguments().getString(ARG_USERNAME);
            fetchProfile(username);
        }


        return view;
    }

    private void fetchProfile(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chess.com/pub/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChessApiService apiService = retrofit.create(ChessApiService.class);
        apiService.getPlayerProfile(username).enqueue(new Callback<PlayerProfile>() {
            @Override
            public void onResponse(Call<PlayerProfile> call, Response<PlayerProfile> response) {
                if (response.isSuccessful() && response.body() != null) {
                    displayProfile(response.body());
                }
            }

            @Override
            public void onFailure(Call<PlayerProfile> call, Throwable t) {
            }
        });
    }

    private void displayProfile(PlayerProfile profile) {
        Glide.with(this)
                .load(profile.getAvatar() != null ? profile.getAvatar() : R.drawable.avatar_template)
                .placeholder(R.drawable.avatar_template)
                .into(avatarImageView);

        usernameTextView.setText(profile.getUsername());
        titleTextView.setText((profile.getTitle() != null ? profile.getTitle() : "N/A"));
        statusTextView.setText("Status: " + profile.getStatus());
        followersTextView.setText("Followers: " + profile.getFollowers());
        joinedTextView.setText("Joined: " + formatDate(profile.getJoined()));
        lastOnlineTestView.setText("Last time online: " + formatDate(profile.getLast_online()));


        fetchCountry(profile.getCountry());
    }

    private void fetchCountry(String countryUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chess.com/pub/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChessApiService apiService = retrofit.create(ChessApiService.class);
        apiService.getCountry(countryUrl).enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                if (response.isSuccessful() && response.body() != null) {
                    countryTextView.setText("Country: " + response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
            }
        });
    }

    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        return sdf.format(new Date(timestamp * 1000));
    }
}
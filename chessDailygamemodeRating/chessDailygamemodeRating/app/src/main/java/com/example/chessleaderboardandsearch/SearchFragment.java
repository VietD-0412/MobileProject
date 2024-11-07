package com.example.chessleaderboardandsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    private EditText usernameEditText;
    private Button searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        usernameEditText = view.findViewById(R.id.usernameEditText);
        searchButton = view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(v -> searchUsername(usernameEditText.getText().toString()));

        return view;
    }

    private void searchUsername(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chess.com/pub/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChessApiService apiService = retrofit.create(ChessApiService.class);
        apiService.getPlayerProfile(username).enqueue(new Callback<PlayerProfile>() {
            @Override
            public void onResponse(Call<PlayerProfile> call, Response<PlayerProfile> response) {
                if (response.isSuccessful() && response.body() != null) {
//                    navigateToProfileFragment(username);
                    navigateToDailyRatingFragment(username);
                } else {
                    Toast.makeText(getContext(), "Invalid username", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlayerProfile> call, Throwable t) {
                Toast.makeText(getContext(), "Invalid username", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void navigateToProfileFragment(String username) {
//        ProfileFragment profileFragment = ProfileFragment.newInstance(username);
//        if (getActivity() instanceof MainActivity) {
//            ((MainActivity) getActivity()).replaceFragment(profileFragment);
            private void navigateToDailyRatingFragment(String username) {
                DailyRatingFragment dailyRatingFragment = DailyRatingFragment.newInstance(username);
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).replaceFragment(dailyRatingFragment);
        }
    }
}
package com.example.chessleaderboardandsearch;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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
    private ImageButton searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        usernameEditText = view.findViewById(R.id.usernameEditText);
        searchButton = view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(v -> searchUsername(usernameEditText.getText().toString()));

        usernameEditText.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                searchUsername(usernameEditText.getText().toString());
                return true;
            }
            return false;
        });

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
                    navigateToAllFragment(username);
                } else {
                    Toast.makeText(getContext(), "Player not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlayerProfile> call, Throwable t) {
                Toast.makeText(getContext(), "Player not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToAllFragment(String username) {
        InformationFragment informationFragment = InformationFragment.newInstance(username);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).replaceFragment(informationFragment);
        }
    }
}

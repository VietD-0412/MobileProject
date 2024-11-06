package vn.edu.usth.chess;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load SearchFragment by default
        SearchFragment searchFragment = new SearchFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_search, searchFragment)
                .commit();

        // Load ProfileFragment by default
        //ProfileFragment profileFragment = new ProfileFragment();
        //getSupportFragmentManager().beginTransaction()
        //        .replace(R.id.fragment_profile, profileFragment)
        //        .commit();

        // Load RankingFragment by default
        //RankingFragment rankingFragment = new RankingFragment();
        //getSupportFragmentManager().beginTransaction()
        //        .replace(R.id.fragment_ranking, rankingFragment)
        //        .commit();

        Log.i("create", "onCreate called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("start", "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("resume", "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("pause", "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("stop", "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("destroy", "onDestroy called");
    }
}
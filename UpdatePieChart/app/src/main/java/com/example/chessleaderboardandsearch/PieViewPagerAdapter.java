package com.example.chessleaderboardandsearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PieViewPagerAdapter extends FragmentPagerAdapter {

    private final String username;

    public PieViewPagerAdapter(@NonNull FragmentManager fm, String username) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.username = username;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PieDaily.newInstance(username);
            case 1:
                return PieRapid.newInstance(username);
            case 2:
                return PieBlitz.newInstance(username);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Daily";
            case 1:
                return "Rapid";
            case 2:
                return "Blitz";
            default:
                return null;
        }
    }
}
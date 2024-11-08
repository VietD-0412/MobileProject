// LeaderboardViewPagerAdapter.java
package com.example.chessleaderboardandsearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LeaderboardViewPagerAdapter extends FragmentPagerAdapter {

    public LeaderboardViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RapidLeaderboardFragment();
            case 1:
                return new BlitzLeaderboardFragment();
            case 2:
                return new BulletLeaderboardFragment();
            case 3:
                return new DailyLeaderboardFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return null;
            case 1:
                return null;
            case 2:
                return null;
            case 3:
                return null;
            default:
                return null;
        }
    }
}
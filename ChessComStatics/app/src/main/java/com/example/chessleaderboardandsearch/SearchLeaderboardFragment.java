// SearchLeaderboardFragment.java
package com.example.chessleaderboardandsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class SearchLeaderboardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_leaderboard, container, false);

        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SearchFragment())
                    .commit();
        }

        ViewPager viewPager = view.findViewById(R.id.viewPager);
        LeaderboardViewPagerAdapter adapter = new LeaderboardViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        int[] tabIcons = {R.drawable.rapid, R.drawable.blitz,R.drawable.bullet, R.drawable.daily};

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            if (tabLayout.getTabAt(i) != null) {
                tabLayout.getTabAt(i).setIcon(tabIcons[i]);
            }
        }
        return view;
    }
}
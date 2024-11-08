package com.example.chessleaderboardandsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class PieFragment extends Fragment {

    private static final String ARG_USERNAME = "username";

    public static PieFragment newInstance(String username) {
        PieFragment fragment = new PieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie, container, false);

        String username = getArguments() != null ? getArguments().getString(ARG_USERNAME) : "";

        ViewPager viewPager = view.findViewById(R.id.viewPager);
        PieViewPagerAdapter adapter = new PieViewPagerAdapter(getChildFragmentManager(), username);
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
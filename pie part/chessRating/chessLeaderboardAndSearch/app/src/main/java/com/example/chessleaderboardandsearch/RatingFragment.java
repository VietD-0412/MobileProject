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

public class RatingFragment extends Fragment {

    private static final String ARG_USERNAME = "username";

    public static RatingFragment newInstance(String username) {
        RatingFragment fragment = new RatingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);

        String username = getArguments() != null ? getArguments().getString(ARG_USERNAME) : "";

        ViewPager viewPager = view.findViewById(R.id.viewPager);
        RatingViewPagerAdapter adapter = new RatingViewPagerAdapter(getChildFragmentManager(), username);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        ImageButton goBackButton = view.findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(v -> getParentFragmentManager().popBackStack());


        return view;
    }
}
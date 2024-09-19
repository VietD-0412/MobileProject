package vn.edu.usth.chesscomstatics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentResultAll extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentResultAll() {
        // Required empty public constructor
    }

    public static FragmentResultAll newInstance(String param1, String param2) {
        FragmentResultAll fragment = new FragmentResultAll();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_all, container, false);

        Button buttonToFragmentRanking = view.findViewById(R.id.ranking);
        Button buttonToFragmentRating = view.findViewById(R.id.rating);
        Button buttonToFragmentResultStandard = view.findViewById(R.id.standard);
        Button buttonToFragmentResultRapid = view.findViewById(R.id.rapid);
        Button buttonToFragmentResultBlitz = view.findViewById(R.id.blitz);

        buttonToFragmentRanking.setOnClickListener(v -> navigateToFragment(new FragmentRanking()));
        buttonToFragmentRating.setOnClickListener(v -> navigateToFragment(new FragmentRatingStandard()));
        buttonToFragmentResultStandard.setOnClickListener(v -> navigateToFragment(new FragmentResultStandard()));
        buttonToFragmentResultRapid.setOnClickListener(v -> navigateToFragment(new FragmentResultRapid()));
        buttonToFragmentResultBlitz.setOnClickListener(v -> navigateToFragment(new FragmentResultBlitz()));
        hideLineChart();
        displayPieChart();
        return view;
    }
    private void displayPieChart(){
        ChessActivity activity = (ChessActivity) getActivity();
        if (activity != null) {
            activity.updatePieChart(852,462,664);
        }
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.current_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void hideLineChart() {
        ChessActivity activity = (ChessActivity) getActivity();
        if (activity != null) {
            activity.hideLineChart();
        }
    }
}

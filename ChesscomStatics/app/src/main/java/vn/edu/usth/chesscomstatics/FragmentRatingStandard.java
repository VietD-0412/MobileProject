package vn.edu.usth.chesscomstatics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;

public class FragmentRatingStandard extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentRatingStandard() {
        // Required empty public constructor
    }

    public static FragmentRatingStandard newInstance(String param1, String param2) {
        FragmentRatingStandard fragment = new FragmentRatingStandard();
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
        View view = inflater.inflate(R.layout.fragment_rating_standard, container, false);

        Button buttonToFragmentRanking = view.findViewById(R.id.ranking);
        Button buttonToFragmentResult = view.findViewById(R.id.result);
        Button buttonToFragmentRatingRapid = view.findViewById(R.id.rapid);
        Button buttonToFragmentRatingBlitz = view.findViewById(R.id.blitz);
        Button buttonToFragmentRatingPuzzle = view.findViewById(R.id.puzzle);



        buttonToFragmentRanking.setOnClickListener(v -> navigateToFragment(new FragmentRanking()));
        buttonToFragmentResult.setOnClickListener(v -> navigateToFragment(new FragmentResultAll()));
        buttonToFragmentRatingRapid.setOnClickListener(v -> navigateToFragment(new FragmentRatingRapid()));
        buttonToFragmentRatingBlitz.setOnClickListener(v -> navigateToFragment(new FragmentRatingBlitz()));
        buttonToFragmentRatingPuzzle.setOnClickListener(v -> navigateToFragment(new FragmentRatingPuzzle()));

        displayLineChart();
        hidePieChart();
        return view;
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.current_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void displayLineChart() {
        ChessActivity activity = (ChessActivity) getActivity();
        if (activity != null) {
            ArrayList<Entry> entries = new ArrayList<>();
            entries.add(new Entry(0, 2297));
            entries.add(new Entry(1, 2422));
            entries.add(new Entry(2, 2540));
            entries.add(new Entry(3, 2647));
            entries.add(new Entry(4, 2714));
            entries.add(new Entry(5, 2703));
            entries.add(new Entry(6, 2718));
            entries.add(new Entry(7, 2737));
            entries.add(new Entry(8, 2713));
            entries.add(new Entry(9, 2709));
            entries.add(new Entry(10, 2731));

            String[] years = {"2004", "2006", "2008", "2010", "2012", "2014", "2016", "2018", "2020", "2022", "2024"};
            activity.updateLineChart(entries, years);
        }
    }
    private void hidePieChart() {
        ChessActivity activity = (ChessActivity) getActivity();
        if (activity != null) {
            activity.hidePieChart();
        }
    }
}

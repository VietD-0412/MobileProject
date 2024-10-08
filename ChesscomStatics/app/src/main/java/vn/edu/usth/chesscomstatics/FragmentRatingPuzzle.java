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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRatingPuzzle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRatingPuzzle extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentRatingPuzzle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRatingPuzzle.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRatingPuzzle newInstance(String param1, String param2) {
        FragmentRatingPuzzle fragment = new FragmentRatingPuzzle();
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
        View view = inflater.inflate(R.layout.fragment_rating_puzzle, container, false);

        Button buttonToFragmentRanking = view.findViewById(R.id.ranking);
        Button buttonToFragmentResult = view.findViewById(R.id.result);
        Button buttonToFragmentRatingStandard = view.findViewById(R.id.standard);
        Button buttonToFragmentRatingRapid = view.findViewById(R.id.rapid);
        Button buttonToFragmentRatingBlitz = view.findViewById(R.id.blitz);

        buttonToFragmentRanking.setOnClickListener(v -> navigateToFragment(new FragmentRanking()));
        buttonToFragmentResult.setOnClickListener(v -> navigateToFragment(new FragmentResultAll()));
        buttonToFragmentRatingStandard.setOnClickListener(v -> navigateToFragment(new FragmentRatingStandard()));
        buttonToFragmentRatingRapid.setOnClickListener(v -> navigateToFragment(new FragmentRatingRapid()));
        buttonToFragmentRatingBlitz.setOnClickListener(v -> navigateToFragment(new FragmentRatingBlitz()));

        displayLineChart();

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
            entries.add(new Entry(0, 2202));
            entries.add(new Entry(1, 2419));
            entries.add(new Entry(2, 2589));
            entries.add(new Entry(3, 2689));
            entries.add(new Entry(4, 2705));
            entries.add(new Entry(5, 2709));
            entries.add(new Entry(6, 2701));
            entries.add(new Entry(7, 2695));
            entries.add(new Entry(8, 2695));
            entries.add(new Entry(9, 2683));
            entries.add(new Entry(10, 2690));
            String[] years = {"2004", "2006", "2008", "2010", "2012", "2014", "2016", "2018", "2020", "2022", "2024"};
            activity.updateLineChart(entries, years);
        }
    }
}
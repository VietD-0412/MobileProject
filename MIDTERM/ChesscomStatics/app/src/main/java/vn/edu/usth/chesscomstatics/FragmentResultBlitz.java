package vn.edu.usth.chesscomstatics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentResultBlitz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentResultBlitz extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentResultBlitz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentResultBlitz.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentResultBlitz newInstance(String param1, String param2) {
        FragmentResultBlitz fragment = new FragmentResultBlitz();
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
        View view = inflater.inflate(R.layout.fragment_result_blitz, container, false);

        Button buttonToFragmentRanking = view.findViewById(R.id.ranking);
        Button buttonToFragmentRating = view.findViewById(R.id.rating);
        Button buttonToFragmentResultAll = view.findViewById(R.id.all);
        Button buttonToFragmentResultStandard = view.findViewById(R.id.standard);
        Button buttonToFragmentResultRapid = view.findViewById(R.id.rapid);

        buttonToFragmentRanking.setOnClickListener(v -> navigateToFragment(new FragmentRanking()));
        buttonToFragmentRating.setOnClickListener(v -> navigateToFragment(new FragmentRatingStandard()));
        buttonToFragmentResultAll.setOnClickListener(v -> navigateToFragment(new FragmentResultAll()));
        buttonToFragmentResultStandard.setOnClickListener(v -> navigateToFragment(new FragmentResultStandard()));
        buttonToFragmentResultRapid.setOnClickListener(v -> navigateToFragment(new FragmentResultRapid()));
        displayPieChart();
        return view;
    }
    private void displayPieChart(){
        ChessActivity activity = (ChessActivity) getActivity();
        if (activity != null) {
            activity.updatePieChart(278,136,149);
        }
    }
    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.current_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
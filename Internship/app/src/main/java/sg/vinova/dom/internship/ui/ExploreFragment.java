package sg.vinova.dom.internship.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

import sg.vinova.dom.internship.adapter.ExploreAdapter;
import sg.vinova.dom.internship.Model.Food;
import sg.vinova.dom.internship.R;

public class ExploreFragment extends Fragment {

    private View rootView;
    private static ArrayList<ArrayList<Food>> listData;

//    private OnFragmentInteractionListener mListener;

    public static ExploreFragment newInstance(ArrayList<ArrayList<Food>> listFood) {
        ExploreFragment fragment = new ExploreFragment();
        listData = listFood;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_explore, container, false);;
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvExplore = (RecyclerView) rootView.findViewById(R.id.rvExplore);
        Animation translate_left = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.translate_left);
        rvExplore.startAnimation(translate_left);

        ExploreAdapter exploreAdapter = new ExploreAdapter(rootView.getContext(), listData);
        rvExplore.setAdapter(exploreAdapter);
        rvExplore.setLayoutManager(new LinearLayoutManager(view.getContext()));

        exploreAdapter.setOnClickMoreButton(new ExploreAdapter.OnClickMoreButton() {
            @Override
            public void onClickMore(int num) {
                NavigationView nav_view = (NavigationView) getActivity().findViewById(R.id.nav_view);
                nav_view.setCheckedItem(R.id.nav_deliveries);
                MainActivity main = (MainActivity) getActivity();
                main.setPosition(num);
                nav_view.getMenu().performIdentifierAction(R.id.nav_deliveries, 0);
            }
        });
    }

//        @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}

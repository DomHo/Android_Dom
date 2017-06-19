package sg.vinova.dom.internship3_foodi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sg.vinova.dom.internship3_foodi.R;
import sg.vinova.dom.internship3_foodi.SimpleDividerItemDecoration;
import sg.vinova.dom.internship3_foodi.adapter.StoreAdapter;
import sg.vinova.dom.internship3_foodi.model.Food;

public class StoreFragment extends Fragment {

    private static ArrayList<Food> listData;
    private static StoreFragment fragment = null;
    //    private OnFragmentInteractionListener mListener;

    public static StoreFragment newInstance(ArrayList<Food> listFood) {
        if (fragment == null)
            fragment = new StoreFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        AppBarLayout appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.ablStore);
//        appBarLayout.setPadding(0, statusBarHeight(getResources()), 0, 0);

        RecyclerView rvStore = (RecyclerView) getActivity().findViewById(R.id.rvStore);
        StoreAdapter storeAdapter = new StoreAdapter(listData, getContext());
        rvStore.setAdapter(storeAdapter);
        rvStore.setLayoutManager(new LinearLayoutManager(getContext()));

        rvStore.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

    }

//    private static int statusBarHeight(android.content.res.Resources res) {
//        return (int) (24 * res.getDisplayMetrics().density);
//    }

    //    @Override
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

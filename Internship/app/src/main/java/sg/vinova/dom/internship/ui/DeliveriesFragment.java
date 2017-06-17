package sg.vinova.dom.internship.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

import sg.vinova.dom.internship.adapter.DeliveriesAdapter;
import sg.vinova.dom.internship.Model.Food;
import sg.vinova.dom.internship.R;

public class DeliveriesFragment extends Fragment {

    private View rootView;
    private static ArrayList<Food> listData;

//    private OnFragmentInteractionListener mListener;

    public static DeliveriesFragment newInstance(ArrayList<Food> listFood) {
        DeliveriesFragment fragment = new DeliveriesFragment();
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
        rootView = inflater.inflate(R.layout.fragment_deliveries, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvDeliveries = (RecyclerView) rootView.findViewById(R.id.rvDeliveries);
        Animation translate_left = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.translate_left);
        rvDeliveries.startAnimation(translate_left);

        DeliveriesAdapter deliveriesAdapter = new DeliveriesAdapter(rootView.getContext(), listData);
        rvDeliveries.setAdapter(deliveriesAdapter);
        rvDeliveries.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

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

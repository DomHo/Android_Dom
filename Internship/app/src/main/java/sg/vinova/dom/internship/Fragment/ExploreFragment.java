package sg.vinova.dom.internship.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sg.vinova.dom.internship.Adapter.ExploreAdapter;
import sg.vinova.dom.internship.Model.Explore;
import sg.vinova.dom.internship.R;

public class ExploreFragment extends Fragment {

    private View rootView;
    private static ExploreFragment fragment = null;

//    private OnFragmentInteractionListener mListener;

    public static ExploreFragment getInstance() {
        if (fragment == null)
            fragment = new ExploreFragment();
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
        List<List<Explore>> listData = new ArrayList<>();

        // Data demo
        for (int i = 1; i <= 10; i++) {
            List<Explore> temp = new ArrayList<>();
            for (int k = 1; k <= 10; k++) {
                if (k%2 == 1)
                    temp.add(new Explore(i, "https://www.gimmesomeoven.com/wp-content/uploads/2009/10/sesame-noodles.jpg", "Food " + i + k, "Rate " + i + k ,"Nation " + i + k));
                else
                    temp.add(new Explore(i, "https://www.toppers.com/Portals/0/house-pizza-bacon-cheeseburger.jpg", "Food " + i + k, "Rate " + i + k ,"Nation " + i + k));
            }
            listData.add(temp);
        }

        ExploreAdapter exploreAdapter = new ExploreAdapter(rootView.getContext(), listData);
        rvExplore.setAdapter(exploreAdapter);
        rvExplore.setLayoutManager(new LinearLayoutManager(view.getContext()));
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}

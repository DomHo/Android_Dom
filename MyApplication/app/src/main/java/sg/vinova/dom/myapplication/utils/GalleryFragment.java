package sg.vinova.dom.myapplication.utils;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.loadImageFeature.LoadImage;
import sg.vinova.dom.myapplication.loadImageFeature.LoadImageAdapter;
import sg.vinova.dom.myapplication.loadImageFeature.LoadImagePresenterImpl;

public class GalleryFragment extends Fragment implements LoadImage.View {

    private LoadImagePresenterImpl loadImagePresenter;

    View rootView;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
//        rootView.startAnimation(AnimationUtils.loadAnimation(rootView.getContext(), R.anim.translate_up));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadImagePresenter = new LoadImagePresenterImpl(this);

        final LoadImageAdapter loadImageAdapter = new LoadImageAdapter(getContext(), loadImagePresenter.getListImage(), this);
        RecyclerView rvGallery = (RecyclerView) getActivity().findViewById(R.id.rvGallery);
        rvGallery.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        rvGallery.setAdapter(loadImageAdapter);
    }
}
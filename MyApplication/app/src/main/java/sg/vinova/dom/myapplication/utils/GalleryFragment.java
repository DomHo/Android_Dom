package sg.vinova.dom.myapplication.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.loadImageFeature.LoadPhoto;
import sg.vinova.dom.myapplication.adapter.LoadPhotoAdapter;
import sg.vinova.dom.myapplication.loadImageFeature.LoadPhotoPresenterImpl;
import sg.vinova.dom.myapplication.model.Photo;

public class GalleryFragment extends Fragment implements LoadPhoto.View {

    private LoadPhotoPresenterImpl loadPhotoPresenter;

    View rootView;
    RecyclerView rvGallery;
    LoadPhotoAdapter loadPhotoAdapter = null;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPhotoPresenter = new LoadPhotoPresenterImpl(this);
        loadPhotoPresenter.getNewData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvGallery = (RecyclerView) getActivity().findViewById(R.id.rvGallery);
        rvGallery.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));

        if (loadPhotoAdapter != null) {
            rvGallery.setAdapter(loadPhotoAdapter);
        }
    }

    @Override
    public void loadNewData(List<Photo> photoList) {
        loadPhotoAdapter = new LoadPhotoAdapter(getContext(), photoList, GalleryFragment.this);
        rvGallery.setAdapter(loadPhotoAdapter);
    }

    @Override
    public void shareElement(ImageView imageView, TextView textView, Photo photo) {
        setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.transition_share_element));
//        setExitTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.fade));

        DetailFragment detailFragment = DetailFragment.newInstance();
        detailFragment.setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.transition_share_element));
//        detailFragment.setEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.fade));

        Bundle bundle = new Bundle();
        bundle.putString(DetailFragment.TRANSITION_PHOTO, imageView.getTransitionName());
        bundle.putString(DetailFragment.TRANSITION_TITLE, textView.getTransitionName());

        bundle.putString(DetailFragment.URL_KEY, photo.getUrl());
        bundle.putString(DetailFragment.TITLE_KEY, photo.getTitle());

        detailFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.flMain, detailFragment)
                .addSharedElement(imageView, imageView.getTransitionName())
                .addSharedElement(textView, textView.getTransitionName())
                .addToBackStack("Gallery - Share Element").commit();
    }
}
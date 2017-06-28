package sg.vinova.dom.myapplication.utils;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
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

import com.bumptech.glide.Glide;

import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.loadImageFeature.LoadImage;
import sg.vinova.dom.myapplication.loadImageFeature.LoadImageAdapter;
import sg.vinova.dom.myapplication.loadImageFeature.LoadImagePresenterImpl;
import sg.vinova.dom.myapplication.loginFeature.Login;

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
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadImagePresenter = new LoadImagePresenterImpl(this);

        LoadImageAdapter loadImageAdapter = new LoadImageAdapter(getContext(), loadImagePresenter.getListImage(), this);
        RecyclerView rvGallery = (RecyclerView) getActivity().findViewById(R.id.rvGallery);
        rvGallery.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        rvGallery.setAdapter(loadImageAdapter);
    }

    @Override
    public void shareElement(ImageView imageView, TextView textView) {
        DetailFragment detailFragment = DetailFragment.newInstance();
        detailFragment.setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.transition_share_element));
//        detailFragment.setEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.fade));

        setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.transition_share_element));
//        setExitTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.fade));

        Bundle bundle = new Bundle();
        bundle.putString(DetailFragment.TRANSITION_IMAGE, imageView.getTransitionName());
        bundle.putParcelable(DetailFragment.BITMAP_KEY, ((BitmapDrawable) (imageView.getDrawable())).getBitmap());
        bundle.putString(DetailFragment.TRANSITION_CONTENT, textView.getTransitionName());
        bundle.putString(DetailFragment.CONTENT_KEY, textView.getText().toString());
        detailFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.flMain, detailFragment)
                .addSharedElement(imageView, imageView.getTransitionName())
                .addSharedElement(textView, textView.getTransitionName())
                .addToBackStack("Gallery - Share Element").commit();
    }
}
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
import sg.vinova.dom.myapplication.loadImageFeature.LoadImage;
import sg.vinova.dom.myapplication.adapter.LoadImageAdapter;
import sg.vinova.dom.myapplication.loadImageFeature.LoadImagePresenterImpl;
import sg.vinova.dom.myapplication.model.Image;

public class GalleryFragment extends Fragment implements LoadImage.View {

    private LoadImagePresenterImpl loadImagePresenter;

    View rootView;
    RecyclerView rvGallery;
    LoadImageAdapter loadImageAdapter = null;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadImagePresenter = new LoadImagePresenterImpl(this);
        loadImagePresenter.getNewData();
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

        if (loadImageAdapter != null) {
            rvGallery.setAdapter(loadImageAdapter);
        }
    }

    @Override
    public void loadNewData(List<Image> imageList) {
        loadImageAdapter = new LoadImageAdapter(getContext(), imageList, GalleryFragment.this);
        rvGallery.setAdapter(loadImageAdapter);
    }

    @Override
    public void shareElement(ImageView imageView, TextView textView, Image image) {
        DetailFragment detailFragment = DetailFragment.newInstance();
        detailFragment.setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.transition_share_element));
//        detailFragment.setEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.fade));

        setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.transition_share_element));
//        setExitTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.fade));

        Bundle bundle = new Bundle();
        bundle.putString(DetailFragment.TRANSITION_IMAGE, imageView.getTransitionName());
        bundle.putString(DetailFragment.TRANSITION_CONTENT, textView.getTransitionName());

//        bundle.putParcelable(DetailFragment.BITMAP_KEY, ((BitmapDrawable) (imageView.getDrawable())).getBitmap());
        bundle.putString(DetailFragment.CONTENT_KEY, image.getContent());
        bundle.putString("url", image.getLink());

        detailFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.flMain, detailFragment)
                .addSharedElement(imageView, imageView.getTransitionName())
                .addSharedElement(textView, textView.getTransitionName())
                .addToBackStack("Gallery - Share Element").commit();
    }
}
package sg.vinova.dom.myapplication.utils;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import sg.vinova.dom.myapplication.R;

public class DetailFragment extends Fragment {

    public static final String TRANSITION_PHOTO = "transition_photo";
    public static final String TRANSITION_TITLE = "transition_title";
    public static final String URL_KEY = "url_key";
    public static final String TITLE_KEY = "title_key";
    private View rootView;

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ImageView ivPhoto = (ImageView) rootView.findViewById(R.id.ivPhoto);
        TextView tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
        if (getArguments() != null) {
            ivPhoto.setTransitionName(getArguments().getString(TRANSITION_PHOTO, ""));
            tvTitle.setTransitionName(getArguments().getString(TRANSITION_TITLE, ""));
            Glide.with(getContext()).load(getArguments().getString(URL_KEY, "")).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    ivPhoto.setImageDrawable(resource);
                    return false;
                }
            }).submit();
            tvTitle.setText(getArguments().getString(TITLE_KEY, ""));
        }
    }
}
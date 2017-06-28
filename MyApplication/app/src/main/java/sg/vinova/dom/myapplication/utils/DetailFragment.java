package sg.vinova.dom.myapplication.utils;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import sg.vinova.dom.myapplication.R;

public class DetailFragment extends Fragment {

    public static final String TRANSITION_IMAGE = "transition_image";
    public static final String TRANSITION_CONTENT = "transition_content";
    public static final String CONTENT_KEY = "content_key";
    public static final String BITMAP_KEY = "bitmap_key";
    private View rootView;
    private ImageView ivImage;
    private TextView tvContent;

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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivImage = (ImageView) rootView.findViewById(R.id.ivImage);
        tvContent = (TextView) rootView.findViewById(R.id.tvContent);
        if (getArguments() != null) {
            ivImage.setTransitionName(getArguments().getString(TRANSITION_IMAGE, ""));
            ivImage.setImageBitmap((Bitmap) getArguments().getParcelable(BITMAP_KEY));
            tvContent.setTransitionName(getArguments().getString(TRANSITION_CONTENT, ""));
            tvContent.setText(getArguments().getString(CONTENT_KEY, ""));
        }
    }
}
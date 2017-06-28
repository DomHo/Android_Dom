package sg.vinova.dom.myapplication.loadImageFeature;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sg.vinova.dom.myapplication.model.Image;

/**
 * Created by HNS on 26/06/2017.
 */

public interface LoadImage {
    interface View {
        void shareElement(ImageView imageView, TextView textView);
    }

    interface Presenter {
        List<Image> getListImage();
    }
}
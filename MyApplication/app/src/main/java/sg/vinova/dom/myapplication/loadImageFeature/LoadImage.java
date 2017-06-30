package sg.vinova.dom.myapplication.loadImageFeature;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sg.vinova.dom.myapplication.model.Image;

public interface LoadImage {
    interface View {
        void loadNewData(List<Image> imageList);

        void shareElement(ImageView imageView, TextView textView, Image image);
    }

    interface Presenter {
        void getNewData();
    }
}
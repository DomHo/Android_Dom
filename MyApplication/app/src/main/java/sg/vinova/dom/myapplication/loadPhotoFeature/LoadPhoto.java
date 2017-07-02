package sg.vinova.dom.myapplication.loadPhotoFeature;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sg.vinova.dom.myapplication.model.Photo;

public interface LoadPhoto {
    interface View {
        void loadNewData(List<Photo> photoList);

        void shareElement(ImageView imageView, TextView textView, Photo photo);

        void error(String message);
    }

    interface Presenter {
        void getNewData();
    }
}
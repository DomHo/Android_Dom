package sg.vinova.dom.myapplication.loadImageFeature;

import java.util.List;

import sg.vinova.dom.myapplication.model.Image;

/**
 * Created by HNS on 26/06/2017.
 */

public interface LoadImage {
    interface View {

    }

    interface Presenter {
        List<Image> getListImage();
    }
}
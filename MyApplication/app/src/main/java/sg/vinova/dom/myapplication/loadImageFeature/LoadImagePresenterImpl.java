package sg.vinova.dom.myapplication.loadImageFeature;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;
import sg.vinova.dom.myapplication.model.Image;

/**
 * Created by HNS on 26/06/2017.
 */

public class LoadImagePresenterImpl implements LoadImage.Presenter {
    private LoadImage.View loadimageView;

    public LoadImagePresenterImpl(LoadImage.View loadimageView) {
        this.loadimageView = loadimageView;
    }

    @Override
    public List<Image> getListImage() {
        Realm realm = Realm.getDefaultInstance();
        List<Image> imageList = realm.where(Image.class).findAllSorted("id", Sort.ASCENDING);
        realm.close();
        return imageList;
    }
}

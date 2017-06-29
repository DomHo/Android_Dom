package sg.vinova.dom.myapplication.retrofit;

import java.util.List;

import sg.vinova.dom.myapplication.model.Photo;

/**
 * Created by HNS on 29/06/2017.
 */

public class PlaceholderRepo {

    private List<Photo> photoList;

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }
}
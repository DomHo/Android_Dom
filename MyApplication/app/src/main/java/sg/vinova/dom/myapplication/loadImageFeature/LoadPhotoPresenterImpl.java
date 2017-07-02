package sg.vinova.dom.myapplication.loadImageFeature;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.vinova.dom.myapplication.photoAPI.PlaceholderServiceImpl;
import sg.vinova.dom.myapplication.model.Photo;

public class LoadPhotoPresenterImpl implements LoadPhoto.Presenter {

    private LoadPhoto.View loadPhotoView;
    private List<Photo> photoList;

    public LoadPhotoPresenterImpl(LoadPhoto.View loadPhotoView) {
        this.loadPhotoView = loadPhotoView;
    }

    @Override
    public void getNewData() {
        new PlaceholderServiceImpl().getPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                photoList = response.body();
                loadPhotoView.loadNewData(photoList);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e("photoAPI", t.toString());
            }
        });
    }
}
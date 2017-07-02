package sg.vinova.dom.myapplication.loadPhotoFeature;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.photoAPI.PlaceholderServiceImpl;
import sg.vinova.dom.myapplication.model.Photo;

public class LoadPhotoPresenterImpl implements LoadPhoto.Presenter {

    private Context context;
    private LoadPhoto.View loadPhotoView;
    private List<Photo> photoList;

    public LoadPhotoPresenterImpl(Context context, LoadPhoto.View loadPhotoView) {
        this.context = context;
        this.loadPhotoView = loadPhotoView;
    }

    @Override
    public void getNewData() {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo == null) {
            loadPhotoView.error("Network unavailable");
            return;
        }
        if (!netInfo.isAvailable()) {
            loadPhotoView.error("Network error");
            return;
        }
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
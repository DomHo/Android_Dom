package sg.vinova.dom.myapplication.loadImageFeature;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.vinova.dom.myapplication.ExampleAPI.PlaceholderServiceImpl;
import sg.vinova.dom.myapplication.model.Image;

public class LoadImagePresenterImpl implements LoadImage.Presenter {

    private LoadImage.View loadimageView;
    private PlaceholderServiceImpl placeholderService = null;
    private List<Image> imageList;

    public LoadImagePresenterImpl(LoadImage.View loadimageView) {
        this.loadimageView = loadimageView;
    }

    @Override
    public void getNewData() {
        new PlaceholderServiceImpl().getImages().enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                imageList = response.body();
                loadimageView.loadNewData(imageList);
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                Log.e("ExampleAPI", t.toString());
            }
        });
    }
}
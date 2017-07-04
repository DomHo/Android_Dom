package sg.vinova.dom.myapplication.API;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sg.vinova.dom.myapplication.model.Photo;

public class PhotoServiceImpl implements PhotoService {
    @Override
    public Observable<List<Photo>> getPhotos() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        PhotoService service = retrofit.create(PhotoService.class);
        return service.getPhotos();
    }
}
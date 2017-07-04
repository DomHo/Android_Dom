package sg.vinova.dom.myapplication.API;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import sg.vinova.dom.myapplication.model.Photo;

public interface PhotoService {

    String baseURL = "https://jsonplaceholder.typicode.com/";

    @GET("photos")
    Observable<List<Photo>> getPhotos();
}
package sg.vinova.dom.myapplication.photoAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sg.vinova.dom.myapplication.model.Photo;

public interface PlaceholderService {

    @GET("photos")
    Call<List<Photo>> getPhotos();
}
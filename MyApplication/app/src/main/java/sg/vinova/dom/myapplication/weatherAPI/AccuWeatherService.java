package sg.vinova.dom.myapplication.weatherAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sg.vinova.dom.myapplication.model.Photo;

/**
 * Created by HNS on 02/07/2017.
 */

public interface AccuWeatherService {
    @GET("photos")
    Call<List<Photo>> getPhotos();
}

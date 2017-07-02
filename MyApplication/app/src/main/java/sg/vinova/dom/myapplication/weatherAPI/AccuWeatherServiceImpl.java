package sg.vinova.dom.myapplication.weatherAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sg.vinova.dom.myapplication.model.Photo;
import sg.vinova.dom.myapplication.photoAPI.PlaceholderService;

/**
 * Created by HNS on 02/07/2017.
 */

public class AccuWeatherServiceImpl implements AccuWeatherService {
    @Override
    public Call<List<Photo>> getPhotos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceholderService service = retrofit.create(PlaceholderService.class);
        return service.getPhotos();
    }
}

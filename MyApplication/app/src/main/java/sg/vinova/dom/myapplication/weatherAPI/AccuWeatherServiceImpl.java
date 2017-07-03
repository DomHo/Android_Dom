package sg.vinova.dom.myapplication.weatherAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sg.vinova.dom.myapplication.model.Photo;
import sg.vinova.dom.myapplication.model.Weather.Weather;
import sg.vinova.dom.myapplication.photoAPI.PlaceholderService;

/**
 * Created by HNS on 02/07/2017.
 */

public class AccuWeatherServiceImpl implements AccuWeatherService {
    @Override
    public Call<Weather> getWeather(@Path("locationId") String locationId, @Query("apikey") String apikey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AccuWeatherService service = retrofit.create(AccuWeatherService.class);
        return service.getWeather(locationId, apikey);
    }
}

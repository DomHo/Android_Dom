package sg.vinova.dom.myapplication.weatherAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sg.vinova.dom.myapplication.model.Photo;
import sg.vinova.dom.myapplication.model.Weather.Weather;
import sg.vinova.dom.myapplication.photoAPI.PlaceholderService;

/**
 * Created by HNS on 02/07/2017.
 */

public class AccuWeatherServiceImpl implements AccuWeatherService {
    @Override
    public Call<Weather> getWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/forecasts/v1/daily/5day/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AccuWeatherService service = retrofit.create(AccuWeatherService.class);
        return service.getWeather();
    }
}

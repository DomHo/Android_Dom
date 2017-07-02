package sg.vinova.dom.myapplication.weatherAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import sg.vinova.dom.myapplication.model.Weather.Weather;

/**
 * Created by HNS on 02/07/2017.
 */

public interface AccuWeatherService {

    @GET("353981?apikey=7PlMr174CF5AQPZtbCmvAxfZUhlYk1y5&language=vi&details=trua&metric=true")
    Call<Weather> getWeather();
}

package sg.vinova.dom.myapplication.API;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sg.vinova.dom.myapplication.model.Weather.Weather;

/**
 * Created by HNS on 02/07/2017.
 */

public interface WeatherService {

    String baseURL = "http://dataservice.accuweather.com/";

    @GET("forecasts/v1/daily/5day/{locationId}?language=vi&details=true&metric=true")
    Observable<Weather> getWeather(@Path("locationId") String locationId, @Query("apikey") String apikey);
}

package sg.vinova.dom.myapplication.API;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sg.vinova.dom.myapplication.model.Weather.Weather;

/**
 * Created by HNS on 02/07/2017.
 */

public class WeatherServiceImpl implements WeatherService {
    @Override
    public Observable<Weather> getWeather(@Path("locationId") String locationId, @Query("apikey") String apikey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        return service.getWeather(locationId, apikey);
    }
}

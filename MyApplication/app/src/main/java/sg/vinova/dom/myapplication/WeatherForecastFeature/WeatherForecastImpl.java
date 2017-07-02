package sg.vinova.dom.myapplication.WeatherForecastFeature;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.model.Weather.Weather;
import sg.vinova.dom.myapplication.weatherAPI.AccuWeatherServiceImpl;

/**
 * Created by HNS on 02/07/2017.
 */

public class WeatherForecastImpl implements WeatherForecast.Presenter {

    Context context;
    private WeatherForecast.View weatherForecastView;
    Weather weather;

    public WeatherForecastImpl(Context context, WeatherForecast.View weatherForecastView) {
        this.context = context;
        this.weatherForecastView = weatherForecastView;
    }

    @Override
    public void updateWeather() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo == null) {
            weatherForecastView.updateWeatherResult("Network unavailable");
            return;
        }
        if (!netInfo.isAvailable()) {
            weatherForecastView.updateWeatherResult("Network error");
            return;
        }
        new AccuWeatherServiceImpl().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                weatherForecastView.updateWeatherResult(context.getString(R.string.time_now_format, sdf.format(calendar.getTime())));
                weather = response.body();
                weatherForecastView.loadData(weather, calendar);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

    }
}
package sg.vinova.dom.myapplication.WeatherForecastFeature;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.model.Weather.Weather;
import sg.vinova.dom.myapplication.API.WeatherServiceImpl;

/**
 * Created by HNS on 02/07/2017.
 */

public class WeatherForecastImpl implements WeatherForecast.Presenter {

    Context context;
    private WeatherForecast.View weatherForecastView;
    Weather weather;
    private Observer<Weather> myObserver = null;
    private Observable<Weather> myObservable = null;

    public WeatherForecastImpl(Context context, WeatherForecast.View weatherForecastView) {
        this.context = context;
        this.weatherForecastView = weatherForecastView;
        init();
    }

    private void init() {
        if (myObservable == null)
            myObservable = new WeatherServiceImpl().getWeather("353981", "QPhSaIjTzIZkIAMz28qz9ONXSbJgGbXd");

        if (myObserver == null)
            myObserver = new Observer<Weather>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    Log.d("weather", "onSubscribe");
                }

                @Override
                public void onNext(@NonNull Weather weather) {
                    Log.d("weather", "onNext");
                    if (weather == null)
                        weatherForecastView.updateWeatherResult("Turn end");
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    weatherForecastView.updateWeatherResult(context.getString(R.string.time_now_format, sdf.format(calendar.getTime())));
                    WeatherForecastImpl.this.weather = weather;
                    weatherForecastView.loadData(weather, calendar);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d("weather", "onError");
                    weatherForecastView.updateWeatherResult(e.toString());
                }

                @Override
                public void onComplete() {
                    Log.d("weather", "onComplete");
                }
            };
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
        myObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver);

    }
}
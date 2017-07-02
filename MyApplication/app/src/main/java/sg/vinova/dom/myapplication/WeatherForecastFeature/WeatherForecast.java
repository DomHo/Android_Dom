package sg.vinova.dom.myapplication.WeatherForecastFeature;

import java.util.Calendar;

import sg.vinova.dom.myapplication.model.Weather.Weather;

/**
 * Created by HNS on 02/07/2017.
 */

public interface WeatherForecast {
    interface View{
        void updateWeatherResult(String message);

        void loadData(Weather weather, Calendar calendar);
    }

    interface Presenter{
        void updateWeather();
    }
}

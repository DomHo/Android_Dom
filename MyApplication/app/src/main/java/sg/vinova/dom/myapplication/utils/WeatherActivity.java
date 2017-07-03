package sg.vinova.dom.myapplication.utils;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.WeatherForecastFeature.WeatherForecast;
import sg.vinova.dom.myapplication.WeatherForecastFeature.WeatherForecastImpl;
import sg.vinova.dom.myapplication.adapter.WeatherForecastAdapter;
import sg.vinova.dom.myapplication.model.Weather.DailyForecast;
import sg.vinova.dom.myapplication.model.Weather.Weather;

public class WeatherActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    static WeatherForecastImpl weatherForecastPresenter;

    static TextView tvNow;
    Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        tvNow = (TextView) findViewById(R.id.tvNow);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherForecastPresenter.updateWeather();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.switch_main_weather_left_in, R.anim.switch_main_weather_left_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements WeatherForecast.View {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        View rootView;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_weather, container, false);
            return rootView;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            weatherForecastPresenter = new WeatherForecastImpl(getContext(), this);
            weatherForecastPresenter.updateWeather();

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            final int width = displayMetrics.widthPixels;
            final ImageView ivBackgroundToday = (ImageView) rootView.findViewById(R.id.ivBackgroundToday);

            TranslateAnimation animation1 = new TranslateAnimation(0, width / 2, 0, 0);
            animation1.setDuration(5000);
            animation1.setStartOffset(3000);
            final TranslateAnimation animation2 = new TranslateAnimation(width / 2, -width / 2, 0, 0);
            animation2.setDuration(10000);
            animation2.setRepeatMode(Animation.REVERSE);
            animation2.setRepeatCount(Animation.INFINITE);

            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ivBackgroundToday.startAnimation(animation2);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            ivBackgroundToday.startAnimation(animation1);


        }

        @Override
        public void updateWeatherResult(String message) {
            tvNow.setText(message);
        }

        @Override
        public void loadData(Weather weather, Calendar calendar) {
            DailyForecast today = weather.getDailyForecasts().get(0);

            TextView tvLocation = (TextView) rootView.findViewById(R.id.tvLocation);
            TextView tvDate = (TextView) rootView.findViewById(R.id.tvDate);
            ImageView ivToday = (ImageView) rootView.findViewById(R.id.ivToday);
            TextView tvTempMid = (TextView) rootView.findViewById(R.id.tvTempMid);
            TextView tvTempMax = (TextView) rootView.findViewById(R.id.tvTempMax);
            TextView tvTempMin = (TextView) rootView.findViewById(R.id.tvTempMin);
            TextView tvToday = (TextView) rootView.findViewById(R.id.tvToday);

            TextView tvRealFeel1 = (TextView) rootView.findViewById(R.id.tvRealFeel1);
            TextView tvUV1 = (TextView) rootView.findViewById(R.id.tvUV1);
            TextView tvChanceRain = (TextView) rootView.findViewById(R.id.tvChanceRain1);
            TextView tvSunSet = (TextView) rootView.findViewById(R.id.tvSunSet1);

            tvLocation.setText("Thành phố Hồ Chí Minh");
            int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
            int day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1;
            if (day_of_week == Calendar.SUNDAY)
                tvDate.setText("CN, " + day_of_month + " Tháng " + month);
            else
                tvDate.setText("T." + day_of_week + ", " + day_of_month + " Tháng " + month);

            int max = today.getTemperature().getMaximum().getValue().intValue();
            int min = today.getTemperature().getMinimum().getValue().intValue();
            int mid = (max + min) / 2;
            tvTempMax.setText(Integer.toString(max) + (char) 0x00B0 + "");
            tvTempMin.setText(Integer.toString(min) + (char) 0x00B0 + "");
            tvTempMid.setText(Integer.toString(mid) + (char) 0x00B0 + "C");
            if (calendar.get(Calendar.HOUR_OF_DAY) < 12) {
                ivToday.setImageLevel(today.getDay().getIcon());
                tvToday.setText(today.getDay().getIconPhrase());
                tvChanceRain.setText(today.getDay().getRainProbability().toString()+ "%");
            } else {
                ivToday.setImageLevel(today.getNight().getIcon());
                tvToday.setText(today.getNight().getIconPhrase());
                tvChanceRain.setText(today.getNight().getRainProbability().toString() + "%");
            }
            tvRealFeel1.setText(Integer.toString(today.getRealFeelTemperature().getMaximum().getValue().intValue()) + (char) 0x00B0 + "C");
            tvUV1.setText(today.getAirAndPollen().get(0).getCategory());
            tvSunSet.setText(today.getSun().getSet().substring(11, 16));
            List<DailyForecast> dailyForecastList = weather.getDailyForecasts();
            dailyForecastList.remove(0);
            RecyclerView rvWeather = (RecyclerView) rootView.findViewById(R.id.rvWeather);
            rvWeather.setLayoutManager(new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false));
            WeatherForecastAdapter weatherForecastAdapter = new WeatherForecastAdapter(getContext(), dailyForecastList, calendar);
            rvWeather.setAdapter(weatherForecastAdapter);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
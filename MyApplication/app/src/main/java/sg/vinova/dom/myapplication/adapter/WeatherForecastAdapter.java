package sg.vinova.dom.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.model.Weather.DailyForecast;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {

    private Context context;
    private List<DailyForecast> dailyForecastList = null;
    private Calendar calendar;

    public WeatherForecastAdapter(Context context, List<DailyForecast> dailyForecastList, Calendar calendar) {
        this.context = context;
        this.dailyForecastList = dailyForecastList;
        this.calendar = calendar;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DailyForecast dailyForecast = dailyForecastList.get(position);
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        if (day_of_week == Calendar.SUNDAY)
            holder.tvDayItem.setText("CN");
        else
            holder.tvDayItem.setText( "T." + day_of_week);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        holder.tvDateItem.setText(sdf.format(calendar.getTime()));
        holder.ivTodayItem.setImageLevel(dailyForecast.getDay().getIcon());
        String max = Integer.toString(dailyForecast.getTemperature().getMaximum().getValue().intValue()) + (char) 0x00B0;
        String min = Integer.toString(dailyForecast.getTemperature().getMinimum().getValue().intValue()) + (char) 0x00B0;
        holder.tvMaxMin.setText(max + "/" + min);
        if (dailyForecast.getDay().getPrecipitationProbability() != null)
            holder.tvWater.setText(dailyForecast.getDay().getPrecipitationProbability() + "%");
        else
            holder.tvWater.setText("N/A");
    }

    @Override
    public int getItemCount() {
        if (dailyForecastList != null)
            return dailyForecastList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDayItem;
        private TextView tvDateItem;
        private ImageView ivTodayItem;
        private TextView tvMaxMin;
        private TextView tvWater;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDayItem = (TextView) itemView.findViewById(R.id.tvDayItem);
            tvDateItem = (TextView) itemView.findViewById(R.id.tvDateItem);
            ivTodayItem = (ImageView) itemView.findViewById(R.id.ivTodayItem);
            tvMaxMin = (TextView) itemView.findViewById(R.id.tvMaxMin);
            tvWater = (TextView) itemView.findViewById(R.id.tvWater);
        }
    }
}
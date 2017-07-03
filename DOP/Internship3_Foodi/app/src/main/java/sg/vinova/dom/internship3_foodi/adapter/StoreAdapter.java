package sg.vinova.dom.internship3_foodi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sg.vinova.dom.internship3_foodi.R;
import sg.vinova.dom.internship3_foodi.model.Food;

/**
 * Created by HNS on 18/06/2017.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private ArrayList<Food> listData;
    private Context context;

    public StoreAdapter(ArrayList<Food> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = listData.get(position);
        Glide.with(context).load(food.getImage()).into(holder.ivImage);
        holder.tvName.setText(food.getName());
    }

    @Override
    public int getItemCount() {
        if (listData == null)
            return 0;
        else
            return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvName = (TextView) itemView.findViewById(R.id.tvname);
        }
    }
}

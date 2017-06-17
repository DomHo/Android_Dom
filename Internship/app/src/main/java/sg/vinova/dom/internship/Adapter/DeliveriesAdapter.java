package sg.vinova.dom.internship.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sg.vinova.dom.internship.Model.Food;
import sg.vinova.dom.internship.R;

/**
 * Created by HNS on 13/06/2017.
 */

public class DeliveriesAdapter extends RecyclerView.Adapter <DeliveriesAdapter.ViewHolder> {

    private ArrayList<Food> listData;
    private Context context;

    public DeliveriesAdapter(Context context, ArrayList<Food> listFood) {
        this.listData = listFood;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = listData.get(position);
        Glide.with(context).load(food.getImage()).into(holder.ivImage);
        holder.tvName.setText(food.getName());
        holder.tvNation.setText(food.getNation());
    }

    @Override
    public int getItemCount() {
        if (listData == null)
            return  0;
        else
            return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivImage;
        public TextView tvName;
        public TextView tvNation;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvNation = (TextView) itemView.findViewById(R.id.tvNation);
        }
    }
}

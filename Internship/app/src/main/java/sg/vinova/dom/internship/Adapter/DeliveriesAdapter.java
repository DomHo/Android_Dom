package sg.vinova.dom.internship.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import sg.vinova.dom.internship.Model.Delivery;
import sg.vinova.dom.internship.R;

/**
 * Created by HNS on 13/06/2017.
 */

public class DeliveriesAdapter extends RecyclerView.Adapter <DeliveriesAdapter.ViewHolder> {

    private List<Delivery> listData;
    private Context context;

    public DeliveriesAdapter(Context context, List<Delivery> listData) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_delivery, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Delivery delivery = listData.get(position);
        Glide.with(context).load(delivery.getImage()).into(holder.ivDelivery);
        holder.tvName.setText(delivery.getName());
        holder.tvNation.setText(delivery.getNation());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivDelivery;
        public TextView tvName;
        public TextView tvNation;

        public ViewHolder(View itemView) {
            super(itemView);
            ivDelivery = (ImageView) itemView.findViewById(R.id.ivDelivery);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvNation = (TextView) itemView.findViewById(R.id.tvNation);
        }
    }
}

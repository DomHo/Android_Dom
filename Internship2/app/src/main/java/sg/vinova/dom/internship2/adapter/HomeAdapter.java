package sg.vinova.dom.internship2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sg.vinova.dom.internship2.R;
import sg.vinova.dom.internship2.model.Home;

/**
 * Created by HNS on 18/06/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<Home> listData;
    private Context context;

    public HomeAdapter(ArrayList<Home> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        Home home = listData.get(position);
        if (position == 0)
            holder.ivImage.setVisibility(View.GONE);
        else
            Glide.with(context).load(home.getImage()).into(holder.ivImage);
        holder.tvContent.setText(home.getContent());
    }

    @Override
    public int getItemCount() {
        if (listData == null)
            return 0;
        else
            return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivImage;
        public TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
        }
    }
}

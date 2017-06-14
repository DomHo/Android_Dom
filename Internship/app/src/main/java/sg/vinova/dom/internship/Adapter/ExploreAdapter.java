package sg.vinova.dom.internship.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import sg.vinova.dom.internship.Model.Explore;
import sg.vinova.dom.internship.R;

/**
 * Created by HNS on 14/06/2017.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private List<List<Explore>> listData;
    private Context context;

    public ExploreAdapter(Context context, List<List<Explore>> listData) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_explore, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Explore explore1 = listData.get(position).get(0);
        Explore explore2 = listData.get(position).get(1);

//        holder.tvExplore.setText("1");
        Glide.with(context).load(explore1.getImage()).into(holder.ivDelivery1);
        Glide.with(context).load(explore2.getImage()).into(holder.ivDelivery2);
        holder.tvName1.setText(explore1.getName());
        holder.tvName2.setText(explore2.getName());
        holder.rbRate1.setRating((float) 3.5);
        holder.rbRate2.setRating((float) 2.5);
        holder.tvNation1.setText(explore1.getNation());
        holder.tvNation2.setText(explore2.getNation());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button btnMore;
        ImageView ivDelivery1, ivDelivery2;
        TextView tvExplore, tvName1, tvName2, tvNation1, tvNation2;
        RatingBar rbRate1, rbRate2;

        public ViewHolder(View itemView) {
            super(itemView);
            tvExplore = (TextView) itemView.findViewById(R.id.tvExplore);
            btnMore = (Button) itemView.findViewById(R.id.btnMore);
            ivDelivery1 = (ImageView) itemView.findViewById(R.id.ivExplore1);
            ivDelivery2 = (ImageView) itemView.findViewById(R.id.ivExplore2);
            tvName1 = (TextView) itemView.findViewById(R.id.tvName1);
            tvName2 = (TextView) itemView.findViewById(R.id.tvName2);
            rbRate1 = (RatingBar) itemView.findViewById(R.id.rbRate1);
            rbRate2 = (RatingBar) itemView.findViewById(R.id.rbRate2);
            tvNation1 = (TextView) itemView.findViewById(R.id.tvNation1);
            tvNation2 = (TextView) itemView.findViewById(R.id.tvNation2);
        }
    }
}

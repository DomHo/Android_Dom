package sg.vinova.dom.internship.adapter;

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

import java.util.ArrayList;

import sg.vinova.dom.internship.Model.Food;
import sg.vinova.dom.internship.R;

/**
 * Created by HNS on 14/06/2017.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private ArrayList<ArrayList<Food>> listData;
    private Context context;
    private OnClickMoreButton onClickMoreButton;

    public ExploreAdapter(Context context, ArrayList<ArrayList<Food>> listFood) {
        this.listData = listFood;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food1 = listData.get(position).get(0);
        Food food2 = listData.get(position).get(1);

        holder.tvType.setText("Type " + food1.getType());
        Glide.with(context).load(food1.getImage()).into(holder.ivImage1);
        Glide.with(context).load(food2.getImage()).into(holder.ivImage2);
        holder.tvName1.setText(food1.getName());
        holder.tvName2.setText(food2.getName());
        holder.rbRate1.setRating((float) 3.3);
        holder.rbRate2.setRating((float) 2.5);
        holder.tvNation1.setText(food1.getNation());
        holder.tvNation2.setText(food2.getNation());
    }

    @Override
    public int getItemCount() {
        if (listData == null)
            return 0;
        else
            return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button btnMore;
        ImageView ivImage1, ivImage2;
        TextView tvType, tvName1, tvName2, tvNation1, tvNation2;
        RatingBar rbRate1, rbRate2;

        public ViewHolder(View itemView) {
            super(itemView);
            tvType = (TextView) itemView.findViewById(R.id.tvType);
            btnMore = (Button) itemView.findViewById(R.id.btnMore);
            ivImage1 = (ImageView) itemView.findViewById(R.id.ivImage1);
            ivImage2 = (ImageView) itemView.findViewById(R.id.ivImage2);
            tvName1 = (TextView) itemView.findViewById(R.id.tvName1);
            tvName2 = (TextView) itemView.findViewById(R.id.tvName2);
            rbRate1 = (RatingBar) itemView.findViewById(R.id.rbRate1);
            rbRate2 = (RatingBar) itemView.findViewById(R.id.rbRate2);
            tvNation1 = (TextView) itemView.findViewById(R.id.tvNation1);
            tvNation2 = (TextView) itemView.findViewById(R.id.tvNation2);

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickMoreButton != null){
                        onClickMoreButton.onClickMore(getAdapterPosition());
                    }

                }
            });

        }
    }

    public interface OnClickMoreButton{
        void onClickMore(int num);
    }

    public void setOnClickMoreButton(OnClickMoreButton onClickMoreButton) {
        this.onClickMoreButton = onClickMoreButton;
    }
}

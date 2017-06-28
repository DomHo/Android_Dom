package sg.vinova.dom.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.loadImageFeature.LoadImage;
import sg.vinova.dom.myapplication.model.Image;

/**
 * Created by HNS on 27/06/2017.
 */

public class LoadImageAdapter extends RecyclerView.Adapter<LoadImageAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Image> imageList = null;
    private LoadImage.View loadImageView;

    public LoadImageAdapter(Context context, List<Image> imageList, LoadImage.View loadImageView) {
        this.context = context;
        this.imageList = imageList;
        this.loadImageView = loadImageView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Image image = imageList.get(position);
        Glide.with(context).load(image.getLink()).into(holder.ivImage);
        holder.tvContent.setText(image.getContent());
        holder.cvItem.setAlpha(0);
        holder.cvItem.animate().alpha(1.0f).setDuration(300).setStartDelay(position*2);

        holder.ivImage.setTransitionName("image" + position);
        holder.tvContent.setTransitionName("content" + position);
        holder.llItem.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        if (imageList == null)
            return 0;
        else
            return imageList.size();
    }

    @Override
    public void onClick(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout v = (LinearLayout) view;
            loadImageView.shareElement((ImageView) v.getChildAt(0), (TextView) v.getChildAt(1));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImage;
        private TextView tvContent;
        private LinearLayout llItem;
        private CardView cvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            llItem = (LinearLayout) itemView.findViewById(R.id.llItem);
            cvItem = (CardView) itemView.findViewById(R.id.cvItem);
        }
    }
}
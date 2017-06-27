package sg.vinova.dom.myapplication.loadImageFeature;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.model.Image;
import sg.vinova.dom.myapplication.utils.GalleryFragment;

/**
 * Created by HNS on 27/06/2017.
 */

public class LoadImageAdapter extends RecyclerView.Adapter<LoadImageAdapter.ViewHolder> {

    private Context context;
    private List<Image> imageList = null;
    private GalleryFragment galleryFragment;

    private float fromY = 100;
    private float toY = 0;

    public LoadImageAdapter(Context context, List<Image> imageList, GalleryFragment galleryFragment) {
        this.context = context;
        this.imageList = imageList;
        this.galleryFragment = galleryFragment;
    }

    public void setcoordinates(float fromY, float toY) {
        this.fromY = fromY;
        this.toY = toY;
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

//        holder.cvItem.animate().cancel();
//        holder.cvItem.setTranslationY(fromY);
        holder.cvItem.setAlpha(0);
        holder.cvItem.animate().alpha(1.0f).setDuration(300).setStartDelay(position*2);
    }

    @Override
    public int getItemCount() {
        if (imageList == null)
            return 0;
        else
            return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImage;
        private TextView tvContent;
        private CardView cvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            cvItem = (CardView) itemView.findViewById(R.id.cvItem);
        }
    }
}
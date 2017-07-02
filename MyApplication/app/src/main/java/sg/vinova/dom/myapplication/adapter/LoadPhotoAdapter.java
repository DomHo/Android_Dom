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
import sg.vinova.dom.myapplication.loadPhotoFeature.LoadPhoto;
import sg.vinova.dom.myapplication.model.Photo;

public class LoadPhotoAdapter extends RecyclerView.Adapter<LoadPhotoAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Photo> photoList = null;
    private LoadPhoto.View loadPhotoView;

    public LoadPhotoAdapter(Context context, List<Photo> photoList, LoadPhoto.View loadPhotoView) {
        this.context = context;
        this.photoList = photoList;
        this.loadPhotoView = loadPhotoView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.tvId.setText(Integer.toString(photo.getId()));
        Glide.with(context).load(photo.getThumbnailUrl()).into(holder.ivPhoto);
        holder.tvTitle.setText(photo.getTitle());

        holder.cvItem.setAlpha(0);
        holder.cvItem.animate().alpha(1.0f).setDuration(300).setStartDelay(position / 3);

        holder.ivPhoto.setTransitionName("Photo" + position);
        holder.tvTitle.setTransitionName("Title" + position);
        holder.llItem.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        if (photoList == null)
            return 0;
        else
            return photoList.size();
    }

    @Override
    public void onClick(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout v = (LinearLayout) view;
            int ID = Integer.parseInt(((TextView) v.getChildAt(0)).getText().toString());
            ImageView imageView = (ImageView) v.getChildAt(1);
            TextView textView = (TextView) v.getChildAt(2);
            loadPhotoView.shareElement(imageView, textView, photoList.get(ID - 1));
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cvItem;
        private LinearLayout llItem;
        private TextView tvId;
        private ImageView ivPhoto;
        private TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);
            cvItem = (CardView) itemView.findViewById(R.id.cvItem);
            llItem = (LinearLayout) itemView.findViewById(R.id.llItem);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
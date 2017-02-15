package com.develop.gleb.deliveryphones.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.databinding.ItemPhotoBinding;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private List<PhotoEntity> photos;
    private static Context context;

    public PhotoAdapter(List<PhotoEntity> photos, Context context) {
        this.photos = photos;
        this.context = context;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemPhotoBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_photo, parent, false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.bindEntity(position);
    }

    @Override
    public int getItemCount() {
        if (photos != null)
            return photos.size();
        else
            return  0;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        private ItemPhotoBinding binding;

        public PhotoViewHolder(ItemPhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindEntity(int position){
            PhotoEntity photoEntity = photos.get(position);
            binding.setPhoto(photoEntity);
        }
    }


    @BindingAdapter("srcImage")
    public static void setSrcImage(ImageView imageView, String path){
        File imgFile = new File(path);
        Picasso.with(context).load(imgFile).into(imageView);
    }

    public List<PhotoEntity> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoEntity> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }
}

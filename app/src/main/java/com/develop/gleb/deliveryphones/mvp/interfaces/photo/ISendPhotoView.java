package com.develop.gleb.deliveryphones.mvp.interfaces.photo;

import com.develop.gleb.deliveryphones.entities.PhotoEntity;

import java.util.List;

public interface ISendPhotoView {
    void initAdapter(List<PhotoEntity> photos);
}

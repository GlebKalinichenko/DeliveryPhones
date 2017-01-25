package com.develop.gleb.deliveryphones.mvp.interfaces.photo;

import com.develop.gleb.deliveryphones.entities.PhotoEntity;

import java.util.List;

public interface ISendPhotoPresenter {
    void onStart();
    void onResume();
    void onDestroy();
    void getPhonesPhotos();
    void uploadPhotos(List<PhotoEntity> entities);
}

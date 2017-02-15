package com.develop.gleb.deliveryphones.mvp.interfaces.sendphotos;

import com.develop.gleb.deliveryphones.entities.PhotoEntity;

import java.util.List;

public interface ISendPhotoPresenter {
    void onStart();
    void onResume();
    void onPause();
    void onDestroy();
    void getPhonesPhotos();
    void uploadPhotos(List<PhotoEntity> entities);
    void clearPhotos();
}

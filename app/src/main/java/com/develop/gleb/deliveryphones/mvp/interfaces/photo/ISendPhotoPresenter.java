package com.develop.gleb.deliveryphones.mvp.interfaces.photo;

public interface ISendPhotoPresenter {
    void onStart();
    void onResume();
    void onDestroy();
    void getPhonesPhotos();
}

package com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos;

public interface IReceivePhotosPresenter {
    void rcvPhotos();
    void onStart();
    void onResume();
    void onPause();
    void onDestroy();
    void clearPhotos();
/*    void getPhotos();*/
}

package com.develop.gleb.deliveryphones.mvp.implementations.sendphotos;

import android.util.Log;
import com.develop.gleb.deliveryphones.callbacks.ISendPhotoCallback;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoView;

import java.util.List;

import javax.inject.*;

public class SendPhotosPresenter implements ISendPhotoPresenter, ISendPhotoCallback {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ISendPhotoView view;
    private ISendPhotoModel model;

    @Inject
    public SendPhotosPresenter(ISendPhotoView view, ISendPhotoModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onStart() {
        getPhonesPhotos();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getPhonesPhotos() {
        Log.d(LOG_TAG, "Receive photos is started");
        model.getPhonesPhotos(this);
    }

    @Override
    public void success(List<PhotoEntity> photos) {
        Log.d(LOG_TAG, "Receive photos is loaded successful");
        view.initAdapter(photos);
    }

    @Override
    public void unsuccess() {
        Log.d(LOG_TAG, "Receive photos is loaded unsuccessful");
    }
}

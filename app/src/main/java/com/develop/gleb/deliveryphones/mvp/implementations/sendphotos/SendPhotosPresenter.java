package com.develop.gleb.deliveryphones.mvp.implementations.sendphotos;

import android.util.Log;
import com.develop.gleb.deliveryphones.callbacks.ISendPhotoCallback;
import com.develop.gleb.deliveryphones.callbacks.IUploadPhotosCallback;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphotos.ISendPhotoModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphotos.ISendPhotoPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphotos.ISendPhotoView;
import java.util.List;
import javax.inject.*;
import rx.subscriptions.CompositeSubscription;

public class SendPhotosPresenter implements ISendPhotoPresenter, ISendPhotoCallback, IUploadPhotosCallback {
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
    public void onPause() {
        clearSubscriptions();
    }

    @Override
    public void onDestroy() {
        clearSubscriptions();
    }

    private void clearSubscriptions(){
        CompositeSubscription subscriptions = model.getSubscriptions();
        subscriptions.clear();
    }

    @Override
    public void getPhonesPhotos() {
        Log.d(LOG_TAG, "Receive photos is started");
        model.getPhonesPhotos(this);
    }

    @Override
    public void uploadPhotos(List<PhotoEntity> entities) {
        model.savePhotos(entities, this);
    }

    @Override
    public void clearPhotos() {
        model.initClearPhotos(this);
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

    @Override
    public void clearSuccess() {
        view.clearSuccess();
    }

    @Override
    public void clearUnsuccess() {
        view.clearUnsuccess();
    }

    @Override
    public void uploadSuccess() {
        Log.d(LOG_TAG, "Upload file is finished success");
        view.uploadSuccess();
    }

    @Override
    public void uploadUnsuccess() {
        Log.d(LOG_TAG, "Upload file is finished unsuccess");
        view.uploadUnsuccess();
    }
}

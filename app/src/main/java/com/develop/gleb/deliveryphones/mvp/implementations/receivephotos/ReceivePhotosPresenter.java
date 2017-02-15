package com.develop.gleb.deliveryphones.mvp.implementations.receivephotos;

import com.develop.gleb.deliveryphones.callbacks.IReceivePhotosCallback;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos.IReceivePhotosModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos.IReceivePhotosPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos.IReceivePhotosView;

import java.util.List;

import javax.inject.*;

import rx.subscriptions.CompositeSubscription;

public class ReceivePhotosPresenter implements IReceivePhotosPresenter, IReceivePhotosCallback {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IReceivePhotosView view;
    private IReceivePhotosModel model;

    @Inject
    public ReceivePhotosPresenter(IReceivePhotosView view, IReceivePhotosModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void rcvPhotos() {
        model.rcvPhotos(this);
    }

    @Override
    public void onStart() {
        rcvPhotos();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {
        clearSubscription();
    }

    @Override
    public void onDestroy() {
        clearSubscription();
    }

    @Override
    public void clearPhotos() {
        model.initClearPhotos(this);
    }

    private void clearSubscription(){
        CompositeSubscription subscription = model.getCompositeSubscription();
        subscription.clear();
    }

    @Override
    public void downloadSuccess(int size) {
        model.getPhonesPhotos(this, size);
    }

    @Override
    public void downloadUnsuccess() {

    }

    @Override
    public void loadPhotosSuccess(List<PhotoEntity> photos) {
        view.initAdapter(photos);
    }

    @Override
    public void loadPhotosUnsuccess() {

    }

    @Override
    public void clearSuccess() {
        view.clearSuccess();
    }

    @Override
    public void clearUnsuccess() {
        view.clearUnsuccess();
    }
}

package com.develop.gleb.deliveryphones.mvp.implementations.sendphotos;

import android.util.Log;

import com.develop.gleb.deliveryphones.callbacks.ISendPhotoCallback;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.helpers.PhotoHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoModel;

import java.util.List;

import javax.inject.*;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SendPhotosModel implements ISendPhotoModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    @Inject
    public PhotoHelper photoHelper;

    @Inject
    public SendPhotosModel(PhotoHelper photoHelper) {
        this.photoHelper = photoHelper;
    }

    @Override
    public void getPhonesPhotos(ISendPhotoCallback callback) {
        Log.d(LOG_TAG, "Receive photos is started");
        Observable<List<PhotoEntity>> photosObservable = photoHelper.rcvPhoneContacts();
        photosObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(i -> callback.success(i),
                e -> callback.unsuccess());
    }
}

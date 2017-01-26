package com.develop.gleb.deliveryphones.mvp.interfaces.photo;

import com.develop.gleb.deliveryphones.callbacks.ISendPhotoCallback;
import com.develop.gleb.deliveryphones.callbacks.IUploadPhotosCallback;
import com.develop.gleb.deliveryphones.entities.PhoneEntity;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

public interface ISendPhotoModel {
    void getPhonesPhotos(ISendPhotoCallback callback);
    void savePhotos(List<PhotoEntity> photos, IUploadPhotosCallback callback);
    CompositeSubscription getSubscriptions();
}

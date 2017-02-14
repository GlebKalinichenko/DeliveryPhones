package com.develop.gleb.deliveryphones.mvp.interfaces.sendphotos;

import com.develop.gleb.deliveryphones.callbacks.IReceivePhotosCallback;
import com.develop.gleb.deliveryphones.callbacks.ISendPhotoCallback;
import com.develop.gleb.deliveryphones.callbacks.IUploadPhotosCallback;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.mvp.base.ICommonPhotoLogicModel;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

public interface ISendPhotoModel extends ICommonPhotoLogicModel<ISendPhotoCallback> {
    void getPhonesPhotos(ISendPhotoCallback callback);
    void savePhotos(List<PhotoEntity> photos, IUploadPhotosCallback callback);
    CompositeSubscription getSubscriptions();
/*    void initClearPhotos(ISendPhotoCallback callback);*/
}

package com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos;

import com.develop.gleb.deliveryphones.callbacks.IReceivePhotosCallback;
import com.develop.gleb.deliveryphones.mvp.base.ICommonPhotoLogicModel;

import rx.subscriptions.CompositeSubscription;

public interface IReceivePhotosModel extends ICommonPhotoLogicModel<IReceivePhotosCallback> {
    void rcvPhotos(IReceivePhotosCallback callback);
    void getPhonesPhotos(IReceivePhotosCallback callback, int size);
    CompositeSubscription getCompositeSubscription();
    /*void initClearPhotos(IReceivePhotosCallback callback);*/
}

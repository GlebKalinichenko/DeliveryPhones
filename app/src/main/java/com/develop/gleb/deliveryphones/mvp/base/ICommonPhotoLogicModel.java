package com.develop.gleb.deliveryphones.mvp.base;

import com.develop.gleb.deliveryphones.callbacks.ISendPhotoCallback;

public interface ICommonPhotoLogicModel<T> {
    void initClearPhotos(T callback);
    void clearPhotos(int size, T callback);
}

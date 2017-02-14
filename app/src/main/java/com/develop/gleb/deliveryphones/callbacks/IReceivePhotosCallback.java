package com.develop.gleb.deliveryphones.callbacks;

import com.develop.gleb.deliveryphones.entities.PhotoEntity;

import java.util.List;

public interface IReceivePhotosCallback {
    void downloadSuccess(int size);
    void downloadUnsuccess();
    void loadPhotosSuccess(List<PhotoEntity> photos);
    void loadPhotosUnsuccess();
    void clearSuccess();
    void clearUnsuccess();
}

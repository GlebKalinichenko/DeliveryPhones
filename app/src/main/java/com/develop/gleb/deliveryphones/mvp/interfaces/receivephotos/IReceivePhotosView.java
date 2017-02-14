package com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos;

import com.develop.gleb.deliveryphones.entities.PhotoEntity;

import java.util.List;

public interface IReceivePhotosView {
    void initAdapter(List<PhotoEntity> photos);
    void downloadSuccess();
    void clearSuccess();
    void clearUnsuccess();
}

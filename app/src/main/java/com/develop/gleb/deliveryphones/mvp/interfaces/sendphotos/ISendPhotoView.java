package com.develop.gleb.deliveryphones.mvp.interfaces.sendphotos;

import com.develop.gleb.deliveryphones.entities.PhotoEntity;

import java.util.List;

public interface ISendPhotoView {
    void initAdapter(List<PhotoEntity> photos);
    void uploadSuccess();
    void uploadUnsuccess();
    void clearSuccess();
    void clearUnsuccess();
}

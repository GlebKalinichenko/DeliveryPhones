package com.develop.gleb.deliveryphones.callbacks;

import com.develop.gleb.deliveryphones.entities.PhotoEntity;

import java.util.List;

public interface ISendPhotoCallback {
    void success(List<PhotoEntity> photos);
    void unsuccess();
}

package com.develop.gleb.deliveryphones.mvp.implementations.sendphotos;

import android.util.Log;

import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoModel;
import javax.inject.*;

public class SendPhotosModel implements ISendPhotoModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();

    @Inject
    public SendPhotosModel() {
    }

    @Override
    public void getPhonesPhotos() {
        Log.d(LOG_TAG, "Receive photos is started");

    }
}

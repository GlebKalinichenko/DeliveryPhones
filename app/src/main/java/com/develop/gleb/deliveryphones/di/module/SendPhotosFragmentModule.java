package com.develop.gleb.deliveryphones.di.module;

import android.content.Context;

import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.PhotoHelper;
import com.develop.gleb.deliveryphones.mvp.implementations.sendphotos.SendPhotosModel;
import com.develop.gleb.deliveryphones.mvp.implementations.sendphotos.SendPhotosPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoView;
import com.google.firebase.storage.StorageReference;

import dagger.Module;
import dagger.Provides;

@Module
public class SendPhotosFragmentModule {
    private ISendPhotoView view;
    private Context context;

    public SendPhotosFragmentModule(ISendPhotoView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    @FragmentScope
    public PhotoHelper createPhotoHelper(){
        PhotoHelper photoHelper = PhotoHelper.getInstance(context);
        return photoHelper;
    }

    @Provides
    @FragmentScope
    public ISendPhotoModel createSendPhotoModel(PhotoHelper photoHelper, IdHelper idHelper,
                                                StorageReference storageReference){
        ISendPhotoModel model = new SendPhotosModel(photoHelper, idHelper, storageReference);
        return model;
    }

    @Provides
    @FragmentScope
    public ISendPhotoPresenter createSendPhotoPresenter(ISendPhotoModel model){
        ISendPhotoPresenter presenter = new SendPhotosPresenter(view, model);
        return presenter;
    }
}

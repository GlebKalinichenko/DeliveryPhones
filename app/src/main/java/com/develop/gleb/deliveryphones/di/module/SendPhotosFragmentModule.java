package com.develop.gleb.deliveryphones.di.module;

import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.mvp.implementations.sendphotos.SendPhotosModel;
import com.develop.gleb.deliveryphones.mvp.implementations.sendphotos.SendPhotosPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoView;

import dagger.Module;
import dagger.Provides;

@Module
public class SendPhotosFragmentModule {
    private ISendPhotoView view;

    public SendPhotosFragmentModule(ISendPhotoView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public ISendPhotoModel createSendPhotoModel(){
        ISendPhotoModel model = new SendPhotosModel();
        return model;
    }

    @Provides
    @FragmentScope
    public ISendPhotoPresenter createSendPhotoPresenter(ISendPhotoModel model){
        ISendPhotoPresenter presenter = new SendPhotosPresenter(view, model);
        return presenter;
    }
}

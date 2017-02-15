package com.develop.gleb.deliveryphones.di.module;

import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.PhotoHelper;
import com.develop.gleb.deliveryphones.mvp.implementations.receivephotos.ReceivePhotosModel;
import com.develop.gleb.deliveryphones.mvp.implementations.receivephotos.ReceivePhotosPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos.IReceivePhotosModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos.IReceivePhotosPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos.IReceivePhotosView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import dagger.Module;
import dagger.Provides;

@Module
public class ReceivePhotosFragmentModule {
    private IReceivePhotosView view;

    public ReceivePhotosFragmentModule(IReceivePhotosView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public IReceivePhotosModel createReceivePhotoModel(IdHelper idHelper, StorageReference
            storageReference, PhotoHelper photoHelper, DatabaseReference database){
        ReceivePhotosModel model = new ReceivePhotosModel(idHelper, storageReference, photoHelper,
                database);
        return model;
    }

    @Provides
    @FragmentScope
    public IReceivePhotosPresenter createReceivePhotoPresenter(IReceivePhotosModel model){
        ReceivePhotosPresenter presenter = new ReceivePhotosPresenter(view, model);
        return presenter;
    }
}

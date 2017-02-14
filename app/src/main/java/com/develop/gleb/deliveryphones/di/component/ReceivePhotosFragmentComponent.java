package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.di.module.ReceivePhotosFragmentModule;
import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.fragments.photo.ReceivePhotoFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = ReceivePhotosFragmentModule.class)
public interface ReceivePhotosFragmentComponent {
    void inject(ReceivePhotoFragment fragment);
}

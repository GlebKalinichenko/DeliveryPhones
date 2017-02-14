package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.di.module.SendPhotosFragmentModule;
import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.fragments.photo.SendPhotoFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = SendPhotosFragmentModule.class)
public interface SendPhotosFragmentComponent {
    void inject(SendPhotoFragment fragment);
}

package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.MainActivity;
import com.develop.gleb.deliveryphones.di.module.MainActivityModule;
import com.develop.gleb.deliveryphones.di.module.ReceivePhoneFragmentModule;
import com.develop.gleb.deliveryphones.di.module.SendPhoneFragmentModule;
import com.develop.gleb.deliveryphones.di.module.SendPhotosFragmentModule;
import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;

import dagger.Component;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {
    SendPhoneFragmentComponent plus(SendPhoneFragmentModule module);
    ReceivePhoneFragmentComponent plus(ReceivePhoneFragmentModule module);
    SendPhotosFragmentComponent plus(SendPhotosFragmentModule module);
    void inject(MainActivity activity);
}

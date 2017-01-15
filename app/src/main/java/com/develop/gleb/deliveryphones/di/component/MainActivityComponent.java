package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.MainActivity;
import com.develop.gleb.deliveryphones.di.module.MainActivityModule;
import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;

import dagger.Component;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}

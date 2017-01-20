package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.di.module.BaseActivityModule;
import com.develop.gleb.deliveryphones.di.module.LoginActivityModule;
import com.develop.gleb.deliveryphones.di.module.MainActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = BaseActivityModule.class)
public interface BaseActivityComponent {
    LoginActivityComponent plus(LoginActivityModule module);
    MainActivityComponent plus(MainActivityModule module);
}

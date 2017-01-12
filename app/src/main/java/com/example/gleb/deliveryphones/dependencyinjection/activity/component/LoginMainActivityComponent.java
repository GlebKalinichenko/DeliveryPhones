package com.example.gleb.deliveryphones.dependencyinjection.activity.component;

import com.example.gleb.deliveryphones.LoginActivity;
import com.example.gleb.deliveryphones.MainActivity;
import com.example.gleb.deliveryphones.dependencyinjection.activity.modules.LoginActivityModule;
import com.example.gleb.deliveryphones.dependencyinjection.activity.modules.MainActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainActivityModule.class, LoginActivityModule.class})
public interface LoginMainActivityComponent {
    void inject(MainActivity activity);
    void inject(LoginActivity activity);
}

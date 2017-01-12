package com.example.gleb.deliveryphones.dependencyinjection;

import com.example.gleb.deliveryphones.LoginActivity;
import com.example.gleb.deliveryphones.MainActivity;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainActivityModule.class, LoginActivityModule.class})
public interface LoginMainActivityComponent {
    void inject(MainActivity activity);
    void inject(LoginActivity activity);
}

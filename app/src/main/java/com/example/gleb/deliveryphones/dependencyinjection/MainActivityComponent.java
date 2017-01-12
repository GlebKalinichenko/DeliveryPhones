package com.example.gleb.deliveryphones.dependencyinjection;

import com.example.gleb.deliveryphones.MainActivity;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}

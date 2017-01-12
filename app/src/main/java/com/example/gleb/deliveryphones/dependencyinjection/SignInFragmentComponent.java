package com.example.gleb.deliveryphones.dependencyinjection;

import com.example.gleb.deliveryphones.fragments.sign.SignInFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = SignInFragmentModule.class)
public interface SignInFragmentComponent {
    void inject(SignInFragment fragment);

}

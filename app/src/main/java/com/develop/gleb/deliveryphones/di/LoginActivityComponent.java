package com.develop.gleb.deliveryphones.di;

import com.develop.gleb.deliveryphones.LoginActivity;
import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.di.scopes.UserScope;

import javax.inject.Singleton;

import dagger.Component;

@UserScope
@Component(modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    void inject(LoginActivity activity);
}

package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.LoginActivity;
import com.develop.gleb.deliveryphones.di.module.LoginActivityModule;
import com.develop.gleb.deliveryphones.di.module.SignInFragmentModule;
import com.develop.gleb.deliveryphones.di.module.SignUpFragmentModule;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    void inject(LoginActivity activity);
    SignInFragmentComponent plus(SignInFragmentModule module);
    SignUpFragmentComponent plus(SignUpFragmentModule module);
}

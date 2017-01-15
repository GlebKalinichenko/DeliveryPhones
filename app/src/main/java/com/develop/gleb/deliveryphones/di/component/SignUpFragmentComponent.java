package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.di.module.SignUpFragmentModule;
import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.fragments.sign.SignUpFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = SignUpFragmentModule.class)
public interface SignUpFragmentComponent {
    void inject(SignUpFragment fragment);
}

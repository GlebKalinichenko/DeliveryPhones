package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.di.module.SignInFragmentModule;
import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.fragments.sign.SignInFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = SignInFragmentModule.class)
public interface SignInFragmentComponent {
    void inject(SignInFragment fragment);
}

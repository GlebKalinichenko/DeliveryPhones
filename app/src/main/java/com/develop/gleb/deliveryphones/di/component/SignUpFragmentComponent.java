package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.di.module.SignUpFragmentModule;
import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.fragments.sign.SignUpFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = SignUpFragmentModule.class)
public interface SignUpFragmentComponent {
    void inject(SignUpFragment fragment);
}

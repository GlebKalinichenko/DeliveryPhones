package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.di.module.SendPhoneFragmentModule;
import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.fragments.phones.SendPhonesFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = SendPhoneFragmentModule.class)
public interface SendPhoneFragmentComponent {
    void inject(SendPhonesFragment fragment);
}

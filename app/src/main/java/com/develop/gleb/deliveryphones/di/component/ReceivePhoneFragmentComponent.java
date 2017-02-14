package com.develop.gleb.deliveryphones.di.component;

import com.develop.gleb.deliveryphones.di.module.ReceivePhoneFragmentModule;
import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.fragments.phones.ReceivePhoneFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = ReceivePhoneFragmentModule.class)
public interface ReceivePhoneFragmentComponent {
    void inject(ReceivePhoneFragment fragment);
}

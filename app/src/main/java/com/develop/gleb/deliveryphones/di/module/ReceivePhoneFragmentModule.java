package com.develop.gleb.deliveryphones.di.module;

import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.mvp.implementations.ReceivePhonesModel;
import com.develop.gleb.deliveryphones.mvp.implementations.ReceivePhonesPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesView;

import dagger.Module;
import dagger.Provides;

@Module
public class ReceivePhoneFragmentModule {
    private IReceivePhonesView view;

    public ReceivePhoneFragmentModule(IReceivePhonesView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public IReceivePhonesModel createReceivePhoneModel() {
        IReceivePhonesModel model = new ReceivePhonesModel();
        return model;
    }

    @Provides
    @FragmentScope
    public IReceivePhonesPresenter createReceivePhonePresenter(IReceivePhonesModel model) {
        IReceivePhonesPresenter presenter = new ReceivePhonesPresenter(view, model);
        return presenter;
    }

}

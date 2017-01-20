package com.develop.gleb.deliveryphones.di.module;

import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.mvp.implementations.sendphones.SendPhoneModel;
import com.develop.gleb.deliveryphones.mvp.implementations.sendphones.SendPhonePresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneView;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInView;

import dagger.Module;
import dagger.Provides;

@Module
public class SendPhoneFragmentModule {
    private ISendPhoneView view;

    public SendPhoneFragmentModule(ISendPhoneView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public ISendPhoneModel createSendPhoneModel(){
        ISendPhoneModel model = new SendPhoneModel();
        return model;
    }

    @Provides
    @FragmentScope
    public ISendPhonePresenter createSendPresenter(ISendPhoneModel model){
        ISendPhonePresenter presenter = new SendPhonePresenter(view, model);
        return presenter;
    }

}

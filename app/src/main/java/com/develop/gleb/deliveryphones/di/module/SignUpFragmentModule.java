package com.develop.gleb.deliveryphones.di.module;

import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.mvp.implementations.signup.SignUpModel;
import com.develop.gleb.deliveryphones.mvp.implementations.signup.SignUpPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpView;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpFragmentModule {
    private ISignUpView view;

    public SignUpFragmentModule(ISignUpView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public ISignUpPresenter createSignUpPresenter(ISignUpModel model){
        ISignUpPresenter presenter = new SignUpPresenter(view, model);
        return presenter;
    }

    @Provides
    @FragmentScope
    public ISignUpModel createSignUpModel(){
        SignUpModel model = new SignUpModel();
        return model;
    }
}

package com.develop.gleb.deliveryphones.di.module;

import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.mvp.implementations.signin.SignInModel;
import com.develop.gleb.deliveryphones.mvp.implementations.signin.SignInPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInView;
import dagger.Module;
import dagger.Provides;

@Module
public class SignInFragmentModule {
    private ISignInView view;

    public SignInFragmentModule(ISignInView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public ISignInPresenter createSignInPresenter(ISignInModel model){
        ISignInPresenter presenter = new SignInPresenter(view, model);
        return presenter;
    }

    @Provides
    @ActivityScope
    public ISignInModel createSignInModel(){
        SignInModel model = new SignInModel();
        return model;
    }

}

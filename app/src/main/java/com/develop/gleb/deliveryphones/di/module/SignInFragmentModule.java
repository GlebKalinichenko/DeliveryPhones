package com.develop.gleb.deliveryphones.di.module;

import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.mvp.implementations.signin.SignInPresenter;
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
    public ISignInPresenter createSignInPresenter(){
        ISignInPresenter presenter = new SignInPresenter(view);
        return presenter;
    }

}

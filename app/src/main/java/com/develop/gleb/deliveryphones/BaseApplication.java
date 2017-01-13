package com.develop.gleb.deliveryphones;

import android.app.Application;

import com.develop.gleb.deliveryphones.di.component.DaggerLoginActivityComponent;
import com.develop.gleb.deliveryphones.di.component.LoginActivityComponent;
import com.develop.gleb.deliveryphones.di.component.SignUpFragmentComponent;
import com.develop.gleb.deliveryphones.di.module.LoginActivityModule;
import com.develop.gleb.deliveryphones.di.component.SignInFragmentComponent;
import com.develop.gleb.deliveryphones.di.module.SignInFragmentModule;
import com.develop.gleb.deliveryphones.di.module.SignUpFragmentModule;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInView;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpView;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public LoginActivityComponent getLoginActivityComponent(LoginActivity activity){
        LoginActivityComponent component = initBaseComponent()
                .loginActivityModule(new LoginActivityModule(activity)).build();
        return component;
    }

    public SignInFragmentComponent getSignInFragmentComponent(LoginActivity activity, ISignInView view){
        SignInFragmentComponent component = getLoginActivityComponent(activity).plus(new SignInFragmentModule(view));
        return component;
    }

    public SignUpFragmentComponent getSignUpFragmentComponent(LoginActivity activity, ISignUpView view){
        SignUpFragmentComponent component = getLoginActivityComponent(activity).plus(new SignUpFragmentModule(view));
        return component;
    }

    private DaggerLoginActivityComponent.Builder initBaseComponent(){
        return DaggerLoginActivityComponent.builder();
    }
}

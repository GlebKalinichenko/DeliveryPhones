package com.develop.gleb.deliveryphones;

import android.app.Activity;
import android.app.Application;

import com.develop.gleb.deliveryphones.di.component.BaseActivityComponent;
import com.develop.gleb.deliveryphones.di.component.DaggerBaseActivityComponent;
import com.develop.gleb.deliveryphones.di.component.LoginActivityComponent;
import com.develop.gleb.deliveryphones.di.component.MainActivityComponent;
import com.develop.gleb.deliveryphones.di.component.SignUpFragmentComponent;
import com.develop.gleb.deliveryphones.di.module.BaseActivityModule;
import com.develop.gleb.deliveryphones.di.module.LoginActivityModule;
import com.develop.gleb.deliveryphones.di.component.SignInFragmentComponent;
import com.develop.gleb.deliveryphones.di.module.MainActivityModule;
import com.develop.gleb.deliveryphones.di.module.SignInFragmentModule;
import com.develop.gleb.deliveryphones.di.module.SignUpFragmentModule;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInView;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpView;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public BaseActivityComponent getBaseActivityComponent(Activity activity){
        BaseActivityComponent component = initBaseComponent().baseActivityModule(new BaseActivityModule(activity)).build();
        return component;
    }

    public MainActivityComponent getMainActivityComponent(Activity activity){
        MainActivityComponent component = getBaseActivityComponent(activity)
                .plus(new MainActivityModule(activity));
        return component;
    }

    public LoginActivityComponent getLoginActivityComponent(LoginActivity activity){
        LoginActivityComponent component = getBaseActivityComponent(activity).plus(new LoginActivityModule(activity));
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

    private DaggerBaseActivityComponent.Builder initBaseComponent(){
        return DaggerBaseActivityComponent.builder();
    }
}

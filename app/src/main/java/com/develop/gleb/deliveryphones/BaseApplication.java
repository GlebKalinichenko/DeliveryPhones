package com.develop.gleb.deliveryphones;

import android.app.Application;

import com.develop.gleb.deliveryphones.di.DaggerLoginActivityComponent;
import com.develop.gleb.deliveryphones.di.LoginActivityComponent;
import com.develop.gleb.deliveryphones.di.LoginActivityModule;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public LoginActivityComponent getLoginActivityComponent(LoginActivity activity){
        LoginActivityComponent component = DaggerLoginActivityComponent.builder()
                .loginActivityModule(new LoginActivityModule(activity)).build();
        return component;
    }
}

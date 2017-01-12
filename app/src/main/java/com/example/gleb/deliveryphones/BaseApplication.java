package com.example.gleb.deliveryphones;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.gleb.deliveryphones.dependencyinjection.DaggerLoginMainActivityComponent;
import com.example.gleb.deliveryphones.dependencyinjection.LoginActivityModule;
import com.example.gleb.deliveryphones.dependencyinjection.LoginMainActivityComponent;
import com.example.gleb.deliveryphones.dependencyinjection.MainActivityModule;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public LoginMainActivityComponent returnLoginMainActivityComponent(Activity context){
        LoginMainActivityComponent component = DaggerLoginMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(context))
                .loginActivityModule(new LoginActivityModule(context)).build();
        return component;
    }

}

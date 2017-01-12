package com.example.gleb.deliveryphones;

import android.app.Application;
import android.content.Context;

import com.example.gleb.deliveryphones.dependencyinjection.DaggerMainActivityComponent;
import com.example.gleb.deliveryphones.dependencyinjection.MainActivityComponent;
import com.example.gleb.deliveryphones.dependencyinjection.MainActivityModule;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MainActivityComponent returnMainActivityComponent(Context context){
        MainActivityComponent component = DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule(context)).build();
        return component;
    }

}

package com.example.gleb.deliveryphones;

import android.app.Activity;
import android.app.Application;

import com.example.gleb.deliveryphones.dependencyinjection.DaggerSignInFragmentComponent;
import com.example.gleb.deliveryphones.dependencyinjection.SignInFragmentComponent;
import com.example.gleb.deliveryphones.dependencyinjection.SignInFragmentModule;
import com.example.gleb.deliveryphones.dependencyinjection.activity.component.DaggerLoginMainActivityComponent;
import com.example.gleb.deliveryphones.dependencyinjection.activity.modules.LoginActivityModule;
import com.example.gleb.deliveryphones.dependencyinjection.activity.component.LoginMainActivityComponent;
import com.example.gleb.deliveryphones.dependencyinjection.activity.modules.MainActivityModule;

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

    public SignInFragmentComponent returnSignInFragmentComponent(){
        SignInFragmentComponent component = DaggerSignInFragmentComponent.builder().signInFragmentModule(new SignInFragmentModule()).build();
        return component;
    }

}

package com.example.gleb.deliveryphones.dependencyinjection;

import android.app.Activity;
import android.content.Context;

import com.example.gleb.deliveryphones.helpers.PermissionHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {
    private Activity context;

    public LoginActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public PermissionHelper createPermissionHelper(){
        PermissionHelper helper = PermissionHelper.getInstance(context);
        return helper;
    }
}

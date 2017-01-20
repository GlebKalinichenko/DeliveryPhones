package com.develop.gleb.deliveryphones.di.module;

import android.app.Activity;

import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.helpers.FragmentHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private Activity context;

    public MainActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    public FragmentHelper createFragmentHelper(){
        FragmentHelper helper = FragmentHelper.getInstance(context);
        return helper;
    }
}

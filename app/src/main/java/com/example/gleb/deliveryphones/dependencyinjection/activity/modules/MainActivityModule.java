package com.example.gleb.deliveryphones.dependencyinjection.activity.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gleb.deliveryphones.helpers.FragmentHelper;
import com.example.gleb.deliveryphones.helpers.SharedPreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;
import static com.example.gleb.deliveryphones.MainActivity.IS_FRAGMENT_DIALOG;

@Module
public class MainActivityModule {
    private Context context;

    public MainActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public FragmentHelper createFragmentHelper(){
        FragmentHelper fragmentHelper = FragmentHelper.getInstance(context);
        return fragmentHelper;
    }

    @Provides
    @Singleton
    public SharedPreferencesHelper createSharedPreferencesHelper(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(IS_FRAGMENT_DIALOG, MODE_PRIVATE);
        SharedPreferencesHelper sharedPreferencesHelper = SharedPreferencesHelper.getInstance(sharedPreferences);
        return sharedPreferencesHelper;
    }
}

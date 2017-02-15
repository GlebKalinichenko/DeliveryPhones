package com.develop.gleb.deliveryphones.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.develop.gleb.deliveryphones.MainActivity;
import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

@Module
public class BaseActivityModule {
    private Context context;

    public BaseActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public SharedPreferencesHelper createSharedPreferencesHelper(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.IS_FRAGMENT_DIALOG, MODE_PRIVATE);
        SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance(sharedPreferences);
        return helper;
    }

    @Provides
    @Singleton
    public IdHelper createIdHelper(){
        IdHelper helper = IdHelper.getInstance();
        return helper;
    }

    @Provides
    @Singleton
    public DatabaseReference createDatabaseReference(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        return database;
    }
}

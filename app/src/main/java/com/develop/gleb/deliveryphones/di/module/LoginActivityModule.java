package com.develop.gleb.deliveryphones.di.module;

import android.app.Activity;
import android.content.SharedPreferences;
import com.develop.gleb.deliveryphones.MainActivity;
import com.develop.gleb.deliveryphones.di.scopes.UserScope;
import com.develop.gleb.deliveryphones.helpers.PermissionHelper;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import dagger.Module;
import dagger.Provides;
import static android.content.Context.MODE_PRIVATE;

@Module
public class LoginActivityModule {
    private Activity context;

    public LoginActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @UserScope
    public SharedPreferencesHelper createSharedPreferencesHelper(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.IS_FRAGMENT_DIALOG, MODE_PRIVATE);
        SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance(sharedPreferences);
        return helper;
    }

    @Provides
    @UserScope
    public PermissionHelper createPermissionHelper(){
        PermissionHelper permissionHelper = PermissionHelper.getInstance(context);
        return permissionHelper;
    }
}

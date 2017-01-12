package com.develop.gleb.deliveryphones.helpers;

import android.app.Activity;
import android.content.SharedPreferences;

import com.develop.gleb.deliveryphones.MainActivity;

public class SharedPreferencesHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private static final String SHARED_PREFERENCES_KEY = "SharedPreferencesKey";
    private Activity context;
    private static SharedPreferencesHelper instance = null;
    private SharedPreferences sharedPref;

    public static SharedPreferencesHelper getInstance(SharedPreferences sharedPreferences) {
        if (instance == null){
            instance = new SharedPreferencesHelper(sharedPreferences);
        }

        return instance;
    }

    private SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPref = sharedPreferences;
    }

    /**
    * Save is needed display choose dialog when screen is changed orientation
    * @param value        Is changed orientation
    * */
    public void saveDisplayDialogOnChangeOrientation(boolean value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(MainActivity.IS_FRAGMENT_DIALOG, value);
        editor.commit();
    }

    /**
     * Retrieve saved value that need to display choose dialog when screen is changed orientation
     * */
    public boolean isDisplayChooseDialog(){
        boolean value = sharedPref.getBoolean(MainActivity.IS_FRAGMENT_DIALOG, true);
        return value;
    }

}

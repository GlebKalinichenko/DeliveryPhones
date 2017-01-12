package com.develop.gleb.deliveryphones.helpers;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class FragmentHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private static FragmentHelper instance = null;
    private Context context;

    public static FragmentHelper getInstance(Context context) {
        if (instance == null){
            instance = new FragmentHelper(context);
        }

        return instance;
    }

    private FragmentHelper(Context context) {
        this.context = context;
    }

    /**
     * Load fragments into activity
     * @param activity    Activity host
     * @param fragment    Inserted fragment
     * @param resId       Id of container
     * */
    public void loadFragment(FragmentActivity activity, int resId, Fragment fragment){
        Log.d(LOG_TAG, "Load fragment is started");

        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft = ft.add(resId, fragment, null);
        ft.commit();
    }
}

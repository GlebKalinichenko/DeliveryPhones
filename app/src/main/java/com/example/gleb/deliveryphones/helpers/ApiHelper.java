package com.example.gleb.deliveryphones.helpers;

import android.os.Build;

public class ApiHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();

    /**
    * Check is api version is 19.
    * @return        Is api version equals 19
    * */
    public static boolean checkApiVersionYoungerLollipop(){
        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion <= Build.VERSION_CODES.LOLLIPOP)
            return true;
        else
            return false;
    }

    public static boolean checkApiVersionOlderMarshmallow(){
        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion >= Build.VERSION_CODES.M)
            return true;
        else
            return false;
    }

}

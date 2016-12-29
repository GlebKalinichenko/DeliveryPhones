package com.example.gleb.deliveryphones.helpers;

import android.os.Build;

public class ApiHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();

    /**
    * Check is api version is 17.
    * @return        Is api version equals 17
    * */
    public static boolean checkApiVersionJellyBean(){
        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion == Build.VERSION_CODES.JELLY_BEAN_MR1)
            return true;
        else
            return false;
    }

}

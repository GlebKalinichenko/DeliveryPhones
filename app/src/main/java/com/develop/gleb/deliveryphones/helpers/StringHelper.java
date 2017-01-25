package com.develop.gleb.deliveryphones.helpers;

import android.content.Context;

public class StringHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private static final String LEFT_BRACKETS = "[";
    private static final String RIGHT_BRACKETS = "]";
    private static final int FIRST_SYMBOL_PHONE = 1;

    /**
    * Convert phone from left and right brackets.
    * @param phone        Number of phone
    * @return             Refactor number of phone
    * */
    public static String clearPhonesFromBrackets(String phone){
        if (hasBrackets(phone)) {
            int phoneLength = phone.length();
            phone = phone.substring(FIRST_SYMBOL_PHONE, phoneLength - 1);
        }

        return phone;
    }

    /**
    * Check is string has left of right brackets.
    * @param entity        String entity
    * @return              Is string has left or right
    * */
    public static boolean hasBrackets(String entity){
        if (entity.startsWith(LEFT_BRACKETS) && entity.endsWith(RIGHT_BRACKETS))
            return true;
        else
            return false;
    }

    public static String getString(Context context, int resId){
        String res = context.getString(resId);
        return res;
    }

    /**
     * Check is valid file path without brackets /.
     * @param path        File path
     * @return String     Refactored file path
     * */
    public static String validPathFile(String path){
        int lastIndex = path.lastIndexOf("/") + 1;
        String res = path.substring(lastIndex);
        return res;
    }
}

package com.example.gleb.deliveryphones.helpers;

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
        if (phone.startsWith(LEFT_BRACKETS) && phone.endsWith(RIGHT_BRACKETS)) {
            int phoneLength = phone.length();
            phone = phone.substring(FIRST_SYMBOL_PHONE, phoneLength - 1);
        }

        return phone;
    }
}

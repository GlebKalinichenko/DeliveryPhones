package com.example.gleb.deliveryphones.helpers;

public class IdHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private static IdHelper instance = null;
    private String emailHash;

    public static IdHelper getInstance() {
        if (instance == null)
            instance = new IdHelper();
        return instance;
    }

    private IdHelper() {
    }

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }
}

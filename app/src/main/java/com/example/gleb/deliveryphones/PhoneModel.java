package com.example.gleb.deliveryphones;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.List;

public class PhoneModel implements IPhoneModel{
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IPhonePresenter presenter;
    private ContactPhoneHelper helper;

    public PhoneModel(IPhonePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public List<PhoneEntity> getPhones(Context context) {
        Log.d(LOG_TAG, "load phone model");
        helper = ContactPhoneHelper.getInstance(context);
        List<PhoneEntity> entities = helper.rcvCursorPhone();
        return entities;
    }

    @Override
    public void pushPhones(List<PhoneEntity> phones) {

    }

    @Override
    public List<PhoneEntity> getDatabasePhones() {
        return null;
    }
}

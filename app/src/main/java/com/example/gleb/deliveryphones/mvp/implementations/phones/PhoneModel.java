package com.example.gleb.deliveryphones.mvp.implementations.phones;

import android.content.Context;
import android.util.Log;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.helpers.ContactPhoneHelper;
import com.example.gleb.deliveryphones.mvp.interfaces.phones.IPhoneModel;
import com.example.gleb.deliveryphones.mvp.interfaces.phones.IPhonePresenter;

import java.util.List;

public class PhoneModel implements IPhoneModel {
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

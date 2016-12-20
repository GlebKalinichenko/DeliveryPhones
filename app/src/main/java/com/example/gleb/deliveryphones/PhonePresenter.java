package com.example.gleb.deliveryphones;

import android.content.Context;
import android.util.Log;

import java.util.List;

public class PhonePresenter implements IPhonePresenter{
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IPhoneModel model = new PhoneModel(this);
    private IPhoneView view;

    public PhonePresenter(IPhoneView view) {
        this.model = model;
    }

    public List<PhoneEntity> getPhones(Context context) {
        Log.d(LOG_TAG, "load phone presenter");
        List<PhoneEntity> entities = model.getPhones(context);
        return entities;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}

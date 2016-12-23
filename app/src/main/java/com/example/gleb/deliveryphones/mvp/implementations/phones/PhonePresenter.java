package com.example.gleb.deliveryphones.mvp.implementations.phones;

import android.content.Context;
import android.util.Log;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.mvp.interfaces.phones.IPhoneView;
import com.example.gleb.deliveryphones.mvp.interfaces.phones.IPhoneModel;
import com.example.gleb.deliveryphones.mvp.interfaces.phones.IPhonePresenter;

import java.util.List;

public class PhonePresenter implements IPhonePresenter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IPhoneModel model = new PhoneModel(this);
    private IPhoneView view;

    public PhonePresenter(IPhoneView view) {
        this.view = view;
        this.model = model;
    }

    public List<PhoneEntity> getPhones(Context context) {
        Log.d(LOG_TAG, "load phone presenter");
        List<PhoneEntity> entities = model.getPhones(context);
        return entities;
    }

    @Override
    public void sendPhones(List<PhoneEntity> entities) {
        model.pushPhones(entities);
    }

    @Override
    public void responseSync() {
        view.responseSync();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}

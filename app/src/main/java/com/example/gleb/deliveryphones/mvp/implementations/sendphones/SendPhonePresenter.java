package com.example.gleb.deliveryphones.mvp.implementations.sendphones;

import android.content.Context;
import android.util.Log;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneView;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneModel;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;

import java.util.List;

public class SendPhonePresenter implements ISendPhonePresenter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ISendPhoneModel model = new SendPhoneModel(this);
    private ISendPhoneView view;

    public SendPhonePresenter(ISendPhoneView view) {
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

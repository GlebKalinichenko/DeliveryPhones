package com.develop.gleb.deliveryphones.mvp.interfaces.receivephones;

import android.content.Context;

import com.develop.gleb.deliveryphones.callbacks.IReceivePhoneCallback;
import com.develop.gleb.deliveryphones.entities.PhoneEntity;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;

import java.util.List;

public interface IReceivePhonesPresenter extends IReceivePhoneCallback {
    void receivePhones();
    void savePhones(Context context, List<PhoneEntity> entities);
    void clearPhones();
    void onResume();
    void onPause(SharedPreferencesHelper helper);
    void onDestroy(SharedPreferencesHelper helper);
}

package com.develop.gleb.deliveryphones.mvp.interfaces.sendphones;

import android.content.Context;

import com.develop.gleb.deliveryphones.callbacks.ISendPhoneCallback;
import com.develop.gleb.deliveryphones.entities.PhoneEntity;

import java.util.List;

public interface ISendPhoneModel {
    List<PhoneEntity> getPhones(Context context);
    void pushPhones(List<PhoneEntity> phones, ISendPhoneCallback callback);
    List<PhoneEntity> getDatabasePhones();
    void clearPhones(ISendPhoneCallback callback);
}

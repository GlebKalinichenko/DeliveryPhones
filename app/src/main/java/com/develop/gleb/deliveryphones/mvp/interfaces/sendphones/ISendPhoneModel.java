package com.develop.gleb.deliveryphones.mvp.interfaces.sendphones;

import android.content.Context;

import com.develop.gleb.deliveryphones.ISendPhoneCallback;
import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.callbacks.ILoginCallback;

import java.util.List;

public interface ISendPhoneModel {
    List<PhoneEntity> getPhones(Context context);
    void pushPhones(List<PhoneEntity> phones, ISendPhoneCallback callback);
    List<PhoneEntity> getDatabasePhones();
    void clearPhones(ISendPhoneCallback callback);
}

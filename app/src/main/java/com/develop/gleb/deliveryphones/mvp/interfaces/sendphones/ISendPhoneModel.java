package com.develop.gleb.deliveryphones.mvp.interfaces.sendphones;

import android.content.Context;

import com.develop.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface ISendPhoneModel {
    List<PhoneEntity> getPhones(Context context);
    void pushPhones(List<PhoneEntity> phones);
    List<PhoneEntity> getDatabasePhones();
    void clearPhones();
}

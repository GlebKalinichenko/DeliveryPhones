package com.example.gleb.deliveryphones;

import android.content.Context;

import java.util.List;

public interface IPhoneModel {
    List<PhoneEntity> getPhones(Context context);
    void pushPhones(List<PhoneEntity> phones);
    List<PhoneEntity> getDatabasePhones();
}

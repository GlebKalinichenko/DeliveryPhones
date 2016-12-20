package com.example.gleb.deliveryphones.mvp.interfaces;

import android.content.Context;

import com.example.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface IPhoneModel {
    List<PhoneEntity> getPhones(Context context);
    void pushPhones(List<PhoneEntity> phones);
    List<PhoneEntity> getDatabasePhones();
}

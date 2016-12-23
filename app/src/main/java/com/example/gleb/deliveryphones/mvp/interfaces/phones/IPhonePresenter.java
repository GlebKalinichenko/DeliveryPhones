package com.example.gleb.deliveryphones.mvp.interfaces.phones;

import android.content.Context;

import com.example.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface IPhonePresenter {
    List<PhoneEntity> getPhones(Context context);
    void sendPhones(List<PhoneEntity> entities);
    void responseSync();
    void onResume();
    void onDestroy();
}

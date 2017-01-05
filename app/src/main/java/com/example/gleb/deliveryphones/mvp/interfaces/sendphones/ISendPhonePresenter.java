package com.example.gleb.deliveryphones.mvp.interfaces.sendphones;

import android.content.Context;

import com.example.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface ISendPhonePresenter {
    List<PhoneEntity> getPhones(Context context);
    void sendPhones(List<PhoneEntity> entities);
    void responseSync();
    void clearPhones();
    void clearSuccess();
    void onResume();
    void onDestroy();
}

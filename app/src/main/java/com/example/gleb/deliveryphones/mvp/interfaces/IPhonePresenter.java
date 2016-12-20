package com.example.gleb.deliveryphones.mvp.interfaces;

import android.content.Context;

import com.example.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface IPhonePresenter {
    List<PhoneEntity> getPhones(Context context);
    void onResume();
    void onDestroy();
}

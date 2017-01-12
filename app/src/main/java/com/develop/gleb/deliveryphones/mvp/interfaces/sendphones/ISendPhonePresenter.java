package com.develop.gleb.deliveryphones.mvp.interfaces.sendphones;

import android.content.Context;

import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;

import java.util.List;

import rx.Observable;

public interface ISendPhonePresenter {
    List<PhoneEntity> getPhones(Context context);
    void sendPhones(List<PhoneEntity> entities);
    void responseSync();
    void clearPhones();
    void clearSuccess();
    void onResume(Observable<PhoneEntity> phoneObservable);
    void onPause(SharedPreferencesHelper helper);
    void onDestroy(SharedPreferencesHelper helper);
}
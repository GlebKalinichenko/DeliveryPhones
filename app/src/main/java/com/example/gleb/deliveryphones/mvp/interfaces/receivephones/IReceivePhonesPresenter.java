package com.example.gleb.deliveryphones.mvp.interfaces.receivephones;

import android.content.Context;

import com.example.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface IReceivePhonesPresenter {
    void receivePhones();
    void receivePhonesSuccess(List<PhoneEntity> entities);
    void receivePhonesUnsuccess();
    void savePhones(Context context, List<PhoneEntity> entities);
    void savePhonesFinish();
    void clearPhones();
    void clearSuccess();
    void onPause();
    void onDestroy();
}

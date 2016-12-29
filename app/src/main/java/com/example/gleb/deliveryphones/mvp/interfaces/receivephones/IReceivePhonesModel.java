package com.example.gleb.deliveryphones.mvp.interfaces.receivephones;

import android.content.Context;

import com.example.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface IReceivePhonesModel {
    void receivePhones();
    void savePhones(Context context, List<PhoneEntity> entities);
}

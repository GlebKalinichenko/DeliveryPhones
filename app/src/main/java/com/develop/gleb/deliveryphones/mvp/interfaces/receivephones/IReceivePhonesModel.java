package com.develop.gleb.deliveryphones.mvp.interfaces.receivephones;

import android.content.Context;

import com.develop.gleb.deliveryphones.callbacks.IReceivePhoneCallback;
import com.develop.gleb.deliveryphones.entities.PhoneEntity;

import java.util.List;

public interface IReceivePhonesModel {
    void receivePhones(IReceivePhoneCallback callback);
    void savePhones(Context context, List<PhoneEntity> entities, IReceivePhoneCallback callback);
    void clearPhones(IReceivePhoneCallback callback);
}

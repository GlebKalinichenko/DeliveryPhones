package com.develop.gleb.deliveryphones.callbacks;

import com.develop.gleb.deliveryphones.entities.PhoneEntity;

import java.util.List;

public interface IReceivePhoneCallback {
    void receivePhonesSuccess(List<PhoneEntity> entities);
    void receivePhonesUnsuccess();
    void savePhonesFinish();
    void clearSuccess();
}

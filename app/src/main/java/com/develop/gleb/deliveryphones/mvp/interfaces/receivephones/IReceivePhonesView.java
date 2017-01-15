package com.develop.gleb.deliveryphones.mvp.interfaces.receivephones;

import com.develop.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface IReceivePhonesView {
    void receivePhoneSuccess(List<PhoneEntity> entities);
    void receivePhoneUnsuccess();
    void savePhonesFinish();
    void clearSuccess();
}

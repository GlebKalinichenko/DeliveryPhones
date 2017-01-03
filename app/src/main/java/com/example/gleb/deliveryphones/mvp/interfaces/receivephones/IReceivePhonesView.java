package com.example.gleb.deliveryphones.mvp.interfaces.receivephones;

import com.example.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface IReceivePhonesView {
    void receivePhoneSuccess(List<PhoneEntity> entities);
    void receivePhoneUnsuccess();
    void savePhonesFinish();
}

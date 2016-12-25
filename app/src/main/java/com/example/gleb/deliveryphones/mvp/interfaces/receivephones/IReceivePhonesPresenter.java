package com.example.gleb.deliveryphones.mvp.interfaces.receivephones;

import com.example.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface IReceivePhonesPresenter {
    void receivePhones();
    void receivePhonesSuccess(List<PhoneEntity> entities);
    void receivePhonesUnsuccess();
}

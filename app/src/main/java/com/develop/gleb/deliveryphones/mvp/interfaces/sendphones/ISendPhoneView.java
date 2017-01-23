package com.develop.gleb.deliveryphones.mvp.interfaces.sendphones;

import com.develop.gleb.deliveryphones.entities.PhoneEntity;

import java.util.List;

public interface ISendPhoneView {
    void finishSync();
    void clearSuccess();
    void initAdapter(List<PhoneEntity> entities);
}

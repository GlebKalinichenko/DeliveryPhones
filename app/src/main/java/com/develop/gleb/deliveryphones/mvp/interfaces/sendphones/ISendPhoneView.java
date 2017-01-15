package com.develop.gleb.deliveryphones.mvp.interfaces.sendphones;

import com.develop.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface ISendPhoneView {
    void responseSync();
    void clearSuccess();
    void initAdapter(List<PhoneEntity> entities);
}

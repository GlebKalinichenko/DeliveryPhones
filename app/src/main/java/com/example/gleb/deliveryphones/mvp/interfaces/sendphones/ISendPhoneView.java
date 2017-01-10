package com.example.gleb.deliveryphones.mvp.interfaces.sendphones;

import com.example.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface ISendPhoneView {
    void responseSync();
    void clearSuccess();
    void initAdapter(List<PhoneEntity> entities);
}

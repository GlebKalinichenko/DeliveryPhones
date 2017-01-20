package com.develop.gleb.deliveryphones.mvp.interfaces.sendphones;

import com.develop.gleb.deliveryphones.IBaseView;
import com.develop.gleb.deliveryphones.PhoneEntity;

import java.util.List;

public interface ISendPhoneView {
    void finishSync();
    void clearSuccess();
    void initAdapter(List<PhoneEntity> entities);
}

package com.develop.gleb.deliveryphones;

import java.util.List;

public interface IReceivePhoneCallback {
    void receivePhonesSuccess(List<PhoneEntity> entities);
    void receivePhonesUnsuccess();
    void savePhonesFinish();
    void clearSuccess();
}

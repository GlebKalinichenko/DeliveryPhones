package com.example.gleb.deliveryphones;

import android.content.Context;

import java.util.List;

public interface IPhonePresenter {
    List<PhoneEntity> getPhones(Context context);
    void onResume();
    void onDestroy();
}

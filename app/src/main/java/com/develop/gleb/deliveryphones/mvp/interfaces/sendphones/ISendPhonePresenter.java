package com.develop.gleb.deliveryphones.mvp.interfaces.sendphones;

import android.content.Context;

<<<<<<< HEAD
import com.develop.gleb.deliveryphones.PhoneEntity;
=======
import com.develop.gleb.deliveryphones.ISendPhoneCallback;
import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.callbacks.ILoginCallback;
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;

import java.util.List;

import rx.Observable;

<<<<<<< HEAD
public interface ISendPhonePresenter {
    List<PhoneEntity> getPhones(Context context);
    void sendPhones(List<PhoneEntity> entities);
    void responseSync();
    void clearPhones();
    void clearSuccess();
=======
public interface ISendPhonePresenter extends ISendPhoneCallback {
    List<PhoneEntity> getPhones(Context context);
    void sendPhones(List<PhoneEntity> entities);
    void clearPhones();
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
    void onResume(Observable<PhoneEntity> phoneObservable);
    void onPause(SharedPreferencesHelper helper);
    void onDestroy(SharedPreferencesHelper helper);
}

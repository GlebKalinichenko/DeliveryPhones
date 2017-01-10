package com.example.gleb.deliveryphones.mvp.implementations.sendphones;

import android.content.Context;
import android.util.Log;
import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneView;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneModel;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SendPhonePresenter implements ISendPhonePresenter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ISendPhoneModel model = new SendPhoneModel(this);
    private ISendPhoneView view;
    private Subscription phoneSubscription;

    public SendPhonePresenter(ISendPhoneView view) {
        this.view = view;
    }

    public List<PhoneEntity> getPhones(Context context) {
        Log.d(LOG_TAG, "load phone presenter");
        List<PhoneEntity> entities = model.getPhones(context);
        return entities;
    }

    @Override
    public void sendPhones(List<PhoneEntity> entities) {
        Log.d(LOG_TAG, "Send phones");
        model.pushPhones(entities);
    }

    @Override
    public void responseSync() {
        Log.d(LOG_TAG, "Sync phones");
        view.responseSync();
    }

    @Override
    public void clearPhones() {
        Log.d(LOG_TAG, "Clean phones");
        model.clearPhones();
    }

    @Override
    public void clearSuccess() {
        Log.d(LOG_TAG, "Clean phones were successful");
        view.clearSuccess();
    }

    @Override
    public void onResume(Observable<PhoneEntity> phoneObservable) {
        Log.d(LOG_TAG, "On resume");
        phoneSubscription = phoneObservable.toList().filter(i -> i.size() > 0).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(i -> view.initAdapter(i));

    }

    @Override
    public void onPause(SharedPreferencesHelper helper) {
        Log.d(LOG_TAG, "On pause");
        helper.saveDisplayDialogOnChangeOrientation(false);
        phoneSubscription.unsubscribe();

        CompositeSubscription subscriptions =((SendPhoneModel) model).getSubscriptions();
        subscriptions.unsubscribe();
    }

    @Override
    public void onDestroy(SharedPreferencesHelper helper) {
        Log.d(LOG_TAG, "on destroy");
        helper.saveDisplayDialogOnChangeOrientation(true);
        phoneSubscription.unsubscribe();

        CompositeSubscription subscriptions =((SendPhoneModel) model).getSubscriptions();
        subscriptions.unsubscribe();
    }
}

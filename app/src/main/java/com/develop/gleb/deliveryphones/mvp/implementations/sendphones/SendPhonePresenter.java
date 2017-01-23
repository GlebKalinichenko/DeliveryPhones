package com.develop.gleb.deliveryphones.mvp.implementations.sendphones;

import android.content.Context;
import android.util.Log;
import com.develop.gleb.deliveryphones.entities.PhoneEntity;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneView;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SendPhonePresenter implements ISendPhonePresenter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ISendPhoneModel model;
    private ISendPhoneView view;
    private Subscription phoneSubscription;

    @Inject
    public SendPhonePresenter(ISendPhoneView view, ISendPhoneModel model) {
        this.view = view;
        this.model = model;
    }

    public List<PhoneEntity> getPhones(Context context) {
        Log.d(LOG_TAG, "load phone presenter");
        List<PhoneEntity> entities = model.getPhones(context);
        return entities;
    }

    @Override
    public void sendPhones(List<PhoneEntity> entities) {
        Log.d(LOG_TAG, "Send phones");
        model.pushPhones(entities, this);
    }

    /*@Override
    public void finishSync() {
        Log.d(LOG_TAG, "Sync phones");
        view.finishSync();
    }*/

    @Override
    public void clearPhones() {
        Log.d(LOG_TAG, "Clean phones");
        model.clearPhones(this);
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

    @Override
    public void saveSuccess() {
        view.finishSync();
    }
}

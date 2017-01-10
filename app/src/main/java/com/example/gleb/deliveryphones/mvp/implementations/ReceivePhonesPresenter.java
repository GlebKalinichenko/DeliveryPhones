package com.example.gleb.deliveryphones.mvp.implementations;

import android.content.Context;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesModel;
import com.example.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesPresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesView;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

public class ReceivePhonesPresenter implements IReceivePhonesPresenter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IReceivePhonesView view;
    private IReceivePhonesModel model = new ReceivePhonesModel(this);

    public ReceivePhonesPresenter(IReceivePhonesView view) {
        this.view = view;
    }

    @Override
    public void receivePhones() {
        model.receivePhones();
    }

    @Override
    public void receivePhonesSuccess(List<PhoneEntity> entityies) {
        view.receivePhoneSuccess(entityies);
    }

    @Override
    public void receivePhonesUnsuccess() {
        view.receivePhoneUnsuccess();
    }

    @Override
    public void savePhones(Context context, List<PhoneEntity> entities) {
        model.savePhones(context, entities);
    }

    @Override
    public void savePhonesFinish() {
        view.savePhonesFinish();
    }

    @Override
    public void clearPhones() {
        model.clearPhones();
    }

    @Override
    public void clearSuccess() {
        view.clearSuccess();
    }

    @Override
    public void onPause() {
        CompositeSubscription subscriptions =((ReceivePhonesModel) model).getSubscriptions();
        subscriptions.unsubscribe();
    }

    @Override
    public void onDestroy() {
        CompositeSubscription subscriptions =((ReceivePhonesModel) model).getSubscriptions();
        subscriptions.unsubscribe();
    }
}

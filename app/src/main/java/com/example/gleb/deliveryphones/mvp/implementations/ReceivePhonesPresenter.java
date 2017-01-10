package com.example.gleb.deliveryphones.mvp.implementations;

import android.content.Context;
import android.util.Log;
import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.helpers.SharedPreferencesHelper;
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
        Log.d(LOG_TAG, "Receive phones");
        model.receivePhones();
    }

    @Override
    public void receivePhonesSuccess(List<PhoneEntity> entityies) {
        Log.d(LOG_TAG, "Received phones were successful");
        view.receivePhoneSuccess(entityies);
    }

    @Override
    public void receivePhonesUnsuccess() {
        Log.d(LOG_TAG, "Received phones were unsuccessful");
        view.receivePhoneUnsuccess();
    }

    @Override
    public void savePhones(Context context, List<PhoneEntity> entities) {
        Log.d(LOG_TAG, "Save phones");
        model.savePhones(context, entities);
    }

    @Override
    public void savePhonesFinish() {
        Log.d(LOG_TAG, "Saved phones were finished");
        view.savePhonesFinish();
    }

    @Override
    public void clearPhones() {
        Log.d(LOG_TAG, "Clean phones");
        model.clearPhones();
    }

    @Override
    public void clearSuccess() {
        Log.d(LOG_TAG, "Cleaned phones were successful");
        view.clearSuccess();
    }

    @Override
    public void onResume() {
        Log.d(LOG_TAG, "On resume");
        receivePhones();
    }

    @Override
    public void onPause(SharedPreferencesHelper helper) {
        Log.d(LOG_TAG, "On pause");
        helper.saveDisplayDialogOnChangeOrientation(false);

        CompositeSubscription subscriptions =((ReceivePhonesModel) model).getSubscriptions();
        subscriptions.unsubscribe();
    }

    @Override
    public void onDestroy(SharedPreferencesHelper helper) {
        Log.d(LOG_TAG, "On destroy");
        helper.saveDisplayDialogOnChangeOrientation(true);

        CompositeSubscription subscriptions =((ReceivePhonesModel) model).getSubscriptions();
        subscriptions.unsubscribe();
    }
}

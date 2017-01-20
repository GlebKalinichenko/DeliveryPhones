package com.develop.gleb.deliveryphones.mvp.implementations;

import android.content.Context;
import android.util.Log;
import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesView;
import java.util.List;
<<<<<<< HEAD
=======

import javax.inject.Inject;

>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
import rx.subscriptions.CompositeSubscription;

public class ReceivePhonesPresenter implements IReceivePhonesPresenter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IReceivePhonesView view;
<<<<<<< HEAD
    private IReceivePhonesModel model = new ReceivePhonesModel(this);

    public ReceivePhonesPresenter(IReceivePhonesView view) {
        this.view = view;
=======
    private IReceivePhonesModel model;

    @Inject
    public ReceivePhonesPresenter(IReceivePhonesView view, IReceivePhonesModel model) {
        this.view = view;
        this.model = model;
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
    }

    @Override
    public void receivePhones() {
        Log.d(LOG_TAG, "Receive phones");
<<<<<<< HEAD
        model.receivePhones();
=======
        model.receivePhones(this);
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
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
<<<<<<< HEAD
        model.savePhones(context, entities);
=======
        model.savePhones(context, entities, this);
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
    }

    @Override
    public void savePhonesFinish() {
        Log.d(LOG_TAG, "Saved phones were finished");
        view.savePhonesFinish();
    }

    @Override
    public void clearPhones() {
        Log.d(LOG_TAG, "Clean phones");
<<<<<<< HEAD
        model.clearPhones();
=======
        model.clearPhones(this);
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
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

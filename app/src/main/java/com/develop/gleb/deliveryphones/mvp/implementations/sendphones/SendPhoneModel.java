package com.develop.gleb.deliveryphones.mvp.implementations.sendphones;

import android.content.Context;
import android.util.Log;

import com.develop.gleb.deliveryphones.ISendPhoneCallback;
import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.callbacks.ILoginCallback;
import com.develop.gleb.deliveryphones.helpers.ContactPhoneHelper;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SendPhoneModel implements ISendPhoneModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private final static String PHONES = "Phones";
    private ContactPhoneHelper helper;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private IdHelper idHelper = IdHelper.getInstance();
    private String emailHash = idHelper.getEmailHash();
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public SendPhoneModel() {
    }

    @Override
    public List<PhoneEntity> getPhones(Context context) {
        Log.d(LOG_TAG, "load phone model");
        helper = ContactPhoneHelper.getInstance(context);
        List<PhoneEntity> entities = helper.rcvCursorPhone();
        return entities;
    }

    @Override
    public void pushPhones(List<PhoneEntity> phones, ISendPhoneCallback callback) {
        Log.d(LOG_TAG, "Push phones");
        DatabaseReference res = database.child(emailHash).child(PHONES);
        Subscription subscription = Observable.from(phones).doOnNext(i -> res.push().setValue(i)).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PhoneEntity>() {
            @Override
            public void onCompleted() {
                callback.saveSuccess();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(PhoneEntity phoneEntity) {

            }
        });
        subscriptions.add(subscription);
    }

    @Override
    public List<PhoneEntity> getDatabasePhones() {
        return null;
    }

    @Override
    public void clearPhones(ISendPhoneCallback callback) {
        Log.d(LOG_TAG, "Clean phones");
        Subscription subscription = Observable.just(database).map(i -> i.child(emailHash).removeValue()).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(i -> callback.clearSuccess());
        subscriptions.add(subscription);
    }

    public CompositeSubscription getSubscriptions() {
        return subscriptions;
    }
}

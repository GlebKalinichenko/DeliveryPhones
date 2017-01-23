package com.develop.gleb.deliveryphones.mvp.implementations;

import android.content.Context;
import android.util.Log;

import com.develop.gleb.deliveryphones.callbacks.IReceivePhoneCallback;
import com.develop.gleb.deliveryphones.entities.PhoneEntity;
import com.develop.gleb.deliveryphones.helpers.ContactPhoneHelper;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.ReceivePhoneHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ReceivePhonesModel implements IReceivePhonesModel {
    private final String LOG_TAG =  this.getClass().getCanonicalName();
    @Inject
    public DatabaseReference database;
    private CompositeSubscription subscriptions = new CompositeSubscription();
    @Inject
    public IdHelper idHelper;
    private String emailHash;

    @Inject
    public ReceivePhonesModel(DatabaseReference database, IdHelper helper) {
        this.database = database;
        this.idHelper = helper;
    }

    @Override
    public void receivePhones(IReceivePhoneCallback callback) {
        Log.d(LOG_TAG, "Receive phones from model");
;
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Subscription subscription = Observable.just(ReceivePhoneHelper.getInstance()).map(i -> i.convertPhones(dataSnapshot))
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(i -> callback.receivePhonesSuccess(i));
                subscriptions.add(subscription);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.receivePhonesUnsuccess();
            }
        });
    }

    @Override
    public void savePhones(Context context, List<PhoneEntity> entities, IReceivePhoneCallback callback) {
        Log.d(LOG_TAG, "Save phones");

        ContactPhoneHelper helper = ContactPhoneHelper.getInstance(context);
        Observable<String> values = helper.savePhones(context, entities);
        Subscription subscription = values.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                callback.savePhonesFinish();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
        subscriptions.add(subscription);
    }

    @Override
    public void clearPhones(IReceivePhoneCallback callback) {
        Log.d(LOG_TAG, "Clean phones");
        emailHash = idHelper.getEmailHash();

        Subscription subscription = Observable.just(database).map(i -> i.child(emailHash).removeValue())
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> callback.clearSuccess());
        subscriptions.add(subscription);
    }

    public CompositeSubscription getSubscriptions() {
        return subscriptions;
    }
}

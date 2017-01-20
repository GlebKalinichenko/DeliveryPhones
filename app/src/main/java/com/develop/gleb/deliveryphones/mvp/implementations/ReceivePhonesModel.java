package com.develop.gleb.deliveryphones.mvp.implementations;

import android.content.Context;
import android.util.Log;
<<<<<<< HEAD
=======

import com.develop.gleb.deliveryphones.IReceivePhoneCallback;
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.helpers.ContactPhoneHelper;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.ReceivePhoneHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
<<<<<<< HEAD
=======

import javax.inject.Inject;

>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ReceivePhonesModel implements IReceivePhonesModel {
    private final String LOG_TAG =  this.getClass().getCanonicalName();
<<<<<<< HEAD
    private IReceivePhonesPresenter presenter;
=======
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private IdHelper idHelper = IdHelper.getInstance();
    private String emailHash = idHelper.getEmailHash();

<<<<<<< HEAD
    public ReceivePhonesModel(IReceivePhonesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void receivePhones() {
=======
    @Inject
    public ReceivePhonesModel() {
    }

    @Override
    public void receivePhones(IReceivePhoneCallback callback) {
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
        Log.d(LOG_TAG, "Receive phones from model");
;
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Subscription subscription = Observable.just(ReceivePhoneHelper.getInstance()).map(i -> i.convertPhones(dataSnapshot))
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
<<<<<<< HEAD
                        .subscribe(i -> presenter.receivePhonesSuccess(i));
=======
                        .subscribe(i -> callback.receivePhonesSuccess(i));
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
                subscriptions.add(subscription);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
<<<<<<< HEAD
                presenter.receivePhonesUnsuccess();;
=======
                callback.receivePhonesUnsuccess();
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
            }
        });
    }

    @Override
<<<<<<< HEAD
    public void savePhones(Context context, List<PhoneEntity> entities) {
        Log.d(LOG_TAG, "Save phones");

        ContactPhoneHelper helper = ContactPhoneHelper.getInstance(context);
        Observable<String> values = helper.savePhones(context, entities, presenter);
=======
    public void savePhones(Context context, List<PhoneEntity> entities, IReceivePhoneCallback callback) {
        Log.d(LOG_TAG, "Save phones");

        ContactPhoneHelper helper = ContactPhoneHelper.getInstance(context);
        Observable<String> values = helper.savePhones(context, entities);
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
        Subscription subscription = values.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
<<<<<<< HEAD
                presenter.savePhonesFinish();
=======
                callback.savePhonesFinish();
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
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
<<<<<<< HEAD
    public void clearPhones() {
        Log.d(LOG_TAG, "Clean phones");

        Subscription subscription = Observable.just(database).map(i -> i.child(emailHash).removeValue())
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(i -> presenter.clearSuccess());
=======
    public void clearPhones(IReceivePhoneCallback callback) {
        Log.d(LOG_TAG, "Clean phones");

        Subscription subscription = Observable.just(database).map(i -> i.child(emailHash).removeValue())
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> callback.clearSuccess());
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
        subscriptions.add(subscription);
    }

    public CompositeSubscription getSubscriptions() {
        return subscriptions;
    }
}

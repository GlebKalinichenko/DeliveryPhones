package com.example.gleb.deliveryphones.mvp.implementations.sendphones;

import android.content.Context;
import android.util.Log;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.helpers.ContactPhoneHelper;
import com.example.gleb.deliveryphones.helpers.IdHelper;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneModel;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SendPhoneModel implements ISendPhoneModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private final static String PHONES = "Phones";
    private ISendPhonePresenter presenter;
    private ContactPhoneHelper helper;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private IdHelper idHelper = IdHelper.getInstance();
    private String emailHash = idHelper.getEmailHash();
    private CompositeSubscription subscriptions = new CompositeSubscription();

    public SendPhoneModel(ISendPhonePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public List<PhoneEntity> getPhones(Context context) {
        Log.d(LOG_TAG, "load phone model");
        helper = ContactPhoneHelper.getInstance(context);
        List<PhoneEntity> entities = helper.rcvCursorPhone();
        return entities;
    }

    @Override
    public void pushPhones(List<PhoneEntity> phones) {
        DatabaseReference res = database.child(emailHash).child(PHONES);
        Subscription subscription = Observable.from(phones).doOnNext(i -> res.push().setValue(i)).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PhoneEntity>() {
            @Override
            public void onCompleted() {
                presenter.responseSync();
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
    public void clearPhones() {
        Subscription subscription = Observable.just(database).map(i -> i.child(emailHash).removeValue()).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(i -> presenter.clearSuccess());
        subscriptions.add(subscription);
    }

    public CompositeSubscription getSubscriptions() {
        return subscriptions;
    }
}

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

public class SendPhoneModel implements ISendPhoneModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private final static String PHONES = "Phones";
    private ISendPhonePresenter presenter;
    private ContactPhoneHelper helper;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();

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
        IdHelper idHelper = IdHelper.getInstance();
        String emailHash = idHelper.getEmailHash();

        DatabaseReference res = database.child(emailHash).child(PHONES);
        for (PhoneEntity entity : phones) {
            res.push().setValue(entity);
        }
        presenter.responseSync();
    }

    @Override
    public List<PhoneEntity> getDatabasePhones() {
        return null;
    }

    @Override
    public void clearPhones() {
        IdHelper idHelper = IdHelper.getInstance();
        String emailHash = idHelper.getEmailHash();

        database.child(emailHash).removeValue();
        presenter.clearSuccess();
    }
}

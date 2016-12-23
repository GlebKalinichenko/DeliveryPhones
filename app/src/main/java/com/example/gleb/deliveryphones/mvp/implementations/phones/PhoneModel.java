package com.example.gleb.deliveryphones.mvp.implementations.phones;

import android.content.Context;
import android.util.Log;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.helpers.ContactPhoneHelper;
import com.example.gleb.deliveryphones.mvp.interfaces.phones.IPhoneModel;
import com.example.gleb.deliveryphones.mvp.interfaces.phones.IPhonePresenter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneModel implements IPhoneModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private final static String PHONES = "Phones";
    private IPhonePresenter presenter;
    private ContactPhoneHelper helper;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public PhoneModel(IPhonePresenter presenter) {
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
        Task<Void> res = database.child(PHONES).push().setValue(phones);
        presenter.responseSync();
    }

    @Override
    public List<PhoneEntity> getDatabasePhones() {
        return null;
    }
}

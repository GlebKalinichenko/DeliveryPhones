package com.example.gleb.deliveryphones.mvp.implementations;

import android.content.Context;
import android.util.Log;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.helpers.ContactPhoneHelper;
import com.example.gleb.deliveryphones.helpers.ReceivePhoneHelper;
import com.example.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesModel;
import com.example.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;

public class ReceivePhonesModel implements IReceivePhonesModel {
    private final String LOG_TAG =  this.getClass().getCanonicalName();
    private IReceivePhonesPresenter presenter;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public ReceivePhonesModel(IReceivePhonesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void receivePhones() {
        Log.d(LOG_TAG, "Receive phones from model");
;
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<PhoneEntity> entities = ReceivePhoneHelper.convertPhones(dataSnapshot);
                presenter.receivePhonesSuccess(entities);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                presenter.receivePhonesUnsuccess();;
            }
        });
    }

    @Override
    public void savePhones(Context context, List<PhoneEntity> entities) {
        ContactPhoneHelper helper = ContactPhoneHelper.getInstance(context);
        helper.savePhones(context, entities);

        presenter.savePhonesFinish();

    }
}

package com.example.gleb.deliveryphones.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.PhonesAdapter;
import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.mvp.interfaces.phones.IPhonePresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.phones.IPhoneView;
import com.example.gleb.deliveryphones.mvp.implementations.phones.PhonePresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;

public class PhonesFragment extends Fragment implements IPhoneView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IPhonePresenter presenter = new PhonePresenter(this);
    private RecyclerView phoneList;
    private Observable<PhoneEntity> phoneObservable;
    private PhonesAdapter adapter;
    private FloatingActionButton syncButton;

    public static PhonesFragment getInstance() {
        PhonesFragment fragment = new PhonesFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phones, container, false);
        initWidgets(view);

        return view;
    }

    @Override
    public void initWidgets(View view) {
        phoneList = (RecyclerView) view.findViewById(R.id.phone_list);
        syncButton = (FloatingActionButton) view.findViewById(R.id.sync_button);

        syncButton.setOnClickListener(i -> {List<PhoneEntity> entities = adapter.getEntities();
            presenter.sendPhones(entities);});
    }

    @Override
    public void responseSync() {
        Log.d(LOG_TAG, "Sync is finished");

        Context context = getActivity();
        Toast.makeText(context, "Sync is finished", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Context context = getActivity();
        List<PhoneEntity> entities = presenter.getPhones(context);
        phoneObservable = Observable.from(entities);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();

        phoneObservable.toList().filter(i -> i.size() > 0) .subscribe(i -> initAdapter(i));
    }

    private void initAdapter(List<PhoneEntity> entities){
        Context context = getActivity();
        adapter = new PhonesAdapter(context, entities);

        LinearLayoutManager lm = new LinearLayoutManager(context);
        phoneList.setLayoutManager(lm);
        phoneList.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}

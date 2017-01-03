package com.example.gleb.deliveryphones.fragments.phones;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.adapters.PhonesAdapter;
import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.fragments.base.BasePhoneFragment;
import com.example.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneView;
import com.example.gleb.deliveryphones.mvp.implementations.sendphones.SendPhonePresenter;

import java.util.List;

import rx.Observable;

public class SendPhonesFragment extends BasePhoneFragment implements ISendPhoneView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ISendPhonePresenter presenter = new SendPhonePresenter(this);
    private RecyclerView phoneList;
    private Observable<PhoneEntity> phoneObservable;
    private PhonesAdapter adapter;
    private FloatingActionButton actionButton;
    private ProgressBar progressBar;
    private SharedPreferencesHelper sharedPreferencesHelper;

    public static SendPhonesFragment getInstance() {
        SendPhonesFragment fragment = new SendPhonesFragment();

        return fragment;
    }

    @Override
    public void initWidgets(View view) {
        phoneList = (RecyclerView) view.findViewById(R.id.phone_list);
        actionButton = (FloatingActionButton) view.findViewById(R.id.action_button);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarReceive);
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(null);

        setButtonDrawable();

        actionButton.setOnClickListener(i -> {List<PhoneEntity> entities = adapter.getEntities();
            presenter.sendPhones(entities);});
    }

    @Override
    protected void setButtonDrawable() {
        actionButton.setImageResource(R.drawable.sync);
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

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferencesHelper.saveFragmentIndex(false);
    }

    private void initAdapter(List<PhoneEntity> entities){
        Context context = getActivity();
        adapter = new PhonesAdapter(context, entities);

        LinearLayoutManager lm = new LinearLayoutManager(context);
        phoneList.setLayoutManager(lm);
        phoneList.setAdapter(adapter);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        sharedPreferencesHelper.saveFragmentIndex(true);
    }
}

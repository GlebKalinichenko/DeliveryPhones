package com.develop.gleb.deliveryphones.fragments.phones;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.develop.gleb.deliveryphones.BaseApplication;
import com.develop.gleb.deliveryphones.entities.PhoneEntity;
import com.develop.gleb.deliveryphones.adapters.PhonesAdapter;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.di.component.SendPhoneFragmentComponent;
import com.develop.gleb.deliveryphones.fragments.base.BasePhoneFragment;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class SendPhoneFragment extends BasePhoneFragment implements ISendPhoneView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    @Inject
    public ISendPhonePresenter presenter;
    @Inject
    public ISendPhoneModel model;
    @Inject
    public SharedPreferencesHelper sharedPreferencesHelper;
    private RecyclerView phoneList;
    private Observable<PhoneEntity> phoneObservable;
    private PhonesAdapter adapter;
    private FloatingActionButton actionButton;
    private ProgressBar progressBar;

    public static SendPhoneFragment getInstance() {
        SendPhoneFragment fragment = new SendPhoneFragment();

        return fragment;
    }

    @Override
    public void initWidgets(View view) {
        phoneList = (RecyclerView) view.findViewById(R.id.phone_list);
        actionButton = (FloatingActionButton) view.findViewById(R.id.action_button);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarReceive);

        setButtonDrawable();

        actionButton.setOnClickListener(i -> sendPhone());
    }

    private void sendPhone(){
        progressBar.setVisibility(View.VISIBLE);
        progressBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<PhoneEntity> entities = adapter.getEntities();
                presenter.sendPhones(entities);
            }
        }, 3000);
    }

    @Override
    protected void setButtonDrawable() {
        actionButton.setImageResource(R.drawable.sync);
    }

    @Override
    public void finishSync() {
        Log.d(LOG_TAG, "Sync is finished");

        progressBar.setVisibility(View.GONE);
        Context context = getActivity();
        Toast.makeText(context, R.string.sync_finish, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearSuccess() {
        Log.d(LOG_TAG, "Clear phones is finished");

        Context context = getActivity();
        Toast.makeText(context, R.string.clean_finish, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "On start");
        Context context = getActivity();
        List<PhoneEntity> entities = presenter.getPhones(context);
        phoneObservable = Observable.from(entities);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "On resume");
        presenter.onResume(phoneObservable);
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "On pause");
        presenter.onPause(sharedPreferencesHelper);
    }

    @Override
    public void initAdapter(List<PhoneEntity> entities){
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

        Log.d(LOG_TAG, "On destroy");
        presenter.onDestroy(sharedPreferencesHelper);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.clear_phones:
                Log.d(LOG_TAG, "Clear phones button is clicked");
                presenter.clearPhones();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initInject() {
        Activity context = getActivity();
        SendPhoneFragmentComponent component = ((BaseApplication) getActivity().getApplication())
                .getSendPhoneFragmentComponent(context, this);
        component.inject(this);
    }
}

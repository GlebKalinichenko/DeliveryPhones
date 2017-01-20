package com.example.gleb.deliveryphones.fragments.phones;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/fragments/phones/SendPhonesFragment.java
import com.example.gleb.deliveryphones.PhoneEntity;
import com.example.gleb.deliveryphones.adapters.PhonesAdapter;
import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.fragments.base.BasePhoneFragment;
import com.example.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneView;
import com.example.gleb.deliveryphones.mvp.implementations.sendphones.SendPhonePresenter;

import java.util.List;

=======
import com.develop.gleb.deliveryphones.BaseApplication;
import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.adapters.PhonesAdapter;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.di.component.SendPhoneFragmentComponent;
import com.develop.gleb.deliveryphones.fragments.base.BasePhoneFragment;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneView;
import com.develop.gleb.deliveryphones.mvp.implementations.sendphones.SendPhonePresenter;
import java.util.List;

import javax.inject.Inject;

>>>>>>> 509e1eb... Refactor send screen by using injections:app/src/main/java/com/develop/gleb/deliveryphones/fragments/phones/SendPhonesFragment.java
import rx.Observable;

public class SendPhonesFragment extends BasePhoneFragment implements ISendPhoneView {
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
    private SharedPreferencesHelper sharedPreferencesHelper;

    public static SendPhonesFragment getInstance() {
        SendPhonesFragment fragment = new SendPhonesFragment();

        return fragment;
    }

    @Override
    public void initWidgets(View view) {
        phoneList = (RecyclerView) view.findViewById(R.id.phone_list);
        actionButton = (FloatingActionButton) view.findViewById(R.id.action_button);
<<<<<<< HEAD
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarReceive);
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(null);

=======
<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/fragments/phones/SendPhonesFragment.java
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
=======
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarReceive);

>>>>>>> 509e1eb... Refactor send screen by using injections:app/src/main/java/com/develop/gleb/deliveryphones/fragments/phones/SendPhonesFragment.java
>>>>>>> 41472d7abb6b32b33e291841c49b24a1b48e9637
        setButtonDrawable();

        actionButton.setOnClickListener(i -> {List<PhoneEntity> entities = adapter.getEntities();
            presenter.sendPhones(entities);});
    }

    @Override
    protected void setButtonDrawable() {
        actionButton.setImageResource(R.drawable.sync);
    }

    @Override
    public void finishSync() {
        Log.d(LOG_TAG, "Sync is finished");

        Context context = getActivity();
        Toast.makeText(context, "Sync is finished", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearSuccess() {
        Log.d(LOG_TAG, "Clear phones is finished");

        Context context = getActivity();
        Toast.makeText(context, "Clear phones is finished", Toast.LENGTH_SHORT).show();
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
        sharedPreferencesHelper.saveDisplayDialogOnChangeOrientation(false);
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
        sharedPreferencesHelper.saveDisplayDialogOnChangeOrientation(true);
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

package com.develop.gleb.deliveryphones;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.develop.gleb.deliveryphones.di.component.MainActivityComponent;
import com.develop.gleb.deliveryphones.events.ReceivePhonesEvent;
import com.develop.gleb.deliveryphones.events.SendPhonesEvent;
import com.develop.gleb.deliveryphones.fragments.phones.ReceivePhonesFragment;
import com.develop.gleb.deliveryphones.fragments.phones.SendPhonesFragment;
import com.develop.gleb.deliveryphones.helpers.AlertHelper;
import com.develop.gleb.deliveryphones.helpers.FragmentHelper;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IBaseView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    @Inject
    public FragmentHelper fragmentHelper;
    @Inject
    public SharedPreferencesHelper sharedPreferencesHelper;
    public static final String IS_FRAGMENT_DIALOG = "IsFragmentDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInject();
        if (sharedPreferencesHelper.isDisplayChooseDialog())
            loadAlertConfiguration();
    }

    /*
    * Display alert for choosing user way.
    * */
    private void loadAlertConfiguration(){
        Log.d(LOG_TAG, "Alert is displayed");

        AlertDialog dialog = AlertHelper.createDialog(this, R.style.AlertDialogConfiguration);
        dialog.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        org.greenrobot.eventbus.EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        org.greenrobot.eventbus.EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void sendPhonesEvent(SendPhonesEvent event){
        SendPhonesFragment fragment = SendPhonesFragment.getInstance();
        fragmentHelper.loadFragment(this, R.id.container_phones, fragment);
    }

    @Subscribe
    public void receivePhoneEvent(ReceivePhonesEvent event){
        ReceivePhonesFragment fragment = ReceivePhonesFragment.getInstance();
        fragmentHelper.loadFragment(this, R.id.container_phones, fragment);
    }

    @Override
    public void initInject() {
        MainActivityComponent component = ((BaseApplication) getApplication())
                .getMainActivityComponent(this);
        component.inject(this);
    }
}

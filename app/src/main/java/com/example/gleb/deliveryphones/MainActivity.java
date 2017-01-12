package com.example.gleb.deliveryphones;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gleb.deliveryphones.dependencyinjection.MainActivityComponent;
import com.example.gleb.deliveryphones.events.ReceivePhonesEvent;
import com.example.gleb.deliveryphones.events.SendPhonesEvent;
import com.example.gleb.deliveryphones.fragments.phones.ReceivePhonesFragment;
import com.example.gleb.deliveryphones.fragments.phones.SendPhonesFragment;
import com.example.gleb.deliveryphones.helpers.AlertHelper;
import com.example.gleb.deliveryphones.helpers.FragmentHelper;
import com.example.gleb.deliveryphones.helpers.SharedPreferencesHelper;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
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

    private void initInject(){
        MainActivityComponent component =((BaseApplication) getApplication()).returnMainActivityComponent(this);
        component.inject(this);
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
}

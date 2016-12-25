package com.example.gleb.deliveryphones;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gleb.deliveryphones.events.ReceivePhonesEvent;
import com.example.gleb.deliveryphones.events.SendPhonesEvent;
import com.example.gleb.deliveryphones.fragments.SendPhonesFragment;
import com.example.gleb.deliveryphones.helpers.AlertHelper;
import com.example.gleb.deliveryphones.helpers.FragmentHelper;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private FragmentHelper fragmentHelper = FragmentHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}

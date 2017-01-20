package com.develop.gleb.deliveryphones;

<<<<<<< HEAD
import android.content.SharedPreferences;
=======
<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/MainActivity.java
=======
import android.app.Activity;
import android.content.SharedPreferences;
>>>>>>> d9671cc... Refactor main activity by using di:app/src/main/java/com/develop/gleb/deliveryphones/MainActivity.java
>>>>>>> 41472d7abb6b32b33e291841c49b24a1b48e9637
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

<<<<<<< HEAD:app/src/main/java/com/develop/gleb/deliveryphones/MainActivity.java
=======
<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/MainActivity.java
import com.example.gleb.deliveryphones.events.ReceivePhonesEvent;
import com.example.gleb.deliveryphones.events.SendPhonesEvent;
import com.example.gleb.deliveryphones.fragments.phones.ReceivePhonesFragment;
import com.example.gleb.deliveryphones.fragments.phones.SendPhonesFragment;
import com.example.gleb.deliveryphones.helpers.AlertHelper;
import com.example.gleb.deliveryphones.helpers.FragmentHelper;
<<<<<<< HEAD
import com.example.gleb.deliveryphones.helpers.SharedPreferencesHelper;
=======
=======
import com.develop.gleb.deliveryphones.di.component.MainActivityComponent;
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779:app/src/main/java/com/example/gleb/deliveryphones/MainActivity.java
import com.develop.gleb.deliveryphones.events.ReceivePhonesEvent;
import com.develop.gleb.deliveryphones.events.SendPhonesEvent;
import com.develop.gleb.deliveryphones.fragments.phones.ReceivePhonesFragment;
import com.develop.gleb.deliveryphones.fragments.phones.SendPhonesFragment;
import com.develop.gleb.deliveryphones.helpers.AlertHelper;
import com.develop.gleb.deliveryphones.helpers.FragmentHelper;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;
<<<<<<< HEAD:app/src/main/java/com/develop/gleb/deliveryphones/MainActivity.java
=======
>>>>>>> d9671cc... Refactor main activity by using di:app/src/main/java/com/develop/gleb/deliveryphones/MainActivity.java
>>>>>>> 41472d7abb6b32b33e291841c49b24a1b48e9637
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779:app/src/main/java/com/example/gleb/deliveryphones/MainActivity.java

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IBaseView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/MainActivity.java
    private FragmentHelper fragmentHelper = FragmentHelper.getInstance(this);
<<<<<<< HEAD
    private SharedPreferencesHelper sharedPreferencesHelper;
    public static final String IS_FRAGMENT_DIALOG = "IsFragmentDialog";
=======
=======
    @Inject
    public FragmentHelper fragmentHelper;
    @Inject
    public SharedPreferencesHelper sharedPreferencesHelper;
    public static final String IS_FRAGMENT_DIALOG = "IsFragmentDialog";
>>>>>>> d9671cc... Refactor main activity by using di:app/src/main/java/com/develop/gleb/deliveryphones/MainActivity.java
>>>>>>> 41472d7abb6b32b33e291841c49b24a1b48e9637

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        SharedPreferences sharedPreferences = getSharedPreferences(IS_FRAGMENT_DIALOG, MODE_PRIVATE);
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(sharedPreferences);
        if (sharedPreferencesHelper.isDisplayChooseDialog())
            loadAlertConfiguration();
=======
<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/MainActivity.java
        loadAlertConfiguration();
=======
        initInject();
        if (sharedPreferencesHelper.isDisplayChooseDialog())
            loadAlertConfiguration();
>>>>>>> d9671cc... Refactor main activity by using di:app/src/main/java/com/develop/gleb/deliveryphones/MainActivity.java
>>>>>>> 41472d7abb6b32b33e291841c49b24a1b48e9637
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

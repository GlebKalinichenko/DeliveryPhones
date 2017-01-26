package com.develop.gleb.deliveryphones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.develop.gleb.deliveryphones.di.component.MainActivityComponent;
import com.develop.gleb.deliveryphones.entities.ModeEntity;
import com.develop.gleb.deliveryphones.events.ReceivePhonesEvent;
import com.develop.gleb.deliveryphones.events.SendPhonesEvent;
import com.develop.gleb.deliveryphones.events.SendPhotosEvent;
import com.develop.gleb.deliveryphones.fragments.mode.ModeFragment;
import com.develop.gleb.deliveryphones.fragments.phones.ReceivePhonesFragment;
import com.develop.gleb.deliveryphones.fragments.phones.SendPhonesFragment;
import com.develop.gleb.deliveryphones.fragments.photo.SendPhotoFragment;
import com.develop.gleb.deliveryphones.helpers.FragmentHelper;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IBaseView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    @Inject
    public FragmentHelper fragmentHelper;
    @Inject
    public SharedPreferencesHelper sharedPreferencesHelper;
    @Inject
    public List<ModeEntity> entities;
    public static final String IS_FRAGMENT_DIALOG = "IsFragmentDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInject();
        loadModeFragment();
    }

    /**
    * Load fragments with modes that user can choose.
    * */
    private void loadModeFragment(){
        ModeFragment fragment = ModeFragment.getInstance(entities);
        fragmentHelper.loadFragment(this, R.id.container_modes, fragment);
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
        fragmentHelper.replaceFragment(this, R.id.container_modes, fragment);
    }

    @Subscribe
    public void receivePhoneEvent(ReceivePhonesEvent event){
        ReceivePhonesFragment fragment = ReceivePhonesFragment.getInstance();
        fragmentHelper.replaceFragment(this, R.id.container_modes, fragment);
    }

    @Subscribe
    public void sendPhotosEvent(SendPhotosEvent event){
        SendPhotoFragment fragment = SendPhotoFragment.getInstance();
        fragmentHelper.replaceFragment(this, R.id.container_modes, fragment);
    }

    @Override
    public void initInject() {
        MainActivityComponent component = ((BaseApplication) getApplication())
                .getMainActivityComponent(this);
        component.inject(this);
    }
}

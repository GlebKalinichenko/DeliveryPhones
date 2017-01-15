package com.develop.gleb.deliveryphones;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.develop.gleb.deliveryphones.adapters.SignInUpFragmentPagerAdapter;
import com.develop.gleb.deliveryphones.di.component.LoginActivityComponent;
import com.develop.gleb.deliveryphones.events.AllowPermissionEvent;
import com.develop.gleb.deliveryphones.events.SignUpEvent;
import com.develop.gleb.deliveryphones.events.SwitchToSignUpEvent;
import com.develop.gleb.deliveryphones.fragments.sign.SignInFragment;
import com.develop.gleb.deliveryphones.fragments.sign.SignUpFragment;
import com.develop.gleb.deliveryphones.helpers.ApiHelper;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.PermissionHelper;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        IBaseView{
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ViewPager viewPager;
    private SignInUpFragmentPagerAdapter adapter;
    @Inject
    public SharedPreferencesHelper sharedPreferencesHelper;
    @Inject
    public PermissionHelper permissionHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EventBus.getDefault().register(this);
        viewPager = (ViewPager) findViewById(R.id.container_login);
        initInject();
        checkPermissions();
        clearChooseDialog();
    }

    /**
     * Clear identifier for show choosing dialog
     * */
    private void clearChooseDialog(){
        sharedPreferencesHelper.saveDisplayDialogOnChangeOrientation(true);
    }

    private void checkPermissions(){
        if (ApiHelper.checkApiVersionOlderMarshmallow()){
            permissionHelper.checkPermissions();
        }
        else {
            initializeSign();
        }
    }

    private void initializeSign() {
        SignInFragment signInFragment = SignInFragment.getInstance();
        SignUpFragment signUpFragment = SignUpFragment.getInstance();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(signInFragment);
        fragments.add(signUpFragment);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new SignInUpFragmentPagerAdapter(fm, fragments);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void signUpEvent(SignUpEvent event){
        String emailHash = event.getEmailHash();
        IdHelper idHelper = IdHelper.getInstance();
        idHelper.setEmailHash(emailHash);

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Subscribe
    public void allowPermissionEvent(AllowPermissionEvent event){
        initializeSign();
    }

    @Subscribe
    public void switchSignUpFragment(SwitchToSignUpEvent event){
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d(LOG_TAG, "Read permission is allowed");
                    initializeSign();
                }
                else{
                    Log.d(LOG_TAG, "Read permission is denied");
                }
                break;

        }

    }

    @Override
    public void initInject() {
        LoginActivityComponent component =((BaseApplication) getApplication()).getLoginActivityComponent(this);
        component.inject(this);
    }
}

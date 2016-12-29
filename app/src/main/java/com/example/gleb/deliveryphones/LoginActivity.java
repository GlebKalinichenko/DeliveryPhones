package com.example.gleb.deliveryphones;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.gleb.deliveryphones.adapters.SignInUpFragmentPagerAdapter;
import com.example.gleb.deliveryphones.events.SignUpEvent;
import com.example.gleb.deliveryphones.fragments.sign.SignInFragment;
import com.example.gleb.deliveryphones.fragments.sign.SignUpFragment;
import com.example.gleb.deliveryphones.helpers.FragmentHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private FirebaseAuth firebaseAuth;
    private FragmentHelper fragmentHelper = FragmentHelper.getInstance(this);
    private ViewPager viewPager;
    private SignInUpFragmentPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewPager = (ViewPager) findViewById(R.id.container_login);
        initializeSign();
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
        //initGoogleSignIn();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initGoogleSignIn(){
        firebaseAuth = FirebaseAuth.getInstance();

//        String email = "Glebjn@gmail.com";
//        String password = "Gleb80507078620";
//
//        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(LoginActivity.this, "Unsuccessful", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    @Subscribe
    public void signUpEvent(SignUpEvent event){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

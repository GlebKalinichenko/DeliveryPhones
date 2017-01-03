package com.example.gleb.deliveryphones.java;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gleb.deliveryphones.BuildConfig;
import com.example.gleb.deliveryphones.LoginActivity;
import com.example.gleb.deliveryphones.MainActivity;
import com.example.gleb.deliveryphones.fragments.phones.ReceivePhonesFragment;
import com.example.gleb.deliveryphones.fragments.sign.SignInFragment;
import com.example.gleb.deliveryphones.helpers.SharedPreferencesHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SharedPreferencesTest {

    @Test
    public void testSharedPreferences(){
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        SharedPreferences sharedPreferences = activity.getSharedPreferences(MainActivity.IS_FRAGMENT_DIALOG, Context.MODE_PRIVATE);
        SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance(sharedPreferences);
        boolean isFragment = helper.isDisplayChooseDialog();
        Assert.assertEquals(isFragment, true);
    }

}

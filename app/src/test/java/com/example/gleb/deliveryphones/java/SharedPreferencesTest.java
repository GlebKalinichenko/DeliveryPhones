package com.develop.gleb.deliveryphones.java;

import android.content.Context;
import android.content.SharedPreferences;

import com.develop.gleb.deliveryphones.BuildConfig;
import com.develop.gleb.deliveryphones.LoginActivity;
import com.develop.gleb.deliveryphones.MainActivity;
import com.develop.gleb.deliveryphones.helpers.SharedPreferencesHelper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

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

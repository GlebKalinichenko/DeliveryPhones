package com.develop.gleb.deliveryphones.java;

import com.develop.gleb.deliveryphones.BuildConfig;
import com.develop.gleb.deliveryphones.MainActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    @Test
    public void testMainActivity(){
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        Assert.assertNotNull(activity);
    }
}

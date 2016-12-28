package com.example.gleb.deliveryphones.java;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import com.example.gleb.deliveryphones.BuildConfig;
import com.example.gleb.deliveryphones.LoginActivity;
import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.fragments.sign.SignInFragment;
import com.google.firebase.auth.FirebaseAuth;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginTest {

    @Test
    public void testLoginWidgets(){
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        ViewPager viewPager = (ViewPager) activity.findViewById(R.id.container_login);
        Assert.assertNotNull(viewPager);
    }

    @Test
    public void testClickOpenPhonesActivity(){
        SignInFragment fragment = SignInFragment.getInstance();
        SupportFragmentTestUtil.startFragment(fragment, LoginActivity.class);

        View view = fragment.getView();
        Button signInButton = (Button) view.findViewById(R.id.sign_in_button);
        boolean isClicked = signInButton.performClick();

        org.junit.Assert.assertEquals(true, isClicked);
    }
}

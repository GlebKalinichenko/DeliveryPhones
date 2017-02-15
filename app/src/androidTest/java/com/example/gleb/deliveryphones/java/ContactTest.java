package com.develop.gleb.deliveryphones.java;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.develop.gleb.deliveryphones.MainActivity;
import com.develop.gleb.deliveryphones.entities.PhoneEntity;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.helpers.ContactPhoneHelper;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContactTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testGetPhoneContact(){
        MainActivity context = activityRule.getActivity();
        ContactPhoneHelper helper = ContactPhoneHelper.getInstance(context);
        List<PhoneEntity> entityList = helper.rcvCursorPhone();
        Assert.assertNotNull(entityList);
    }

    @Test
    public void testPhoneListContact(){
        MainActivity context = activityRule.getActivity();
        RecyclerView phoneList = (RecyclerView) context.findViewById(R.id.phone_list);
        Assert.assertNull(phoneList);
    }
}

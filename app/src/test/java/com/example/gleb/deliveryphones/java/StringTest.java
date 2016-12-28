package com.example.gleb.deliveryphones.java;

import com.example.gleb.deliveryphones.helpers.StringHelper;

import org.junit.Assert;
import org.junit.Test;

public class StringTest {

    @Test
    public void phoneFormatTest(){
        String etalonPhone = "12-334-34-45-46";
        String phone = "[12-334-34-45-46]";

        phone = StringHelper.clearPhonesFromBrackets(phone);
        Assert.assertEquals(etalonPhone, phone);
    }
}

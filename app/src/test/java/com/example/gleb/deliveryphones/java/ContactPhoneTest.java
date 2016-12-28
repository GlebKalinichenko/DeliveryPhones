package com.example.gleb.deliveryphones.java;

import com.example.gleb.deliveryphones.PhoneEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactPhoneTest {

    @Test
    public void assertNumOfPhoneNumbers(){
        List phoneList = mock(List.class);
        when(phoneList.get(0)).thenReturn(new PhoneEntity("Name", null));

        Assert.assertEquals("Name", ((PhoneEntity) phoneList.get(0)).getName());
    }
}

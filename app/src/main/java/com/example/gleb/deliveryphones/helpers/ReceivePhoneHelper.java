package com.example.gleb.deliveryphones.helpers;

import android.util.Log;

import com.example.gleb.deliveryphones.PhoneEntity;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReceivePhoneHelper {
    private static final String LOG_TAG = ReceivePhoneHelper.class.getCanonicalName();
    private static final String ROOT_TAG = "Phones";

    /**
     * Receive list of phones from database.
     * @param dataSnapshot        Object of database
     * @return                    List of phones
     * */
    public static List<PhoneEntity> convertPhones(DataSnapshot dataSnapshot){
        Log.d(LOG_TAG, "Convert list of phones from database");

        List<PhoneEntity> entities = new ArrayList<PhoneEntity>();

        if (!ApiHelper.checkApiVersionJellyBean()) {
            Map<String, Object> objectMap = (HashMap<String, Object>) dataSnapshot.child(ROOT_TAG).getValue();
            Collection<Object> mapObject = objectMap.values();

            Iterator<Object> it = mapObject.iterator();
            while (it.hasNext()) {
                HashMap obj = (HashMap) it.next();
                Collection<HashMap> itemsPerson = obj.values();
                Iterator<HashMap> itemsPersonIter = itemsPerson.iterator();

                String name = "";
                List<String> phones = new ArrayList<String>();

                if (!ApiHelper.checkApiVersionJellyBean()) {
                    for (int i = 0; itemsPersonIter.hasNext(); i++) {
                        if (i == 0)
                            name = String.valueOf(itemsPersonIter.next());
                        else {
                            String phone = String.valueOf(itemsPersonIter.next());
                            phone = StringHelper.clearPhonesFromBrackets(phone);
                            phones.add(phone);
                        }
                    }
                }
                else{
                    for (int i = 0; itemsPersonIter.hasNext(); i++) {
                        String entityString = String.valueOf(itemsPersonIter.next());
                        if (StringHelper.hasBrackets(entityString)) {
                            String phone = StringHelper.clearPhonesFromBrackets(entityString);
                            phones.add(phone);
                        }
                        else
                            name = entityString;
                    }
                }

                PhoneEntity entity = new PhoneEntity(name, phones);
                entities.add(entity);
            }
        }

        return entities;
    }
}

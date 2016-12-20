package com.example.gleb.deliveryphones;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class ContactPhoneHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private static ContactPhoneHelper instance = null;
    private Context context;

    public static ContactPhoneHelper getInstance(Context context) {
        if (instance == null){
            instance = new ContactPhoneHelper(context);
        }
        return instance;
    }

    private ContactPhoneHelper(Context context) {
        this.context = context;
    }

    public List<PhoneEntity> rcvCursorPhone(){
        List<PhoneEntity> entites = new ArrayList<>();
        List<String> phones = new ArrayList<>();

        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                boolean hasPhone = (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) ? true : false ;

                if (hasPhone){
                    Cursor phoneCursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);


                    while (phoneCursor.moveToNext()){
                        String phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phones.add(phone);
                    }

                    PhoneEntity entity = new PhoneEntity(contactName, phones);
                    entites.add(entity);

                    phoneCursor.close();
                }

            }
        }

        return entites;

    }
}

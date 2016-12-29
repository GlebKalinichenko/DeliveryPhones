package com.example.gleb.deliveryphones.helpers;

import android.Manifest;
import android.accounts.AccountManager;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;

import com.example.gleb.deliveryphones.PhoneEntity;

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

    /**
     * Receive phones from media database on device.
     * @return        List of phones from device
     * */
    public List<PhoneEntity> rcvCursorPhone(){
        List<PhoneEntity> entites = new ArrayList<>();

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

                    List<String> phones = new ArrayList<>();

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

    /**
    * Save phones that was received from backend database.
    * @param entities        List of received phones
    * */
    public void savePhones(Context activity, List<PhoneEntity> entities){
        for (PhoneEntity entity : entities) {
            String name = entity.getName();
            for (String phone : entity.getPhones()) {
                ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
                int rawContactInsertIndex = ops.size();

                ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, AccountManager.KEY_ACCOUNT_TYPE)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, AccountManager.KEY_ACCOUNT_NAME)
                        .build());

                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                        .build());

                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
                        .build());


                try {
                    activity.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

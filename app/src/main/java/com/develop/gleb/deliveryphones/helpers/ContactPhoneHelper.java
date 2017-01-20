package com.develop.gleb.deliveryphones.helpers;

import android.accounts.AccountManager;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;

import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephones.IReceivePhonesPresenter;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;

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
        Log.d(LOG_TAG, "Receive contact's cursor");

        List<PhoneEntity> entites = new ArrayList<>();

        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                boolean hasPhone = (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) ? true : false;

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
<<<<<<< HEAD
    public Observable<String> savePhones(Context activity, List<PhoneEntity> entities, IReceivePhonesPresenter presenter){
=======
    public Observable<String> savePhones(Context activity, List<PhoneEntity> entities){
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779
        Log.d(LOG_TAG, "Save phones to storage");

        final String[] name = {""};
        return Observable.from(entities).doOnNext(i -> name[0] = i.getName()).flatMap(i -> Observable.from(i.getPhones()))
                .doOnNext(i -> formatPhoneSave(name[0], i, activity));
    }

    /**
    * Formatting phone to save to storage
    * @param name        User name
    * @param phone       Phone number
    * @param activity    Context of activity
    * */
    private void formatPhoneSave(String name, String phone, Context activity){
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

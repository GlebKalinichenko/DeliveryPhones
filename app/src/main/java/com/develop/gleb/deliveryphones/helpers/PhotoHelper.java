package com.develop.gleb.deliveryphones.helpers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

import rx.Observable;

public class PhotoHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private Context context;
    private PhotoHelper instance = null;

    public PhotoHelper getInstance(Context context) {
        if (instance == null)
            instance = new PhotoHelper(context);
        return instance;
    }

    private PhotoHelper(Context context) {
        this.context = context;
    }

    public void rcvPhoneContacts(){
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projections = {MediaStore.Images.Media.DATA};
        Observable.just(context.getContentResolver()).map(i -> i.query(imageUri, projections, null,
                null, null)).filter(i -> i.moveToNext() && i != null)
                .map(i -> i.getString(i.getColumnIndex(MediaStore.Images.Media.DATA)))
                .reduce(new ArrayList<String>(), (arr, val) -> {arr.add(val); return arr;});
    }

}

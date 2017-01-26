package com.develop.gleb.deliveryphones.helpers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.develop.gleb.deliveryphones.entities.PhoneEntity;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class PhotoHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private Context context;
    private static PhotoHelper instance = null;

    public static PhotoHelper getInstance(Context context) {
        if (instance == null)
            instance = new PhotoHelper(context);
        return instance;
    }

    private PhotoHelper(Context context) {
        this.context = context;
    }

    public Observable<List<PhotoEntity>> rcvPhoneContacts(){
/*        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projections = {MediaStore.Images.Media.DATA};
        Observable.just(context.getContentResolver())
                .map(i -> i.query(imageUri, projections, null, null, null))
                .takeWhile(i -> !i.isLast())
                .doOnNext(i -> Log.d(LOG_TAG, i.toString()));*/

        List<PhotoEntity> entities = new ArrayList<>();
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projections = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(imageUri, projections, null, null, null);
        while (cursor != null && cursor.moveToNext()){
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            PhotoEntity entity = new PhotoEntity(path);
            entities.add(entity);
        }

        return Observable.just(entities);
    }

}

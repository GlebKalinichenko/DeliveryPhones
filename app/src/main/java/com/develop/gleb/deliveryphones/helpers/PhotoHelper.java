package com.develop.gleb.deliveryphones.helpers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.develop.gleb.deliveryphones.entities.PhoneEntity;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.google.firebase.database.DataSnapshot;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Observable;

public class PhotoHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private final String PHOTOS_COUNT = "PhotosCount";
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

    public Observable<List<PhotoEntity>> rcvPhotosFromPicturesFolder(){
        List<PhotoEntity> photos = new ArrayList<>();
        File pictureFolders = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File[] files = pictureFolders.listFiles();
        for (File file : files){
            String path = file.getAbsolutePath();
            photos.add(new PhotoEntity(path));
        }

        return Observable.just(photos);
    }

    public int convertPhotosSize(DataSnapshot dataSnapshot, String emailHash){
        List<PhoneEntity> entities = new ArrayList<PhoneEntity>();
        Map<String, Object> objectMap = (HashMap<String, Object>) dataSnapshot.child(emailHash).child(PHOTOS_COUNT).getValue();
        int size = 0;

        if (objectMap != null) {
            Collection<Object> mapObject = objectMap.values();
            Object[] obj = mapObject.toArray();
            String value = obj[0].toString();
            size = Integer.valueOf(value);
        }

        return size;
    }

}

package com.develop.gleb.deliveryphones.mvp.implementations.sendphotos;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import com.develop.gleb.deliveryphones.callbacks.ISendPhotoCallback;
import com.develop.gleb.deliveryphones.callbacks.IUploadPhotosCallback;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.PhotoHelper;
import com.develop.gleb.deliveryphones.helpers.StringHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javax.inject.*;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SendPhotosModel implements ISendPhotoModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private final String SLASH = "/";
    private final String PHOTOS = "Photos";
    @Inject
    public PhotoHelper photoHelper;
    @Inject
    public IdHelper idHelper;
    private String emailHash;
    @Inject
    public StorageReference storageReference;

    @Inject
    public SendPhotosModel(PhotoHelper photoHelper, IdHelper idHelper,
                           StorageReference storageReference) {
        this.photoHelper = photoHelper;
        this.idHelper = idHelper;
        this.storageReference = storageReference;
    }

    @Override
    public void getPhonesPhotos(ISendPhotoCallback callback) {
        Log.d(LOG_TAG, "Receive photos is started");
        Observable<List<PhotoEntity>> photosObservable = photoHelper.rcvPhoneContacts();
        photosObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(i -> callback.success(i),
                e -> callback.unsuccess());
    }

    @Override
    public void savePhotos(List<PhotoEntity> photos, IUploadPhotosCallback callback) {
  /*      final boolean[] isSuccessSequence = {true};
        emailHash = idHelper.getEmailHash();
        for (PhotoEntity photo : photos) {
            String filePath = StringHelper.validPathFile(photo.getPath());
            StorageReference reference = storageReference.child(emailHash).child(PHOTOS + SLASH + filePath);
            InputStream stream = null;
            try {
                stream = new FileInputStream(new File(photo.getPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            UploadTask uploadTask = reference.putStream(stream);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.d(LOG_TAG, "File saved is failed");
                    isSuccessSequence[0] = false;
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d(LOG_TAG, "File is saved");
                }
            });
        }


        if (isSuccessSequence[0] == true)
            callback.uploadSuccess();
        else
            callback.uploadUnsuccess();*/

        emailHash = idHelper.getEmailHash();
        Observable<PhotoEntity> photosObservable = Observable.from(photos);
        Observable<StorageReference> storageObservable = Observable.from(photos).map(i -> StringHelper.validPathFile(i.getPath()))
                .map(i -> storageReference.child(emailHash).child(PHOTOS + SLASH + i));


        Observable.zip(photosObservable, storageObservable, (photo, storageReference) -> {
            final boolean[] isSuccess = {true};
            InputStream stream = null;
            try {
                stream = new FileInputStream(new File(photo.getPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            UploadTask uploadTask = storageReference.putStream(stream);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.d(LOG_TAG, "File saved is failed");
                    isSuccess[0] = false;
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d(LOG_TAG, "File is saved");
                }
            });
            return isSuccess[0];
        }).filter(i -> i == true).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(i -> callback.uploadSuccess());

    }
}

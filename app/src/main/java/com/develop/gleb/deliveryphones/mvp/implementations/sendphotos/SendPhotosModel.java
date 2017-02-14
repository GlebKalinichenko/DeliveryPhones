package com.develop.gleb.deliveryphones.mvp.implementations.sendphotos;

import android.util.Log;

import com.develop.gleb.deliveryphones.callbacks.IReceivePhotosCallback;
import com.develop.gleb.deliveryphones.callbacks.ISendPhotoCallback;
import com.develop.gleb.deliveryphones.callbacks.IUploadPhotosCallback;
import com.develop.gleb.deliveryphones.entities.PhoneEntity;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.PhotoHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphotos.ISendPhotoModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javax.inject.*;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SendPhotosModel implements ISendPhotoModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private final String SLASH = "/";
    private final String PHOTOS = "Photos";
    private final String PHOTOS_COUNT = "PhotosCount";
    private CompositeSubscription subscriptions = new CompositeSubscription();
    @Inject
    public PhotoHelper photoHelper;
    @Inject
    public IdHelper idHelper;
    private String emailHash;
    @Inject
    public StorageReference storageReference;
    @Inject
    public DatabaseReference database;
    private boolean isChangeValue = true;

    @Inject
    public SendPhotosModel(PhotoHelper photoHelper, IdHelper idHelper,
                           StorageReference storageReference, DatabaseReference database) {
        this.photoHelper = photoHelper;
        this.idHelper = idHelper;
        this.storageReference = storageReference;
        this.database = database;
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
        emailHash = idHelper.getEmailHash();
        int size = photos.size();
        sendPhotosCount(size);
        Observable<PhotoEntity> photosObservable = Observable.from(photos);
        Observable<Integer>  indexObservable = Observable.range(0, size);
        Observable<StorageReference> storageObservable = Observable.zip(photosObservable, indexObservable, (photo, index) -> {
            return storageReference.child(emailHash).child(PHOTOS + SLASH + index);
        });

        Subscription subscription = Observable.zip(photosObservable, storageObservable, (photo, storageReference) -> {
            final boolean[] isSuccess = {true};
            InputStream stream = null;
            try {
                stream = new FileInputStream(new File(photo.getPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            UploadTask uploadTask = storageReference.putStream(stream);
            uploadTask.addOnFailureListener(i -> {Log.d(LOG_TAG, "File saved is failed");
                isSuccess[0] = false;}, e -> {Log.d(LOG_TAG, "File is saved");});
            return isSuccess[0];
        }).filter(i -> i == true).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        callback.uploadSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public CompositeSubscription getSubscriptions() {
        return subscriptions;
    }

    @Override
    public void initClearPhotos(ISendPhotoCallback callback) {
        emailHash = idHelper.getEmailHash();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int size = photoHelper.convertPhotosSize(dataSnapshot, emailHash);
                if (size != 0)
                    clearPhotos(size, callback);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void clearPhotos(int size, ISendPhotoCallback callback) {
        Observable<Integer> numPhotosObservable = Observable.range(0, size);
        Subscription subscription = numPhotosObservable.map(i -> {
            storageReference = storageReference.child(emailHash).child(PHOTOS).child(String.valueOf(i));
            storageReference.delete().addOnSuccessListener(s -> {}, e -> {});
            storageReference = storageReference.getRoot();

            return storageReference;
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StorageReference>() {
                    @Override
                    public void onCompleted() {
                        database.child(emailHash).child(PHOTOS_COUNT).removeValue();
                        database = database.getRoot();
                        database.child(emailHash).child(PHOTOS_COUNT).push().setValue(0);
                        storageReference = storageReference.getRoot();
                        callback.clearSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.clearUnsuccess();
                    }

                    @Override
                    public void onNext(StorageReference storageReference) {

                    }
                });
        subscriptions.add(subscription);
    }

    private void sendPhotosCount(int size) {
        database.child(emailHash).child(PHOTOS_COUNT).removeValue();
        database = database.getRoot();
        DatabaseReference res = database.child(emailHash).child(PHOTOS_COUNT);
        Subscription subscription = Observable.just(size).doOnNext(i -> res.push().setValue(i)).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).subscribe();
        subscriptions.add(subscription);
    }
}

package com.develop.gleb.deliveryphones.mvp.implementations.receivephotos;

import android.os.Environment;
import android.util.Log;

import com.develop.gleb.deliveryphones.callbacks.IReceivePhotosCallback;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.PhotoHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.receivephotos.IReceivePhotosModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.*;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ReceivePhotosModel implements IReceivePhotosModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private final String PHOTOS = "Photos";
    private final String PHOTOS_COUNT = "PhotosCount";
    private final String EXTENSION = ".jpg";
    @Inject
    public PhotoHelper photoHelper;
    @Inject
    public IdHelper idHelper;
    @Inject
    public StorageReference storageReference;
    @Inject
    public DatabaseReference database;
    private String emailHash;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Inject
    public ReceivePhotosModel(IdHelper idHelper, StorageReference storageReference, PhotoHelper photoHelper,
                              DatabaseReference database) {
        this.idHelper = idHelper;
        this.storageReference = storageReference;
        this.photoHelper = photoHelper;
        this.database = database;
    }

    @Override
    public void rcvPhotos(IReceivePhotosCallback callback) {
        emailHash = idHelper.getEmailHash();
        rcvPhotosCount(callback);
    }

    private void rcvPhotosCount(IReceivePhotosCallback callback){
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int size = photoHelper.convertPhotosSize(dataSnapshot, emailHash);
                if (size != 0)
                    rcvPhotosEntities(size, callback);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void rcvPhotosEntities(int size, IReceivePhotosCallback callback) {
        List<Boolean> isContinues = new ArrayList<>();
        for (int i = 0; i < size; i++){
            isContinues.add(false);
        }
        Observable<Integer> numPhotosObservable = Observable.range(0, size);
        Subscription subscription = numPhotosObservable.map(i -> {
            storageReference = storageReference.child(emailHash).child(PHOTOS).child(String.valueOf(i));
            File localFile = null;
            localFile = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), i + EXTENSION);

            storageReference.getFile(localFile).addOnSuccessListener(s -> {
                Log.d(LOG_TAG, "Download success");
                isContinues.set(i, true);
            }, e -> Log.d(LOG_TAG, "Download unsuccess"));
            storageReference = storageReference.getRoot();

            return localFile;
        }).filter(i -> {
            boolean res = true;
            for (Boolean isContinue: isContinues) {
                if (!isContinue) {
                    res = false;
                    break;
                }
            }
            return res;
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<File>() {
                    @Override
                    public void onCompleted() {
                        callback.downloadSuccess(size);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(File file) {

                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void getPhonesPhotos(IReceivePhotosCallback callback, int size) {
        Log.d(LOG_TAG, "Receive photos is started");
        try {
            Thread.sleep((long) (size / 3 * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Observable<List<PhotoEntity>> photosObservable = photoHelper.rcvPhotosFromPicturesFolder();
        photosObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(i -> callback.loadPhotosSuccess(i),
                e -> callback.loadPhotosUnsuccess());
    }

    @Override
    public CompositeSubscription getCompositeSubscription() {
        return compositeSubscription;
    }

    @Override
    public void initClearPhotos(IReceivePhotosCallback callback) {
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
    public void clearPhotos(int size, IReceivePhotosCallback callback) {
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
        compositeSubscription.add(subscription);
    }
}

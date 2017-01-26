package com.develop.gleb.deliveryphones.mvp.implementations.sendphotos;

import android.util.Log;
import com.develop.gleb.deliveryphones.callbacks.ISendPhotoCallback;
import com.develop.gleb.deliveryphones.callbacks.IUploadPhotosCallback;
import com.develop.gleb.deliveryphones.entities.PhotoEntity;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.PhotoHelper;
import com.develop.gleb.deliveryphones.helpers.StringHelper;
import com.develop.gleb.deliveryphones.mvp.interfaces.photo.ISendPhotoModel;
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
    private CompositeSubscription subscriptions = new CompositeSubscription();
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
        emailHash = idHelper.getEmailHash();
        Observable<PhotoEntity> photosObservable = Observable.from(photos);
        Observable<StorageReference> storageObservable = Observable.from(photos).map(i -> StringHelper.validPathFile(i.getPath()))
                .map(i -> storageReference.child(emailHash).child(PHOTOS + SLASH + i));

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
}

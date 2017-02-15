package com.develop.gleb.deliveryphones.di.module;

import android.app.Activity;
import com.develop.gleb.deliveryphones.FirebaseApi;
import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.entities.ModeEntity;
import com.develop.gleb.deliveryphones.entities.ModeIdentifier;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.helpers.FragmentHelper;
import com.develop.gleb.deliveryphones.helpers.PhotoHelper;
import com.develop.gleb.deliveryphones.helpers.StringHelper;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.List;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private Activity context;

    public MainActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    public FragmentHelper createFragmentHelper(){
        FragmentHelper helper = FragmentHelper.getInstance(context);
        return helper;
    }


    @Provides
    @ActivityScope
    public PhotoHelper createPhotoHelper(){
        PhotoHelper photoHelper = PhotoHelper.getInstance(context);
        return photoHelper;
    }

    @Provides
    @ActivityScope
    public FirebaseStorage createFirebaseStorage(){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        return firebaseStorage;
    }

    @Provides
    @ActivityScope
    public StorageReference createStorageReference(FirebaseStorage firebaseStorage){
        StorageReference storageReference = firebaseStorage.getReferenceFromUrl(FirebaseApi.STORAGE_URL);
        return storageReference;
    }

    @Provides
    @ActivityScope
    public List<ModeEntity> createModesEntities(){
        List<ModeEntity> entities = new ArrayList<>();

        String title;
        String descriptionHeader;
        String description;

        title = StringHelper.getString(context, R.string.receive_phones_title);
        descriptionHeader = StringHelper.getString(context, R.string.receive_phones_description_header);
        description = StringHelper.getString(context, R.string.receive_phones_description);

        ModeEntity entity = new ModeEntity(title, descriptionHeader, description, ModeIdentifier.RECEIVE_PHONES,
                R.drawable.receive_phone);
        entities.add(entity);

        title = StringHelper.getString(context, R.string.send_phones_title);
        descriptionHeader = StringHelper.getString(context, R.string.send_phones_description_header);
        description = StringHelper.getString(context, R.string.send_phones_description);

        entity = new ModeEntity(title, descriptionHeader, description, ModeIdentifier.SEND_PHONES,
                R.drawable.send_phones);
        entities.add(entity);

        title = StringHelper.getString(context, R.string.send_photos_title);
        descriptionHeader = StringHelper.getString(context, R.string.send_photos_description_header);
        description = StringHelper.getString(context, R.string.send_photos_description);

        entity = new ModeEntity(title, descriptionHeader, description, ModeIdentifier.SEND_PHOTO,
                R.drawable.send_photos);
        entities.add(entity);

        title = StringHelper.getString(context, R.string.receive_photos_title);
        descriptionHeader = StringHelper.getString(context, R.string.receive_photos_description_header);
        description = StringHelper.getString(context, R.string.receive_photos_description);

        entity = new ModeEntity(title, descriptionHeader, description, ModeIdentifier.RECEIVE_PHOTO,
                R.drawable.receive_photos);
        entities.add(entity);

        return entities;
    }
}

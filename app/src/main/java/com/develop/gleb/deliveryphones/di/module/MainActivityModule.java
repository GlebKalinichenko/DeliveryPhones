package com.develop.gleb.deliveryphones.di.module;

import android.app.Activity;

import com.develop.gleb.deliveryphones.ModeEntity;
import com.develop.gleb.deliveryphones.ModeIdentifier;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.di.scopes.ActivityScope;
import com.develop.gleb.deliveryphones.helpers.FragmentHelper;
import com.develop.gleb.deliveryphones.helpers.StringHelper;

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
    public List<ModeEntity> createModesEntities(){
        List<ModeEntity> entities = new ArrayList<>();

        String title;
        String descriptionHeader;
        String description;

        title = StringHelper.getString(context, R.string.receive_phones_title);
        descriptionHeader = StringHelper.getString(context, R.string.receive_phones_description_header);
        description = StringHelper.getString(context, R.string.receive_phones_description);

        ModeEntity entity = new ModeEntity(title, descriptionHeader, description, ModeIdentifier.RECEIVE_PHONES);
        entities.add(entity);

        title = StringHelper.getString(context, R.string.send_phones_title);
        descriptionHeader = StringHelper.getString(context, R.string.send_phones_description_header);
        description = StringHelper.getString(context, R.string.send_phones_description);

        entity = new ModeEntity(title, descriptionHeader, description, ModeIdentifier.SEND_PHONES);
        entities.add(entity);

        return entities;
    }
}

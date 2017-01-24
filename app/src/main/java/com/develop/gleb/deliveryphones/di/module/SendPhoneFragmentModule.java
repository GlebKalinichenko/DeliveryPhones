package com.develop.gleb.deliveryphones.di.module;

import android.content.Context;

import com.develop.gleb.deliveryphones.di.scopes.FragmentScope;
import com.develop.gleb.deliveryphones.helpers.ContactPhoneHelper;
import com.develop.gleb.deliveryphones.helpers.IdHelper;
import com.develop.gleb.deliveryphones.helpers.PhotoHelper;
import com.develop.gleb.deliveryphones.mvp.implementations.sendphones.SendPhoneModel;
import com.develop.gleb.deliveryphones.mvp.implementations.sendphones.SendPhonePresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhonePresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.sendphones.ISendPhoneView;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInView;
import com.google.firebase.database.DatabaseReference;

import dagger.Module;
import dagger.Provides;

@Module
public class SendPhoneFragmentModule {
    private ISendPhoneView view;
    private Context context;

    public SendPhoneFragmentModule(Context context, ISendPhoneView view) {
        this.view = view;
        this.context = context;
    }

    @Provides
    @FragmentScope
    public PhotoHelper createPhotoHelper(){
        PhotoHelper helper = PhotoHelper.getInstance(context);
        return helper;
    }

    @Provides
    @FragmentScope
    public ContactPhoneHelper createContactPhoneHelper(){
        ContactPhoneHelper helper = ContactPhoneHelper.getInstance(context);
        return helper;
    }

    @Provides
    @FragmentScope
    public ISendPhoneModel createSendPhoneModel(ContactPhoneHelper helper, IdHelper idHelper,
                                                DatabaseReference reference){
        ISendPhoneModel model = new SendPhoneModel(helper, idHelper, reference);
        return model;
    }

    @Provides
    @FragmentScope
    public ISendPhonePresenter createSendPresenter(ISendPhoneModel model){
        ISendPhonePresenter presenter = new SendPhonePresenter(view, model);
        return presenter;
    }
}

package com.develop.gleb.deliveryphones.mvp.implementations.signin;

import com.develop.gleb.deliveryphones.callbacks.ILoginCallback;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInView;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class SignInPresenter implements ISignInPresenter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ISignInView view;
    private ISignInModel model;

    @Inject
    public SignInPresenter(ISignInView view, ISignInModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void signInUser(FirebaseAuth firebaseAuth, String email, String password) {
        model.signInUser(firebaseAuth, email, password, this);
    }

    @Override
    public void onSuccess() {
        view.signInSuccess();
    }

    @Override
    public void onUnsuccess() {
        view.signInUnsuccess();
    }
}

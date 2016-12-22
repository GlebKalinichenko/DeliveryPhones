package com.example.gleb.deliveryphones.mvp.implementations.signin;

import com.example.gleb.deliveryphones.mvp.interfaces.signin.ISignInModel;
import com.example.gleb.deliveryphones.mvp.interfaces.signin.ISignInPresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.signin.ISignInView;
import com.google.firebase.auth.FirebaseAuth;

public class SignInPresenter implements ISignInPresenter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ISignInView view;
    private ISignInModel model;

    public SignInPresenter(ISignInView view) {
        this.view = view;
        this.model = new SignInModel(this);
    }

    @Override
    public void signInUser(FirebaseAuth firebaseAuth, String email, String password) {
        model.signInUser(firebaseAuth, email, password);
    }

    @Override
    public void signInSuccess() {
        view.signInSuccess();
    }

    @Override
    public void signInUnsuccess() {
        view.signInUnsuccess();
    }
}

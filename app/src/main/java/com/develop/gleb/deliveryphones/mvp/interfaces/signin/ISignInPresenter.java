package com.develop.gleb.deliveryphones.mvp.interfaces.signin;

import com.google.firebase.auth.FirebaseAuth;

public interface ISignInPresenter {
    void signInUser(FirebaseAuth firebaseAuth, String email, String password);
    void signInSuccess();
    void signInUnsuccess();
}

package com.develop.gleb.deliveryphones.mvp.interfaces.signin;

import com.google.firebase.auth.FirebaseAuth;

public interface ISignInModel {
    void signInUser(FirebaseAuth firebaseAuth, String email, String password);

}

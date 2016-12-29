package com.example.gleb.deliveryphones.mvp.interfaces.signup;

import com.google.firebase.auth.FirebaseAuth;

public interface ISignUpModel {
    void signUpUser(FirebaseAuth firebaseAuth, String email, String password);
}

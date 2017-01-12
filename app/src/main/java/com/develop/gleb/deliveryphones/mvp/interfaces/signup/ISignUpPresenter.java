package com.develop.gleb.deliveryphones.mvp.interfaces.signup;

import com.google.firebase.auth.FirebaseAuth;

public interface ISignUpPresenter {
    void signUpUser(FirebaseAuth firebaseAuth, String email, String password, String confirmPassword);
    void signUpSuccess();
    void signUpUnSuccess();
}

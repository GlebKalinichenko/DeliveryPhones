package com.develop.gleb.deliveryphones.mvp.interfaces.signup;

import com.develop.gleb.deliveryphones.callbacks.ILoginCallback;
import com.google.firebase.auth.FirebaseAuth;

public interface ISignUpPresenter extends ILoginCallback {
    void signUpUser(FirebaseAuth firebaseAuth, String email, String password, String confirmPassword);
}

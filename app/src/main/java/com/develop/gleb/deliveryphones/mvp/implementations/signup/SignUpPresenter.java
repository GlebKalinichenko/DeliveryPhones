package com.develop.gleb.deliveryphones.mvp.implementations.signup;

import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpView;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpPresenter;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPresenter implements ISignUpPresenter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ISignUpView view;
    private ISignUpModel model;

    public SignUpPresenter(ISignUpView view) {
        this.view = view;
        this.model = new SignUpModel(this);
    }

    @Override
    public void signUpUser(FirebaseAuth firebaseAuth, String email, String password, String confirmPassword) {
        if (password.equals(confirmPassword))
            model.signUpUser(firebaseAuth, email, password);
    }

    @Override
    public void signUpSuccess() {
        view.signUpSuccess();
    }

    @Override
    public void signUpUnSuccess() {
        view.signUpUnSuccess();;
    }
}

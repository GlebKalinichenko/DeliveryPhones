package com.develop.gleb.deliveryphones.mvp.implementations.signup;

import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpView;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpPresenter;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class SignUpPresenter implements ISignUpPresenter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ISignUpView view;
    private ISignUpModel model;

    @Inject
    public SignUpPresenter(ISignUpView view, ISignUpModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void signUpUser(FirebaseAuth firebaseAuth, String email, String password, String confirmPassword) {
        if (password.equals(confirmPassword))
            model.signUpUser(firebaseAuth, email, password, this);
    }

    @Override
    public void onSuccess() {
        view.signUpSuccess();
    }

    @Override
    public void onUnsuccess() {
        view.signUpUnSuccess();
    }
}

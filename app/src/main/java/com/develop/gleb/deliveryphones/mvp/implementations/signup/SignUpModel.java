package com.develop.gleb.deliveryphones.mvp.implementations.signup;

import android.support.annotation.NonNull;

import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpModel implements ISignUpModel {
    private ISignUpPresenter presenter;

    public SignUpModel(ISignUpPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signUpUser(FirebaseAuth firebaseAuth, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                    presenter.signUpSuccess();
                else
                    presenter.signUpUnSuccess();
            }
        });
    }
}

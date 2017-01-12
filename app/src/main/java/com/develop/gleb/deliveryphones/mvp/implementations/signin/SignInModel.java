package com.develop.gleb.deliveryphones.mvp.implementations.signin;

import android.support.annotation.NonNull;

import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInModel;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInModel implements ISignInModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private ISignInPresenter presenter;

    public SignInModel(ISignInPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signInUser(FirebaseAuth firebaseAuth, String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                    presenter.signInSuccess();
                else
                    presenter.signInUnsuccess();
            }
        });
    }
}

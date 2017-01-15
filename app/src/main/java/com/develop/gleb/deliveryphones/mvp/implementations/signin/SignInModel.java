package com.develop.gleb.deliveryphones.mvp.implementations.signin;

import android.support.annotation.NonNull;
import android.util.Log;

import com.develop.gleb.deliveryphones.callbacks.ILoginCallback;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class SignInModel implements ISignInModel {
    private final String LOG_TAG = this.getClass().getCanonicalName();

    @Inject
    public SignInModel() {
    }

    @Override
    public void signInUser(FirebaseAuth firebaseAuth, String email, String password, ILoginCallback
                           callback) {
        Log.d(LOG_TAG, "Sign in user");
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                    callback.onSuccess();
                else
                    callback.onUnsuccess();
            }
        });
    }
}

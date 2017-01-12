package com.example.gleb.deliveryphones.dependencyinjection;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SignInFragmentModule {

    @Provides
    @Singleton
    public FirebaseAuth createFirebaseAuth(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        return firebaseAuth;
    }
}

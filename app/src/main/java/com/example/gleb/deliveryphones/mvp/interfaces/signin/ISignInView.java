package com.example.gleb.deliveryphones.mvp.interfaces.signin;

import android.view.View;

import com.example.gleb.deliveryphones.IBaseView;

public interface ISignInView extends IBaseView {
    void initWidgets(View view);
    void signInSuccess();
    void signInUnsuccess();
}

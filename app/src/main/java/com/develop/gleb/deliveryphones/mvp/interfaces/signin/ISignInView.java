package com.develop.gleb.deliveryphones.mvp.interfaces.signin;

import android.view.View;

public interface ISignInView {
    void initWidgets(View view);
    void signInSuccess();
    void signInUnsuccess();
}

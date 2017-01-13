package com.develop.gleb.deliveryphones.fragments.sign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.develop.gleb.deliveryphones.BaseApplication;
import com.develop.gleb.deliveryphones.IBaseView;
import com.develop.gleb.deliveryphones.LoginActivity;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.di.component.SignInFragmentComponent;
import com.develop.gleb.deliveryphones.events.SignUpEvent;
import com.develop.gleb.deliveryphones.events.SwitchToSignUpEvent;
import com.develop.gleb.deliveryphones.helpers.SHA1Helper;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInPresenter;
import com.develop.gleb.deliveryphones.mvp.interfaces.signin.ISignInView;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class SignInFragment extends Fragment implements ISignInView, IBaseView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private EditText emailText;
    private EditText passwordText;
    private Button signInButton;
    private TextView signUpText;
    @Inject
    public FirebaseAuth firebaseAuth;
    @Inject
    public ISignInPresenter presenter;

    public static SignInFragment getInstance() {
        SignInFragment fragment = new SignInFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        initWidgets(view);
        initInject();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        firebaseAuth = null;
    }

    @Override
    public void initWidgets(View view) {
        emailText = (EditText) view.findViewById(R.id.sign_in_email);
        passwordText = (EditText) view.findViewById(R.id.sign_in_password);
        signInButton = (Button) view.findViewById(R.id.sign_in_button);
        signUpText = (TextView) view.findViewById(R.id.sign_up_text);

        signInButton.setOnClickListener(i -> {String email = emailText.getText().toString();
            String password = passwordText.getText().toString(); presenter.signInUser(firebaseAuth, email, password);});

        signUpText.setOnClickListener(i -> EventBus.getDefault().post(new SwitchToSignUpEvent()));
    }

    @Override
    public void signInSuccess() {
        Context context = getActivity();
        Toast.makeText(context, R.string.successful, Toast.LENGTH_LONG).show();

        String email = emailText.getText().toString();
        email = SHA1Helper.hashSHA1(email);

        EventBus.getDefault().post(new SignUpEvent(email));
    }

    @Override
    public void signInUnsuccess() {
        Context context = getActivity();
        Toast.makeText(context, R.string.unsuccessful, Toast.LENGTH_LONG).show();
    }

    @Override
    public void initInject() {
        LoginActivity context = (LoginActivity) getActivity();
        SignInFragmentComponent component =((BaseApplication) getActivity().getApplication())
                 .getSignInFragmentComponent(context, this);
        component.inject(this);
    }
}

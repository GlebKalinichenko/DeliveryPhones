package com.example.gleb.deliveryphones.fragments.sign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.events.SignUpEvent;
import com.example.gleb.deliveryphones.helpers.SHA1Helper;
import com.example.gleb.deliveryphones.mvp.implementations.signup.SignUpPresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.signup.ISignUpView;
import com.example.gleb.deliveryphones.mvp.interfaces.signup.ISignUpPresenter;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

public class SignUpFragment extends Fragment implements ISignUpView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private EditText emailText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private Button signUpButton;
    private ProgressBar progressBar;
    private ISignUpPresenter presenter = new SignUpPresenter(this);
    private FirebaseAuth firebaseAuth;

    public static SignUpFragment getInstance() {
        SignUpFragment fragment = new SignUpFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        initWidgets(view);
        return view;
    }

    @Override
    public void initWidgets(View view) {
        Log.d(LOG_TAG, "Init widgets in sign in");

        firebaseAuth = FirebaseAuth.getInstance();

        emailText = (EditText) view.findViewById(R.id.sign_up_email_text);
        passwordText = (EditText) view.findViewById(R.id.sign_up_password_text);
        confirmPasswordText = (EditText) view.findViewById(R.id.sign_up_confirm_password_text);
        signUpButton = (Button) view.findViewById(R.id.sign_up_button);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarReceive);

        signUpButton.setOnClickListener(i -> {String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            String confirmPassword = confirmPasswordText.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            presenter.signUpUser(firebaseAuth, email, password, confirmPassword);});
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        firebaseAuth = null;
    }

    @Override
    public void signUpSuccess() {
        Context context = getActivity();
        Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);

        String email = emailText.getText().toString();
        email = SHA1Helper.hashSHA1(email);

        EventBus.getDefault().post(new SignUpEvent(email));
    }

    @Override
    public void signUpUnSuccess() {
        Context context = getActivity();
        Toast.makeText(context, "Unsuccessful", Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
    }
}

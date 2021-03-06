package com.develop.gleb.deliveryphones.fragments.sign;

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
import com.develop.gleb.deliveryphones.BaseApplication;
import com.develop.gleb.deliveryphones.IBaseView;
import com.develop.gleb.deliveryphones.LoginActivity;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.di.component.SignUpFragmentComponent;
import com.develop.gleb.deliveryphones.events.SignUpEvent;
import com.develop.gleb.deliveryphones.helpers.SHA1Helper;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpView;
import com.develop.gleb.deliveryphones.mvp.interfaces.signup.ISignUpPresenter;
import com.google.firebase.auth.FirebaseAuth;
import org.greenrobot.eventbus.EventBus;
import javax.inject.Inject;

public class SignUpFragment extends Fragment implements ISignUpView, IBaseView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private EditText emailText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private Button signUpButton;
    private ProgressBar progressBar;
    @Inject
    public ISignUpPresenter presenter;
    @Inject
    public FirebaseAuth firebaseAuth;

    public static SignUpFragment getInstance() {
        SignUpFragment fragment = new SignUpFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        initWidgets(view);
        initInject();
        return view;
    }

    @Override
    public void initWidgets(View view) {
        Log.d(LOG_TAG, "Init widgets in sign in");

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
        Toast.makeText(context, R.string.successful, Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);

        String email = emailText.getText().toString();
        email = SHA1Helper.hashSHA1(email);

        EventBus.getDefault().post(new SignUpEvent(email));
    }

    @Override
    public void signUpUnSuccess() {
        Context context = getActivity();
        Toast.makeText(context, R.string.unsuccessful, Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void initInject() {
        LoginActivity activity = (LoginActivity) getActivity();
        SignUpFragmentComponent component =((BaseApplication) getActivity().getApplication())
                .getSignUpFragmentComponent(activity, this);
        component.inject(this);
    }
}

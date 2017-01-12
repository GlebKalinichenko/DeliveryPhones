package com.example.gleb.deliveryphones.fragments.sign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gleb.deliveryphones.BaseApplication;
import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.dependencyinjection.SignInFragmentComponent;
import com.example.gleb.deliveryphones.events.SignUpEvent;
import com.example.gleb.deliveryphones.helpers.SHA1Helper;
import com.example.gleb.deliveryphones.mvp.interfaces.signin.ISignInPresenter;
import com.example.gleb.deliveryphones.mvp.interfaces.signin.ISignInView;
import com.example.gleb.deliveryphones.mvp.implementations.signin.SignInPresenter;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class SignInFragment extends Fragment implements ISignInView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private EditText emailText;
    private EditText passwordText;
    private Button signInButton;
    @Inject
    public FirebaseAuth firebaseAuth;
    private ISignInPresenter presenter = new SignInPresenter(this);

    public static SignInFragment getInstance() {
        SignInFragment fragment = new SignInFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        initInject();
        initWidgets(view);
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

/*        firebaseAuth = FirebaseAuth.getInstance();*/

        signInButton.setOnClickListener(i -> {String email = emailText.getText().toString();
            String password = passwordText.getText().toString(); presenter.signInUser(firebaseAuth, email, password);});
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
        SignInFragmentComponent component = ((BaseApplication) getActivity().getApplication()).returnSignInFragmentComponent();
        component.inject(this);
    }
}

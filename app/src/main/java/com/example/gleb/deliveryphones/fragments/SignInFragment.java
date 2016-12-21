package com.example.gleb.deliveryphones.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gleb.deliveryphones.R;

public class SignInFragment extends Fragment {
    private final String LOG_TAG = this.getClass().getCanonicalName();

    public static SignInFragment getInstance() {
        SignInFragment fragment = new SignInFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        return view;
    }
}

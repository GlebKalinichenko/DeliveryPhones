package com.example.gleb.deliveryphones.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.mvp.base.IBasePhoneView;

public abstract class BasePhoneFragment extends Fragment implements IBasePhoneView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phones, container, false);
        initWidgets(view);

        return view;
    }

    @Override
    public void initWidgets(View view) {

    }

    protected abstract void setButtonDrawable();
}

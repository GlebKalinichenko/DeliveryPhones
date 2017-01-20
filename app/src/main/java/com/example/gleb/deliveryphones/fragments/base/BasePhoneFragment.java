package com.example.gleb.deliveryphones.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/fragments/base/BasePhoneFragment.java
import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.mvp.base.IBasePhoneView;
=======
import com.develop.gleb.deliveryphones.IBaseView;
import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.mvp.base.IBasePhoneView;
>>>>>>> 509e1eb... Refactor send screen by using injections:app/src/main/java/com/develop/gleb/deliveryphones/fragments/base/BasePhoneFragment.java

public abstract class BasePhoneFragment extends Fragment implements IBasePhoneView, IBaseView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phones, container, false);
        initWidgets(view);

<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/fragments/base/BasePhoneFragment.java
=======
        setRetainInstance(true);
        setHasOptionsMenu(true);
        initInject();
>>>>>>> 509e1eb... Refactor send screen by using injections:app/src/main/java/com/develop/gleb/deliveryphones/fragments/base/BasePhoneFragment.java
        return view;
    }

    @Override
    public void initWidgets(View view) {

    }

    protected abstract void setButtonDrawable();
}

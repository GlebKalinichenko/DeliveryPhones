package com.example.gleb.deliveryphones;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import rx.Observable;

public class PhonesFragment extends Fragment implements IPhoneView {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private IPhonePresenter presenter = new PhonePresenter(this);
    private ListView phoneList;
    private Observable<PhoneEntity> phoneObservable;

    public static PhonesFragment getInstance() {
        PhonesFragment fragment = new PhonesFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phones, container, false);

        return view;
    }

    @Override
    public void initWidgets(View view) {
        phoneList = (ListView) view.findViewById(R.id.phone_list);
    }

    @Override
    public void onStart() {
        super.onStart();
        Context context = getActivity();
        List<PhoneEntity> entities = presenter.getPhones(context);
        phoneObservable = Observable.from(entities);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();

        phoneObservable.subscribe(i -> i.getPhones());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
